package com.shinhan.heehee.service;

import java.awt.print.Pageable;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shinhan.heehee.dao.AdminDAO;
import com.shinhan.heehee.dao.AlarmDAO;
import com.shinhan.heehee.dto.request.AdminAuctionDTO;
import com.shinhan.heehee.dto.request.AdminCategoryDTO;
import com.shinhan.heehee.dto.request.AdminFaqManagerDTO;
import com.shinhan.heehee.dto.request.AdminMainDTO;
import com.shinhan.heehee.dto.request.AdminProductDTO;
import com.shinhan.heehee.dto.request.AdminQnaManagerDTO;
import com.shinhan.heehee.dto.request.AdminUserBanDTO;
import com.shinhan.heehee.dto.request.AdminUserDTO;
import com.shinhan.heehee.dto.request.adminQuestionManagerDTO;
import com.shinhan.heehee.dto.response.AlarmDTO;

@Service
public class AdminService {

	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private AlarmDAO alarmDAO;
	
	@Autowired
	SimpMessagingTemplate messagingTemplate;

	// 관리자 홈 관련 SQL문

	//전체 주문통계 조회 (기능: 전체 주문관련 모니터링용 대시보드 항목 조회)
	//전체 주문현황
	public List<AdminMainDTO> searchTotalOrder() {
		return adminDAO.searchTotalOrder();
	}
	
	//일반상품 주문현황
	public List<AdminMainDTO> searchProStatus() {
		return adminDAO.searchProStatus();
	}
	
	//경매상품 주문현황
	public List<AdminMainDTO> searchAucStatus() {
		return adminDAO.searchAucStatus();
	}
	
	//최근 등록내역 조회 (기능: 최근 주문관련 모니터링용 대시보드 항목 조회)
	public List<AdminProductDTO> searchRecentProduct() {
		return adminDAO.searchRecentProduct();
	}
	
	//최근 문의내역 조회 (기능: 최근 문의관련 모니터링용 대시보드 항목 조회)
	public List<AdminQnaManagerDTO> searchRecentQuestion() {
		return adminDAO.searchRecentQuestion();
	}
	
	//최근 회원가입 조회 (기능: 최근 가입회원 모니터링용 대시보드 항목 조회)
	public List<AdminUserDTO> searchRecentJoin() {
		return adminDAO.searchRecentJoin();
	}

	// 관리자 홈 끝

	// 회원정보 관리 관련 SQL문

	// 회원정보 관리 - 회원정보 관리 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능)
	public List<AdminUserDTO> searchAllUser(String category, String categoryDate, String keyword, String startDate,
			String endDate) {
		return adminDAO.searchAllUser(category, categoryDate, keyword, startDate, endDate);
	}

	// 회원정보 관리 - 이용상태 관리 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능, 수정사항 : 시작일,종료일이 null 이거나 현재
	// 날짜가 시작일과 종료일 사이에 포함되지 않는 회원은 조회되지 않도록 수정 예정)
	public List<AdminUserBanDTO> userBanSearch(String category, String categoryDate, String keyword, String startDate,
			String endDate) {
		return adminDAO.userBanSearch(category, categoryDate, keyword, startDate, endDate);
	}

	// 회원정보 관리 - 이용상태 관리 - 신규 등록 기능 (기능 : 정지사유, 정지 시작일, 종료일 신규입력, 정지하고자 하는 회원의 id를
	// 입력 후 정지사유와 종료일을 입력하도록 구성한다. 시작일은 sysdate로 받아오면 좋을듯)
	@Transactional
	public int insertBanUser(String id, String BanContent, Date banStr, Date banEnd) {
		int result = adminDAO.insertBanUser(id, BanContent, banStr, banEnd);
		if(result == 1) {
			int alarmCnt = alarmDAO.alarmCount(id);
			messagingTemplate.convertAndSend("/topic/alarm/" + id, alarmCnt);
		}
		return result;
	}

	// 회원정보 관리 - 이용상태 관리 - 수정 기능 (기능 : 정지하고자 하는 회원의 id를 가입된 회원의 아이디로 입력하여, 그 회원의
	// 정지일을 변경할 수 있도록 구성 예정)
	@Transactional
	public int updateBanUser(String id, String banContent, Date banStr, Date banEnd) {
		int result = adminDAO.updateBanUser(id, banContent, banStr, banEnd);
		if(result == 1) {
			int alarmCnt = alarmDAO.alarmCount(id);
			messagingTemplate.convertAndSend("/topic/alarm/" + id, alarmCnt);
		}
		return result;
	}

	// 회원정보 관리 끝

	// 상품 관리 관련 SQL문

	// 상품 관리 - 일반상품 상세조회 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능)
	public List<AdminProductDTO> searchProductDetail(String category, String keyword) {
		return adminDAO.searchProductDetail(category, keyword);
	}

