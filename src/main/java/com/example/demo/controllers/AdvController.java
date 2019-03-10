package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Advertisement;
import com.example.demo.models.Category;
import com.example.demo.models.Location;
import com.example.demo.models.ModelAdv;
import com.example.demo.models.enums.Status;
import com.example.demo.service.AdvService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ColorServiceImpl;
import com.example.demo.service.LocationService;
import com.example.demo.service.MainCategoryServiceImpl;
import com.example.demo.service.ModelAdvService;
import com.example.demo.service.RegionServiceImpl;
import java.time.LocalDateTime;
import java.util.List;


@Controller
public class AdvController {

    private static final Logger logger = LoggerFactory.getLogger(AdvController.class);

    @Autowired
    private AdvService advService;
    @Autowired
    private GlobalController globalController;
   
    @Autowired
    RegionServiceImpl regionService;
    
    @Autowired
    private MainCategoryServiceImpl mainCategoryService;
    
    @Autowired
    private ColorServiceImpl colorService;
    
    @Autowired
    private ModelAdvService modelAdvService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private LocationService locationService;
    
    private ModelAndView mav;
    
    @RequestMapping(value = "/loadBrands", method = RequestMethod.GET)
    public ModelAndView loadBrands() {

        mav = new ModelAndView();
        mav.addObject("brandList", modelAdvService.listBrands());
        
        return mav;
    }
    @RequestMapping(value = "/loadModels", headers = "Accept=*/*", method = RequestMethod.GET)
    public @ResponseBody
    List<ModelAdv> loadModels(@RequestParam(value = "brandId", required = true) String brandId){
    	logger.info("stiga!");	
        return modelAdvService.loadModels(brandId);
    }
    
    @RequestMapping(value = "/loadLocations", headers = "Accept=*/*", method = RequestMethod.GET)
    public @ResponseBody
    List<Location> loadLocations(@RequestParam(value = "regionId", required = true) String regionName){
    	
        return locationService.loadLocations(regionName);
    }
    
    @RequestMapping(value = "/loadCategories", headers = "Accept=*/*", method = RequestMethod.GET)
    public @ResponseBody
    List<Category> loadCategories(@RequestParam(value = "mainCategoryId", required = true) String mainCategoryName){
    	logger.info("LoadCategories!");
        return categoryService.loadCategories(mainCategoryName);
    }
    
    @RequestMapping("/create")
    public String home(Model model) {
    	Advertisement ad =new Advertisement();
        model.addAttribute("reqAdv", ad);
        model.addAttribute("allReg",regionService.getAllRegions());
        model.addAttribute("brandList",modelAdvService.listBrands());
        model.addAttribute("allColors",colorService.getColors());
 //       model.addAttribute("allModels", modelService.getAllModels());
        model.addAttribute("allMainCategory", mainCategoryService.getAllMainCategories());
        model.addAttribute("allAdv", advService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.ACTIVE.getValue()));
        model.addAttribute("allPassiveAdv", advService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));
        logger.info("create");
        return "create";
    }
 
    @RequestMapping(value = {"/adv/saveAdv"}, method = RequestMethod.POST)
    public String saveAdv(@ModelAttribute("reqAdv") Advertisement reqAdv,
                           final RedirectAttributes redirectAttributes) {
        logger.info("/adv/save");
        try {
        	reqAdv.setCreateDate(LocalDateTime.now());
        	reqAdv.setStatus(Status.ACTIVE.getValue());
        	reqAdv.setUser(globalController.getLoginUser());        	
        	advService.save(reqAdv);
        	redirectAttributes.addFlashAttribute("msg", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "fail");
            logger.error("save: " + e.getMessage());
            return "redirect:/create";
        }

        return "redirect:/home";
    }

    @RequestMapping(value = {"/adv/editAdv"}, method = RequestMethod.POST)
    public String editAdv(@ModelAttribute("editAdv") Advertisement editAdv, Model model) {
        logger.info("/adv/editAdv");
        try {
            Advertisement adv = advService.findById(editAdv.getId());
            if (!adv.equals(editAdv)) {
            	logger.info("VLIZAAAAA");
                advService.update(editAdv);           	
                model.addAttribute("msg", "success");
            } else {
                model.addAttribute("msg", "same");
            }
        } catch (Exception e) {
            model.addAttribute("msg", "fail");
            logger.error("editAdv: " + e.getMessage());
        }
        model.addAttribute("editAdv", editAdv);
        return "edit";
    }


    @RequestMapping(value = "/adv/{operation}/{id}", method = RequestMethod.GET)
    public String advOperation(@PathVariable("operation") String operation,
                                @PathVariable("id") long id, final RedirectAttributes redirectAttributes,
                                Model model) {

        logger.info("/adv/operation: {} ", operation);
        if (operation.equals("trash")) {
            Advertisement adv = advService.findById(id);
            if (adv != null) {
            	adv.setStatus(Status.PASSIVE.getValue());
            	advService.update(adv);
                redirectAttributes.addFlashAttribute("msg", "trash");
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }
        if (operation.equals("restore")) {
            Advertisement adv = advService.findById(id);
            if (adv != null) {
            	adv.setStatus(Status.ACTIVE.getValue());
            	advService.update(adv);
                redirectAttributes.addFlashAttribute("msg", "active");
                redirectAttributes.addFlashAttribute("msgText", "Advertisement " + adv.getId() + " Restored Successfully.");
            } else {
                redirectAttributes.addFlashAttribute("msg", "active_fail");
                redirectAttributes.addFlashAttribute("msgText", "Advertisement Activation failed !!! Advertisement:" + adv.getId());

            }
        } else if (operation.equals("delete")) {

            Advertisement adv = advService.findById(id);

        	List<Advertisement> lst = globalController.getLoginUser().getMyAdvertisement();
        	
        	logger.info("size : " + lst.size());
        	int pos;
        	for(int i = 0; i < lst.size(); i++)
        	{
        		if(lst.get(i).getId() == id)
        		{
        			logger.info("Deleted from index : " + i + ",  with id : " + id);
        			lst.remove(i);
        			break;        			
        		}
        	}
        	logger.info("again size : " + lst.size());

        	if (advService.delete(id)) {
            	
                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", "  deleted permanently");

            	logger.info("deleted!!!!");
                
            } else {
                redirectAttributes.addFlashAttribute("msg", "del_fail");
                redirectAttributes.addFlashAttribute("msgText", " Advertisement could not deleted. Please try later");
            }
        } else if (operation.equals("edit")) {
        	Advertisement editAdv = advService.findById(id);
        	logger.info("found adv :"+editAdv.getId());
            if (editAdv != null) {
                model.addAttribute("editAdv", editAdv);
                model.addAttribute("allReg",regionService.getAllRegions());
                model.addAttribute("brandList",modelAdvService.listBrands());
                model.addAttribute("allColors",colorService.getColors());
                model.addAttribute("allMainCategory", mainCategoryService.getAllMainCategories());
                model.addAttribute("allAdv", advService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.ACTIVE.getValue()));
                model.addAttribute("allPassiveAdv", advService.findByUserIdStatus(globalController.getLoginUser().getId(), Status.PASSIVE.getValue()));

                return "edit";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }
        return "redirect:/home";
    }


}

