
package com.shinhan.heehee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.response.ProdDetailDTO;
import com.shinhan.heehee.dto.response.ProdDetailImgDTO;
import com.shinhan.heehee.dto.response.ProdDetailRecoDTO;

@Repository
public class ProductDetailDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.productDetail.";
	
	public ProdDetailDTO productInfo(Integer prodSeq) {
		return sqlSession.selectOne(namespace + "productInfo", prodSeq);
	}
	
	public List<ProdDetailImgDTO> productImg(Integer prodSeq) {
		return sqlSession.selectList(namespace + "productImg", prodSeq);
	}
	
	/*
	 * public SellProDTO userIntroduce(Integer prodSeq) { return
	 * sqlSession.selectOne(namespace + "userIntroduce", prodSeq); }
	 */

	public List<ProdDetailRecoDTO> prodReco() {
		return sqlSession.selectList(namespace + "prodrecoDetail");
	}
	
	/*
	 * public int viewLog(Integer prodSeq, String id) { return
	 * sqlSession.insert(viewLog(prodSeq, id)); }
	 */
	
	
}
