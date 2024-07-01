package com.shinhan.heehee.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shinhan.heehee.dto.response.CategoryDTO;
import com.shinhan.heehee.dto.response.FaQDTO;
import com.shinhan.heehee.dto.response.InsertDeliveryDTO;
import com.shinhan.heehee.dto.response.JjimDTO;
import com.shinhan.heehee.dto.response.PurchaseListDTO;
import com.shinhan.heehee.dto.response.QnADTO;
import com.shinhan.heehee.dto.response.QnAImgDTO;
import com.shinhan.heehee.dto.response.SaleListAucDTO;
import com.shinhan.heehee.dto.response.SaleListDTO;
import com.shinhan.heehee.service.AWSS3Service;
import com.shinhan.heehee.service.MainService;
import com.shinhan.heehee.service.MyPageService;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

	@Autowired
	MyPageService mypageservice;
	@Autowired
	MainService mainservice;
	@Autowired
	private AWSS3Service s3Service;

	// 마이페이지
	@GetMapping("/main")
	public String sellerInfo(Principal principal, Model model) {
		String userId = principal.getName();
		List<CategoryDTO> mainCateList = mainservice.mainCateList(); // 카테고리 서비스 호출
		model.addAttribute("mainCateList", mainCateList);
		model.addAttribute("sellerInfo", mypageservice.sellerInfo(userId));
		return "/mypage/myPage";
	}

	// 마이페이지-찜 삭제하기
	@PostMapping("/main/deleteJjim")
	public String deleteJjim(@RequestParam List<Integer> seq, Principal principal, RedirectAttributes redirectAttr) {
		String userId = principal.getName();
		System.out.println("=======================");
		System.out.println(seq);
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
	@GetMapping("/main/deleteSale")
	@ResponseBody
	public String deleteSale(@PathVariable("productSeq") int proSeq, RedirectAttributes redirectAttr) {
		int result = 0;
		String message;
		if (result > 0) {
			message = "insert success";
		} else {
			message = "insert fail";
		}
		redirectAttr.addFlashAttribute("result", message);
		return "redirect:/mypage/main";
	}

	// 마이페이지_판매상태에 따른 판매내역 조회(경매)
	@GetMapping("/main/searchSaleStatusAuc")
	@ResponseBody
	public List<SaleListAucDTO> saleListAuc(Principal principal, @RequestParam("status") String status) {
		String userId = principal.getName();
		System.out.println("=======================");
		System.out.println(userId);
		System.out.println(status);
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
	public List<PurchaseListDTO> purchaselistAuc(Principal principal) {
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
	public String saleDetail(@PathVariable("productSeq") int proSeq, Model model) {
		model.addAttribute("saleDetail", mypageservice.saleDetail(proSeq));
		model.addAttribute("dcOption", mypageservice.dcOption());
		return "/mypage/saleDetail";
	}

	// 마이페이지 - 판매 상품 상세페이지_송장입력 모달
	@PostMapping("/saledetail/{productSeq}/insertDelivery")
	public String insertDelivery(InsertDeliveryDTO delivery, RedirectAttributes redirectAttr) {
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

	// 마이페이지 - 판매 상품 상세페이지_update deal_history(거래완료 버튼)
	@PostMapping("/saledetail/{productSeq}/updateSCheck")
	public String updateSCheck(@RequestParam("proSeq") int proSeq, RedirectAttributes redirectAttr) {
		int result = mypageservice.updateSCheck(proSeq);
		String message;
		if (result > 0) {
			message = "insert success";
		} else {
			message = "insert fail";
		}
		redirectAttr.addFlashAttribute("result", message);
		return "redirect:/mypage/saledetail/{productSeq}";
	}

	// 마이페이지 - 구매 상품 상세페이지
	@GetMapping("/purchasedetail/{productSeq}")
	public String purchasedetail(@PathVariable("productSeq") int proSeq, Model model) {
		model.addAttribute("saleDetail", mypageservice.saleDetail(proSeq));
		return "/mypage/purchaseDetail";
	}

	// 마이페이지_소개글 수정
	@PostMapping("/userIntroUpdate")
	public String updateUserIntro(@RequestParam("intro") String intro, Principal principal,
			RedirectAttributes redirectAttr) {
		String userId = principal.getName();
		int result = mypageservice.userIntroduce(intro, userId);
		String message;
		if (result > 0) {
			message = "update success";
		} else {
			message = "update fail";
		}
		redirectAttr.addFlashAttribute("result", message);
		return "redirect:/mypage/main";
	}

	// 마이페이지-프로필 수정 페이지 이동
	@GetMapping("/profile")
	public String profile(Principal principal, Model model) {
		String userId = principal.getName();
		model.addAttribute("profile", mypageservice.profile(userId));
		model.addAttribute("bankList", mypageservice.bankList());
		return "/mypage/editProfile";
	}

//	// 마이페이지_프로필 수정
//	@PostMapping("/profile/updateProfile")
//	public String editProfile(EditProfileDTO profile, Principal principal, HttpServletResponse response, RedirectAttributes redirectAttr) {
//		String userId = principal.getName();
//		profile.setId(userId);
//		int result = mypageservice.editProfile(profile);
//		String message;
//		if (result > 0) {
//			message = "update success";
//		} else {
//			message = "update fail";
//		}
//		
//		response.setContentType("text/html;charset=UTF-8");
//		if (!userDto.getPassword().equals(null)) {
//			
//			// BCryptPasswordEncoder 생성
//			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encodedPassword = passwordEncoder.encode(userDto.getPassword());
//			userDto.setPassword(encodedPassword);
//		}
//		
//	redirectAttr.addFlashAttribute("result", message);
//		return "redirect:/mypage";
//	}

	// 마이페이지-프로필 수정 페이지_프로필 사진 수정 /editProfile/updateProfileImg

	// 마이페이지-QnA
	@GetMapping("/qnaBoard")
	public String qnaBoard(Principal principal, Model model) {
		String userId = principal.getName();
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

//	// 마이페이지_QnA 1:1문의하기
//	@PostMapping("/qnaBoard/insertQna")
//	public String insertQna(@RequestParam("uploadImgs") List<MultipartFile> uploadImgs, InsertQnADTO qna,
//			InsertQnAImgDTO qnaImg, Principal principal, RedirectAttributes redirectAttr) {
//		String userId = principal.getName();
//		qna.setId(userId);
//		qnaImg.setTablePk(qna.getSeqQnaBno());
//		qnaImg.setId(userId);
//		
//		if (uploadImgs != null && !uploadImgs.isEmpty()) {
//			for (MultipartFile img : uploadImgs) {
//				try {
//					String imgName = s3Service.uploadOneObject(img, "images/mypage/qnaboard/");
//					qnaImg.setImgName(imgName);
//					mypageservice.insertQnaImg(qnaImg);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		int result = mypageservice.insertQna(qna);
//
//		String message;
//		if (result > 0) {
//			message = "insert success";
//		} else {
//			message = "insert fail";
//		}
//		redirectAttr.addFlashAttribute("result", message);
//		return "redirect:/mypage/qnaBoard";
//	}

	// 마이페이지-FAQ
	@GetMapping("/faqBoard")
	public String faqBoard(Model model) {
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

	// 포인트 내역
	@GetMapping("/pointlist")
	public String pointlist(Principal principal, Model model) {
		String userId = principal.getName();
//		model.addAttribute("point", mypageservice.searchPoint(userId));
		return "/mypage/pointList";
	}

	// 회원 탈퇴
	@PostMapping("/userWithdrawal")
	public String withdrawal() {
		return "/main";
	}

}
