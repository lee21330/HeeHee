package com.shinhan.heehee.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.request.AdminAuctionDTO;
import com.shinhan.heehee.dto.request.AdminCategoryDTO;
import com.shinhan.heehee.dto.request.AdminFaqManagerDTO;
import com.shinhan.heehee.dto.request.AdminProductDTO;
import com.shinhan.heehee.dto.request.AdminQnaManagerDTO;
import com.shinhan.heehee.dto.request.AdminUserBanDTO;
import com.shinhan.heehee.dto.request.AdminUserDTO;
import com.shinhan.heehee.dto.request.adminQuestionManagerDTO;

@Repository
public class AdminDAO {
	
	@Autowired
	 SqlSession sqlSession;
	
	String namespace = "com.shinhan.heehee.admin.";
	
	//관리자 홈 관련 SQL문
	
	//전체 주문통계 조회 (기능: 전체 주문관련 모니터링용 대시보드 항목 조회)
	

	//최근 주문내역 조회 (기능: 최근 주문관련 모니터링용 대시보드 항목 조회)
	
	
	//최근 문의내역 조회 (기능: 최근 문의관련 모니터링용 대시보드 항목 조회)
	
	
	//관리자 홈 끝
	
	//회원정보 관리 관련 SQL문
	
	//회원정보 관리 - 회원정보 관리 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능)
	public List<AdminUserDTO> searchAllUser(String category, String categoryDate, String keyword, String startDate, String endDate) {
		Map<String, Object> params = new HashMap<>();
		params.put("category", category);
		params.put("categoryDate", categoryDate);
		params.put("keyword", keyword == "" || keyword == null?null:keyword);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return sqlSession.selectList(namespace + "searchAllUser", params);
	}

	//회원정보 관리 - 이용상태 관리 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능, 수정사항 : 시작일,종료일이 null 이거나 현재 날짜가 시작일과 종료일 사이에 포함되지 않는 회원은 조회되지 않도록 수정 예정)
	public List<AdminUserBanDTO> userBanSearch(String category, String categoryDate, String keyword, String startDate, String endDate) {
		Map<String, Object> params = new HashMap<>();
		params.put("category", category);
		params.put("categoryDate", categoryDate);
		params.put("keyword", keyword == "" || keyword == null?null:keyword);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return sqlSession.selectList(namespace + "userBanSearch", params);
	}
	
    //회원정보 관리 - 이용상태 관리 - 신규 등록 기능 (기능 : 정지사유, 정지 시작일, 종료일 신규입력, 정지하고자 하는 회원의 id를 입력 후 정지사유와 종료일을 입력하도록 구성한다. 시작일은 sysdate로 받아오면 좋을듯)
	
	
    //회원정보 관리 - 이용상태 관리 - 수정 기능 (기능 : 정지하고자 하는 회원의 id를 가입된 회원의 아이디로 입력하여, 그 회원의 정지일을 변경할 수 있도록 구성 예정)

	
	//회원정보 관리 끝
	
	//상품 관리 관련 SQL문
	
	//상품관리 - 일반상품 상세조회 - 조회기능
	public List<AdminProductDTO> searchProductDetail(String category, String keyword){
		Map<String, Object> params = new HashMap<>();
		params.put("category", category);
		params.put("keyword", keyword);
		return sqlSession.selectList(namespace + "searchProductDetail", params);
	}
	
	//상품 관리 - 일반상품 상세조회 - 수정(정지사유 조회) 기능 (기능 : 관리자가 선택된 게시글의 판매중지 사유를 열람 가능)
	public AdminProductDTO getProductBanReason (int product_seq) {
		AdminProductDTO dto = new AdminProductDTO(product_seq);
		return sqlSession.selectOne(namespace + "getProductBanReason", dto);
	}
	
	//상품 관리 - 일반상품 상세조회 - 수정 기능 (기능 : 선택된 상품의 판매상태(SELL_STATUS)를 "판매중지"로 업데이트 가능해야 함, 입력된 텍스트는 판매 중지사유(SELL_PRODUCT 테이블 -> PRODUCT_BAN_REASON 컬럼)로 입력(null -> 텍스트) 될 것임)
	public void updateProductStatus (int product_seq, String pro_status, String product_ban_reason) {
		AdminProductDTO dto = new AdminProductDTO(product_seq, pro_status, product_ban_reason);
		sqlSession.update("updateProductStatus", dto);
	}
	
