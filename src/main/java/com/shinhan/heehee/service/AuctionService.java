package com.shinhan.heehee.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shinhan.heehee.dao.AlarmDAO;
import com.shinhan.heehee.dao.AuctionDAO;
import com.shinhan.heehee.dto.request.auction.AuctionHistoryDTO;
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
	SimpMessagingTemplate messagingTemplate;

	public List<AuctionProdDTO> aucProdList() {
		return auctionDAO.aucProdList();
	}

	public List<AuctionProdDTO> aucPriceList(List<Integer> seqArr) {
		return auctionDAO.aucPriceList(seqArr);
	}

	public void insertAucHistory(AuctionHistoryDTO aucHistory) {
		auctionDAO.insertAucHistory(aucHistory);
	}

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

	@Scheduled(fixedRate = 60000)
	@Transactional
	public void updateBiddingStatus() {
		List<SchedulerBidDTO> successArr = auctionDAO.bidSuccessList();
		List<SchedulerBidDTO> failArr = auctionDAO.bidFailList();

		List<Integer> successIntList = new ArrayList<>();
		List<Integer> failIntList = new ArrayList<>();

		AlarmDTO buyerAlarmDto = new AlarmDTO();
		AlarmDTO sellerAlarmDto = new AlarmDTO();

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
				buyerAlarmDto.setAlContent("입찰하신 경매물품이 낙찰되었습니다.");

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
		logger.info("-------------- 낙찰, 유찰 스케줄러 ---------------");
	}

}
