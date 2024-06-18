package com.shinhan.heehee.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.codebuild.model.Build;
import com.shinhan.heehee.service.AWSS3Service;
import com.shinhan.heehee.service.AuctionService;

@Controller
public class MainController {
	
	@Autowired
	AuctionService auctionService;
	
	@Autowired
	private AWSS3Service s3Service;
	
	@GetMapping("/main")
	public String main() {
		return "/main/main";
	}
	
	@GetMapping("/auc")
	public String auction(Model model) {
		model.addAttribute("aucList", auctionService.aucProdList());
		return "/main/auction";
	}
	
	@PostMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile multipartFile) throws IOException {
		String filePath ="images/auction/";
		return s3Service.uploadObject(multipartFile, filePath + multipartFile.getOriginalFilename());
	}
}
