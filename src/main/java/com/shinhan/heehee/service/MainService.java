package com.shinhan.heehee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.MainDAO;
import com.shinhan.heehee.dto.response.SellProDTO;

@Service
public class MainService {

	@Autowired
	MainDAO mainDao;
	
	public List<SellProDTO> rankProdList() {
		return mainDao.rankProdList();
	}

	public List<SellProDTO> recommandList() {
		return mainDao.recommandList();
	}

	public List<SellProDTO> recentprodList() {
		return mainDao.recentprodList();
	}
}
