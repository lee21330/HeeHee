package com.shinhan.heehee.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.IllegalInstantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.heehee.dto.request.pay.PayRequestDTO;
import com.shinhan.heehee.dto.response.pay.PayResponseDTO;
import com.shinhan.heehee.service.PayService;

@Controller
@RequestMapping("/pay")
public class PayController {
	
	@Autowired
	PayService payService;
	
	@PostMapping("/before")
	@ResponseBody
	public PayResponseDTO beforePay(PayRequestDTO payDto, Principal principal) {
		payDto.setBuyerId(principal.getName());
		PayResponseDTO payResDto = payService.insertPay(payDto);
		return payResDto;
	}
	
	@PutMapping(value = "/complete", produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<?> completePay(@RequestBody Map<String, Integer> payload) {
	    int paySeq = payload.get("paySeq");
	    
	    int result = payService.completePay(paySeq);
	    if(result == 0) throw new IllegalArgumentException("결제 상태 업데이트 실패");
	    return ResponseEntity.ok("결제 상태 업데이트 완료");
	}
}
