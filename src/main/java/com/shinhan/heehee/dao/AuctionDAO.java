package com.shinhan.heehee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.request.ImageFileDTO;
import com.shinhan.heehee.dto.request.auction.AuctionHistoryDTO;
import com.shinhan.heehee.dto.request.auction.AuctionInsertDTO;
import com.shinhan.heehee.dto.request.auction.InsertDealHistoryDTO;
import com.shinhan.heehee.dto.response.ElasticSyncDTO;
import com.shinhan.heehee.dto.response.auction.AuctionImgsDTO;
import com.shinhan.heehee.dto.response.auction.AuctionProdDTO;
import com.shinhan.heehee.dto.response.auction.AuctionProdInfoDTO;
import com.shinhan.heehee.dto.response.auction.SchedulerBidDTO;
import com.shinhan.heehee.dto.response.auction.SellerInfoResponseDTO;

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
	
	public List<ElasticSyncDTO> aucProdAll() {
		return sqlSession.selectList(namespace + "aucProdAll");
	}
	
	public List<AuctionImgsDTO> aucProdImgList(int aucSeq) {
		return sqlSession.selectList(namespace + "aucProdImgList", aucSeq);
	}
	
	public SellerInfoResponseDTO sellerInfo(String userId) {
		return sqlSession.selectOne(namespace + "sellerInfo", userId);
	}
	
	public int joinCount(int aucSeq) {
		return sqlSession.selectOne(namespace + "joinCount", aucSeq);
	}
	
	public List<SchedulerBidDTO> bidSuccessList() {
		return sqlSession.selectList(namespace + "bidSuccessList");
	}
	
	public List<SchedulerBidDTO> bidFailList() {
		return sqlSession.selectList(namespace + "bidFailList");
	}
	
	public int updateBidSuccess(List<Integer> seqArr) {
		return sqlSession.update(namespace + "updateBidSuccess", seqArr);
	}
	
	public int updateBidFail(List<Integer> seqArr) {
		return sqlSession.update(namespace + "updateBidFail", seqArr);
	}
	
	public int insertAucProd(AuctionInsertDTO aucInsertDto) {
		return sqlSession.insert(namespace + "insertAucProd", aucInsertDto);
	}
	
	public List<AuctionProdDTO> recommandAucProd(int aucSeq) {
		return sqlSession.selectList(namespace + "recommandAucProd", aucSeq);
	}
	
	public int insertImgFile(ImageFileDTO imageFileDto) {
		return sqlSession.insert(namespace + "insertImgFile", imageFileDto);
	}
	
	public int returnPointToUser(int aucSeq) {
		return sqlSession.update(namespace + "returnPointToUser", aucSeq);
	}
	
	public int deductionPointFromUser(AuctionHistoryDTO aucHistory) {
		return sqlSession.update(namespace + "deductionPointFromUser", aucHistory);
	}
	
	public int remainingPointsFromUser(String userId) {
		return sqlSession.selectOne(namespace + "remainingPointsFromUser", userId);
	}
	
	public int insertDealHistory(InsertDealHistoryDTO insDto) {
		return sqlSession.insert(namespace + "insertDealHistory", insDto);
	}
	
	public List<ElasticSyncDTO> findByKeywordProd(String keyword) {
		return sqlSession.selectList(namespace + "findByKeywordProd", keyword);
	}
	
	public List<ElasticSyncDTO> findByCategoryProd(int cateNum) {
		return sqlSession.selectList(namespace + "findByCategoryProd", cateNum);
	}
	
	public int updateWinningBId(SchedulerBidDTO dto) {
		return sqlSession.update(namespace + "updateWinningBId",dto);
	}
}
