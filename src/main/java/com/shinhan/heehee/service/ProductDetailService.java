package com.shinhan.heehee.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shinhan.heehee.dao.ProductDetailDAO;
import com.shinhan.heehee.dto.request.ImageFileDTO;
import com.shinhan.heehee.dto.request.ProductModifyRequestDTO;
import com.shinhan.heehee.dto.request.ViewLogDTO;
import com.shinhan.heehee.dto.response.ProdDetailDTO;
import com.shinhan.heehee.dto.response.ProdDetailImgDTO;
import com.shinhan.heehee.dto.response.ProdDetailRecoDTO;

@Service
public class ProductDetailService {

	@Autowired
	ProductDetailDAO productDetailDao;
	
	@Autowired
	AWSS3Service fileUploadService;
	
	public ProdDetailDTO prodInfo(Integer prodSeq) {
		return productDetailDao.productInfo(prodSeq);
	}
	
	public List<ProdDetailImgDTO> prodImg(Integer prodSeq) {
		return productDetailDao.productImg(prodSeq);
	}
	
	/*
	 * public SellProDTO userIntroduce(Integer prodSeq) { return
	 * productDetailDao.userIntroduce(prodSeq); }
	 */

	public List<ProdDetailRecoDTO> prodReco(Integer prodSeq) {
		return productDetailDao.prodReco(prodSeq);
	}
	
	@Transactional
	public void prodModify(ProductModifyRequestDTO modiDTO) throws IOException {
		String filePath = "images/sell/";
		List<MultipartFile> files = modiDTO.getUploadFiles();
		
		// 파일 업로드 전 기존 파일 삭제
		if(modiDTO.getDelArr() != null) {
			for(String delItem : modiDTO.getDelArr()) {
				ImageFileDTO imgDTO= new ImageFileDTO();
				imgDTO.setImgSeq(Integer.parseInt(delItem));
				imgDTO.setTablePk(modiDTO.getProdSeq());
				productDetailDao.deleteImgFiles(imgDTO);
			}
		}
		
		// 파일 업로드 로직
		for(MultipartFile file : files) {
			if(file.getSize() != 0) {
				ImageFileDTO imgfile = new ImageFileDTO();
				String fileName = fileUploadService.uploadOneObject(file, filePath);
				imgfile.setImgName(fileName);
				imgfile.setProdSeq(modiDTO.getProdSeq());
				imgfile.setUserId("a");
				productDetailDao.insertImgFile(imgfile);
			}
		}
		
		// SELL_PRODUCT 테이블 UPDATE
		productDetailDao.updateProduct(modiDTO);
		
	}
	
	@Transactional
	public void proStatusSelling(Integer prodSeq) { 
		productDetailDao.proStatusSelling(prodSeq);
	}

	public void insertViewLog(ViewLogDTO viewLogDTO) {
		productDetailDao.insertViewLog(viewLogDTO);
	}
	
	
}
