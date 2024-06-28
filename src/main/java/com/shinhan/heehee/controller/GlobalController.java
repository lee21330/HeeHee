package com.shinhan.heehee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.shinhan.heehee.dto.response.CategoryDTO;
import com.shinhan.heehee.service.MainService;

/*
 * @ControllerAdvice public class GlobalController {
 * 
 * @Autowired MainService mainService;
 * 
 * @ModelAttribute public void mainCateList(Model model) { List<CategoryDTO>
 * mainCateList = mainService.mainCateList(); model.addAttribute("mainCateList",
 * mainCateList); // 카테고리 서비스 호출 } }
 */
