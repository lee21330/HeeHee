package com.shinhan.heehee.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shinhan.heehee.dao.AlarmDAO;
import com.shinhan.heehee.dao.MyPageDAO;
import com.shinhan.heehee.dto.request.RateAdminDTO;
import com.shinhan.heehee.dto.response.AlarmDTO;
import com.shinhan.heehee.dto.response.BankKindDTO;
import com.shinhan.heehee.dto.response.DeliveryCompanyDTO;
import com.shinhan.heehee.dto.response.EditProfileDTO;
import com.shinhan.heehee.dto.response.FaQDTO;
import com.shinhan.heehee.dto.response.InsertDeliveryDTO;
import com.shinhan.heehee.dto.response.InsertQnADTO;
import com.shinhan.heehee.dto.response.JjimDTO;
import com.shinhan.heehee.dto.response.MyPageHeaderDTO;
import com.shinhan.heehee.dto.response.PurchaseListDTO;
import com.shinhan.heehee.dto.response.QnADTO;
import com.shinhan.heehee.dto.response.QnAImgDTO;
import com.shinhan.heehee.dto.response.SaleDetailDTO;
import com.shinhan.heehee.dto.response.SaleListAucDTO;
import com.shinhan.heehee.dto.response.SaleListDTO;

@Service
public class MyPageService {

	@Autowired
	MyPageDAO mypageDao;
	
	@Autowired
	AlarmDAO alarmDao;
	
	@Autowired
	SimpMessagingTemplate messagingTemplate;


	public List<PurchaseListDTO> purchaseList(String userId) {
		return mypageDao.purchaseList(userId);
	}

	public List<JjimDTO> jjimList(String userId) {
		return mypageDao.jjimList(userId);
	}

	public MyPageHeaderDTO sellerInfo(String userId) {
		return mypageDao.sellerInfo(userId);
	}

	public List<SaleListDTO> saleList(String status, String userId) {
		return mypageDao.saleList(status, userId);
	}

//	public int userIntroduce(String intro, String userId) {
//		return mypageDao.userIntroduce(intro, userId);
//	}

	public List<QnADTO> qnaOption() {
		return mypageDao.qnaOption();
	}

	public List<FaQDTO> faqOption(int option) {
		return mypageDao.faqOption(option);
	}

	public List<QnADTO> myQna(String userId) {
		return mypageDao.myQna(userId);
	}

	public int insertQna(InsertQnADTO qna, List<MultipartFile> uploadImgs) {
		return mypageDao.insertQna(qna, uploadImgs);
	}

	// public int insertQnaImg(InsertQnAImgDTO qnaImg, ) {
	// return mypageDao.insertQnaImg(qnaImg);
	//
	// }

	public List<QnAImgDTO> myQnaImg(String userId, int seqQnaBno) {
		return mypageDao.myQnaImg(userId, seqQnaBno);
	}

	public int deleteQna(Integer seqQnaBno) {
		return mypageDao.deleteQna(seqQnaBno);
	}

	public int deleteQnaImg(Integer seqQnaBno) {
		return mypageDao.deleteQnaImg(seqQnaBno);

	}

	public SaleDetailDTO saleDetail(int proSeq) {
		return mypageDao.saleDetail(proSeq);
	}

	public List<DeliveryCompanyDTO> dcOption() {
		return mypageDao.dcOption();
	}

	@Transactional
	public int insertDelivery(InsertDeliveryDTO delivery) {
		int result = mypageDao.insertDelivery(delivery);
		Integer dSeq = delivery.getDSeq();
		System.out.println(dSeq);
		if(result==1) {
			// 알림 insert
    		AlarmDTO alarmDTO = new AlarmDTO();
    		
    		String buyerId = delivery.getBuyerId();
    		
    		alarmDTO.setId(buyerId);
    		alarmDTO.setCateNum(5); // 알림 분류 코드 (배송)
    		alarmDTO.setReqSeq(dSeq);
    		alarmDTO.setAlContent("송장 번호가 입력되었습니다.");
    		
    		alarmDao.alarmInsert(alarmDTO);
    		
    		int alarmCnt = alarmDao.alarmCount(buyerId);
    		messagingTemplate.convertAndSend("/topic/alarm/" + buyerId, alarmCnt);
		}
		return result;
	}

	public int updateSCheck(int proSeq) {
		return mypageDao.updateSCheck(proSeq);
	}

	public List<BankKindDTO> bankList() {
		return mypageDao.bankList();
	}

	public int deleteJjim(List<Integer> seq, String userId) {
		return mypageDao.deleteJjim(seq, userId);
	}

	public List<SaleListAucDTO> saleListAuc(String status, String userId) {
		return mypageDao.saleListAuc(status, userId);
	}

	public List<PurchaseListDTO> purchaselistAuc(String userId) {
		return mypageDao.purchaselistAuc(userId);
	}

	public int proStatusDelete(int productSeq) {
		return mypageDao.proStatusDelete(productSeq);
	}

	public int updateStatus(int productSeq, String proStatus) {
		return mypageDao.updateStatus(productSeq, proStatus);
	}

	public Object editProfile(String nickName, String userIntroduce, String image, String userId) {
		return mypageDao.editProfile(nickName, userIntroduce, image, userId);

	}

	public EditProfileDTO profile(String userId) {
		return mypageDao.profile(userId);
	}

	public Object updateAcc(String userId, int bankSeq, String accountNum) {
		return mypageDao.updateAcc(userId, bankSeq, accountNum);

	}

	public Map<String, Object> dupNickCheck(String nickName) {
		Map<String, Object> response = new HashMap<String, Object>();
		int result = mypageDao.dupNickCheck(nickName);
		if (result == 0) {
			response.put("able", true);
			response.put("message", "사용가능한 닉네임입니다.");
		} else {
			response.put("able", false);
			response.put("message", "중복된 닉네임이 존재합니다.");
		}
		return response;
	}

	public int currentPwCheck(String userId, String currentPw) {
		String encPw = mypageDao.selectEncPw(userId);
		return 0;

	}
	
	// 평점 관련
	public int rating(RateAdminDTO rateDTO) {
		return mypageDao.rating(rateDTO);
	}
	
	public int updateRate(RateAdminDTO rateDTO) {
		return mypageDao.updateRate(rateDTO);
	}

}
