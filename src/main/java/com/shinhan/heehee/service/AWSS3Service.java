package com.shinhan.heehee.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class AWSS3Service {
	
	@Autowired
	private AmazonS3 s3Client;
	
	private String bucketName = "sh-heehee-bucket";
	
	public ArrayList<String> uploadObject(List<MultipartFile> multipartFiles, String filePath) throws IOException {
		
		ArrayList<String> saveUrls = new ArrayList<String>();
		
		for(MultipartFile file: multipartFiles) {
			ObjectMetadata metadata = new ObjectMetadata();
	        metadata.setContentLength(file.getSize());
	        metadata.setContentType(file.getContentType());
	        metadata.setContentDisposition("inline");
	        s3Client.putObject(new PutObjectRequest(bucketName, filePath + file.getOriginalFilename(), file.getInputStream(), metadata));
	        saveUrls.add(s3Client.getUrl(bucketName, filePath + file.getOriginalFilename()).toString());
		}
		
		return saveUrls;
	}

}
