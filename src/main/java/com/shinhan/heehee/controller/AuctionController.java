package com.shinhan.heehee.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shinhan.heehee.dto.request.auction.AuctionHistoryDTO;
import com.shinhan.heehee.dto.request.auction.AuctionInsertDTO;
import com.shinhan.heehee.dto.response.auction.AuctionProdDTO;
import com.shinhan.heehee.dto.response.auction.AuctionProdInfoDTO;
import com.shinhan.heehee.dto.response.auction.SellerInfoResponseDTO;
import com.shinhan.heehee.exception.AucListZeroException;
import com.shinhan.heehee.service.AlarmService;
import com.shinhan.heehee.service.AuctionService;
import com.shinhan.heehee.service.MyPageService;
import com.shinhan.heehee.service.ProductModifyService;

@Controller
@RequestMapping("/auc")
public class AuctionController {

	@Autowired
	AuctionService auctionService;

	@Autowired
	MyPageService myPageService;
	
	@Autowired
	ProductModifyService productmodifyservice;

	@Autowired
	SimpMessagingTemplate messagingTemplate;

	@Autowired
	AlarmService alarmService;
	
	// 동시성 문제를 방지
	private final ReentrantLock bidLock = new ReentrantLock();
	
	private final Map<Integer, ReentrantLock> auctionLocks = new ConcurrentHashMap<>();

	@GetMapping
	public String auction(Model model, Principal principal) {
		List<AuctionProdDTO> aucList =auctionService.aucProdList(); 
		model.addAttribute("aucList", aucList);
		model.addAttribute("lastURL", "auc");
		if (principal != null) {
			model.addAttribute("userId", principal.getName());
			model.addAttribute("userInfo", myPageService.sellerInfo(principal.getName()));
			int alarmCount = alarmService.alarmCount(principal.getName());
			model.addAttribute("alarmCount",alarmCount);
		}
		return "/main/auction";
	}

	@GetMapping("/detail/{aucSeq}")
	public String detail(@PathVariable("aucSeq") int aucSeq, Model model, Principal principal) {
		AuctionProdInfoDTO aucProdInfo = auctionService.aucProdInfo(aucSeq);
		if (aucProdInfo == null)
			return "redirect:/auc";
		
		aucProdInfo.setIntroduce(aucProdInfo.getIntroduce().replace(" ", "&nbsp;").replace("\n", "<br>"));

		if (principal != null) {
			model.addAttribute("userId", principal.getName());
			model.addAttribute("userInfo", myPageService.sellerInfo(principal.getName()));
			int alarmCount = alarmService.alarmCount(principal.getName());
			model.addAttribute("alarmCount",alarmCount);
		}

		SellerInfoResponseDTO sellerInfo = auctionService.sellerInfo(aucProdInfo.getSellerId());

		model.addAttribute("lastURL", "auc");
		model.addAttribute("aucProdInfo", aucProdInfo);
		model.addAttribute("aucImgs", auctionService.aucProdImgList(aucSeq));
		model.addAttribute("sellerInfo", sellerInfo);
		return "/auction/detail";
	}

	@GetMapping("/prices")
	@ResponseBody
	public List<AuctionProdDTO> prices(@RequestParam(value = "seqArr[]", required = false) List<Integer> seqArr) {
		if(seqArr == null) throw new AucListZeroException();
		List<AuctionProdDTO> aucPriceList =  auctionService.aucPriceList(seqArr);
		return aucPriceList;

	}

	@GetMapping("/regi")
	public String regi(Model model, Principal principal) {
		String userId = principal.getName();
		model.addAttribute("lastURL", "auc");
		model.addAttribute("userId", userId);
		
		int alarmCount = alarmService.alarmCount(userId);
		model.addAttribute("alarmCount",alarmCount);
		
		model.addAttribute("categoryList", productmodifyservice.category());
		
		return "/auction/aucProductRegi";
	}
	
	@PostMapping("/regi")
	public String aucProdRegist(@RequestParam("uploadImgs") List<MultipartFile> uploadImgs
			,AuctionInsertDTO aucInsertDto, Principal principal) throws IOException {
		aucInsertDto.setExpTime(aucInsertDto.getExpTime().replace("T", " "));
		aucInsertDto.setUploadFiles(uploadImgs);
		aucInsertDto.setSellerId(principal.getName());
		
		auctionService.insertAucProd(aucInsertDto, principal.getName());
		
		return "redirect:/auc/detail/" + aucInsertDto.getProductSeq();
	}
	
	@GetMapping("/remaining")
	@ResponseBody
	public int getRemainingPoint(Principal principal) {
		return auctionService.getRemainingPoint(principal.getName());
	}

	@MessageMapping("/bid/{aucSeq}")
	public void handleBid(AuctionHistoryDTO aucHistory, @DestinationVariable("aucSeq") int aucSeq, Principal principal) {
		// 현재 스레드가 락을 획득할 때까지 대기, 락을 획득한 후에는 다른 스레드가 이 락을 흭득할 수 없게함
		ReentrantLock lock = auctionLocks.computeIfAbsent(aucSeq, k -> new ReentrantLock());
	    lock.lock();
		try {
			aucHistory = auctionService.insertAucHistory(aucHistory, principal.getName());
			aucHistory = auctionService.joinCount(aucHistory);
			messagingTemplate.convertAndSend("/topic/auction/" + aucSeq, aucHistory);
		} finally {
			lock.unlock();
	        auctionLocks.remove(aucSeq, lock);
		}
	}
}
