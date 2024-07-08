package com.shinhan.heehee.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shinhan.heehee.config.AuctionLockManager;
import com.shinhan.heehee.dao.AlarmDAO;
import com.shinhan.heehee.dao.AuctionDAO;
import com.shinhan.heehee.dao.MyPageDAO;
import com.shinhan.heehee.dto.request.ImageFileDTO;
import com.shinhan.heehee.dto.request.auction.AuctionHistoryDTO;
import com.shinhan.heehee.dto.request.auction.AuctionInsertDTO;
import com.shinhan.heehee.dto.request.auction.InsertDealHistoryDTO;
import com.shinhan.heehee.dto.response.AlarmDTO;
import com.shinhan.heehee.dto.response.auction.AuctionImgsDTO;
import com.shinhan.heehee.dto.response.auction.AuctionProdDTO;
import com.shinhan.heehee.dto.response.auction.AuctionProdInfoDTO;
import com.shinhan.heehee.dto.response.auction.SchedulerBidDTO;
import com.shinhan.heehee.dto.response.auction.SellerInfoResponseDTO;

@Service
public class AuctionService {

	Logger logger = LoggerFactory.getLogger(AuctionService.class);

	@Autowired
	AuctionDAO auctionDAO;

	@Autowired
	AlarmDAO alarmDAO;
	
	@Autowired
	MyPageDAO mypageDAO;

	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	AWSS3Service awss3Service;


	public List<AuctionProdDTO> aucProdList() {
		return auctionDAO.aucProdList();
	}

	public List<AuctionProdDTO> aucPriceList(List<Integer> seqArr) {
		return auctionDAO.aucPriceList(seqArr);
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public AuctionHistoryDTO insertAucHistory(AuctionHistoryDTO aucHistory, String userId) {
		int aucSeq = aucHistory.getAucProdSeq();
	    Lock lock = AuctionLockManager.getLock(aucSeq);
	    lock.lock();
	    try {
	        // 기존 유저에게 포인트 반환
	        auctionDAO.returnPointToUser(aucSeq);
	        // 입찰
	        auctionDAO.insertAucHistory(aucHistory);
	        // 입찰자 포인트 차감
	        auctionDAO.deductionPointFromUser(aucHistory);
	        
	        return aucHistory;
	    } finally {
	        lock.unlock();
	    }
	}
	
	public int getRemainingPoint(String userId) {
		return auctionDAO.remainingPointsFromUser(userId);
	}

	@Transactional
	public AuctionHistoryDTO joinCount(AuctionHistoryDTO aucHistory) {
		int joinCount = auctionDAO.joinCount(aucHistory.getAucProdSeq());
		aucHistory.setJoinCount(joinCount);
		return aucHistory;
	}

	public AuctionProdInfoDTO aucProdInfo(int aucSeq) {
		return auctionDAO.aucProdInfo(aucSeq);
	}

	public List<AuctionImgsDTO> aucProdImgList(int aucSeq) {
		return auctionDAO.aucProdImgList(aucSeq);
	}

	public SellerInfoResponseDTO sellerInfo(String userId) {
		return auctionDAO.sellerInfo(userId);
	}
	
	public void insertAucProd(AuctionInsertDTO aucInsertDto, String userId) throws IOException {
		String filePath = "images/auction/";
		List<MultipartFile> files = aucInsertDto.getUploadFiles();
		
		auctionDAO.insertAucProd(aucInsertDto);
		
		// 파일 업로드 로직
		for(MultipartFile file : files) {
			if(file.getSize() != 0) {
				ImageFileDTO imgfile = new ImageFileDTO();
				String fileName = awss3Service.uploadOneObject(file, filePath);
				imgfile.setImgName(fileName);
				imgfile.setTablePk(aucInsertDto.getProductSeq());
				imgfile.setUserId(userId);
				auctionDAO.insertImgFile(imgfile);
			}
		}
	}

	@Scheduled(fixedRate = 30000)
	@Transactional
	public void updateBiddingStatus() {
		List<SchedulerBidDTO> successArr = auctionDAO.bidSuccessList();
		List<SchedulerBidDTO> failArr = auctionDAO.bidFailList();

		List<Integer> successIntList = new ArrayList<>();
		List<Integer> failIntList = new ArrayList<>();

		AlarmDTO buyerAlarmDto = new AlarmDTO();
		AlarmDTO sellerAlarmDto = new AlarmDTO();
		
		InsertDealHistoryDTO dealHistoryDto = new InsertDealHistoryDTO();

		// 낙찰 리스트 PROD_SEQ 담기
		for (SchedulerBidDTO bid : successArr) {
			successIntList.add(bid.getProductSeq());
		}

		// 유찰 리스트 PROD_SEQ 담기
		for (SchedulerBidDTO bid : failArr) {
			failIntList.add(bid.getProductSeq());
		}
		
		if (successIntList.size() > 0) {
			auctionDAO.updateBidSuccess(successIntList);
			
			for (SchedulerBidDTO bid : successArr) {
				sellerAlarmDto.setId(bid.getSellerId());
				sellerAlarmDto.setCateNum(3);
				sellerAlarmDto.setReqSeq(bid.getProductSeq());
				sellerAlarmDto.setAlContent("등록하신 경매물품이 낙찰되었습니다.");

				alarmDAO.alarmInsert(sellerAlarmDto);

				int sellerAlarmCnt = alarmDAO.alarmCount(bid.getSellerId());
				messagingTemplate.convertAndSend("/topic/alarm/" + bid.getSellerId(), sellerAlarmCnt);

				buyerAlarmDto.setId(bid.getBuyerId());
				buyerAlarmDto.setCateNum(3);
				buyerAlarmDto.setReqSeq(bid.getProductSeq());
				buyerAlarmDto.setAlContent("입찰하신 경매물품에 낙찰되었습니다.");
				
				dealHistoryDto.setAucSeq(bid.getProductSeq());
				dealHistoryDto.setBuyerId(bid.getBuyerId());
				
				auctionDAO.insertDealHistory(dealHistoryDto);
				
				alarmDAO.alarmInsert(buyerAlarmDto);

				int buyerAlarmCnt = alarmDAO.alarmCount(bid.getBuyerId());
				messagingTemplate.convertAndSend("/topic/alarm/" + bid.getBuyerId(), buyerAlarmCnt);
			}
		}

		if (failIntList.size() > 0) {
			auctionDAO.updateBidFail(failIntList);
			
			for (SchedulerBidDTO bid : failArr) {
				sellerAlarmDto.setId(bid.getSellerId());
				sellerAlarmDto.setCateNum(3);
				sellerAlarmDto.setReqSeq(bid.getProductSeq());
				sellerAlarmDto.setAlContent("등록하신 경매물품이 유찰되었습니다.");

				alarmDAO.alarmInsert(sellerAlarmDto);

				int sellerAlarmCnt = alarmDAO.alarmCount(bid.getSellerId());
				messagingTemplate.convertAndSend("/topic/alarm/" + bid.getSellerId(), sellerAlarmCnt);
			}
		}
		
		mypageDAO.updateSecondDstatus();
		mypageDAO.updateFirstDstatus();
		
		logger.info("-------------- 낙찰, 유찰, 택배 스케줄러 ---------------");
	}

}
