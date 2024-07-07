package com.shinhan.heehee.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shinhan.heehee.dao.AlarmDAO;
import com.shinhan.heehee.dao.MyPageDAO;
import com.shinhan.heehee.dao.UserDAO;
import com.shinhan.heehee.dto.request.RateAdminDTO;
import com.shinhan.heehee.dto.request.RateAdminDTO;
import com.shinhan.heehee.dto.response.AlarmDTO;
import com.shinhan.heehee.dto.response.BankKindDTO;
import com.shinhan.heehee.dto.response.DeliveryCompanyDTO;
import com.shinhan.heehee.dto.response.EditProfileDTO;
import com.shinhan.heehee.dto.response.FaQDTO;
import com.shinhan.heehee.dto.response.InsertDeliveryDTO;
import com.shinhan.heehee.dto.response.InsertQnADTO;
import com.shinhan.heehee.dto.response.InsertQnAImgDTO;
import com.shinhan.heehee.dto.response.JjimDTO;
import com.shinhan.heehee.dto.response.MyPageHeaderDTO;
import com.shinhan.heehee.dto.response.PointListDTO;
import com.shinhan.heehee.dto.response.PurchaseListDTO;
import com.shinhan.heehee.dto.response.QnADTO;
import com.shinhan.heehee.dto.response.QnAImgDTO;
import com.shinhan.heehee.dto.response.SaleDetailAucDTO;
import com.shinhan.heehee.dto.response.SaleDetailDTO;
import com.shinhan.heehee.dto.response.SaleListAucDTO;
import com.shinhan.heehee.dto.response.SaleListDTO;
import com.shinhan.heehee.dto.response.UserDTO;

@Service
public class MyPageService {

	@Autowired
	MyPageDAO mypageDao;

	@Autowired
	AlarmDAO alarmDao;

	@Autowired
	SimpMessagingTemplate messagingTemplate;

	@Autowired
	AWSS3Service s3Service;

	@Autowired
	UserDAO userDao;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

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

	public List<QnADTO> qnaOption() {
		return mypageDao.qnaOption();
	}

	public List<FaQDTO> faqOption(int option) {
		return mypageDao.faqOption(option);
	}

	public List<QnADTO> myQna(String userId) {
		return mypageDao.myQna(userId);
	}

