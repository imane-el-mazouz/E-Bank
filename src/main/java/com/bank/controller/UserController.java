package com.bank.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/diabetic")
public class UserController {

//    @Autowired
//    private DiabeticService diabeticService;
//
//    @GetMapping("/diabetic")
//    public String show(Model model) {
//        model.addAttribute("diabetics", diabeticService.getAll());
//        return "index";
//    }
//
//    @GetMapping("/addnew")
//    public String addNewDiabetic(Model model) {
//        model.addAttribute("diabetic", new Diabetic());
//        return "add";
//    }
//
//    @PostMapping("/save")
//    public String saveDiabetic(
//            @RequestParam("name") String name,
//            @RequestParam("type") String type,
//            @RequestParam("age") Integer age,
//            @RequestParam("weight") float weight,
//            @RequestParam("height") float height,
//            @RequestParam("picture") MultipartFile file) {
//
//        Diabetic diabetic = new Diabetic();
//        diabetic.setName(name);
//        diabetic.setType(DiabeticType.valueOf(type));
//        diabetic.setAge(age);
//        diabetic.setWeight(weight);
//        diabetic.setHeight(height);
//
//        if (!file.isEmpty()) {
//            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//            String uploadDir = "src/main/resources/static/" + "img/";
//
//            try {
//                FileUploadUtil.saveFile(uploadDir, fileName, file);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            diabetic.setPicture(fileName);
//        }
//
//        diabeticService.save(diabetic);
//        return "redirect:/diabetic/diabetic";
//    }
//
//    @PostMapping("/signUp")
//    public String SignUpDiabetic(
//            @RequestParam("name") String name,
//            @RequestParam("type") String type,
//            @RequestParam("age") Integer age,
//            @RequestParam("weight") float weight,
//            @RequestParam("height") float height,
//            @RequestParam("email") String email,
//            @RequestParam("password") String password,
//            HttpSession session, Model model) {
//
//        Diabetic diabetic = new Diabetic();
//        diabetic.setName(name);
//        diabetic.setType(DiabeticType.valueOf(type));
//        diabetic.setAge(age);
//        diabetic.setWeight(weight);
//        diabetic.setHeight(height);
//        diabetic.setEmail(email);
//        diabetic.setPassword(password);
//
//        diabeticService.save(diabetic);
//
//        Diabetic diabetic1 = diabeticService.findDiabetic(email, password);
//        if (diabetic1 != null && diabetic1.getEmail().equals(email) && diabetic1.getPassword().equals(password)) {
//            session.setAttribute("diabetic", diabetic1);
//            return "redirect:/home2";
//        }
//
//        return "redirect:/home2";
//    }
//
//    @PostMapping("/login")
//    public String loginDiabetic(
//            @RequestParam("email") String email,
//            @RequestParam("password") String password,
//            HttpSession session, Model model) {
//
//        Diabetic diabetic1 = diabeticService.findDiabetic(email, password);
//        if (diabetic1 != null && diabetic1.getEmail().equals(email) && diabetic1.getPassword().equals(password)) {
//            session.setAttribute("diabetic", diabetic1);
//            return "redirect:/home2";
//        }
//
//        return "redirect:/home";
//    }
//
//    @GetMapping("/showFormForUpdate/{id}")
//    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
//        Diabetic diabetic = diabeticService.getDiabeticById(id);
//        model.addAttribute("diabetic", diabetic);
//        return "update";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateDiabetic(@PathVariable(value = "id") long id, @ModelAttribute("diabetic") Diabetic diabetic) {
//        diabetic.setId(id);
//        diabeticService.update(diabetic);
//        return "redirect:/home2again/{id}";
//    }
//
//    @GetMapping("/deleteDiabetic/{id}")
//    public String deleteThroughId(@PathVariable(value = "id") Long id) {
//        diabeticService.delete(id);
//        return "redirect:/diabetic/diabetic";
//    }
}
