package com.shinhan.heehee.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shinhan.heehee.dto.request.RateAdminDTO;
import com.shinhan.heehee.dto.response.CategoryDTO;
import com.shinhan.heehee.dto.response.FaQDTO;
import com.shinhan.heehee.dto.response.InsertDeliveryDTO;
import com.shinhan.heehee.dto.response.InsertQnADTO;
import com.shinhan.heehee.dto.response.JjimDTO;
import com.shinhan.heehee.dto.response.PointListDTO;
import com.shinhan.heehee.dto.response.PurchaseListDTO;
import com.shinhan.heehee.dto.response.QnADTO;
import com.shinhan.heehee.dto.response.QnAImgDTO;
import com.shinhan.heehee.dto.response.SaleListAucDTO;
import com.shinhan.heehee.dto.response.SaleListDTO;
import com.shinhan.heehee.service.AWSS3Service;
import com.shinhan.heehee.service.AlarmService;
import com.shinhan.heehee.service.MainService;
import com.shinhan.heehee.service.MyPageService;
import com.shinhan.heehee.service.ProductDetailService;
import com.shinhan.heehee.service.SellerProfileService;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

	@Autowired
	MyPageService mypageservice;
	@Autowired
	MainService mainservice;
	@Autowired
	AWSS3Service s3Service;
	@Autowired
	ProductDetailService productservice;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	AlarmService alarmService;
	@Autowired
	SellerProfileService sellerprofileservice;

	// 마이페이지
	@GetMapping("/main")
	public String sellerInfo(Principal principal, Model model) {
		String userId = principal.getName();
		model.addAttribute("userId", principal.getName()); // 로그인 유저 아이디

		int alarmCount = alarmService.alarmCount(principal.getName());
		model.addAttribute("alarmCount", alarmCount); // 알림 개수

		List<CategoryDTO> mainCateList = mainservice.mainCateList();
		model.addAttribute("mainCateList", mainCateList); // header 카테고리
		model.addAttribute("sellerInfo", mypageservice.sellerInfo(userId));
		model.addAttribute("dealComplete", sellerprofileservice.dealComplete(userId));
		model.addAttribute("userId", userId);
		return "/mypage/myPage";
	}

	// 마이페이지-찜 삭제하기
	@PostMapping("/main/deleteJjim")
	public String deleteJjim(@RequestParam List<Integer> seq, Principal principal, RedirectAttributes redirectAttr) {
		String userId = principal.getName();
		int result = mypageservice.deleteJjim(seq, userId);
		String message;
		if (result > 0) {
			message = "delete success";
		} else {
			message = "delete fail";
		}
		redirectAttr.addFlashAttribute("result", message);
		return "redirect:/mypage/main";
	}

	// 마이페이지_판매상태에 따른 판매내역 조회(중고)
	@GetMapping("/main/searchSaleStatus")
	@ResponseBody
	public List<SaleListDTO> saleList(Principal principal, @RequestParam("status") String status) {
		String userId = principal.getName();
		return mypageservice.saleList(status, userId);
	}

	// 마이페이지_판매상태에 따른 판매내역 조회(중고)_상품 삭제
	@PostMapping("/main/deleteSale")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> proStatusDelete(@RequestParam("productSeq") int productSeq,
			RedirectAttributes redirectAttr) {
		Map<String, Object> response = new HashMap<String, Object>();
		int result = mypageservice.proStatusDelete(productSeq);
		if (result == 0) {
			response.put("success", false);
			response.put("message", "삭제에 실패했습니다.");
		} else {
			response.put("success", true);
			response.put("message", "삭제에 성공했습니다.");
		}
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
	}

	// 마이페이지_판매상태에 따른 판매내역 조회(중고)_상품상태 변경
	@PostMapping("/main/updateStatus")
	@ResponseBody
	public ResponseEntity<?> updateStatus(int productSeq, String proStatus, HttpServletResponse response) {
		response.setContentType("text/plain;charset=UTF-8");
		return mypageservice.updateStatus(productSeq, proStatus);
	}

	// 마이페이지_판매상태에 따른 판매내역 조회(경매)
	@GetMapping("/main/searchSaleStatusAuc")
	@ResponseBody
	public List<SaleListAucDTO> saleListAuc(Principal principal, @RequestParam("status") String status) {
		String userId = principal.getName();
		return mypageservice.saleListAuc(status, userId);
	}

	// 마이페이지_구매내역 조회(중고)
	@PostMapping("/main/purchaselist")
	@ResponseBody
	public List<PurchaseListDTO> purchaseList(Principal principal) {
		String userId = principal.getName();
		return mypageservice.purchaseList(userId);
	}

	// 마이페이지_구매내역 조회(경매)
	@PostMapping("/main/purchaselistAuc")
	@ResponseBody
	public List<SaleListAucDTO> purchaselistAuc(Principal principal) {
		String userId = principal.getName();
		return mypageservice.purchaselistAuc(userId);
	}

	// 마이페이_찜내역 조회
	@PostMapping("/main/jjimlist")
	@ResponseBody
	public List<JjimDTO> jjimList(Principal principal) {
		String userId = principal.getName();
		return mypageservice.jjimList(userId);
	}

	// 마이페이지 - 판매 상품 상세페이지
	@GetMapping("/saledetail/{productSeq}")
	public String saleDetail(@PathVariable("productSeq") int proSeq, Model model, Principal principal) {
		model.addAttribute("saleDetail", mypageservice.saleDetail(proSeq));
		model.addAttribute("dcOption", mypageservice.dcOption());

		int alarmCount = alarmService.alarmCount(principal.getName());
		model.addAttribute("alarmCount", alarmCount); // 알림 개수

		List<CategoryDTO> mainCateList = mainservice.mainCateList();
		model.addAttribute("mainCateList", mainCateList); // header 카테고리
		String userId = principal.getName();
		model.addAttribute("userId", userId);
		return "/mypage/saleDetail";
	}

	// 마이페이지 - 판매 상품 상세페이지_송장입력 모달
	@PostMapping("/saledetail/{productSeq}/insertDelivery")
	public String insertDelivery(InsertDeliveryDTO delivery, String buyerId, RedirectAttributes redirectAttr) {
		delivery.setBuyerId(buyerId);
		int result = mypageservice.insertDelivery(delivery);
		String message;
		if (result > 0) {
			message = "insert success";
		} else {
			message = "insert fail";
		}
		redirectAttr.addFlashAttribute("result", message);
		return "redirect:/mypage/saledetail/{productSeq}";
	}

	// 마이페이지 - 판매 상품 상세페이지:거래완료 버튼
	@PostMapping("/saledetail/updateSCheck")
	@ResponseBody
	public ResponseEntity<?> updateSCheck(int proSeq, HttpServletResponse response) {
		response.setContentType("text/plain;charset=UTF-8");
		return mypageservice.updateSCheck(proSeq);
	}

	// 마이페이지 - 판매 상품 상세페이지:거래완료 버튼(경매)
	@PostMapping("/saledetail/updateSCheckAuc")
	@ResponseBody
	public ResponseEntity<?> updateSCheckAuc(int proSeq, HttpServletResponse response) {
		response.setContentType("text/plain;charset=UTF-8");

		return mypageservice.updateSCheckAuc(proSeq);

	}

	// 마이페이지 - 판매 상품 상세페이지(경매)
	@GetMapping("/saledetailAuc/{productSeq}")
	public String saledetailAuc(@PathVariable("productSeq") int proSeq, Model model, Principal principal) {
		model.addAttribute("saleDetail", mypageservice.saledetailAuc(proSeq));
		model.addAttribute("dcOption", mypageservice.dcOption());

		int alarmCount = alarmService.alarmCount(principal.getName());
		model.addAttribute("alarmCount", alarmCount); // 알림 개수

		List<CategoryDTO> mainCateList = mainservice.mainCateList();
		model.addAttribute("mainCateList", mainCateList); // header 카테고리
		String userId = principal.getName();
		model.addAttribute("userId", userId);
		return "/mypage/saleDetailAuc";
	}

	// 마이페이지 - 구매 상품 상세페이지
	@GetMapping("/purchasedetail/{productSeq}")
	public String purchasedetail(@PathVariable("productSeq") int proSeq, Model model, Principal principal) {
		model.addAttribute("saleDetail", mypageservice.saleDetail(proSeq));
		model.addAttribute("userId", principal.getName()); // 로그인 유저 아이디

		int alarmCount = alarmService.alarmCount(principal.getName());
		model.addAttribute("alarmCount", alarmCount); // 알림 개수

		List<CategoryDTO> mainCateList = mainservice.mainCateList();
		model.addAttribute("mainCateList", mainCateList); // header 카테고리
		String userId = principal.getName();
		model.addAttribute("userId", userId);
		return "/mypage/purchaseDetail";
	}

	// 마이페이지 - 구매 상품 상세페이지(경매)
	@GetMapping("/purchasedetailAuc/{productSeq}")
	public String purchasedetailAuc(@PathVariable("productSeq") int proSeq, Model model, Principal principal) {
		String userId = principal.getName();
		model.addAttribute("saleDetail", mypageservice.saledetailAuc(proSeq));
		model.addAttribute("userId", userId); // 로그인 유저 아이디

		int alarmCount = alarmService.alarmCount(principal.getName());
		model.addAttribute("alarmCount", alarmCount); // 알림 개수

		List<CategoryDTO> mainCateList = mainservice.mainCateList();
		model.addAttribute("mainCateList", mainCateList); // header 카테고리

		return "/mypage/purchaseDetailAuc";
	}

	// 마이페이지 - 구매 상품 상세페이지:거래완료 버튼
	@PostMapping("/purchasedetail/updatePCheck")
	@ResponseBody
	public ResponseEntity<?> updatePCheck(int proSeq, HttpServletResponse response) {
		response.setContentType("text/plain;charset=UTF-8");

		return mypageservice.updatePCheck(proSeq);
	}

	// 마이페이지 - 구매 상품 상세페이지:거래완료 버튼(경매)
	@PostMapping("/purchasedetail/updatePCheckAuc")
	@ResponseBody
	public ResponseEntity<?> updatePCheckAuc(int proSeq, HttpServletResponse response) {
		response.setContentType("text/plain;charset=UTF-8");

		return mypageservice.updatePCheckAuc(proSeq);
	}

	// 마이페이지-프로필 수정 페이지 이동
	@GetMapping("/profile")
	public String profile(Principal principal, Model model) {
		String userId = principal.getName();
		model.addAttribute("profile", mypageservice.profile(userId));
		model.addAttribute("bankList", mypageservice.bankList());
		model.addAttribute("userId", principal.getName()); // 로그인 유저 아이디

		int alarmCount = alarmService.alarmCount(principal.getName());
		model.addAttribute("alarmCount", alarmCount); // 알림 개수

		List<CategoryDTO> mainCateList = mainservice.mainCateList();
		model.addAttribute("mainCateList", mainCateList); // header 카테고리
		return "/mypage/editProfile";
	}

	// 마이페이지_프로필 수정 페이지:프로필 수정
	@PostMapping("/profile/updateProfile")
	public String editProfile(@RequestParam("profileImg") MultipartFile profileImages,
			@RequestParam("originalProfileImg") String originalProfileImg, @RequestParam("nickName") String nickName,
			@RequestParam("userIntroduce") String userIntroduce, Principal principal, RedirectAttributes redirectAttr) {
		String userId = principal.getName();
		String image = "";
		try {
			if (profileImages != null && !profileImages.isEmpty()) {
				// AWS S3에 이미지 업로드
				image = s3Service.uploadOneObject(profileImages, "images/mypage/");
			} else if (originalProfileImg != null) {
				image = originalProfileImg;
			}

			// 데이터베이스에 프로필 정보 저장
			mypageservice.editProfile(nickName, userIntroduce, image, userId);

		} catch (IOException e) {
			// 업로드 실패 시 처리할 로직
			e.printStackTrace();

		}
		return "redirect:/mypage/profile";

	}

	// 마이페이지_프로필 수정 페이지:계좌 수정
	@PostMapping("/profile/updateAcc")
	public String updateAcc(Integer bankSeq, String accountNum, Principal principal, RedirectAttributes redirectAttr) {
		String userId = principal.getName();
		mypageservice.updateAcc(userId, bankSeq, accountNum);
		return "redirect:/mypage/profile";

	}

	// 마이페이지_프로필 수정 페이지:아이디 중복체크
	@GetMapping("/profile/dupNickCheck")
	@ResponseBody
	public Map<String, Object> dupNickCheck(@RequestParam String nickName) {
		return mypageservice.dupNickCheck(nickName);
	}

	// 마이페이지_프로필 수정 페이지: 전화번호 수정
	@PostMapping("/profile/updatePhone")
	@ResponseBody
	public ResponseEntity<?> updatePhone(Principal principal, String phoneNum, HttpServletResponse response) {
		String userId = principal.getName();
		response.setContentType("text/plain;charset=UTF-8");
		return mypageservice.updatePhone(userId, phoneNum);
	}

	// 마이페이지_프로필 수정 페이지: 주소 수정
	@PostMapping("/profile/updateAddress")
	@ResponseBody
	public ResponseEntity<?> updateAddress(Principal principal, String address, String detailAddress,
			HttpServletResponse response) {
		String userId = principal.getName();
		response.setContentType("text/plain;charset=UTF-8");

		return mypageservice.updateAddress(userId, address, detailAddress);
	}

	// 마이페이지_프로필 수정 페이지:비밀번호 수정
	@PutMapping("/profile/updatePw")
	@ResponseBody
	public Map<String, Object> updatePw(Principal principal, @RequestBody Map<String, String> passwordData) {
		String userId = principal.getName();
		String currentPassword = passwordData.get("currentPassword");
		String password = passwordData.get("password");
		String confirmPassword = passwordData.get("confirmPassword");
		return mypageservice.updatePw(userId, currentPassword, password, confirmPassword);
	}

	// 마이페이지_프로필 수정 페이지:회원 탈퇴
	@DeleteMapping("/profile/deleteUser")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteUser(Principal principal) {
		Map<String, Object> response = new HashMap<String, Object>();
		String userId = principal.getName();
		int result = mypageservice.deleteUser(userId);

		if (result > 0) {
			response.put("success", true);
		} else {
			response.put("success", false);
		}
		return ResponseEntity.ok(response);
	}

	// 마이페이지-QnA
	@GetMapping("/qnaBoard")
	public String qnaBoard(Principal principal, Model model) {
		String userId = principal.getName();
		model.addAttribute("userId", principal.getName()); // 로그인 유저 아이디

		int alarmCount = alarmService.alarmCount(principal.getName());
		model.addAttribute("alarmCount", alarmCount); // 알림 개수

		List<CategoryDTO> mainCateList = mainservice.mainCateList();
		model.addAttribute("mainCateList", mainCateList); // header 카테고리

		model.addAttribute("qnaOption", mypageservice.qnaOption());
		model.addAttribute("faq", mypageservice.faqOption(0));

		// 나의 QNA 가져오기
		List<QnADTO> myQnaList = mypageservice.myQna(userId);

		// 각 QNA에 대해 이미지 정보 가져오기
		for (QnADTO qna : myQnaList) {
			List<QnAImgDTO> imgList = mypageservice.myQnaImg(userId, qna.getSeqQnaBno());
			qna.setImgList(imgList);
		}

		// 모델에 추가
		model.addAttribute("myQna", myQnaList);
		return "/mypage/qnaBoard";
	}

	// 마이페이지-QnA-나의 문의 삭제하기
	@GetMapping("/qnaBoard/deleteQna")
	public String deleteQna(Integer seqQnaBno, Principal principal, RedirectAttributes redirectAttr) {
		String userId = principal.getName();
		int result = mypageservice.deleteQna(seqQnaBno);
		List<QnAImgDTO> imgList = mypageservice.myQnaImg(userId, seqQnaBno);
		// 이미지가 있으면 이미지 삭제
		if (imgList != null && !imgList.isEmpty()) {
			mypageservice.deleteQnaImg(seqQnaBno);
		}
		String message;
		if (result > 0) {
			message = "delete success";
		} else {
			message = "delete fail";
		}
		redirectAttr.addFlashAttribute("result", message);
		return "redirect:/mypage/qnaBoard";
	}

	@PostMapping("/qnaBoard/insertQna")
	public String insertQna(@RequestParam("uploadImgs") List<MultipartFile> uploadImgs, InsertQnADTO qna,
			Principal principal, RedirectAttributes redirectAttr, Model model) throws IOException {
		String userId = principal.getName();
		model.addAttribute("userId", userId);
		qna.setId(userId);
		System.out.println(qna);
		System.out.println(uploadImgs);
		mypageservice.insertQna(qna, uploadImgs);

		return "redirect:/mypage/qnaBoard";
	}

	// 마이페이지-FAQ
	@GetMapping("/faqBoard")
	public String faqBoard(Model model, Principal principal) {
		model.addAttribute("userId", principal.getName()); // 로그인 유저 아이디

		int alarmCount = alarmService.alarmCount(principal.getName());
		model.addAttribute("alarmCount", alarmCount); // 알림 개수

		List<CategoryDTO> mainCateList = mainservice.mainCateList();
		model.addAttribute("mainCateList", mainCateList); // header 카테고리

		model.addAttribute("qnaOption", mypageservice.qnaOption());
		model.addAttribute("faq", mypageservice.faqOption(0));
		return "/mypage/faqBoard";
	}

	// 마이페이지-FAQ_문의유형에 따른 FAQ조회
	@GetMapping("/faqBoard/faqOption")
	@ResponseBody
	public List<FaQDTO> faqOption(int option) {
		System.out.println("----------------");
		System.out.println(option);
		return mypageservice.faqOption(option);
	}

	// 포인트 내역 페이지
	@GetMapping("/pointlist")
	public String pointlist(Principal principal, Model model) {
		model.addAttribute("userId", principal.getName()); // 로그인 유저 아이디

		int alarmCount = alarmService.alarmCount(principal.getName());
		model.addAttribute("alarmCount", alarmCount); // 알림 개수

		List<CategoryDTO> mainCateList = mainservice.mainCateList();
		model.addAttribute("mainCateList", mainCateList); // header 카테고리

		String userId = principal.getName();
		model.addAttribute("userId", userId);

		model.addAttribute("sellerInfo", mypageservice.sellerInfo(userId));

		return "/mypage/pointList";
	}

	// 포인트 내역 페이지: 월별조회
	@GetMapping(value = "/pointlist/searchPoint", produces = "application/json")
	public @ResponseBody List<PointListDTO> searchPoint(Principal principal, @RequestParam String month) {
		String userId = principal.getName();
		String[] parts = month.split("-");
		String year = parts[0];
		String monthPart = parts[1];
		List<PointListDTO> result = mypageservice.searchPoint(userId, year, monthPart);
		System.out.println("============");
		System.out.println(result);
		return result;
	}

	// 포인트 충전
	@PutMapping("/chargePoint")
	@ResponseBody
	public void chargePoint(Principal principal, @RequestBody Map<String, Integer> map) {
		String userId = principal.getName();
		Integer newPoint = map.get("point") + map.get("userPoint");

		mypageservice.chargePoint(userId, newPoint);
	}

	// 평점 매기기
	@PostMapping("/rating")
	@ResponseBody
	@Transactional
	public Map<String, Object> userRating(RateAdminDTO rateDTO, Model model, Principal principal) throws IOException {
		Map<String, Object> response = new HashMap<String, Object>();

		String userId = principal.getName();
		rateDTO.setUserId(userId);
		int result = mypageservice.rating(rateDTO);
		mypageservice.updateRate(rateDTO);

		// response.put("success", true);
		// return response;

		// 성공 실패 분류 로직 구현

		if (result == 1) {
			response.put("success", true);
			return response;
		} else {
			return response;
		}
	}

}