	@Transactional
	public int insertQna(InsertQnADTO qna, List<MultipartFile> uploadImgs) {
		int result = mypageDao.insertQna(qna);
		if (result == 1 && uploadImgs != null && !uploadImgs.isEmpty()) {
			for (MultipartFile img : uploadImgs) {
				try {

					String imgName = s3Service.uploadOneObject(img, "images/mypage/qnaBoard/");
					InsertQnAImgDTO qnaImg = new InsertQnAImgDTO();
					qnaImg.setImgName(imgName);
					qnaImg.setTablePk(qna.getSeqQnaBno());
					qnaImg.setId(qna.getId());
					mypageDao.insertQnaImg(qnaImg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return result;
	}

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
		if (result == 1) {
			// 알림 insert
			AlarmDTO alarmDTO = new AlarmDTO();

			String buyerId = delivery.getBuyerId();

			alarmDTO.setId(buyerId);
			alarmDTO.setCateNum(5); // 알림 분류 코드 (채팅)
			alarmDTO.setReqSeq(delivery.getDSeq());
			alarmDTO.setAlContent("송장 번호가 입력되었습니다.");

			alarmDao.alarmInsert(alarmDTO);

			int alarmCnt = alarmDao.alarmCount(buyerId);
			messagingTemplate.convertAndSend("/topic/alarm/" + buyerId, alarmCnt);
		}
		return result;
	}

	public ResponseEntity<?>  updateSCheck(int proSeq) {
		Map<String, Object> response = new HashMap<String, Object>();
		int result = mypageDao.updateSCheck(proSeq);
		if (result == 0) {
			response.put("success", false);
			response.put("message", "거래완료에 실패했습니다.");
			return ResponseEntity.ok(response);
		} else {
			response.put("success", true);
			response.put("message", "거래완료에 성공했습니다.");
			return ResponseEntity.ok(response);
		}

	}

	public ResponseEntity<?> updatePCheck(int proSeq) {

		Map<String, Object> response = new HashMap<String, Object>();
		int result = mypageDao.updatePCheck(proSeq);

		if (result == 0) {
			response.put("success", false);
			response.put("message", "거래완료에 실패했습니다.");
			return ResponseEntity.ok(response);
		} else {
			response.put("success", true);
			response.put("message", "거래완료에 성공했습니다.");
			return ResponseEntity.ok(response);
		}

	}
	
	public ResponseEntity<?> updatePCheckAuc(int proSeq) {
		Map<String, Object> response = new HashMap<String, Object>();
		int result = mypageDao.updatePCheckAuc(proSeq);

		if (result == 0) {
			response.put("success", false);
			response.put("message", "거래완료에 실패했습니다.");
			return ResponseEntity.ok(response);
		} else {
			response.put("success", true);
			response.put("message", "거래완료에 성공했습니다.");
			return ResponseEntity.ok(response);
		}
	}
	
	public ResponseEntity<?> updateSCheckAuc(int proSeq) {
		Map<String, Object> response = new HashMap<String, Object>();
		int result = mypageDao.updateSCheckAuc(proSeq);

		if (result == 0) {
			response.put("success", false);
			response.put("message", "거래완료에 실패했습니다.");
			return ResponseEntity.ok(response);
		} else {
			response.put("success", true);
			response.put("message", "거래완료에 성공했습니다.");
			return ResponseEntity.ok(response);
		}
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

	public List<SaleListAucDTO> purchaselistAuc(String userId) {
		return mypageDao.purchaselistAuc(userId);
	}

	public int proStatusDelete(int productSeq) {
		return mypageDao.proStatusDelete(productSeq);
	}

	public ResponseEntity<?> updateStatus(int productSeq, String proStatus) {
		Map<String, Object> response = new HashMap<String, Object>();
		int result = mypageDao.updateStatus(productSeq, proStatus);

		if (result == 0) {
			response.put("success", false);
			response.put("message", "상태변경에 실패했습니다.");
			return ResponseEntity.ok(response);
		} else {
			response.put("success", true);
			response.put("message", "상태변경에 성공했습니다.");
			return ResponseEntity.ok(response);
		}
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

	@Transactional
	public int deleteUser(String userId) {
		int result = mypageDao.deleteUser(userId);
		if (result == 1) {
			mypageDao.deleteAucId(userId);
			mypageDao.deleteId(userId);
			mypageDao.deleteChatByBuyer(userId);
			mypageDao.deleteChatBySeller(userId);
		}
		return result;
	}

	public int chargePoint(String userId, Integer chargePoint) {
		return mypageDao.chargePoint(userId, chargePoint);
	}

	public List<PointListDTO> searchPoint(String userId, String year, String monthPart) {
		return mypageDao.searchPoint(userId, year, monthPart);
	}

	public ResponseEntity<?> updatePhone(String userId, String phone) {
		Map<String, Object> response = new HashMap<String, Object>();
		int result = mypageDao.updatePhone(userId, phone);

		if (result > 0) {
			response.put("success", true);
			response.put("message", "전화번호 변경에 성공했습니다!");
			return ResponseEntity.ok(response);
		} else {
			response.put("success", false);
			response.put("message", "전화번호 변경에 실패했습니다.");
			return ResponseEntity.ok(response);
		}

	}
	
	// 평점 관련
	public int rating(RateAdminDTO rateDTO) {
		return mypageDao.rating(rateDTO);
	}
	
	public int updateRate(RateAdminDTO rateDTO) {
		return mypageDao.updateRate(rateDTO);
	}

	public ResponseEntity<?> updateAddress(String userId, String address, String detailAddress) {
		Map<String, Object> response = new HashMap<String, Object>();
		int result = mypageDao.updateAddress(userId, address, detailAddress);

		if (result > 0) {
			response.put("success", true);
			response.put("message", "주소 변경에 성공했습니다!");
			return ResponseEntity.ok(response);
		} else {
			response.put("success", false);
			response.put("message", "주소 변경에 실패했습니다.");
			return ResponseEntity.ok(response);
		}

	}

	public Map<String, Object> updatePw(String userId, String currentPassword, String password,
			String confirmPassword) {
		Map<String, Object> response = new HashMap<>();
		UserDTO user = userDao.findUserByUsername(userId);

		if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
			response.put("able", false);
			response.put("message", "현재 비밀번호와 다릅니다.");
			return response;
		}

		else if (!password.equals(confirmPassword)) {
			response.put("able", false);
			response.put("message", "새 비밀번호가 서로 다릅니다.");
			return response;
		}

		else {
			String encodedPassword = passwordEncoder.encode(password);
			int result = mypageDao.updatePw(userId, encodedPassword);
			if (result == 1) {
				response.put("able", true);
				response.put("message", "비밀번호 변경에 성공했습니다.");
			} else {
				response.put("able", false);
				response.put("message", "비밀번호 변경에 실패했습니다.");
			}
		}

		return response;
	}

	public SaleDetailAucDTO saledetailAuc(int proSeq) {
		return mypageDao.saledetailAuc(proSeq);
	}
	
	
	

}
