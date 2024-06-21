package com.shinhan.heehee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.AuctionDAO;
import com.shinhan.heehee.dto.request.AuctionHistoryDTO;
import com.shinhan.heehee.dto.response.AuctionProdDTO;
import com.shinhan.heehee.dto.response.AuctionProdInfoDTO;

@Service
public class AuctionService {
	
	@Autowired
	AuctionDAO auctionDAO;
	
	public List<AuctionProdDTO> aucProdList() {
		return auctionDAO.aucProdList();
	}
	
	public List<AuctionProdDTO> aucPriceList(List<Integer> seqArr) {
		return auctionDAO.aucPriceList(seqArr);
	}
	
	public void insertAucHistory(AuctionHistoryDTO aucHistory) {
		int result = auctionDAO.insertAucHistory(aucHistory);
	}
	
	public AuctionProdInfoDTO aucProdInfo(int aucSeq) {
		return auctionDAO.aucProdInfo(aucSeq);
	}
}
