package com.shinhan.heehee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shinhan.heehee.dao.AdminDAO;
import com.shinhan.heehee.dto.request.AdminAuctionDTO;
import com.shinhan.heehee.dto.request.AdminCategoryDTO;
import com.shinhan.heehee.dto.request.AdminFaqManagerDTO;
import com.shinhan.heehee.dto.request.AdminProductDTO;
import com.shinhan.heehee.dto.request.AdminQnaManagerDTO;
import com.shinhan.heehee.dto.request.AdminUserBanDTO;
import com.shinhan.heehee.dto.request.AdminUserDTO;
import com.shinhan.heehee.dto.request.adminQuestionManagerDTO;

@Service
public class AdminService {
	
	@Autowired
	private AdminDAO adminDAO;
	
	//관리자 홈 관련 SQL문
	
	//전체 주문통계 조회 (기능: 전체 주문관련 모니터링용 대시보드 항목 조회)
	
	
	//최근 주문내역 조회 (기능: 최근 주문관련 모니터링용 대시보드 항목 조회)
	
	
	//최근 문의내역 조회 (기능: 최근 문의관련 모니터링용 대시보드 항목 조회)
	
	
	//관리자 홈 끝
	
	//회원정보 관리 관련 SQL문
	
	//회원정보 관리 - 회원정보 관리 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능)
	public List<AdminUserDTO> searchAllUser(String category, String categoryDate, String keyword, String startDate, String endDate) {
		return adminDAO.searchAllUser(category, categoryDate, keyword, startDate, endDate);
	}
	
	//회원정보 관리 - 이용상태 관리 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능, 수정사항 : 시작일,종료일이 null 이거나 현재 날짜가 시작일과 종료일 사이에 포함되지 않는 회원은 조회되지 않도록 수정 예정)
	public List<AdminUserBanDTO> userBanSearch(String category, String categoryDate, String keyword, String startDate, String endDate) {
		return adminDAO.userBanSearch(category, categoryDate, keyword, startDate, endDate);
	}
	
    //회원정보 관리 - 이용상태 관리 - 신규 등록 기능 (기능 : 정지사유, 정지 시작일, 종료일 신규입력, 정지하고자 하는 회원의 id를 입력 후 정지사유와 종료일을 입력하도록 구성한다. 시작일은 sysdate로 받아오면 좋을듯)

	
    //회원정보 관리 - 이용상태 관리 - 수정 기능 (기능 : 정지하고자 하는 회원의 id를 가입된 회원의 아이디로 입력하여, 그 회원의 정지일을 변경할 수 있도록 구성 예정)

	
	//회원정보 관리 끝
	
	//상품 관리 관련 SQL문
	
	//상품 관리 - 일반상품 상세조회 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능)
	public List<AdminProductDTO> searchProductDetail(String category, String keyword){
		return adminDAO.searchProductDetail(category, keyword);
	}
	
	//상품 관리 - 일반상품 상세조회 - 수정(정지사유 조회) 기능 (기능 : 관리자가 선택된 게시글의 판매중지 사유를 열람 가능)
	public AdminProductDTO getProductBanReason (int product_seq) {
		return adminDAO.getProductBanReason(product_seq);
	}
	
	//상품 관리 - 일반상품 상세조회 - 수정 기능 (기능 : 선택된 상품의 판매상태(SELL_STATUS)를 "판매중지"로 업데이트 가능해야 함, 입력된 텍스트는 판매 중지사유(SELL_PRODUCT 테이블 -> PRODUCT_BAN_REASON 컬럼)로 입력(null -> 텍스트) 될 것임)
	public void updateProductStatus (int product_seq, String pro_status, String product_ban_reason) {
		adminDAO.updateProductStatus(product_seq, pro_status, product_ban_reason);
	}
	
