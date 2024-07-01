package com.shinhan.heehee.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;

@Controller
@RequestMapping("/sms")
public class SmsController {
	
	final DefaultMessageService messageService;
	
	Logger logger = LoggerFactory.getLogger(SmsController.class);
	
	public SmsController() {
        this.messageService = NurigoApp.INSTANCE.initialize("NCSAEC7JVOESUIQH", "DBTV1LDXAMSQ017JWMGZI8696N1OEFGW", "https://api.coolsms.co.kr");
    }
	
	@PostMapping("/send")
	@ResponseBody
    public Map<String,Object> sendOne(@RequestParam String phoneNum, HttpSession session) {
        Message message = new Message();
        
        phoneNum = phoneNum.replace("-", "");
        
        int authNo = generateAuthNo();
        
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력
        message.setFrom("01092902752");
        message.setTo(phoneNum);
        message.setText("[희희낙찰] 인증번호 [" + authNo + "]를 입력해주세요.");
        
        session.setAttribute("authNo", authNo);

        SingleMessageSentResponse MessageResponse = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(MessageResponse);
        
        Map<String,Object> response = new HashMap<String,Object>();
        
        if(MessageResponse.getStatusCode().equals("2000")) {
        	response.put("status", 200);
        	response.put("message", "인증번호 발송이 완료되었습니다.");
        	logger.info(phoneNum + ": 인증번호 발송 완료.");
        } else {
        	response.put("status", 500);
        	response.put("message", "인증번호 발송에 실패했습니다.");
        	logger.info(phoneNum + ": 인증번호 발송 실패.");
        }
        
        return response;
    }
	
	@GetMapping("/authNo")
	public ResponseEntity<?> authNoCheck(@RequestParam int authNo, HttpSession session) {
		Map<String,Object> response = new HashMap<String,Object>();
        
		int sessionAuthNo = (int) session.getAttribute("authNo");
		
        if(sessionAuthNo == authNo) {
        	response.put("status", 200);
        	response.put("message", "인증되었습니다.");
        	logger.info(sessionAuthNo + "인증 완료");
        } else {
        	response.put("status", 404);
        	response.put("message", "인증번호가 일치하지 않습니다.");
        	logger.info(sessionAuthNo + "인증 실패");
        }
        
        return ResponseEntity.ok(response);
	}
	
	private int generateAuthNo() {
        return ThreadLocalRandom.current().nextInt(100000, 1000000);
    }
}
