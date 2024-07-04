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

import com.shinhan.heehee.dto.request.JjimDTO;
import com.shinhan.heehee.dto.request.ProductModifyRequestDTO;
import com.shinhan.heehee.dto.request.RecentlyDTO;
import com.shinhan.heehee.dto.request.ProductDetailRequestDTO;
import com.shinhan.heehee.dto.request.ViewLogDTO;
import com.shinhan.heehee.dto.response.CategoryDTO;
import com.shinhan.heehee.dto.response.ProdDetailDTO;
import com.shinhan.heehee.dto.response.ProductCategoryDTO;
import com.shinhan.heehee.exception.ProductNotFoundException;
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
		String userId = (principal != null) ? principal.getName() : "admin";
		
		List<CategoryDTO> mainCateList = mainservice.mainCateList(); // 카테고리 서비스 호출
		model.addAttribute("mainCateList", mainCateList);
		
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("PROD_SEQ", prodSeq);
//		map.put("userId", userId);
		
		ProductDetailRequestDTO sampleDTO = new ProductDetailRequestDTO(prodSeq, userId);
		
		ProdDetailDTO prodInfo = productservice.prodInfo(sampleDTO);

		if(prodInfo == null) throw new ProductNotFoundException();
		model.addAttribute("userId", userId);
		
		model.addAttribute("info", prodInfo);
		model.addAttribute("prodImgList",productservice.prodImg(prodSeq));
		model.addAttribute("prodRecoList",productservice.prodReco(prodSeq));
		model.addAttribute("recentlyList",productservice.selectRecently(userId));
		
		
		ViewLogDTO viewLogDTO = new ViewLogDTO(prodSeq, userId);
		
		productservice.insertViewLog(viewLogDTO); // 해당 페이지 들어가면 view_log 테이블에 자동으로 삽입
		
		return "/used/productdetail";
	}
	
	// 수정하기 페이지
	@GetMapping("/productmodify/{prod_seq}")
	public String modify(@PathVariable("prod_seq") Integer prodSeq, Model model, Principal principal) {
		String userId = (principal != null) ? principal.getName() : "admin";
		
		if(principal == null) throw new ProductNotFoundException();
		
		ProductDetailRequestDTO sampleDTO = new ProductDetailRequestDTO(prodSeq, userId);
		
		List<CategoryDTO> mainCateList = mainservice.mainCateList(); // 카테고리 서비스 호출
		model.addAttribute("mainCateList", mainCateList);

		
		
		/* if(prodInfo == null) return "/"; */
		model.addAttribute("userId", userId);
		
		model.addAttribute("info", productmodifyservice.prodInfo(sampleDTO));
		
		model.addAttribute("prodImgList", productmodifyservice.prodImg(prodSeq));
		
		model.addAttribute("categoryList", productmodifyservice.category());
		return "/used/productmodify";
	}
	// 수정하는거
	@PostMapping("/productModify")
	public String prodModify(@RequestParam("uploadImgs") List<MultipartFile> uploadImgs
							,ProductModifyRequestDTO modiDTO, String userId, Model model) throws IOException {
		List<CategoryDTO> mainCateList = mainservice.mainCateList(); // 카테고리 서비스 호출
		model.addAttribute("mainCateList", mainCateList);
		
		modiDTO.setUploadFiles(uploadImgs);
		productservice.prodModify(modiDTO);
		return "redirect:/sell/productdetail/" + modiDTO.getProdSeq();
	}
	
	// 등록하기 페이지
	@GetMapping("/productregi")
	public String registry(Model model, Principal principal) {
		if(principal == null) throw new ProductNotFoundException();
		
		String userId = principal.getName();
		
		List<CategoryDTO> mainCateList = mainservice.mainCateList(); // 카테고리 서비스 호출
		model.addAttribute("mainCateList", mainCateList);

		/* if(prodInfo == null) return "/"; */
		model.addAttribute("userId", userId);
		
		model.addAttribute("categoryList", productmodifyservice.category());
		/* return "/used/productregi"; */
		return "/used/productregi";
	}
	// 등록하는거
	@PostMapping("/productRegistry")
	public String prodRegistry(@RequestParam("uploadImgs") List<MultipartFile> uploadImgs
							,ProductModifyRequestDTO regiDTO, Model model, Principal principal) throws IOException {
		List<CategoryDTO> mainCateList = mainservice.mainCateList(); // 카테고리 서비스 호출
		model.addAttribute("mainCateList", mainCateList);
		
		
		String userId = principal.getName();
		
		model.addAttribute("userId", userId);
		
		regiDTO.setUploadFiles(uploadImgs);
		productservice.prodRegistry(regiDTO, userId);
		
		return "redirect:/mypage/main";
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
	
	@PostMapping(value = "/insertJjim", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> addJjim(@RequestBody JjimDTO jjimDto, Principal principal) {
	    Map<String, Object> response = new HashMap<>();

	    int result = productservice.insertJjim(jjimDto);

	    if (result == 0) {
	        response.put("success", false);
	        response.put("message", "찜에 실패했습니다.");
	    } else {
	        response.put("success", true);
	        response.put("message", "찜하였습니다.");
	    }
	    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@DeleteMapping(value = "/deleteJjim", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteJjim(@RequestBody JjimDTO jjimDto, Principal principal) {
	    Map<String, Object> response = new HashMap<>();

	    int result = productservice.deleteJjim(jjimDto);

	    if (result == 0) {
	        response.put("success", false);
	        response.put("message", "찜 삭제에 실패했습니다.");
	    } else {
	        response.put("success", true);
	        response.put("message", "찜을 삭제하였습니다.");
	    }
	    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping(value = "/LatestJjimCnt", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> latestJjim(@RequestParam("productSeq") int productSeq, Principal principal) {
	    Map<String, Object> response = new HashMap<>();

	    JjimDTO jjimDto = new JjimDTO();
	    jjimDto.setProductSeq(productSeq);

	    // 찜 개수 조회
	    int jjimCount = productservice.selectJjim(jjimDto);

	    // 찜 개수가 0이면 찜이 없는 것으로 간주
	    if (jjimCount < 0) {
	        response.put("success", false);
	        response.put("message", "조회에 실패했습니다.");
	        response.put("jjimCnt", jjimCount);  // 찜 개수를 응답에 포함
	    } else {
	        response.put("success", true);
	        response.put("message", "조회에 성공했습니다.");
	        response.put("jjimCnt", jjimCount);  // 찜 개수를 응답에 포함
	    }

	    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
	}



}