	//상품 관리 - 일반상품 상세조회 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	public void deleteProduct (int product_seq) {
		adminDAO.deleteProduct(product_seq);
	}
	
	//상품 관리 - 경매상품 상세조회 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능)
	public List<AdminAuctionDTO> searchAuctionDetail(String category, String keyword){
		return adminDAO.searchAuctionDetail(category, keyword);
	}
	
	//상품 관리 - 경매상품 상세조회 - 수정(정지사유 조회) 기능 (기능 : 관리자가 선택된 게시글의 판매중지 사유를 열람 가능)
	public AdminAuctionDTO getAucBanReason (int product_seq){
		return adminDAO.getAucBanReason(product_seq);
	}
	
	//상품 관리 - 경매상품 상세조회 - 수정 기능 (기능 : 선택된 상품의 판매상태(AUC_STATUS)를 "판매중지"로 업데이트 가능해야 함, 입력된 텍스트는 판매 중지사유(AUC_PRODUCT 테이블 -> AUC_BAN_REASON 컬럼)로 입력(null -> 텍스트) 될 것임)
	public void updateAucStatus (int product_seq, String auc_status, String auc_ban_reason) {
		adminDAO.updateAucStatus(product_seq, auc_status, auc_ban_reason);
	}
	
	//상품 관리 - 경매상품 상세조회 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	public void deleteAuction (int product_seq) {
		adminDAO.deleteAuction(product_seq);
	}

	//상품 관리 - 카테고리 관리 - 조회 기능 (기능 : 키워드로 필터검색 가능) - 특이사항 : 향후 제품단위로 추가 상세분류가 필요하면 기능이 늘어날 수 있음
	public List<AdminCategoryDTO> searchCategoryInfo(String category, String keyword) {
		return adminDAO.searchCategoryInfo(category, keyword);
	}
	
	//상품 관리 - 카테고리 관리 - 신규 등록 기능 (기능 : 수기 입력받은 카테고리와 세부 카테고리를 Insert 함)
	public void insertCategory(String category, String detail_category) {
		adminDAO.insertCategory(category, detail_category);
	}
	
	//상품 관리 - 카테고리 관리 - 수정 기능 (기능 : 선택된 기존의 카테고리 항목의 카테고리와 세부 카테고리를 새로운 내용으로 Update 함)
	public void updateCategory (int product_cate_seq, String category, String detail_category) {
		adminDAO.updateCategory(product_cate_seq, category, detail_category);
	}
	
	//상품 관리 - 카테고리 관리 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	public void deleteCategory (int product_cate_seq) {
		adminDAO.deleteCategory(product_cate_seq);
	}
	
	//상품 관리 끝
	
	//고객 지원 관련 SQL문
	
	//고객 지원 - 1:1 상담문의 - 조회 기능 (기능 : 키워드로 필터검색 가능)
	public List<AdminQnaManagerDTO> searchQnaAll(String category, String keyword){
		return adminDAO.searchQnaAll(category, keyword);
	}
	
	//고객 지원 - 1:1 상담문의 - 열람/답변 중 열람 기능 (기능 : 선택된 항목의 문의 상세내용 열람 가능)
	public List<AdminQnaManagerDTO> getQnaContent(int seq_qna_bno){
		return adminDAO.getQnaContent(seq_qna_bno);
	}
	
	//고객 지원 - 1:1 상담문의 - 열람/답변 중 답변 기능 (기능 : 선택된 항목에 대한 답변내용 작성 및 Update 가능)
	@Transactional
	public void updateQnaAns(int seq_qna_bno, String newValue) {
		adminDAO.updateQnaAns(seq_qna_bno, newValue);
	}
	
	//고객 지원 - 1:1 상담문의 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	@Transactional
	public void deleteQna (int seq_qna_bno) {
		adminDAO.deleteQna(seq_qna_bno);
	}
	
	//고객 지원 - FAQ 내용관리 - 조회 기능 (기능 : 키워드로 필터검색 가능)
	public List<AdminFaqManagerDTO> searchFaqAll(String category, String keyword){
		return adminDAO.searchFaqAll(category, keyword);
	}
	
	//고객 지원 - FAQ 내용관리 - 신규 등록 기능 (기능 : 문의유형 관리에 등록되어있는 유형을 select하여 선택된 유형과, 수기 입력받은 FAQ 제목, 내용을 Insert 함)
	
	
	//고객 지원 - FAQ 내용관리 - 열람/수정 중 열람 기능 (기능 : 선택된 항목의 문의 상세내용 열람 가능)
	public List<AdminFaqManagerDTO> getFaqContent(int id) {
		return adminDAO.getFaqContent(id);
	}
	
	//고객 지원 - FAQ 내용관리 - 열람/수정 중 수정 기능 (기능 : 선택된 항목에 대한 세부내용 작성 및 Update 가능)
	public void updateFaq (int seq_faq_bno, int seq_qna_option, String faq_content, String faq_and) {
		adminDAO.updateFaq(seq_faq_bno, seq_qna_option, faq_content, faq_and);
	}
	
	//고객 지원 - FAQ 내용관리 - 열람/수정 중 수정 기능 (기능 : 문의유형을 동적으로 받아와 줌)
	public List<adminQuestionManagerDTO> getQnaOptions(){
		return adminDAO.getQnaOptions();
	}
	
	//고객 지원 - FAQ 내용관리 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	public void deleteFaq(int seq_faq_bno) {
		adminDAO.deleteFaq(seq_faq_bno);
	}
	
	//고객 지원 - 문의 유형 관리 - 조회 기능 (기능 : 키워드로 필터검색 가능)
	public List<adminQuestionManagerDTO> searchQuestionCategory (String category, String keyword) {
		return adminDAO.searchQuestionCategory(category, keyword);
	}
	
	//고객 지원 - 문의 유형 관리 - 신규 등록 (기능 : 수기 입력받은 유형과 내용을 Insert 함)
	public void insertQnaOption (String qna_option, String qna_option_content) {
		adminDAO.insertQnaOption(qna_option, qna_option_content);
	}
	
	//고객 지원 - 문의 유형 관리 - 수정 기능 (기능 : 수기 입력받은 유형과 내용을 Update 함)
	public void updateQnaOption(int seq_qna_bno, String qna_option, String qna_option_content) {
		adminDAO.updateQnaOption(seq_qna_bno, qna_option, qna_option_content);
	}
	
	//고객 지원 - 문의 유형 관리 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	public void deleteQnaOption(int seq_qna_option) {
		adminDAO.deleteQnaOption(seq_qna_option);
	}
	//고객 지원 끝
}





