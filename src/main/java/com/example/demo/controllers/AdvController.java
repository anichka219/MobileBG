package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Advertisement;
import com.example.demo.models.enums.Status;
import com.example.demo.service.AdvService;

import java.time.LocalDateTime;

@Controller
public class AdvController {

    private static final Logger logger = LoggerFactory.getLogger(AdvController.class);

    @Autowired
    private AdvService advService;
    @Autowired
    private GlobalController globalController;

    @RequestMapping(value = {"/adv/saveAdv"}, method = RequestMethod.POST)
    public String saveTodo(@ModelAttribute("reqAdv") Advertisement reqAdv,
                           final RedirectAttributes redirectAttributes) {
        logger.info("/adv/save");
        try {
        	reqAdv.setCreateDate(LocalDateTime.now());
        	reqAdv.setStatus(Status.ACTIVE.getValue());
        	reqAdv.setUser(globalController.getLoginUser());
        	logger.info("predi save");
        	advService.save(reqAdv);
        	logger.info("sled save");
        	redirectAttributes.addFlashAttribute("msg", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "fail");
            logger.error("save: " + e.getMessage());
        }

        return "redirect:/home";
    }

    @RequestMapping(value = {"/adv/editAdv"}, method = RequestMethod.POST)
    public String editTodo(@ModelAttribute("editAdv") Advertisement editAdv, Model model) {
        logger.info("/adv/editAdv");
        try {
        	logger.info("Vlqzoh v try");
            Advertisement adv = advService.findById(editAdv.getId());
            logger.info("tuka li gurmi");
            if (!adv.equals(editAdv)) {
            	logger.info("predi update");
                advService.update(editAdv);
            	logger.info("sled update");
                model.addAttribute("msg", "success");
            } else {
                model.addAttribute("msg", "same");
            }
        } catch (Exception e) {
            model.addAttribute("msg", "fail");
            logger.error("editTask: " + e.getMessage());
        }
        model.addAttribute("editTodo", editAdv);
        return "edit";
    }


    @RequestMapping(value = "/adv/{operation}/{id}", method = RequestMethod.GET)
    public String todoOperation(@PathVariable("operation") String operation,
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
                redirectAttributes.addFlashAttribute("msgText", "Task " + adv.getId() + " Restored Successfully.");
            } else {
                redirectAttributes.addFlashAttribute("msg", "active_fail");
                redirectAttributes.addFlashAttribute("msgText", "Advertisement Activation failed !!! Advertisement:" + adv.getId());

            }
        } else if (operation.equals("delete")) {
            if (advService.delete(id)) {
                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", "  deleted permanently");
            } else {
                redirectAttributes.addFlashAttribute("msg", "del_fail");
                redirectAttributes.addFlashAttribute("msgText", " Advertisement could not deleted. Please try later");
            }
        } else if (operation.equals("edit")) {
        	logger.info("vlqzoh v edita!");
        	Advertisement editAdv = advService.findById(id);
        	logger.info("otkrih adv :"+editAdv.getId());
            if (editAdv != null) {
                model.addAttribute("editAdv", editAdv);
                return "edit";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }
        return "redirect:/home";
    }


}

