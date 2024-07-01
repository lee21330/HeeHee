package com.shinhan.heehee.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.shinhan.heehee.dto.request.ProductModifyRequestDTO;
import com.shinhan.heehee.dto.request.ViewLogDTO;
import com.shinhan.heehee.dto.response.CategoryDTO;
import com.shinhan.heehee.dto.response.ProdDetailDTO;
import com.shinhan.heehee.dto.response.ProductCategoryDTO;
import com.shinhan.heehee.service.MainService;
import com.shinhan.heehee.service.ProductDetailService;
import com.shinhan.heehee.service.ProductModifyService;
import com.shinhan.heehee.service.SellerProfileService;

@Controller
@RequestMapping("/sell")
public class ProductController {
	
	@Autowired
	ProductDetailService productservice;
	
	@Autowired
	ProductModifyService productmodifyservice;
	
	@Autowired
	SellerProfileService sellerprofileservice;
	
	@Autowired
	MainService mainservice;
	
	@GetMapping("/productdetail/{prod_seq}")
	public String detail(@PathVariable("prod_seq") Integer prodSeq, Model model, Principal principal) {
		List<CategoryDTO> mainCateList = mainservice.mainCateList(); // 카테고리 서비스 호출
		model.addAttribute("mainCateList", mainCateList);
		
		ProdDetailDTO prodInfo = productservice.prodInfo(prodSeq);
		if(prodInfo == null) return "/";
		String userId = (principal != null) ? principal.getName() : "admin";
		model.addAttribute("userId", userId);
		
		model.addAttribute("info", prodInfo);
		model.addAttribute("prodImgList",productservice.prodImg(prodSeq));
		model.addAttribute("prodRecoList",productservice.prodReco(prodSeq));
		
		ViewLogDTO viewLogDTO = new ViewLogDTO(prodSeq, userId);
		
		productservice.insertViewLog(viewLogDTO);
		productservice.proStatusSelling(prodSeq); // 판매중으로 바꾸는 코드
		
		return "/used/productdetail";
	}
	
	@GetMapping("/productmodify/{prod_seq}")
	public String modify(@PathVariable("prod_seq") Integer prodSeq, Model model, Principal principal) {
		List<CategoryDTO> mainCateList = mainservice.mainCateList(); // 카테고리 서비스 호출
		model.addAttribute("mainCateList", mainCateList);
		
		ProdDetailDTO prodInfo = productservice.prodInfo(prodSeq);
		if(prodInfo == null) return "/";
		String userId = (principal != null) ? principal.getName() : "admin";
		model.addAttribute("userId", userId);
		
		model.addAttribute("info", productmodifyservice.prodInfo(prodSeq));
		
		model.addAttribute("prodImgList", productmodifyservice.prodImg(prodSeq));
		
		model.addAttribute("categoryList", productmodifyservice.category(prodSeq));
		return "/used/productmodify";
	}
	
	@GetMapping("/sellerProfile/{id}")
	public String home(@PathVariable("id") String id, Model model) {
		List<CategoryDTO> mainCateList = mainservice.mainCateList(); // 카테고리 서비스 호출
		model.addAttribute("mainCateList", mainCateList);
		
		model.addAttribute("sellerinfo", sellerprofileservice.sellerinfo(id));
		model.addAttribute("sellerprodList", sellerprofileservice.sellerprod(id));
		model.addAttribute("dealComplete", sellerprofileservice.dealComplete(id));
		return "/used/sellerProfile";
	}
	
	@GetMapping("/detailCate")
	@ResponseBody
	public List<ProductCategoryDTO> detailCate(String category) {
		return productmodifyservice.detailCategory(category);
	}
	
	@PostMapping("/productModify")
	public String prodModify(@RequestParam("uploadImgs") List<MultipartFile> uploadImgs
							,ProductModifyRequestDTO modiDTO, Model model) throws IOException {
		List<CategoryDTO> mainCateList = mainservice.mainCateList(); // 카테고리 서비스 호출
		model.addAttribute("mainCateList", mainCateList);
		
		modiDTO.setUploadFiles(uploadImgs);
		productservice.prodModify(modiDTO);
		return "redirect:/sell/productdetail/" + modiDTO.getProdSeq();
	}
	
}
