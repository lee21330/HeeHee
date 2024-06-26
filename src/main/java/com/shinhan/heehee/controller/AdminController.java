package com.shinhan.heehee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.heehee.dto.request.AdminAuctionDTO;
import com.shinhan.heehee.dto.request.AdminCategoryDTO;
import com.shinhan.heehee.dto.request.AdminFaqManagerDTO;
import com.shinhan.heehee.dto.request.AdminProductDTO;
import com.shinhan.heehee.dto.request.AdminQnaManagerDTO;
import com.shinhan.heehee.dto.request.AdminUserBanDTO;
import com.shinhan.heehee.dto.request.AdminUserDTO;
import com.shinhan.heehee.dto.request.adminQuestionManagerDTO;
import com.shinhan.heehee.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	//관리자 홈 관련 SQL문
	
	@GetMapping("/main")
	public String admin_main() {
		return "/admin/main";
	}
	
	//전체 주문통계 조회 (기능: 전체 주문관련 모니터링용 대시보드 항목 조회)
	
	
	//최근 주문내역 조회 (기능: 최근 주문관련 모니터링용 대시보드 항목 조회)
	
	
	//최근 문의내역 조회 (기능: 최근 문의관련 모니터링용 대시보드 항목 조회)
	
	
	//관리자 홈 끝
	
	//회원정보 관리 관련 SQL문
	
	@GetMapping("/user")
	public String admin_user(Model model) {
		return "/admin/user";
	}
	
	//회원정보 관리 - 회원정보 관리 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능)
    // AJAX 요청을 처리하여 JSON 데이터 반환
    //@GetMapping(value = "/searchUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/searchAllUser")
    @ResponseBody
    public List<AdminUserDTO> searchAllUser(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String categoryDate,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
    	System.out.println("Received search request with params: " + category + ", " + categoryDate + ", " + keyword + ", " + startDate + ", " + endDate);
    	List<AdminUserDTO> filterSearch = adminService.searchAllUser(category, categoryDate, keyword, startDate, endDate);
    	System.out.println(filterSearch.size());
        return filterSearch;
    }
    
    @GetMapping("/ban")
    public String admin_userVan() {
    	return "/admin/user-ban";
    }
    
    //회원정보 관리 - 이용상태 관리 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능, 수정사항 : 시작일,종료일이 null 이거나 현재 날짜가 시작일과 종료일 사이에 포함되지 않는 회원은 조회되지 않도록 수정 예정)
    // AJAX 요청을 처리하여 JSON 데이터 반환
    //@GetMapping(value = "/userBan", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/userBanSearch")
    @ResponseBody
    public List<AdminUserBanDTO> userBanSearch(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String categoryDate,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
    	System.out.println("Received search request with params: " + category + ", " + categoryDate + ", " + keyword + ", " + startDate + ", " + endDate);
    	List<AdminUserBanDTO> filterSearch = adminService.userBanSearch(category, categoryDate, keyword, startDate, endDate);
    	System.out.println(filterSearch.size());
        return filterSearch;
    }
    
    //회원정보 관리 - 이용상태 관리 - 신규 등록 기능 (기능 : 정지사유, 정지 시작일, 종료일 신규입력, 정지하고자 하는 회원의 id를 입력 후 정지사유와 종료일을 입력하도록 구성한다. 시작일은 sysdate로 받아오면 좋을듯)
    
    
    //회원정보 관리 - 이용상태 관리 - 수정 기능 (기능 : 정지하고자 하는 회원의 id를 가입된 회원의 아이디로 입력하여, 그 회원의 정지일을 변경할 수 있도록 구성 예정)
    
    
    //회원정보 끝
	
    //상품 관리 관련 SQL문
	
	@GetMapping("/product")
	public String admin_product() {
		return "/admin/product";
	}
	
	//상품 관리 - 일반상품 상세조회 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능)
    // AJAX 요청을 처리하여 JSON 데이터 반환
    //@GetMapping(value = "/searchProductDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/searchProductDetail")
    @ResponseBody
    public List<AdminProductDTO> searchProductDetail(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword) {
    	System.out.println("Received search request with params: " + category + ", " + keyword);
    	List<AdminProductDTO> filterSearch = adminService.searchProductDetail(category, keyword);
    	System.out.println(filterSearch.size());
        return filterSearch;
    }
	
	@GetMapping("/auction")
	public String admin_auction() {
		return "/admin/auction";
	}
	
	//상품 관리 - 일반상품 상세조회 - 수정 기능 (기능 : 선택된 상품의 판매상태(SELL_STATUS)를 "판매중지"로 업데이트 가능해야 함, 입력된 텍스트는 판매 중지사유(SELL_PRODUCT 테이블 -> PRODUCT_BAN_REASON 컬럼)로 입력(null -> 텍스트) 될 것임)
	
	
	//상품 관리 - 일반상품 상세조회 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	@PostMapping("/deleteProduct")
	@ResponseBody
	public ResponseEntity<String> deleteProduct(
			Integer product_seq
			) {
		System.out.println("Controller" + product_seq);
		try {
			adminService.deleteProduct(product_seq);
			return ResponseEntity.ok("삭제가 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 진행 중 오류가 발생했습니다.");
		}
	}
	
	//상품 관리 - 경매상품 상세조회 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능)
	// AJAX 요청을 처리하여 JSON 데이터 반환
	//@GetMapping(value = "/searchAuctionDetail", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping("/searchAuctionDetail")
	@ResponseBody
	public List<AdminAuctionDTO> searchAuctionDetail(
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String keyword) {
		System.out.println("Received search request with params: " + category + ", " + keyword);
		List<AdminAuctionDTO> filterSearch = adminService.searchAuctionDetail(category, keyword);
		System.out.println(filterSearch.size());
		return filterSearch;
	}
	
	//상품 관리 - 경매상품 상세조회 - 수정 기능 (기능 : 선택된 상품의 판매상태(AUC_STATUS)를 "판매중지"로 업데이트 가능해야 함, 입력된 텍스트는 판매 중지사유(AUC_PRODUCT 테이블 -> AUC_BAN_REASON 컬럼)로 입력(null -> 텍스트) 될 것임)
	
	
	//상품 관리 - 경매상품 상세조회 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	@PostMapping("/deleteAuction")
	@ResponseBody
	public ResponseEntity<String> deleteAuction(
			Integer product_seq
			) {
		System.out.println("Controller" + product_seq);
		try {
			adminService.deleteAuction(product_seq);
			return ResponseEntity.ok("삭제가 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 진행 중 오류가 발생했습니다.");
		}
	}
	
	
	@GetMapping("/category")
	public String admin_category() {
		return "/admin/category";
	}
	
	//상품 관리 - 카테고리 관리 - 조회 기능 (기능 : 키워드로 필터검색 가능) - 특이사항 : 향후 제품단위로 추가 상세분류가 필요하면 기능이 늘어날 수 있음
	// AJAX 요청을 처리하여 JSON 데이터 반환
	//@GetMapping(value = "/searchCategoryInfo", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping("/searchCategoryInfo")
	@ResponseBody
	public List<AdminCategoryDTO> searchCategoryInfo(
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String keyword) {
		System.out.println("Received search request with params: " + category + ", " + keyword);
		List<AdminCategoryDTO> filterSearch = adminService.searchCategoryInfo(category, keyword);
		System.out.println(filterSearch.size());
		return filterSearch;
	}
	
	//상품 관리 - 카테고리 관리 - 신규 등록 기능 (기능 : 수기 입력받은 카테고리와 세부 카테고리를 Insert 함)
	
	
	//상품 관리 - 카테고리 관리 - 수정 기능 (기능 : 선택된 기존의 카테고리 항목의 카테고리와 세부 카테고리를 새로운 내용으로 Update 함)
	
	
	//상품 관리 - 카테고리 관리 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	@PostMapping("/deleteCategory")
	@ResponseBody
	public ResponseEntity<String> deleteCategory(
			Integer product_cate_seq
			) {
		System.out.println("Controller" + product_cate_seq);
		try {
			adminService.deleteCategory(product_cate_seq);
			return ResponseEntity.ok("삭제가 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 진행 중 오류가 발생했습니다.");
		}
	}
	
	//상품 관리 끝
	
	//고객 지원 관련 SQL문
	
	@GetMapping("/qnaManager")
	public String admin_qnaManager() {
		return "/admin/qnaManager";
	}
	
	//고객 지원 - 1:1 상담문의 - 조회 기능 (기능 : 키워드로 필터검색 가능)
	// AJAX 요청을 처리하여 JSON 데이터 반환
	//@GetMapping(value = "/searchQnaAll", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping("/searchQnaAll")
	@ResponseBody
	public List<AdminQnaManagerDTO> searchQnaAll(
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String keyword) {
		System.out.println("Received search request with params: " + category + ", " + keyword);
		List<AdminQnaManagerDTO> filterSearch = adminService.searchQnaAll(category, keyword);
		System.out.println(filterSearch.size());
		return filterSearch;
	}
	
	//고객 지원 - 1:1 상담문의 - 열람/답변 중 열람 기능 (기능 : 선택된 항목의 문의 상세내용 열람 가능)
	// AJAX 요청을 처리하여 JSON 데이터 반환
	@GetMapping("/getQnaContent")
	@ResponseBody
	public ResponseEntity<?> getQnaContent(
			@RequestParam("seq_qna_bno") String bnoStr) {
			
			try {
				int seq_qna_bno = Integer.parseInt(bnoStr);
				List<AdminQnaManagerDTO> detailSearch = adminService.getQnaContent(seq_qna_bno);
				System.out.println("Received search request with params: " + seq_qna_bno);
				System.out.println(detailSearch.size());
				if(detailSearch.size() == 1) {
					System.out.println(detailSearch);
					return ResponseEntity.ok(detailSearch.get(0)); //첫 번째 요소 반환
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No date found for id: " + seq_qna_bno);
				}
			} catch (NumberFormatException e) {
				return ResponseEntity.badRequest().body("Invalid id format: " + bnoStr);
			}
		}
	
	//고객 지원 - 1:1 상담문의 - 열람/답변 중 답변 기능 (기능 : 선택된 항목에 대한 답변내용 작성 및 Update 가능)
	@PostMapping("/updateQnaAns")
	@ResponseBody
	public ResponseEntity<String> updateQnaAns(
			Integer seq_qna_bno,
			String newValue) {
		System.out.println(seq_qna_bno);
		try {
			adminService.updateQnaAns(seq_qna_bno, newValue);
			return ResponseEntity.ok("문의 답변이 성공적으로 업데이트되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("문의 답변 업데이트 중 오류가 발생했습니다.");
		}
	}
	
	//고객 지원 - 1:1 상담문의 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	@PostMapping("/deleteQna")
	@ResponseBody
	public ResponseEntity<String> deleteQna(
			Integer seq_qna_bno
			) {
		System.out.println(seq_qna_bno);
		try {
			adminService.deleteQna(seq_qna_bno);
			return ResponseEntity.ok("삭제가 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 진행 중 오류가 발생했습니다.");
		}
		
	}
	
			
	@GetMapping("/faqManager")
	public String admin_faqManager() {
		return "/admin/faqManager";
	}
	
	//고객 지원 - FAQ 내용관리 - 조회 기능 (기능 : 키워드로 필터검색 가능)
	// AJAX 요청을 처리하여 JSON 데이터 반환
	@GetMapping("/searchFaqAll")
	@ResponseBody
	public List<AdminFaqManagerDTO> searchFaqAll(
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String keyword) {
		System.out.println("Received search request with params: " + category + ", " + keyword);
		List<AdminFaqManagerDTO> filterSearch = adminService.searchFaqAll(category, keyword);
		System.out.println(filterSearch.size());
		return filterSearch;
	}
	
	//고객 지원 - FAQ 내용관리 - 신규 등록 기능 (기능 : 문의유형 관리에 등록되어있는 유형을 select하여 선택된 유형과, 수기 입력받은 FAQ 제목, 내용을 Insert 함)
	
	
	//고객 지원 - FAQ 내용관리 - 열람/수정 중 열람 기능 (기능 : 선택된 항목의 문의 상세내용 열람 가능)
	// AJAX 요청을 처리하여 JSON 데이터 반환
	@GetMapping("/getFaqContent")
	@ResponseBody
	public ResponseEntity<?> getFaqContent(
			@RequestParam("id") String bnoStr) {
			try {
				int id = Integer.parseInt(bnoStr);
				List<AdminFaqManagerDTO> detailSearch = adminService.getFaqContent(id);
				System.out.println("Received search request with params: " + id);
				System.out.println(detailSearch.size());
				if(detailSearch.size() == 1) {
					return ResponseEntity.ok(detailSearch.get(0)); //첫 번째 요소 반환
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No date found for id: " + id);
				}
			
			} catch (NumberFormatException e) {
				return ResponseEntity.badRequest().body("Invalid id format: " + bnoStr);
			}
		}
	
	@GetMapping("/questionManager")
	public String admin_questionManager() {
		return "/admin/questionManager";
	}
	
	//고객 지원 - FAQ 내용관리 - 열람/수정 중 수정 기능 (기능 : 선택된 항목에 대한 세부내용 작성 및 Update 가능)
	
	
	//고객 지원 - FAQ 내용관리 - 열람/수정 중 수정 기능 (기능 : 문의유형을 동적으로 받아와 줌)
	@GetMapping("/getQnaOptions")
	@ResponseBody
	public List<adminQuestionManagerDTO> getQnaOptions(){
		return adminService.getQnaOptions();
	}
	
	//고객 지원 - FAQ 내용관리 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	@PostMapping("/deleteFaq")
	@ResponseBody
	public ResponseEntity<String> deleteFaq(
			Integer seq_faq_bno
			) {
		System.out.println("Controller" + seq_faq_bno);
		try {
			adminService.deleteFaq(seq_faq_bno);
			return ResponseEntity.ok("삭제가 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 진행 중 오류가 발생했습니다.");
		}
	}
	
	//고객 지원 - 문의 유형 관리 - 조회 기능 (기능 : 키워드로 필터검색 가능)
	// AJAX 요청을 처리하여 JSON 데이터 반환
	@GetMapping("/searchQuestionCategory")
	@ResponseBody
	public List<adminQuestionManagerDTO> searchQuestionCategory(
			@RequestParam(required = false) String category, 
			@RequestParam(required = false) String keyword) {
		System.out.println("Received search request with params: " + category + ", " + keyword);
		List<adminQuestionManagerDTO> filterSearch = adminService.searchQuestionCategory(category, keyword);
		System.out.println(filterSearch.size());
		return filterSearch;
	}
	
	//고객 지원 - 문의 유형 관리 - 신규 등록 (기능 : 수기 입력받은 유형과 내용을 Insert 함)
	@PostMapping("/insertQnaOption")
	@ResponseBody
	public ResponseEntity<String> insertQnaOption(
			String qna_option,
			String qna_option_content){
		System.out.println("Controller" + qna_option);
		try {
			adminService.insertQnaOption(qna_option, qna_option_content);
			return ResponseEntity.ok("문의 유형이 성공적으로 등록되었습니다.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("문의 유형 등록 중 오류가 발생했습니다.");
		}
	}
	
	
	
	
	//고객 지원 - 문의 유형 관리 - 수정 기능 (기능 : 수기 입력받은 유형과 내용을 Update 함)
	@PostMapping("/updateQnaOption")
	@ResponseBody
	public ResponseEntity<String> updateQnaOption(
			Integer seq_qna_option,
			String qna_option,
			String qna_option_content) {
		System.out.println(seq_qna_option);
		try {
			adminService.updateQnaOption(seq_qna_option, qna_option, qna_option_content);
			return ResponseEntity.ok("문의 유형이 성공적으로 수정되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("문의 유형 수정 중 오류가 발생했습니다.");
		}
	}
	
	
	//고객 지원 - 문의 유형 관리 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	@PostMapping("/deleteQnaOption")
	@ResponseBody
	public ResponseEntity<String> deleteQnaOption(
			Integer seq_qna_option
			) {
		System.out.println("Controller" + seq_qna_option);
		try {
			adminService.deleteQnaOption(seq_qna_option);
			return ResponseEntity.ok("삭제가 성공적으로 완료되었습니다.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 진행 중 오류가 발생했습니다.");
		}
	}
	//고객 지원 끝
}














