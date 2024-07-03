package com.shinhan.heehee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.PayDAO;
import com.shinhan.heehee.dao.UserDAO;
import com.shinhan.heehee.dto.request.pay.PayRequestDTO;
import com.shinhan.heehee.dto.response.UserDTO;
import com.shinhan.heehee.dto.response.pay.PayResponseDTO;

@Service
public class PayService {

	@Autowired
	PayDAO payDao;
	
	@Autowired
	UserDAO userDao;
	
	public PayResponseDTO insertPay(PayRequestDTO payDto) {
		payDao.insertPay(payDto);
		PayResponseDTO payResDto = new PayResponseDTO(payDto);
		UserDTO userDto = userDao.getBuyerInfo(payDto.getBuyerId());
		payResDto.setBuyerTel(userDto.getPhoneNum());
		payResDto.setBuyerEmail(userDto.getEmail());
		payResDto.setBuyerAddr(userDto.getAddress());
		return payResDto;
	}
	
	public int completePay(int paySeq) {
		return payDao.completePay(paySeq);
	}
}
