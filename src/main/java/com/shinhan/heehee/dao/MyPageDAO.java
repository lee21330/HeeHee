package com.shinhan.heehee.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.response.EditProfileDTO;
import com.shinhan.heehee.dto.response.FaQDTO;
import com.shinhan.heehee.dto.response.InsertQnADTO;
import com.shinhan.heehee.dto.response.InsertQnAImgDTO;
import com.shinhan.heehee.dto.response.JjimDTO;
import com.shinhan.heehee.dto.response.MyPageHeaderDTO;
import com.shinhan.heehee.dto.response.PurchaseListDTO;
import com.shinhan.heehee.dto.response.QnADTO;
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
		return sqlSession.selectList(namespace + "saleList",params);
	}

	public int userIntroduce(String intro, String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("introduce", intro);
        params.put("userId", userId);
        return sqlSession.update(namespace + "userIntroduce", params);
    }

	public int editProfile(EditProfileDTO profile) {
		return sqlSession.selectOne(namespace + "editProfile", profile);
	}

	public List<QnADTO> qnaOption() {
		return sqlSession.selectList(namespace + "qnaOption");
	}

	public List<FaQDTO> faqOption(int option) {
		return sqlSession.selectList(namespace + "faqOption", option);
	}

	public List<FaQDTO> myQna(String userId) {
		return sqlSession.selectList(namespace + "myQna", userId);
	}

	public int insertQna(InsertQnADTO qna) {
		return sqlSession.insert(namespace + "insertQna", qna);
	}

	public int insertQnaImg(InsertQnAImgDTO qnaImg) {
		return sqlSession.insert(namespace + "insertQnaImg", qnaImg);
	}

	public String profile(String userId) {
		return sqlSession.selectOne(namespace + "profile", userId);
	}

}
