package com.shinhan.heehee.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shinhan.heehee.service.AWSS3Service;


@Controller
public class ImageController {

	@Autowired
	private AWSS3Service s3Service;
	
	@PostMapping("/upload")
	@ResponseBody
	public ArrayList<String> upload(List<MultipartFile> multipartFiles) throws java.io.IOException{
		String filePath ="images/auction/";
		return s3Service.uploadObject(multipartFiles, filePath);
	} 
}