	// 상품 관리 - 일반상품 상세조회 - 수정(정지사유 조회) 기능 (기능 : 관리자가 선택된 게시글의 판매중지 사유를 열람 가능)
	public AdminProductDTO getProductBanReason(int productSeq) {
		return adminDAO.getProductBanReason(productSeq);
	}

	// 상품 관리 - 일반상품 상세조회 - 수정 기능 (기능 : 선택된 상품의 판매상태(SELL_STATUS)를 "판매중지"로 업데이트 가능해야
	// 함, 입력된 텍스트는 판매 중지사유(SELL_PRODUCT 테이블 -> PRODUCT_BAN_REASON 컬럼)로 입력(null ->
	// 텍스트) 될 것임)
	@Transactional
	public int updateProductStatus(int productSeq, String proStatus, String productBanReason, String id) {
		int result = adminDAO.updateProductStatus(productSeq, proStatus, productBanReason);
		if(result ==1) {
			int alarmCnt = alarmDAO.alarmCount(id);
			messagingTemplate.convertAndSend("/topic/alarm/" + id, alarmCnt);
		}
		return result;
	}

	// 상품 관리 - 일반상품 상세조회 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	public void deleteProduct(int productSeq) {
		adminDAO.deleteProduct(productSeq);
	}

	// 상품 관리 - 경매상품 상세조회 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능)
	public List<AdminAuctionDTO> searchAuctionDetail(String category, String keyword) {
		return adminDAO.searchAuctionDetail(category, keyword);
	}

	// 상품 관리 - 경매상품 상세조회 - 수정(정지사유 조회) 기능 (기능 : 관리자가 선택된 게시글의 판매중지 사유를 열람 가능)
	public AdminAuctionDTO getAucBanReason(int productSeq) {
		return adminDAO.getAucBanReason(productSeq);
	}

	// 상품 관리 - 경매상품 상세조회 - 수정 기능 (기능 : 선택된 상품의 판매상태(AUC_STATUS)를 "판매중지"로 업데이트 가능해야
	// 함, 입력된 텍스트는 판매 중지사유(AUC_PRODUCT 테이블 -> AUC_BAN_REASON 컬럼)로 입력(null -> 텍스트) 될
	// 것임)
	@Transactional
	public int updateAucStatus(int productSeq, String aucStatus, String aucBanReason, String id) {
		int result = adminDAO.updateAucStatus(productSeq, aucStatus, aucBanReason);
		if(result == 1) {
			int alarmCnt = alarmDAO.alarmCount(id);
			messagingTemplate.convertAndSend("/topic/alarm/" + id, alarmCnt);
		}
		return result;
	}

	// 상품 관리 - 경매상품 상세조회 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	public void deleteAuction(int productSeq) {
		adminDAO.deleteAuction(productSeq);
	}

	// 상품 관리 - 카테고리 관리 - 조회 기능 (기능 : 키워드로 필터검색 가능) - 특이사항 : 향후 제품단위로 추가 상세분류가 필요하면
	// 기능이 늘어날 수 있음
	
	//페이징 구현을 위한 시도
	public List<AdminCategoryDTO> searchCategoryInfo(String category, String keyword, int page, int size) {
		int offset = page * size;
		return adminDAO.searchCategoryInfo(category, keyword, offset, size);
	}
	public int countCategoryInfo(String category, String keyword) {
		return adminDAO.countCategoryInfo(category, keyword);
	}
	
	/* 원본
	public List<AdminCategoryDTO> searchCategoryInfo(String category, String keyword) {
		return adminDAO.searchCategoryInfo(category, keyword);
	}
	 */
	

	// 상품 관리 - 카테고리 관리 - 신규 등록 기능 (기능 : 수기 입력받은 카테고리와 세부 카테고리를 Insert 함)
	public void insertCategory(String category, String detailCategory, String id) {
		adminDAO.insertCategory(category, detailCategory, id);
	}

	// 상품 관리 - 카테고리 관리 - 수정 기능 (기능 : 선택된 기존의 카테고리 항목의 카테고리와 세부 카테고리를 새로운 내용으로 Update
	// 함)
	public void updateCategory(int productCateSeq, String category, String detailCategory, String id) {
		adminDAO.updateCategory(productCateSeq, category, detailCategory, id);
	}

	// 상품 관리 - 카테고리 관리 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	public void deleteCategory(int productCateSeq) {
		adminDAO.deleteCategory(productCateSeq);
	}

	/*
	 * 데이터 한번에 넣기용 public void dbset (int productCateSeq) {
	 * adminDAO.dbset(productCateSeq); }
	 */

	// 상품 관리 끝

	// 고객 지원 관련 SQL문

	// 고객 지원 - 1:1 상담문의 - 조회 기능 (기능 : 키워드로 필터검색 가능)
	public List<AdminQnaManagerDTO> searchQnaAll(String category, String keyword) {
		return adminDAO.searchQnaAll(category, keyword);
	}

	// 고객 지원 - 1:1 상담문의 - 열람/답변 중 열람 기능 (기능 : 선택된 항목의 문의 상세내용 열람 가능)
	public List<AdminQnaManagerDTO> getQnaContent(int seqQnaBno) {
		return adminDAO.getQnaContent(seqQnaBno);
	}

	// 고객 지원 - 1:1 상담문의 - 열람/답변 중 열람 기능 이미지 불러오기 (기능 : 선택된 항목의 문의 상세내용 열람 가능 - 이미지
	// 불러오기 기능)
	public List<AdminQnaManagerDTO> getQnaImage(int seqQnaBno) {
		return adminDAO.getQnaImage(seqQnaBno);
	}

	// 고객 지원 - 1:1 상담문의 - 열람/답변 중 답변 기능 (기능 : 선택된 항목에 대한 답변내용 작성 및 Update 가능)
	@Transactional
	public int updateQnaAns(int seqQnaBno, String newValue, String id) {
		int result = adminDAO.updateQnaAns(seqQnaBno, newValue); 
		if(result == 1) { 
			AlarmDTO alarmDTO = new AlarmDTO();
			
			alarmDTO.setId(id); 
			alarmDTO.setCateNum(4); // 알림 분류 코드 (문의)
			alarmDTO.setReqSeq(seqQnaBno);
			alarmDTO.setAlContent("문의 답변이 등록되었습니다.");
		 
		 alarmDAO.alarmInsert(alarmDTO);
		 
		 int alarmCnt = alarmDAO.alarmCount(id);
		 messagingTemplate.convertAndSend("/topic/alarm/" + id, alarmCnt);
		 }
		return result;
		//adminDAO.updateQnaAns(seqQnaBno, newValue);
	}

	// 고객 지원 - 1:1 상담문의 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	@Transactional
	public void deleteQnaContent(int seqQnaBno) {
		adminDAO.deleteQnaContent(seqQnaBno);
	}

	// 고객 지원 - FAQ 내용관리 - 조회 기능 (기능 : 키워드로 필터검색 가능)
	public List<AdminFaqManagerDTO> searchFaqAll(String category, String keyword) {
		return adminDAO.searchFaqAll(category, keyword);
	}

	// 고객 지원 - FAQ 내용관리 - 신규 등록 기능 (기능 : 문의유형 관리에 등록되어있는 유형을 select하여 선택된 유형과, 수기
	// 입력받은 FAQ 제목, 내용을 Insert 함)
	public void insertFaq(int seqQnaOption, String faqContent, String faqAns, String id) {
		adminDAO.insertFaq(seqQnaOption, faqContent, faqAns, id);
	}

	// 고객 지원 - FAQ 내용관리 - 열람/수정 중 열람 기능 (기능 : 선택된 항목의 문의 상세내용 열람 가능)
	public List<AdminFaqManagerDTO> getFaqContent(int id) {
		return adminDAO.getFaqContent(id);
	}

	// 고객 지원 - FAQ 내용관리 - 열람/수정 중 수정 기능 (기능 : 선택된 항목에 대한 세부내용 작성 및 Update 가능)
	public void updateFaq(int seqFaqBno, int seqQnaOption, String faqContent, String faqAns, String id) {
		adminDAO.updateFaq(seqFaqBno, seqQnaOption, faqContent, faqAns, id);
	}

	// 고객 지원 - FAQ 내용관리 - 열람/수정 중 수정(문의유형 선택) 기능 (기능 : 문의유형을 동적으로 받아와 줌)
	public List<adminQuestionManagerDTO> getQnaOptions() {
		return adminDAO.getQnaOptions();
	}

	// 고객 지원 - FAQ 내용관리 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	public void deleteFaq(int seqFaqBno) {
		adminDAO.deleteFaq(seqFaqBno);
	}

	// 고객 지원 - 문의 유형 관리 - 조회 기능 (기능 : 키워드로 필터검색 가능)
	public List<adminQuestionManagerDTO> searchQuestionCategory(String category, String keyword) {
		return adminDAO.searchQuestionCategory(category, keyword);
	}

	// 고객 지원 - 문의 유형 관리 - 신규 등록 (기능 : 수기 입력받은 유형과 내용을 Insert 함)
	public void insertQnaOption(String qnaOption, String qnaOptionContent, String id) {
		adminDAO.insertQnaOption(qnaOption, qnaOptionContent, id);
	}

	// 고객 지원 - 문의 유형 관리 - 수정 기능 (기능 : 수기 입력받은 유형과 내용을 Update 함)
	public void updateQnaOption(int seqQnaBno, String qnaOption, String qnaOptionContent, String id) {
		adminDAO.updateQnaOption(seqQnaBno, qnaOption, qnaOptionContent, id);
	}

	// 고객 지원 - 문의 유형 관리 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	public void deleteQnaOption(int seqQnaOption) {
		adminDAO.deleteQnaOption(seqQnaOption);
	}
	// 고객 지원 끝
}