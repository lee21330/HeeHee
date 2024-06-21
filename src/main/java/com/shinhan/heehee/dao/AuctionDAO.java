package com.shinhan.heehee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.request.AuctionHistoryDTO;
import com.shinhan.heehee.dto.response.AuctionProdDTO;
import com.shinhan.heehee.dto.response.AuctionProdInfoDTO;

@Repository
public class AuctionDAO {

	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.auction.";
	
	public List<AuctionProdDTO> aucProdList() {
		return sqlSession.selectList(namespace + "aucProdList");
	}
	
	public List<AuctionProdDTO> aucPriceList(List<Integer> seqArr) {
		return sqlSession.selectList(namespace + "aucPriceList", seqArr);
	}
	
	public int insertAucHistory(AuctionHistoryDTO aucHistory) {
		return sqlSession.insert(namespace + "insertAucHistory", aucHistory);
	}
	
	public AuctionProdInfoDTO aucProdInfo(int aucSeq) {
		return sqlSession.selectOne(namespace + "aucProdInfo", aucSeq);
	}
}
