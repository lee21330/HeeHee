package com.shinhan.heehee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.AdminDAO;
import com.shinhan.heehee.dto.request.AdminAuctionDTO;
import com.shinhan.heehee.dto.request.AdminCategoryDTO;
import com.shinhan.heehee.dto.request.AdminProductDTO;
import com.shinhan.heehee.dto.request.AdminQnaManagerDTO;
import com.shinhan.heehee.dto.request.AdminUserBanDTO;
import com.shinhan.heehee.dto.request.AdminUserDTO;

@Service
public class AdminService {
	
	@Autowired
	private AdminDAO adminDAO;
	
	//회원정보 관리 - 회원정보 관리 - 조회기능
	public List<AdminUserDTO> searchAllUser(String category, String categoryDate, String keyword, String startDate, String endDate) {
		return adminDAO.searchAllUser(category, categoryDate, keyword, startDate, endDate);
	}
	
	//회원정보 관리 - 이용상태 관리 - 전체 조회
	public List<AdminUserBanDTO> userBan(String category, String categoryDate, String keyword, String startDate, String endDate) {
		return adminDAO.userBan(category, categoryDate, keyword, startDate, endDate);
	}
	
	//상품관리 - 일반상품 상세조회 - 조회기능
	public List<AdminProductDTO> searchProductDetail(String category, String keyword){
		return adminDAO.searchProductDetail(category, keyword);
	}
	
	//상품관리 - 경매상품 상세조회 - 조회기능
	public List<AdminAuctionDTO> searchAuctionDetail(String category, String keyword){
		return adminDAO.searchAuctionDetail(category, keyword);
	}

	//상품관리 - 카테고리 관리 - 조회기능
	public List<AdminCategoryDTO> searchCategoryInfo(String category, String keyword) {
		return adminDAO.searchCategoryInfo(category, keyword);
	}
	
	//고객 지원 - 1:1 상담문의 - 조회기능
	public List<AdminQnaManagerDTO> searchQnaAll(String category, String keyword){
		return adminDAO.searchQnaAll(category, keyword);
	}
}