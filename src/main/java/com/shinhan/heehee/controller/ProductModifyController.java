package com.shinhan.heehee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shinhan.heehee.service.ProductDetailService;
import com.shinhan.heehee.service.ProductModifyService;

@Controller
public class ProductModifyController {
	
	@Autowired
	ProductModifyService productmodifyservice;
	
	@GetMapping("/productmodify/{prod_seq}")
	public String home(@PathVariable("prod_seq") Integer prodSeq, Model model) {
		model.addAttribute("userId","dhfl123");
		model.addAttribute("info", productmodifyservice.prodInfo(prodSeq));
		model.addAttribute("prodImgList",productmodifyservice.prodImg(prodSeq));
		return "/used/productmodify";
	}
}
