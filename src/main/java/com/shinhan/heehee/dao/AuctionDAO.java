package com.shinhan.heehee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.response.AuctionProdDTO;

@Repository
public class AuctionDAO {

	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.auction.";
	
	public List<AuctionProdDTO> aucProdList() {
		return sqlSession.selectList(namespace + "aucProdList");
	}
}
