package com.shinhan.heehee.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PutMapping(value="/reserve", produces = "text/plain; charset=UTF-8") 
	@ResponseBody
	public ResponseEntity<Map<String,Object>> reserve(@RequestBody Map<String,Integer> sellMap) {
		Map<String,Object> response = new HashMap<String,Object>();
		int productSeq = sellMap.get("productSeq");
		
		int result = productservice.proStatusReserve(productSeq);
		if(result == 0) {
			response.put("success", false);
			response.put("message", "예약에 실패했습니다.");
		} else {
			response.put("success", true);
			response.put("message", "예약에 성공했습니다.");
		}
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PutMapping(value="/cancelreserve", produces = "text/plain; charset=UTF-8") 
	@ResponseBody
	public ResponseEntity<Map<String,Object>> cancelReserve(@RequestBody Map<String,Integer> sellMap) {
		Map<String,Object> response = new HashMap<String,Object>();
		int productSeq = sellMap.get("productSeq");
		
		int result = productservice.proStatusSelling(productSeq);
		
		if(result == 0) {
			response.put("success", false);
			response.put("message", "예약 취소에 실패했습니다.");
		} else {
			response.put("success", true);
			response.put("message", "예약 취소에 성공했습니다.");
		}
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PutMapping(value="/putoff", produces = "text/plain; charset=UTF-8") 
	@ResponseBody
	public ResponseEntity<Map<String,Object>> toPutOff(@RequestBody Map<String,Integer> sellMap) {
		Map<String,Object> response = new HashMap<String,Object>();
		int productSeq = sellMap.get("productSeq");
		
		int result = productservice.proStatusPutOff(productSeq);
		
		if(result == 0) {
			response.put("success", false);
			response.put("message", "판매 보류에 실패했습니다.");
		} else {
			response.put("success", true);
			response.put("message", "판매 보류에 성공했습니다.");
		}
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PutMapping(value="/delete", produces = "text/plain; charset=UTF-8") 
	@ResponseBody
	public ResponseEntity<Map<String,Object>> toDelete(@RequestBody Map<String,Integer> sellMap) {
		Map<String,Object> response = new HashMap<String,Object>();
		int productSeq = sellMap.get("productSeq");
		
		int result = productservice.proStatusDelete(productSeq);
		
		if(result == 0) {
			response.put("success", false);
			response.put("message", "삭제에 실패했습니다.");
		} else {
			response.put("success", true);
			response.put("message", "삭제에 성공했습니다.");
		}
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
	}
}
