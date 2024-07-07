package com.shinhan.heehee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.shinhan.heehee.dto.request.AdminAuctionDTO;
import com.shinhan.heehee.dto.request.AdminCategoryDTO;
import com.shinhan.heehee.dto.request.AdminFaqManagerDTO;
import com.shinhan.heehee.dto.request.AdminMainDTO;
import com.shinhan.heehee.dto.request.AdminProductDTO;
import com.shinhan.heehee.dto.request.AdminQnaManagerDTO;
import com.shinhan.heehee.dto.request.AdminUserBanDTO;
import com.shinhan.heehee.dto.request.AdminUserDTO;
import com.shinhan.heehee.dto.request.adminQuestionManagerDTO;
import com.shinhan.heehee.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	String id = "";

	@Autowired
	private AdminService adminService;

	// 관리자 홈 관련 SQL문

	@GetMapping("/main")
	public String admin_main() {
		return "/admin/main";
	}

	//전체 주문통계 조회 (기능: 전체 주문관련 모니터링용 대시보드 항목 조회)
	//전체 주문현황
	@GetMapping("/searchTotalOrder")
	@ResponseBody
	public List<AdminMainDTO> searchTotalOrder() {
		List<AdminMainDTO> selectSearch = adminService.searchTotalOrder();
		return selectSearch;
	}
	
	//일반상품 주문현황
	@GetMapping("/searchProStatus")
	@ResponseBody
	public List<AdminMainDTO> searchProStatus() {
		List<AdminMainDTO> selectSearch = adminService.searchProStatus();
		return selectSearch;
	}
	
	//경매상품 주문현황
	@GetMapping("/searchAucStatus")
	@ResponseBody
	public List<AdminMainDTO> searchAucStatus() {
		List<AdminMainDTO> selectSearch = adminService.searchAucStatus();
		return selectSearch;
	}
	
	
	
	// 최근 주문내역 조회 (기능: 최근 주문관련 모니터링용 대시보드 항목 조회)
	@GetMapping("/searchRecentProduct")
	@ResponseBody
	public List<AdminProductDTO> searchRecentProduct() {
		List<AdminProductDTO> selectSearch = adminService.searchRecentProduct();
		return selectSearch;
	}
	
	// 최근 문의내역 조회 (기능: 최근 문의관련 모니터링용 대시보드 항목 조회)
	@GetMapping("/searchRecentQuestion")
	@ResponseBody
	public List<AdminQnaManagerDTO> searchRecentQuestion() {
		List<AdminQnaManagerDTO> selectSearch = adminService.searchRecentQuestion();
		return selectSearch;
	}
	
	//최근 회원가입 조회 (기능: 최근 가입회원 모니터링용 대시보드 항목 조회)
	@GetMapping("/searchRecentJoin")
	@ResponseBody
	public List<AdminUserDTO> searchRecentJoin(){
		List<AdminUserDTO> selectSearch = adminService.searchRecentJoin();
		return selectSearch;
	}
	
	// 관리자 홈 끝
	
	// 회원정보 관리 관련 SQL문

	@GetMapping("/user")
	public String admin_user(Model model) {
		return "/admin/user";
	}

	// 회원정보 관리 - 회원정보 관리 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능)
	@GetMapping("/searchAllUser")
	@ResponseBody
	public List<AdminUserDTO> searchAllUser(@RequestParam(required = false) String category,
			@RequestParam(required = false) String categoryDate, @RequestParam(required = false) String keyword,
			@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
		System.out.println("Received search request with params: " + category + ", " + categoryDate + ", " + keyword
				+ ", " + startDate + ", " + endDate);
		List<AdminUserDTO> filterSearch = adminService.searchAllUser(category, categoryDate, keyword, startDate,
				endDate);
		System.out.println(filterSearch.size());
		return filterSearch;
	}

	@GetMapping("/ban")
	public String admin_userVan() {
		return "/admin/user-ban";
	}

	// 회원정보 관리 - 이용상태 관리 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능, 수정사항 : 시작일,종료일이 null 이거나 현재
	// 날짜가 시작일과 종료일 사이에 포함되지 않는 회원은 조회되지 않도록 수정 예정)
	@GetMapping("/userBanSearch")
	@ResponseBody
	public List<AdminUserBanDTO> userBanSearch(@RequestParam(required = false) String category,
			@RequestParam(required = false) String categoryDate, @RequestParam(required = false) String keyword,
			@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
		System.out.println("Received search request with params: " + category + ", " + categoryDate + ", " + keyword
				+ ", " + startDate + ", " + endDate);
		List<AdminUserBanDTO> filterSearch = adminService.userBanSearch(category, categoryDate, keyword, startDate,
				endDate);
		System.out.println("++++++++++++++++++++++++++++++ filterSearch: " + filterSearch);
		return filterSearch;
	}

	// 회원정보 관리 - 이용상태 관리 - 신규 등록 기능 (기능 : 정지사유, 정지 시작일, 종료일 신규입력, 정지하고자 하는 회원의 id를
	// 입력 후 정지사유와 종료일을 입력하도록 구성한다. 시작일은 sysdate로 받아오면 좋을듯)
	@PostMapping("/insertBanUser")
	@ResponseBody
	public ResponseEntity<String> insertBanUser(String id, String banContent, Date banStr, Date banEnd){
		System.out.println("Controller id : " + id);
		System.out.println("Controller banContent : " + banContent);
		System.out.println("Controller banStr : " + banStr);
		System.out.println("Controller banEnd : " + banEnd);
		try {
			adminService.insertBanUser(id, banContent, banStr, banEnd);
			return ResponseEntity.ok("정지 등록에 성공하였습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("정지 등록 진행 중 오류가 발생했습니다.");
		}
	}
	
	// 회원정보 관리 - 이용상태 관리 - 삭제 기능 (기능 : 선택된 회원의 정지기록을 삭제함)
	@PostMapping("/deleteUserBan")
	@ResponseBody
	public ResponseEntity<String> deleteUserBan(
			@RequestParam("id") String id, 
			@RequestParam("banStr") String banStr) {
		System.out.println("Controller id : " + id);
		System.out.println("Controller banStr : " + banStr);
		try {
            adminService.deleteUserBan(id, banStr);
            
            System.out.println("service로 전달되어야 하는 값 : id: " + id + " banStr: " + banStr);
			return ResponseEntity.ok("삭제가 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 진행 중 오류가 발생했습니다.");
		}
	}
	
	/* 기능 변경 : 수정기능 삭제 후 삭제기능 추가
	// 회원정보 관리 - 이용상태 관리 - 수정 기능 (기능 : 정지하고자 하는 회원의 정지일을 변경할 수 있도록 구성 예정)
	@PostMapping("/updateBanUser")
	@ResponseBody
	public ResponseEntity<String> updateBanUser (String id, String banContent, Date banStr, Date banEnd){
		System.out.println("Controller id : " + id);
		System.out.println("Controller banContent : " + banContent);
		System.out.println("Controller banStr : " + banStr);
		System.out.println("Controller banEnd : " + banEnd);
		try {
			adminService.updateBanUser(id, banContent, banStr, banEnd);
			return ResponseEntity.ok("정지상태 수정에 성공하였습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("정지상태 수정 중 오류가 발생했습니다.");
		}
	}
	 */
	
	// 회원정보 끝

	// 상품 관리 관련 SQL문

	@GetMapping("/product")
	public String admin_product() {
		return "/admin/product";
	}

	// 상품 관리 - 일반상품 상세조회 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능)
	@GetMapping("/searchProductDetail")
	@ResponseBody
	public List<AdminProductDTO> searchProductDetail(@RequestParam(required = false) String category,
			@RequestParam(required = false) String keyword) {
		System.out.println("Received search request with params: " + category + ", " + keyword);
		List<AdminProductDTO> filterSearch = adminService.searchProductDetail(category, keyword);
		System.out.println(filterSearch.size());
		return filterSearch;
	}

	// 상품 관리 - 일반상품 상세조회 - 수정(정지사유 조회) 기능 (기능 : 관리자가 선택된 게시글의 판매중지 사유를 열람 가능)
	@GetMapping("/getProductBanReason")
	@ResponseBody
	public AdminProductDTO getProductBanReason(Integer productSeq) {
		System.out.println("Received search request with params: " + productSeq);
		AdminProductDTO detailSearch = adminService.getProductBanReason(productSeq);
		return detailSearch;
	}

	// 상품 관리 - 일반상품 상세조회 - 수정 기능 (기능 : 선택된 상품의 판매상태(SELL_STATUS)를 "판매중지"로 업데이트 가능해야
	// 함, 입력된 텍스트는 판매 중지사유(SELL_PRODUCT 테이블 -> PRODUCT_BAN_REASON 컬럼)로 입력(null ->
	// 텍스트) 될 것임)
	@PostMapping("/updateProductStatus")
	@ResponseBody
	public ResponseEntity<String> updateProductStatus(Integer productSeq, String proStatus, String productBanReason, String id) {
		System.out.println("Controller" + productSeq);
		try {
			adminService.updateProductStatus(productSeq, proStatus, productBanReason, id);
			return ResponseEntity.ok("수정 등록에 성공하였습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정 등록 중 오류가 발생했습니다.");
		}
	}

	// 상품 관리 - 일반상품 상세조회 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	@PostMapping("/deleteProduct")
	@ResponseBody
	public ResponseEntity<String> deleteProduct(Integer productSeq) {
		System.out.println("Controller" + productSeq);
		try {
			adminService.deleteProduct(productSeq);
			return ResponseEntity.ok("삭제가 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 진행 중 오류가 발생했습니다.");
		}
	}

	@GetMapping("/auction")
	public String admin_auction() {
		return "/admin/auction";
	}

	// 상품 관리 - 경매상품 상세조회 - 조회 기능 (기능 : 키워드, 날짜로 필터검색 가능)
	@GetMapping("/searchAuctionDetail")
	@ResponseBody
	public List<AdminAuctionDTO> searchAuctionDetail(@RequestParam(required = false) String category,
			@RequestParam(required = false) String keyword) {
		System.out.println("Received search request with params: " + category + ", " + keyword);
		List<AdminAuctionDTO> filterSearch = adminService.searchAuctionDetail(category, keyword);
		System.out.println(filterSearch.size());
		return filterSearch;
	}

	// 상품 관리 - 경매상품 상세조회 - 수정(정지사유 조회) 기능 (기능 : 관리자가 선택된 게시글의 판매중지 사유를 열람 가능)
	@GetMapping("/getAucBanReason")
	@ResponseBody
	public AdminAuctionDTO getAucBanReason(Integer productSeq) {
		System.out.println("Received search request with params: " + productSeq);
		AdminAuctionDTO detailSearch = adminService.getAucBanReason(productSeq);
		return detailSearch;
	}

	// 상품 관리 - 경매상품 상세조회 - 수정 기능 (기능 : 선택된 상품의 판매상태(AUC_STATUS)를 "판매중지"로 업데이트 가능해야
	// 함, 입력된 텍스트는 판매 중지사유(AUC_PRODUCT 테이블 -> AUC_BAN_REASON 컬럼)로 입력(null -> 텍스트) 될
	// 것임)
	@PostMapping("/updateAucStatus")
	@ResponseBody
	public ResponseEntity<String> updateAucStatus(Integer productSeq, String aucStatus, String aucBanReason, String id) {
		System.out.println("Controller" + productSeq);
		try {
			adminService.updateAucStatus(productSeq, aucStatus, aucBanReason, id);
			return ResponseEntity.ok("수정 등록에 성공하였습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정 등록 중 오류가 발생했습니다.");
		}
	}

	// 상품 관리 - 경매상품 상세조회 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	@PostMapping("/deleteAuction")
	@ResponseBody
	public ResponseEntity<String> deleteAuction(Integer productSeq) {
		System.out.println("Controller" + productSeq);
		try {
			adminService.deleteAuction(productSeq);
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

	// 상품 관리 - 카테고리 관리 - 조회 기능 (기능 : 키워드로 필터검색 가능) - 특이사항 : 향후 제품단위로 추가 상세분류가 필요하면
	// 기능이 늘어날 수 있음
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

	// 상품 관리 - 카테고리 관리 - 신규 등록 기능 (기능 : 수기 입력받은 카테고리와 세부 카테고리를 Insert 함)
	@PostMapping("/insertCategory")
	@ResponseBody
	public ResponseEntity<String> insertCategory(String category, String detailCategory, Principal principal) {
		String id = principal.getName();
		System.out.println("Controller category : " + category);
		System.out.println("Controller id : " + id);
		try {
			adminService.insertCategory(category, detailCategory, id);
			return ResponseEntity.ok("신규 등록이 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("신규 등록 진행에 실패하였습니다.");
		}
	}

	// 상품 관리 - 카테고리 관리 - 수정 기능 (기능 : 선택된 기존의 카테고리 항목의 카테고리와 세부 카테고리를 새로운 내용으로 Update
	// 함)
	@PostMapping("/updateCategory")
	@ResponseBody
	public ResponseEntity<String> updateCategory(Integer productCateSeq, String category, String detailCategory, Principal principal) {
		String id = principal.getName();
		System.out.println("Controller productCateSeq : " + productCateSeq);
		System.out.println("Controller category : " + category);
		System.out.println("Controller detailCategory : " + detailCategory);
		System.out.println("Controller id : " + id);
		try {
			adminService.updateCategory(productCateSeq, category, detailCategory, id);
			return ResponseEntity.ok("수정사항이 성공적으로 업데이트되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정사항 업데이트 중 오류가 발생했습니다.");
		}

	}

	// 상품 관리 - 카테고리 관리 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	@PostMapping("/deleteCategory")
	@ResponseBody
	public ResponseEntity<String> deleteCategory(Integer productCateSeq) {
		System.out.println("Controller" + productCateSeq);
		try {
			adminService.deleteCategory(productCateSeq);
			return ResponseEntity.ok("삭제가 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 진행 중 오류가 발생했습니다.");
		}
	}

	/*
	 * 데이터 한번에 넣기용
	 * 
	 * @GetMapping("/dbset") public void dbset() { List<AdminCategoryDTO> all =
	 * adminService.searchCategoryInfo(null, null); for(AdminCategoryDTO part :all)
	 * { adminService.dbset(part.getProductCateSeq()); } }
	 */

	// 상품 관리 끝

	// 고객 지원 관련 SQL문

	@GetMapping("/qnaManager")
	public String admin_qnaManager() {
		return "/admin/qnaManager";
	}

	// 고객 지원 - 1:1 상담문의 - 조회 기능 (기능 : 키워드로 필터검색 가능)
	// @GetMapping(value = "/searchQnaAll", produces =
	// MediaType.APPLICATION_JSON_VALUE)
	@GetMapping("/searchQnaAll")
	@ResponseBody
	public List<AdminQnaManagerDTO> searchQnaAll(@RequestParam(required = false) String category,
			@RequestParam(required = false) String keyword) {
		System.out.println("Received search request with params: " + category + ", " + keyword);
		List<AdminQnaManagerDTO> filterSearch = adminService.searchQnaAll(category, keyword);
		System.out.println(filterSearch.size());
		return filterSearch;
	}

	//고객 지원 - 1:1 상담문의 - 열람/답변 중 열람 기능 (기능 : 선택된 항목의 문의 상세내용 열람 가능)
	@GetMapping("/getQnaContent")
	@ResponseBody
	public ResponseEntity<?> getQnaContent(@RequestParam("seqQnaBno") String bnoStr) {
		try {
			int seqQnaBno = Integer.parseInt(bnoStr);
			List<AdminQnaManagerDTO> detailSearch = adminService.getQnaContent(seqQnaBno);
			System.out.println("Received search request with params: " + seqQnaBno);
			System.out.println(detailSearch.size());
			if (detailSearch.size() == 1) {
				System.out.println(detailSearch);
				return ResponseEntity.ok(detailSearch.get(0)); // 첫 번째 요소 반환
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No date found for id: " + seqQnaBno);
			}
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().body("Invalid id format: " + bnoStr);
		}
	}
	
	//고객 지원 - 1:1 상담문의 - 열람/답변 중 열람 기능 이미지 불러오기 (기능 : 선택된 항목의 문의 상세내용 열람 가능 - 이미지 불러오기 기능)
	@GetMapping("/getQnaImage")
	@ResponseBody
	public ResponseEntity<List<AdminQnaManagerDTO>> getQnaImage(@RequestParam("seqQnaBno") Integer seqQnaBno) {
		List<AdminQnaManagerDTO> images = adminService.getQnaImage(seqQnaBno);
		return ResponseEntity.ok(images);
	}
	
	// 고객 지원 - 1:1 상담문의 - 열람/답변 중 답변 기능 (기능 : 선택된 항목에 대한 답변내용 작성 및 Update 가능)
	@PostMapping("/updateQnaAns")
	@ResponseBody
	public ResponseEntity<String> updateQnaAns(Integer seqQnaBno, String newValue, String id) {
		System.out.println("Controller seqQnaBno : " + seqQnaBno);
		System.out.println("Controller newValue : " + newValue);
		System.out.println("Controller id : " + id);
		try {
			adminService.updateQnaAns(seqQnaBno, newValue, id);
			return ResponseEntity.ok("문의 답변이 성공적으로 업데이트되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("문의 답변 업데이트 중 오류가 발생했습니다.");
		}
	}

	// 고객 지원 - 1:1 상담문의 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	@PostMapping("/deleteQnaContent")
	@ResponseBody
	public ResponseEntity<String> deleteQnaContent(Integer seqQnaBno) {
		System.out.println("Controller seqQnaBno : " + seqQnaBno);
		try {
			adminService.deleteQnaContent(seqQnaBno);
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

	// 고객 지원 - FAQ 내용관리 - 조회 기능 (기능 : 키워드로 필터검색 가능)
	@GetMapping("/searchFaqAll")
	@ResponseBody
	public List<AdminFaqManagerDTO> searchFaqAll(@RequestParam(required = false) String category,
			@RequestParam(required = false) String keyword) {
		System.out.println("Received search request with params: " + category + ", " + keyword);
		List<AdminFaqManagerDTO> filterSearch = adminService.searchFaqAll(category, keyword);
		System.out.println(filterSearch.size());
		return filterSearch;
	}

	// 고객 지원 - FAQ 내용관리 - 신규 등록 기능 (기능 : 문의유형 관리에 등록되어있는 유형을 select하여 선택된 유형과, 수기
	// 입력받은 FAQ 제목, 내용을 Insert 함)
	@PostMapping("/insertFaq")
	@ResponseBody
	public ResponseEntity<String> insertFaq(int seqQnaOption, String faqContent, String faqAns, Principal principal){
		String id = principal.getName();
		System.out.println("Controller seqQnaOption : " + seqQnaOption);
		System.out.println("Controller faqContent : " + faqContent);
		System.out.println("Controller faqAns : " + faqAns);
		System.out.println("Controller principal : " + principal);
		try {
			adminService.insertFaq(seqQnaOption, faqContent, faqAns, id);
			return ResponseEntity.ok("문의 유형이 성공적으로 등록되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("문의 유형 등록 중 오류가 발생했습니다.");
		}
	}
	

	// 고객 지원 - FAQ 내용관리 - 열람/수정 중 열람 기능 (기능 : 선택된 항목의 문의 상세내용 열람 가능)
	@GetMapping("/getFaqContent")
	@ResponseBody
	public ResponseEntity<?> getFaqContent(@RequestParam("id") String bnoStr) {
		try {
			int id = Integer.parseInt(bnoStr);
			List<AdminFaqManagerDTO> detailSearch = adminService.getFaqContent(id);
			System.out.println("Received search request with params: " + id);
			System.out.println(detailSearch.size());
			if (detailSearch.size() == 1) {
				return ResponseEntity.ok(detailSearch.get(0)); // 첫 번째 요소 반환
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No date found for id: " + id);
			}

		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().body("Invalid id format: " + bnoStr);
		}
	}

	// 고객 지원 - FAQ 내용관리 - 열람/수정 중 수정 기능 (기능 : 선택된 항목에 대한 세부내용 작성 및 Update 가능)
	@PostMapping("/updateFaq")
	@ResponseBody
	public ResponseEntity<String> updateFaq(Integer seqFaqBno, Integer seqQnaOption, String faqContent, String faqAns, Principal principal) {
		String id = principal.getName();
		System.out.println("Controller seqFaqBno : " + seqFaqBno);
		System.out.println("Controller seqQnaOption : " + seqQnaOption);
		System.out.println("Controller faqContent : " + faqContent);
		System.out.println("Controller faqAns : " + faqAns);
		System.out.println("Controller principal : " + principal);
		try {
			adminService.updateFaq(seqFaqBno, seqQnaOption, faqContent, faqAns, id);
			return ResponseEntity.ok("업데이트에 성공하였습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트 중 오류가 발생했습니다.");
		}
	}

	// 고객 지원 - FAQ 내용관리 - 열람/수정 중 수정(문의유형 선택) 기능 (기능 : 문의유형을 동적으로 받아와 줌)
	@GetMapping("/getQnaOptions")
	@ResponseBody
	public List<adminQuestionManagerDTO> getQnaOptions() {
		return adminService.getQnaOptions();
	}

	// 고객 지원 - FAQ 내용관리 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	@PostMapping("/deleteFaq")
	@ResponseBody
	public ResponseEntity<String> deleteFaq(Integer seqFaqBno) {
		System.out.println("Controller" + seqFaqBno);
		try {
			adminService.deleteFaq(seqFaqBno);
			return ResponseEntity.ok("삭제가 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 진행 중 오류가 발생했습니다.");
		}
	}

	@GetMapping("/questionManager")
	public String admin_questionManager() {
		return "/admin/questionManager";
	}

	// 고객 지원 - 문의 유형 관리 - 조회 기능 (기능 : 키워드로 필터검색 가능)
	@GetMapping("/searchQuestionCategory")
	@ResponseBody
	public List<adminQuestionManagerDTO> searchQuestionCategory(@RequestParam(required = false) String category,
			@RequestParam(required = false) String keyword) {
		System.out.println("Received search request with params: " + category + ", " + keyword);
		List<adminQuestionManagerDTO> filterSearch = adminService.searchQuestionCategory(category, keyword);
		System.out.println(filterSearch.size());
		return filterSearch;
	}

	// 고객 지원 - 문의 유형 관리 - 신규 등록 (기능 : 수기 입력받은 유형과 내용을 Insert 함)
	@PostMapping("/insertQnaOption")
	@ResponseBody
	public ResponseEntity<String> insertQnaOption(String qnaOption, String qnaOptionContent, Principal principal) {
		String id = principal.getName();
		System.out.println("Controller" + qnaOption);
		try {
			adminService.insertQnaOption(qnaOption, qnaOptionContent, id);
			return ResponseEntity.ok("문의 유형이 성공적으로 등록되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("문의 유형 등록 중 오류가 발생했습니다.");
		}
	}

	// 고객 지원 - 문의 유형 관리 - 수정 기능 (기능 : 수기 입력받은 유형과 내용을 Update 함)
	@PostMapping("/updateQnaOption")
	@ResponseBody
	public ResponseEntity<String> updateQnaOption(Integer seqQnaOption, String qnaOption, String qnaOptionContent, Principal principal) {
		String id = principal.getName();
		System.out.println(seqQnaOption);
		try {
			adminService.updateQnaOption(seqQnaOption, qnaOption, qnaOptionContent, id);
			return ResponseEntity.ok("문의 유형이 성공적으로 수정되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("문의 유형 수정 중 오류가 발생했습니다.");
		}
	}

	// 고객 지원 - 문의 유형 관리 - 삭제 기능 (기능 : 선택된 항목의 데이터 삭제)
	@PostMapping("/deleteQnaOption")
	@ResponseBody
	public ResponseEntity<String> deleteQnaOption(Integer seqQnaOption) {
		System.out.println("Controller" + seqQnaOption);
		try {
			adminService.deleteQnaOption(seqQnaOption);
			return ResponseEntity.ok("삭제가 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 진행 중 오류가 발생했습니다.");
		}
	}
	// 고객 지원 끝
}