package com.shinhan.heehee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MyPageController2 {

    @GetMapping("/profile")
    public String editProfile() {
        return "/mypage/editProfile";
    }
    @GetMapping("/jjimList")
    public String jjimList() {
        return "/mypage/jjimList";
    }
    @GetMapping("/pointlist")
    public String pointlist() {
        return "/mypage/pointList";
    }
    @GetMapping("/purchasedetail")
    public String purchasedetail() {
        return "/mypage/purchaseDetail";
    }
    @GetMapping("/purchaseList")
    public String purchaseList() {
        return "/mypage/purchaseList";
    }
    @GetMapping("/saledetail")
    public String saledetail() {
        return "/mypage/saleDetail";
    }
    @GetMapping("/saleList")
    public String saleList() {
        return "/mypage/saleList";
    }
}










