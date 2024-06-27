package com.shinhan.heehee.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shinhan.heehee.dto.request.ProductModifyRequestDTO;
import com.shinhan.heehee.dto.response.ProductCategoryDTO;
import com.shinhan.heehee.service.ProductDetailService;
import com.shinhan.heehee.service.ProductModifyService;
import com.shinhan.heehee.service.SellerProfileService;
import com.shinhan.heehee.service.TestService;

@Controller
@RequestMapping("/sell")
public class ProductController {
	
	@Autowired
	ProductDetailService productservice;
	
	@Autowired
	ProductModifyService productmodifyservice;
	
	@Autowired
	SellerProfileService sellerprofileservice;
	
	@GetMapping("/productdetail/{prod_seq}")
	public String detail(@PathVariable("prod_seq") Integer prodSeq, Model model) {
		model.addAttribute("userId","dhfl123");
		model.addAttribute("info", productservice.prodInfo(prodSeq));
		model.addAttribute("prodImgList",productservice.prodImg(prodSeq));
		model.addAttribute("prodRecoList",productservice.prodReco(prodSeq));
		return "/used/productdetail";
	}
	
	@GetMapping("/productmodify/{prod_seq}")
	public String modify(@PathVariable("prod_seq") Integer prodSeq, Model model) {
		model.addAttribute("userId","dhfl123");
		
		model.addAttribute("info", productmodifyservice.prodInfo(prodSeq));
		
		model.addAttribute("prodImgList", productmodifyservice.prodImg(prodSeq));
		
		model.addAttribute("categoryList", productmodifyservice.category(prodSeq));
		return "/used/productmodify";
	}
	
	@GetMapping("/sellerProfile/{id}")
	public String home(@PathVariable("id") String id, Model model) {
		model.addAttribute("sellerinfo", sellerprofileservice.sellerinfo(id));
		model.addAttribute("sellerprodList", sellerprofileservice.sellerprod(id));
		return "/used/sellerProfile";
	}
	
	@GetMapping("/detailCate")
	@ResponseBody
	public List<ProductCategoryDTO> detailCate(String category) {
		return productmodifyservice.detailCategory(category);
	}
	
	@PostMapping("/productModify")
	public String prodModify(@RequestParam("uploadImgs") List<MultipartFile> uploadImgs
							,ProductModifyRequestDTO modiDTO) throws IOException {
		modiDTO.setUploadFiles(uploadImgs);
		productservice.prodModify(modiDTO);
		return "redirect:/sell/productdetail/" + modiDTO.getProdSeq();
	}
	
}
