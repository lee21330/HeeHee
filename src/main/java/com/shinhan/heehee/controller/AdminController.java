package com.shinhan.heehee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.heehee.dto.request.AdminProductDTO;
import com.shinhan.heehee.dto.request.AdminUserBanDTO;
import com.shinhan.heehee.dto.request.AdminUserDTO;
import com.shinhan.heehee.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/main")
	public String admin_main() {
		return "/admin/main";
	}
	
	//기본 페이지 로딩
	@GetMapping("/user")
	public String admin_user(Model model) {
		List<AdminUserDTO> users = adminService.searchAllUser(null, null, null, null,null);
		model.addAttribute("users", users);
		return "/admin/user";
	}
	
    // AJAX 요청을 처리하여 JSON 데이터 반환
    @GetMapping(value = "/searchUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AdminUserDTO> searchUsers(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String categoryDate,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
    	System.out.println("Received search request with params: " + category + ", " + categoryDate + ", " + keyword + ", " + startDate + ", " + endDate);
    	List<AdminUserDTO> filterSearch = adminService.searchAllUser(category, categoryDate, keyword, startDate, endDate);
    	System.out.println(filterSearch.size());
        return filterSearch;
        		//adminService.searchAllUser(category, keyword, startDate, endDate)
    }
    
    // AJAX 요청을 처리하여 JSON 데이터 반환
    @GetMapping(value = "/userBan", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AdminUserBanDTO> userBan(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String categoryDate,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
    	System.out.println("Received search request with params: " + category + ", " + categoryDate + ", " + keyword + ", " + startDate + ", " + endDate);
    	List<AdminUserBanDTO> filterSearch = adminService.userBan(category, categoryDate, keyword, startDate, endDate);
    	System.out.println(filterSearch.size());
        return filterSearch;
    }
	
	@GetMapping("/ban")
	public String admin_userVan() {
		return "/admin/user-ban";
	}
	
	@GetMapping("/product")
	public String admin_product() {
		return "/admin/product";
	}
	
    // AJAX 요청을 처리하여 JSON 데이터 반환
    @GetMapping(value = "/searchProductDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AdminProductDTO> searchProductDetail(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword) {
    	System.out.println("Received search request with params: " + category + ", " + keyword);
    	List<AdminProductDTO> filterSearch = adminService.searchProductDetail(category, keyword);
    	System.out.println(filterSearch.size());
        return filterSearch;
    }
	
	@GetMapping("/auction")
	public String admin_auction() {
		return "/admin/auction";
	}
	
	@GetMapping("/category")
	public String admin_category() {
		return "/admin/category";
	}
	
	@GetMapping("/qnaManager")
	public String admin_qnaManager() {
		return "/admin/qnaManager";
	}
	
	@GetMapping("/faqManager")
	public String admin_faqManager() {
		return "/admin/faqManager";
	}
	
	@GetMapping("/questionManager")
	public String admin_questionManager() {
		return "/admin/questionManager";
	}
}