	//상품 관리 - 일반상품 상세조회 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	public void deleteProduct (int product_seq) {
		AdminProductDTO dto = new AdminProductDTO(product_seq);
		sqlSession.delete("deleteProduct", dto);
	}
	
	//상품 관리 - 경매상품 상세조회 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능)
	public List<AdminAuctionDTO> searchAuctionDetail(String category, String keyword){
		Map<String, Object> params = new HashMap<>();
		params.put("category", category);
		params.put("keyword", keyword);
		return sqlSession.selectList(namespace + "searchAuctionDetail", params);
	}
	
	//상품 관리 - 경매상품 상세조회 - 수정(정지사유 조회) 기능 (기능 : 관리자가 선택된 게시글의 판매중지 사유를 열람 가능)
	public AdminAuctionDTO getAucBanReason (int product_seq){
		AdminAuctionDTO dto = new AdminAuctionDTO(product_seq);
		return sqlSession.selectOne(namespace + "getAucBanReason", dto);
	}
	
	//상품 관리 - 경매상품 상세조회 - 수정 기능 (기능 : 선택된 상품의 판매상태(AUC_STATUS)를 "판매중지"로 업데이트 가능해야 함, 입력된 텍스트는 판매 중지사유(AUC_PRODUCT 테이블 -> AUC_BAN_REASON 컬럼)로 입력(null -> 텍스트) 될 것임)
	public void updateAucStatus (int product_seq, String auc_status, String auc_ban_reason) {
		AdminAuctionDTO dto = new AdminAuctionDTO(product_seq, auc_status, auc_ban_reason);
		sqlSession.update("updateAucStatus", dto);
	}
	
	//상품 관리 - 경매상품 상세조회 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	public void deleteAuction (int product_seq) {
		AdminAuctionDTO dto = new AdminAuctionDTO(product_seq);
		sqlSession.delete("deleteAuction", dto);
	}
	
	//상품 관리 - 카테고리 관리 - 조회 기능 (기능 : 키워드로 필터검색 가능) - 특이사항 : 향후 제품단위로 추가 상세분류가 필요하면 기능이 늘어날 수 있음
	public List<AdminCategoryDTO> searchCategoryInfo(String category, String keyword) {
		Map<String, Object> params = new HashMap<>();
		params.put("category", category);
		params.put("keyword", keyword);
		return sqlSession.selectList(namespace + "searchCategoryInfo", params);
	}
	
	//상품 관리 - 카테고리 관리 - 신규 등록 기능 (기능 : 수기 입력받은 카테고리와 세부 카테고리를 Insert 함)
	public void insertCategory(String category, String detail_category) {
		AdminCategoryDTO dto = new AdminCategoryDTO(category, detail_category);
		sqlSession.insert("insertCategory", dto);
	}
	
	//상품 관리 - 카테고리 관리 - 수정 기능 (기능 : 선택된 기존의 카테고리 항목의 카테고리와 세부 카테고리를 새로운 내용으로 Update 함)
	public void updateCategory (int product_cate_seq, String category, String detail_category) {
		AdminCategoryDTO dto = new AdminCategoryDTO(product_cate_seq, category, detail_category);
		sqlSession.update("updateCategory", dto);
	}
	
	//상품 관리 - 카테고리 관리 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	public void deleteCategory (int product_cate_seq) {
		AdminCategoryDTO dto = new AdminCategoryDTO(product_cate_seq);
		sqlSession.delete("deleteCategory", dto);
	}
	
	//상품 관리 끝
	
	//고객 지원 관련 SQL문
	
	//고객 지원 - 1:1 상담문의 - 조회 기능 (기능 : 키워드로 필터검색 가능)
	public List<AdminQnaManagerDTO> searchQnaAll(String category, String keyword){
		Map<String, Object> params = new HashMap<>();
		params.put("category", category);
		params.put("keyword", keyword);
		return sqlSession.selectList(namespace + "searchQnaAll", params);
	}
	
	//고객 지원 - 1:1 상담문의 - 열람/답변 중 열람 기능 (기능 : 선택된 항목의 문의 상세내용 열람 가능)
	public List<AdminQnaManagerDTO> getQnaContent(int seq_qna_bno) {
		return sqlSession.selectList(namespace + "getQnaContent", seq_qna_bno);
	}
	
	//고객 지원 - 1:1 상담문의 - 열람/답변 중 답변 기능 (기능 : 선택된 항목에 대한 답변내용 작성 및 Update 가능)
	public void updateQnaAns(int seq_qna_bno, String newValue) {
		AdminQnaManagerDTO dto = new AdminQnaManagerDTO(seq_qna_bno, newValue);
		sqlSession.update("updateQnaAns", dto);
	}
	
	//고객 지원 - 1:1 상담문의 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	public void deleteQna (int seq_qna_bno) {
		AdminQnaManagerDTO dto = new AdminQnaManagerDTO(seq_qna_bno);
		sqlSession.delete("deleteQna", dto);
	}
	
	
	//고객 지원 - FAQ 내용관리 - 조회 기능 (기능 : 키워드로 필터검색 가능)
	public List<AdminFaqManagerDTO> searchFaqAll(String category, String keyword) {
		Map<String, Object> params = new HashMap<>();
		params.put("category", category);
		params.put("keyword", keyword);
		return sqlSession.selectList(namespace + "searchFaqAll", params);
	}
	
	//고객 지원 - FAQ 내용관리 - 신규 등록 기능 (기능 : 문의유형 관리에 등록되어있는 유형을 select하여 선택된 유형과, 수기 입력받은 FAQ 제목, 내용을 Insert 함)
	
	
	//고객 지원 - FAQ 내용관리 - 열람/수정 중 열람 기능 (기능 : 선택된 항목의 문의 상세내용 열람 가능)
	public List<AdminFaqManagerDTO> getFaqContent(int id) {
		return sqlSession.selectList(namespace + "getFaqContent", id);
	}
	
	//고객 지원 - FAQ 내용관리 - 열람/수정 중 수정 기능 (기능 : 선택된 항목에 대한 세부내용 작성 및 Update 가능)
	public void updateFaq (int seq_faq_bno, int seq_qna_option, String faq_content, String faq_and) {
		AdminFaqManagerDTO dto = new AdminFaqManagerDTO(seq_faq_bno, seq_qna_option, faq_content, faq_and);
		sqlSession.update("updateFaq", dto);
	}
	
	//고객 지원 - FAQ 내용관리 - 열람/수정 중 수정 기능 (기능 : 문의유형을 동적으로 받아와 줌)
	public List<adminQuestionManagerDTO> getQnaOptions(){
		return sqlSession.selectList(namespace + "getQnaOptions");
	}
	
	//고객 지원 - FAQ 내용관리 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	public void deleteFaq(int seq_faq_bno) {
		AdminFaqManagerDTO dto = new AdminFaqManagerDTO(seq_faq_bno);
		sqlSession.delete("deleteFaq", dto);
	}
	
	//고객 지원 - 문의 유형 관리 - 조회 기능 (기능 : 키워드로 필터검색 가능)
	public List<adminQuestionManagerDTO> searchQuestionCategory (String category, String keyword) {
		Map<String, Object> params = new HashMap<>();
		params.put("category", category);
		params.put("keyword", keyword);
		return sqlSession.selectList(namespace + "searchQuestionCategory", params);
	}
	
	//고객 지원 - 문의 유형 관리 - 신규 등록 (기능 : 수기 입력받은 유형과 내용을 Insert 함)
	public void insertQnaOption (String qna_option, String qna_option_content) {
		adminQuestionManagerDTO dto = new adminQuestionManagerDTO(qna_option, qna_option_content);
		sqlSession.insert("insertQnaOption", dto);
	}
	
	
	//고객 지원 - 문의 유형 관리 - 수정 기능 (기능 : 수기 입력받은 유형과 내용을 Update 함)
	public void updateQnaOption(int seq_qna_bno, String qna_option, String qna_option_content) {
		adminQuestionManagerDTO dto = new adminQuestionManagerDTO(seq_qna_bno, qna_option, qna_option_content);
		sqlSession.update("updateQnaOption", dto);
	}
	
	//고객 지원 - 문의 유형 관리 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	public void deleteQnaOption(int seq_qna_option) {
		adminQuestionManagerDTO dto = new adminQuestionManagerDTO(seq_qna_option);
		sqlSession.delete("deleteQnaOption", dto);
	}
	//고객 지원 끝
}














