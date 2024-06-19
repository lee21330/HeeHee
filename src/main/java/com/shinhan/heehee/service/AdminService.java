package com.shinhan.heehee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.AdminDAO;
import com.shinhan.heehee.dto.request.AdminProductDTO;
import com.shinhan.heehee.dto.request.AdminUserBanDTO;
import com.shinhan.heehee.dto.request.AdminUserDTO;

@Service
public class AdminService {
	
	@Autowired
	private AdminDAO adminDAO;
	
	//회원정보 전체 조회
	public List<AdminUserDTO> searchAllUser(String category, String categoryDate, String keyword, String startDate, String endDate) {
		return adminDAO.searchAllUser(category, categoryDate, keyword, startDate, endDate);
	}
	
	//회원이용정보 관리 전체 조회
	public List<AdminUserBanDTO> userBan(String category, String categoryDate, String keyword, String startDate, String endDate) {
		return adminDAO.userBan(category, categoryDate, keyword, startDate, endDate);
	}
	
	//상품관리 - 일반상품 상세조회 - 조회기능
	public List<AdminProductDTO> searchProductDetail(String category, String keyword){
		return adminDAO.searchProductDetail(category, keyword);
	}
}