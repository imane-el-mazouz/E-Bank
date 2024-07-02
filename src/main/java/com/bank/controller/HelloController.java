package com.bank.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {

//    @Autowired
//    private DiabeticService diabeticService;
//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String printHelloWorld(ModelMap modelMap){
//        return "Home";
//    }
//    @RequestMapping(value = "/contact", method = RequestMethod.GET)
//    public String printContact(ModelMap modelMap){
//        return "Contact";
//    }
//
//    @RequestMapping(value = "/home2", method = RequestMethod.GET)
//    public String printHome(HttpSession session, ModelMap modelMap) {
//        Diabetic diabetic = (Diabetic) session.getAttribute("diabetic");
//        if (diabetic == null) {
//            return "redirect:/";
//        }
//        modelMap.addAttribute("diabetic", diabetic);
//        return "Home2";
//    }
//
//    @RequestMapping(value = "/home2again/{id}", method = RequestMethod.GET)
//    public String printHomee(@PathVariable Long id, HttpSession session, ModelMap modelMap) {
//        Diabetic diabetic = diabeticService.getById(id);
//        if (diabetic == null) {
//            return "redirect:/";
//        }
//        modelMap.addAttribute("diabetic", diabetic);
//        return "Home2";
//    }
}