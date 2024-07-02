

package com.shinhan.heehee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.request.ImageFileDTO;
import com.shinhan.heehee.dto.request.JjimDTO;
import com.shinhan.heehee.dto.request.ProductDetailRequestDTO;
import com.shinhan.heehee.dto.request.ProductModifyRequestDTO;
import com.shinhan.heehee.dto.request.ViewLogDTO;
import com.shinhan.heehee.dto.response.ProdDetailDTO;
import com.shinhan.heehee.dto.response.ProdDetailImgDTO;
import com.shinhan.heehee.dto.response.ProdDetailRecoDTO;

@Repository
public class ProductDetailDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.productDetail.";
	
	public ProdDetailDTO productInfo(ProductDetailRequestDTO sampleDTO) {
		return sqlSession.selectOne(namespace + "productInfo", sampleDTO);
	}
	
	public List<ProdDetailImgDTO> productImg(Integer prodSeq) {
		return sqlSession.selectList(namespace + "productImg", prodSeq);
	}
	
	/*
	 * public SellProDTO userIntroduce(Integer prodSeq) { return
	 * sqlSession.selectOne(namespace + "userIntroduce", prodSeq); }
	 */

	public List<ProdDetailRecoDTO> prodReco(Integer prodSeq) {
		return sqlSession.selectList(namespace + "prodrecoDetail", prodSeq);
	}
	
	/*
	 * public int viewLog(Integer prodSeq, String id) { return
	 * sqlSession.insert(viewLog(prodSeq, id)); }
	 */
	
	public int deleteImgFiles(ImageFileDTO imgDTO) {
		return sqlSession.delete(namespace + "deleteImgFiles", imgDTO);
	}
	
	public int insertImgFile(ImageFileDTO imgDTO) {
		return sqlSession.insert(namespace + "insertImgFile", imgDTO);
	}
	
	public int updateProduct(ProductModifyRequestDTO modiDTO) {
		return sqlSession.update(namespace + "updateProduct", modiDTO);
	}


	public void insertViewLog(ViewLogDTO viewLogDTO) {
		sqlSession.insert(namespace+"insertViewLog", viewLogDTO);
	}
	
	public List<ProdDetailDTO> selectRecently(String userId) {
		return sqlSession.selectList(namespace + "selectRecently", userId);
	}
	
	public int proStatusReserve(int productSeq) {
		return sqlSession.update(namespace + "proStatusReserve", productSeq);
	}
	
	public int proStatusSelling(int productSeq) {
		return sqlSession.update(namespace + "proStatusSelling", productSeq);
	}

	public int proStatusPutOff(int productSeq) {
		return sqlSession.update(namespace + "proStatusPutoff", productSeq);
	}

	public int proStatusDelete(int productSeq) {
		return sqlSession.delete(namespace + "proStatusDelete", productSeq);
	}

	public int insertJjim(JjimDTO jjimDTO) {
		return sqlSession.insert(namespace + "insertJjim", jjimDTO);
	}
	
	public int deleteJjim(JjimDTO jjimDTO) {
		return sqlSession.delete(namespace + "deleteJjim", jjimDTO);
	}
	
	public int selectJjim(JjimDTO jjimDto) {
		return sqlSession.selectOne(namespace + "selectJjim", jjimDto);
	}

	/*
	 * public int insertRecently(RecentlyDTO recentlyDTO) { return
	 * sqlSession.insert(namespace + "insertRecently", recentlyDTO); }
	 */
	
	
}