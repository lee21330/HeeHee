package com.shinhan.heehee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shinhan.heehee.service.ProductDetailService;

@Controller
public class ProductDetailController {
	
	@Autowired
	ProductDetailService productservice;
	
	@GetMapping("/productdetail/{prod_seq}")
	public String home(@PathVariable("prod_seq") Integer prodSeq, Model model) {
		model.addAttribute("info", productservice.prodInfo(prodSeq));
		/* model.addAttribute("prodImg",productservice.prodImg(prodSeq)); */
		return "/used/productdetail";
	}
}
