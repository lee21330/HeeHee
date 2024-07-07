package com.shinhan.heehee.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.request.RateAdminDTO;
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

@Repository
public class MyPageDAO {

	@Autowired
	SqlSession sqlSession;

	String namespace = "com.shinhan.myPage.";

	public List<PurchaseListDTO> purchaseList(String userId) {
		return sqlSession.selectList(namespace + "purchaseList", userId);
	}

	public List<JjimDTO> jjimList(String userId) {
		return sqlSession.selectList(namespace + "jjimList", userId);
	}

	public MyPageHeaderDTO sellerInfo(String userId) {
		return sqlSession.selectOne(namespace + "sellerInfo", userId);
	}

	public List<SaleListDTO> saleList(String status, String userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("status", status);
		params.put("userId", userId);
		return sqlSession.selectList(namespace + "saleList", params);
	}

	public List<QnADTO> qnaOption() {
		return sqlSession.selectList(namespace + "qnaOption");
	}

	public List<FaQDTO> faqOption(int option) {
		return sqlSession.selectList(namespace + "faqOption", option);
	}

	public List<QnADTO> myQna(String userId) {
		return sqlSession.selectList(namespace + "myQna", userId);
	}

	public int insertQna(InsertQnADTO qna) {
		return sqlSession.insert(namespace + "insertQna", qna);
	}

	public int insertQnaImg(InsertQnAImgDTO qnaImg) {
		return sqlSession.insert(namespace + "insertQnaImg", qnaImg);
	}

	public EditProfileDTO profile(String userId) {
		return sqlSession.selectOne(namespace + "profile", userId);
	}

	public List<QnAImgDTO> myQnaImg(String userId, int seqQnaBno) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("seqQnaBno", seqQnaBno);
		return sqlSession.selectList(namespace + "myQnaImg", params);
	}

	public int deleteQna(Integer seqQnaBno) {
		return sqlSession.delete(namespace + "deleteQna", seqQnaBno);
	}

	public int deleteQnaImg(Integer seqQnaBno) {
		return sqlSession.delete(namespace + "deleteQnaImg", seqQnaBno);
	}

	public SaleDetailDTO saleDetail(int proSeq) {
		return sqlSession.selectOne(namespace + "saleDetail", proSeq);
	}

	public List<DeliveryCompanyDTO> dcOption() {
		return sqlSession.selectList(namespace + "dcOption");
	}

	public int insertDelivery(InsertDeliveryDTO delivery) {
		return sqlSession.insert(namespace + "insertDelivery", delivery);
	}

	public int updateSCheck(int proSeq) {
		return sqlSession.update(namespace + "updateSCheck", proSeq);
	}
	public int updatePCheck(int proSeq) {
		return sqlSession.update(namespace + "updatePCheck", proSeq);
	}

	public List<BankKindDTO> bankList() {
		return sqlSession.selectList(namespace + "bankList");
	}

	public int deleteJjim(List<Integer> seq, String userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("list", seq);
		params.put("userId", userId);
		return sqlSession.delete(namespace + "deleteJjim", params);
	}

	public List<SaleListAucDTO> saleListAuc(String status, String userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("status", status);
		params.put("userId", userId);
		return sqlSession.selectList(namespace + "saleListAuc", params);
	}

	public List<SaleListAucDTO> purchaselistAuc(String userId) {
		return sqlSession.selectList(namespace + "purchaselistAuc", userId);
	}

	public int proStatusDelete(int productSeq) {
		return sqlSession.delete(namespace + "proStatusDelete", productSeq);
	}

	public int updateStatus(int productSeq, String proStatus) {
		Map<String, Object> params = new HashMap<>();
		params.put("productSeq", productSeq);
		params.put("proStatus", proStatus);
		return sqlSession.update(namespace + "updateStatus", params);
	}

	public Object editProfile(String nickName, String userIntroduce, String image, String userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("nickName", nickName);
		params.put("userIntroduce", userIntroduce);
		params.put("profileImg", image);
		params.put("id", userId);
		return sqlSession.update(namespace + "editProfile", params);
	}

	public Object updateAcc(String userId, int bankSeq, String accountNum) {
		Map<String, Object> params = new HashMap<>();
		params.put("bankSeq", bankSeq);
		params.put("accountNum", accountNum);
		params.put("id", userId);
		return sqlSession.update(namespace + "updateAcc", params);
	}

	public int dupNickCheck(String nickName) {
		return sqlSession.selectOne(namespace + "dupNickCheck", nickName);
	}

	public String selectEncPw(String userId) {
		// 현재 암호화된 비밀번호 조회
		return sqlSession.selectOne(namespace + "selectEncPw", userId);
	}

	public int deleteUser(String userId) {
		return sqlSession.delete(namespace + "deleteUser", userId);
	}
	public int deleteAucId(String userId) {
		return sqlSession.update(namespace + "deleteAucId", userId);
	}
	public int deleteId(String userId) {
		return sqlSession.update(namespace + "deleteId", userId);
	}
	public int deleteChatBySeller(String userId) {
		return sqlSession.update(namespace + "deleteChatBySeller", userId);
	}

	public int deleteChatByBuyer(String userId) {
		return sqlSession.update(namespace + "deleteChatByBuyer", userId);
	}
	
	
	public int chargePoint(String userId, Integer userPoint) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("userPoint", userPoint);
		return sqlSession.update(namespace + "chargePoint", params);
	}

	public List<PointListDTO> searchPoint(String userId, String year, String monthPart) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("YEAR", year);
		params.put("MONTH", monthPart);
		return sqlSession.selectList(namespace + "searchPoint", params);
	}

	public int updatePhone(String userId, String phone) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("phone", phone);
		return sqlSession.update(namespace + "updatePhone", params);
	}

	public int updateAddress(String userId, String address, String detailAddress) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("address", address);
		params.put("detailAddress", detailAddress);
		return sqlSession.update(namespace + "updateAddress", params);
	}

	public int updatePw(String userId, String pw) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("pw", pw);
		return sqlSession.update(namespace + "updatePw", params);
	}

	public SaleDetailAucDTO saledetailAuc(int proSeq) {
		return sqlSession.selectOne(namespace + "saledetailAuc", proSeq);
	}
	
	public int updateFirstDstatus() {
		return sqlSession.update(namespace + "updateFirstDstatus");
	}
	
	public int updateSecondDstatus() {
		return sqlSession.update(namespace + "updateSecondDstatus");
	}

	// 평점 관련
    public int rating(RateAdminDTO rateDTO) {
        return sqlSession.insert(namespace + "insertRating", rateDTO);
    }
    
    public int updateRate(RateAdminDTO rateDTO) {
        return sqlSession.insert(namespace + "updateRating", rateDTO);
    }

	public int updateSCheckAuc(int proSeq) {
		return sqlSession.update(namespace + "updateSCheckAuc", proSeq);
	}

	public int updatePCheckAuc(int proSeq) {
		return sqlSession.update(namespace + "updatePCheckAuc", proSeq);
	}
	

}
