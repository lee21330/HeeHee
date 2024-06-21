package com.shinhan.heehee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shinhan.heehee.dto.response.SaleListDTO;
import com.shinhan.heehee.service.MyPageService;

@Controller


public class MyPageController {

	@Autowired
	MyPageService mypageservice;

	@GetMapping("/myPage/{user_id}")
	public String searchSaleList(@PathVariable("user_id") String userId, Model model) {
		model.addAttribute("pInfo", mypageservice.purchaseList(userId));
		model.addAttribute("jInfo", mypageservice.jjimList(userId));
		model.addAttribute("sellerInfo", mypageservice.sellerInfo(userId));
		return "/mypage/myPage";
	}
	
    @GetMapping("/myPage/{user_id}/searchSaleStatus")
	@ResponseBody
	public List<SaleListDTO> selectList(
			@PathVariable("user_id") String userId, 
			@RequestParam("status") String status) {
    	return mypageservice.saleList(status, userId);
	}
    @GetMapping("/saledetail/{productSeq}")
	public String saleDetail(@PathVariable("productSeq") int proSeq, Model model) {
		return "/mypage/saleDetail";
	}

	@GetMapping("/purchasedetail/{productSeq}")
	public String purchasedetail(@PathVariable("productSeq") int proSeq, Model model) {
		return "/mypage/purchaseDetail";
	}

    @GetMapping("/userIntroUpdate")
    public String updateUserIntro(String intro, RedirectAttributes redirectAttr) {
    	int result = mypageservice.userIntroduce(intro);
    	String message;
    	if(result>0) {
    		message = "update sucess";
    	}else {
    		message = "update fail";
    	}
    	redirectAttr.addFlashAttribute("result", message);
    	return "redirect:/myPage/{user_id}";
    }
    
    
	@GetMapping("/qnaBoard")
	public String qnaBoard() {
		return "/mypage/qnaBoard";
	}

	@GetMapping("/faqBoard")
	public String faqBoard() {
		return "/mypage/faqBoard";
	}

	
	
	
	@GetMapping("/profile")
	public String editProfile() {
		return "/mypage/editProfile";
	}

	@GetMapping("/pointlist")
	public String pointlist() {
		return "/mypage/pointList";
	}


	@GetMapping("/saledetail")
	public String saledetail() {
		return "/mypage/saleDetail";
	}


}
