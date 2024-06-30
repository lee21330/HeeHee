package com.shinhan.heehee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.MainDAO;
import com.shinhan.heehee.dto.response.CategoryDTO;
import com.shinhan.heehee.dto.response.MainProdRankDTO;
import com.shinhan.heehee.dto.response.MainProdRecentlyDTO;
import com.shinhan.heehee.dto.response.MainProdRecoDTO;
import com.shinhan.heehee.dto.response.SellProDTO;

@Service
public class MainService {

	@Autowired
	MainDAO mainDao;
	
	public List<MainProdRankDTO> rankProdList() {
		return mainDao.rankProdList();
	}

	public List<MainProdRecoDTO> recommandList() {
		return mainDao.recommandList();
	}

	public List<MainProdRecentlyDTO> recentprodList() {
		return mainDao.recentprodList();
	}
	
	public List<CategoryDTO> mainCateList() {
		List<CategoryDTO> categoryList = mainDao.mainCateList();
		for(CategoryDTO cate: categoryList) {
			cate.setSubCategory(mainDao.subCateList(cate.getCategory()));
		}
		return categoryList;
	}
}
