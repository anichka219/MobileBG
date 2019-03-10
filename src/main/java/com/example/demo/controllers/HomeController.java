package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Advertisement;
import com.example.demo.models.enums.Status;
import com.example.demo.service.AdvService;
import com.example.demo.service.MainCategoryServiceImpl;
import com.example.demo.service.RegionServiceImpl;

@Controller
public class HomeController {
	
	 private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	    @Autowired
	    GlobalController globalController;
	    
	    @Autowired
	    RegionServiceImpl regionService;
	    
	    @Autowired
	    MainCategoryServiceImpl mainCategoryService;
	    
	    @Autowired
	    private AdvService advService;
	    
	    @RequestMapping("/home")
	    public String home(Model model) {
	    	Advertisement ad =new Advertisement();
	        model.addAttribute("reqAdv", ad);
	        model.addAttribute("allReg",regionService.getAllRegions());
	        model.addAttribute("allMainCategory", mainCategoryService.getAllMainCategories());
	        model.addAttribute("allAdv", advService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.ACTIVE.getValue()));
	        model.addAttribute("allPassiveAdv", advService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
	        logger.info("home");
	        return "home";
	    }

}
