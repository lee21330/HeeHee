package com.shinhan.heehee.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.heehee.dto.response.AuctionProdDTO;
import com.shinhan.heehee.service.ElasticsearchService;

@RestController
public class SearchController {

	@Autowired
	ElasticsearchService elasticsearchService;
	
	@GetMapping("/search")
	public List<AuctionProdDTO> search(@RequestParam("keyword") String keyword) {
        try {
            return elasticsearchService.search(keyword);
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
