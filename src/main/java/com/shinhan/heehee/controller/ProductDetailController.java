package com.shinhan.heehee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shinhan.heehee.dto.response.SellProDTO;
import com.shinhan.heehee.service.ProductDetailService;
import com.shinhan.heehee.service.TestService;

@Controller
public class ProductDetailController {
	
	@Autowired
	ProductDetailService productservice;
	
	@GetMapping("/productdetail/{prod_seq}")
	public String home(@PathVariable("prod_seq") Integer pordSeq, Model model) {
		
		SellProDTO info = productservice.prodInfo(pordSeq);
		model.addAttribute(info);
		System.out.println(pordSeq);
		System.out.println(productservice.prodInfo(pordSeq));
		return "/used/productdetail";
	}
	
}
