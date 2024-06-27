package com.shinhan.heehee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.MyPageDAO;
import com.shinhan.heehee.dto.response.DeliveryCompanyDTO;
import com.shinhan.heehee.dto.response.EditProfileDTO;
import com.shinhan.heehee.dto.response.FaQDTO;
import com.shinhan.heehee.dto.response.InsertQnADTO;
import com.shinhan.heehee.dto.response.InsertQnAImgDTO;
import com.shinhan.heehee.dto.response.JjimDTO;
import com.shinhan.heehee.dto.response.MyPageHeaderDTO;
import com.shinhan.heehee.dto.response.PurchaseListDTO;
import com.shinhan.heehee.dto.response.QnADTO;
import com.shinhan.heehee.dto.response.QnAImgDTO;
import com.shinhan.heehee.dto.response.SaleDetailDTO;
import com.shinhan.heehee.dto.response.SaleListDTO;

@Service
public class MyPageService {

	@Autowired
	MyPageDAO mypageDao;

	public List<PurchaseListDTO> purchaseList(String userId) {
		return mypageDao.purchaseList(userId);
	}

	public List<JjimDTO> jjimList(String userId) {
		return mypageDao.jjimList(userId);
	}

	public MyPageHeaderDTO sellerInfo(String userId) {
		return mypageDao.sellerInfo(userId);
	}

	public List<SaleListDTO> saleList(String status, String userId) {
		return mypageDao.saleList(status, userId);
	}

	public int userIntroduce(String intro, String userId) {
		return mypageDao.userIntroduce(intro, userId);
	}

	public int editProfile(EditProfileDTO profile) {
		return mypageDao.editProfile(profile);
	}

	public List<QnADTO> qnaOption() {
		return mypageDao.qnaOption();
	}

	public List<FaQDTO> faqOption(int option) {
		return mypageDao.faqOption(option);
	}

	public List<QnADTO> myQna(String userId) {
		return mypageDao.myQna(userId);
	}

	public int insertQna(InsertQnADTO qna) {
		return mypageDao.insertQna(qna);
	}

	public int insertQnaImg(InsertQnAImgDTO qnaImg) {
		return mypageDao.insertQnaImg(qnaImg);
		
	}

	public EditProfileDTO profile(String userId) {
		return mypageDao.profile(userId);
	}

	public List<QnAImgDTO> myQnaImg(String userId, int seqQnaBno) {
		return mypageDao.myQnaImg(userId,seqQnaBno);
	}

	public int deleteQna(Integer seqQnaBno) {
		return mypageDao.deleteQna(seqQnaBno);
	}

	public int deleteQnaImg(Integer seqQnaBno) {
		return mypageDao.deleteQnaImg(seqQnaBno);
		
	}

	public SaleDetailDTO saleDetail(int proSeq) {
		return mypageDao.saleDetail(proSeq);
	}

	public List<DeliveryCompanyDTO> dcOption() {
		return mypageDao.dcOption();
	}

}
