package com.shinhan.heehee.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.request.AdminAuctionDTO;
import com.shinhan.heehee.dto.request.AdminCategoryDTO;
import com.shinhan.heehee.dto.request.AdminProductDTO;
import com.shinhan.heehee.dto.request.AdminQnaManagerDTO;
import com.shinhan.heehee.dto.request.AdminUserBanDTO;
import com.shinhan.heehee.dto.request.AdminUserDTO;

@Repository
public class AdminDAO {
	
	@Autowired
	 SqlSession sqlSession;
	
	String namespace = "com.shinhan.heehee.admin.";
	
	//회원정보 관리 - 회원정보 관리 - 조회기능
	public List<AdminUserDTO> searchAllUser(String category, String categoryDate, String keyword, String startDate, String endDate) {
		Map<String, Object> params = new HashMap<>();
		params.put("category", category);
		params.put("categoryDate", categoryDate);
		params.put("keyword", keyword == "" || keyword == null?null:keyword);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return sqlSession.selectList(namespace + "wholeUserSearch", params);
	}

	//회원정보 관리 - 이용상태 관리 - 전체 조회
	public List<AdminUserBanDTO> userBan(String category, String categoryDate, String keyword, String startDate, String endDate) {
		Map<String, Object> params = new HashMap<>();
		params.put("category", category);
		params.put("categoryDate", categoryDate);
		params.put("keyword", keyword == "" || keyword == null?null:keyword);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return sqlSession.selectList(namespace + "userBanSearch", params);
	}
	
	//회원정보 관리 - 이용상태 관리 - 수정기능
	
	
	//상품관리 - 일반상품 상세조회 - 조회기능
	public List<AdminProductDTO> searchProductDetail(String category, String keyword){
		Map<String, Object> params = new HashMap<>();
		params.put("category", category);
		params.put("keyword", keyword);
		return sqlSession.selectList(namespace + "searchProductDetail", params);
	}
	
	//상품관리 - 일반상품 상세조회 - 수정기능
	
	
	//상품관리 - 일반상품 상세조회 - 삭제기능
	
	
	//상품관리 - 경매상품 상세조회 - 조회기능
	public List<AdminAuctionDTO> searchAuctionDetail(String category, String keyword){
		Map<String, Object> params = new HashMap<>();
		params.put("category", category);
		params.put("keyword", keyword);
		return sqlSession.selectList(namespace + "searchAuctionDetail", params);
	}
	
	
	//상품관리 - 경매상품 상세조회 - 수정기능
	
	
	//상품관리 - 경매상품 상세조회 - 삭제기능
	
	
	//상품관리 - 카테고리 관리 - 조회기능
	public List<AdminCategoryDTO> searchCategoryInfo(String category, String keyword) {
		Map<String, Object> params = new HashMap<>();
		params.put("category", category);
		params.put("keyword", keyword);
		return sqlSession.selectList(namespace + "searchCategoryInfo", params);
	}
	
	//고객 지원 - 1:1 상담문의 - 조회기능
	public List<AdminQnaManagerDTO> searchQnaAll(String category, String keyword){
		Map<String, Object> params = new HashMap<>();
		params.put("category", category);
		params.put("keyword", keyword);
		return sqlSession.selectList(namespace + "searchQnaAll", params);
	}
	
	
}














