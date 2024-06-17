package com.shinhan.heehee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.AuctionDAO;
import com.shinhan.heehee.dto.response.AuctionProdDTO;

@Service
public class AuctionService {
	
	@Autowired
	AuctionDAO auctionDAO;
	
	public List<AuctionProdDTO> aucProdList() {
		return auctionDAO.aucProdList();
	}
}
