package com.shinhan.heehee.controller;

import java.security.Principal;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.heehee.dto.request.AuctionHistoryDTO;
import com.shinhan.heehee.dto.response.AuctionProdDTO;
import com.shinhan.heehee.service.AuctionService;

@Controller
@RequestMapping("/auc")
public class AuctionController {

	@Autowired
	AuctionService auctionService;
	
	@Autowired
	SimpMessagingTemplate messagingTemplate;
	

	// 동시성 문제를 방지
	private final ReentrantLock bidLock = new ReentrantLock();

	@GetMapping("/detail/{aucSeq}")
	public String detail(@PathVariable("aucSeq") int aucSeq, Model model, Principal principal) {
		model.addAttribute("aucProdInfo", auctionService.aucProdInfo(aucSeq));
		return "/auction/detail";
	}

	@GetMapping("/prices")
	@ResponseBody
	public List<AuctionProdDTO> prices(@RequestParam(value = "seqArr[]") List<Integer> seqArr) {
		return auctionService.aucPriceList(seqArr);

	}

	@MessageMapping("/bid/{aucSeq}")
	public void handleBid(AuctionHistoryDTO aucHistory, @DestinationVariable("aucSeq") int aucSeq) {
		// 현재 스레드가 락을 획득할 때까지 대기, 락을 획득한 후에는 다른 스레드가 이 락을 흭득할 수 없게함
		bidLock.lock();
		try {
			auctionService.insertAucHistory(aucHistory);
			messagingTemplate.convertAndSend("/topic/auction/" + aucSeq, aucHistory);
		} finally {
			bidLock.unlock();
		}
	}
}
