package com.shinhan.heehee.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shinhan.heehee.dto.response.EditProfileDTO;
import com.shinhan.heehee.dto.response.FaQDTO;
import com.shinhan.heehee.dto.response.InsertQnADTO;
import com.shinhan.heehee.dto.response.InsertQnAImgDTO;
import com.shinhan.heehee.dto.response.JjimDTO;
import com.shinhan.heehee.dto.response.PurchaseListDTO;
import com.shinhan.heehee.dto.response.QnADTO;
import com.shinhan.heehee.dto.response.QnAImgDTO;
import com.shinhan.heehee.dto.response.SaleListDTO;
import com.shinhan.heehee.service.MyPageService;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

	@Autowired
	MyPageService mypageservice;

	// 마이페이지
	@GetMapping("/main")
	public String sellerInfo(Principal principal, Model model) {
		String userId = principal.getName();
		model.addAttribute("sellerInfo", mypageservice.sellerInfo(userId));
		return "/mypage/myPage";
	}

	// 마이페이지_판매상태에 따른 판매내역 조회
	@GetMapping("/main/searchSaleStatus")
	@ResponseBody
	public List<SaleListDTO> saleList(Principal principal, @RequestParam("status") String status) {
		String userId = principal.getName();
		return mypageservice.saleList(status, userId);
	}

	
	// 마이페이지_구매내역 조회
	@PostMapping("/main/purchaselist")
	@ResponseBody
	public List<PurchaseListDTO> purchaseList(Principal principal) {
		String userId = principal.getName();
		return mypageservice.purchaseList(userId);
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
		System.out.println(proSeq);
		model.addAttribute("saleDetail", mypageservice.saleDetail(proSeq));
		model.addAttribute("dcOption", mypageservice.dcOption());
		return "/mypage/saleDetail";
	}

	// 마이페이지 - 구매 상품 상세페이지
	@GetMapping("/purchasedetail/{productSeq}")
	public String purchasedetail(@PathVariable("productSeq") int proSeq, Model model) {
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
	
	//마이페이지-프로필 수정 페이지_프로필 사진 수정 /editProfile/updateProfileImg

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
	public String deleteQna(Integer seqQnaBno,RedirectAttributes redirectAttr) {
		System.out.println(seqQnaBno);
		int result = mypageservice.deleteQna(seqQnaBno);
		//이미지가 있으면 이미지 삭제
		mypageservice.deleteQnaImg(seqQnaBno);
		String message;
		if (result > 0) {
			message = "delete success";
		} else {
			message = "delete fail";
		}
		redirectAttr.addFlashAttribute("result", message);
		return "redirect:/mypage/qnaBoard";
	}

	// 마이페이지_QnA 1:1문의하기
	@PostMapping("/qnaBoard/insertQna")
	public String insertQna(InsertQnADTO qna,InsertQnAImgDTO qnaImg, Principal principal, RedirectAttributes redirectAttr) {
		String userId = principal.getName();
		qna.setID(userId);
		//이미지 업로드 3장으로 제한
		qnaImg.setImgName("qna_test.jpg");
		qnaImg.setTablePk(qna.getSeqQnaBno());
		qnaImg.setID(userId);
		System.out.println("==============================");
		System.out.println(qna);
		System.out.println(qnaImg);
		int result = mypageservice.insertQna(qna);
		int imageResult = mypageservice.insertQnaImg(qnaImg);
		
		String message;
		if (result > 0) {
			message = "update success";
		} else {
			message = "update fail";
		}
		redirectAttr.addFlashAttribute("result", message);
		return "redirect:/qnaBoard";
	}

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

	@GetMapping("/pointlist")
	public String pointlist() {
		return "/mypage/pointList";
	}


	// 회원 탈퇴
	@PostMapping("/userWithdrawal")
	public String withdrawal() {
		return "/main";
	}

}
