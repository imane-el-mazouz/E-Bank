package com.bank.controller;

import com.bank.enums.DiabeticType;
import com.bank.model.Diabetic;
import com.bank.service.DiabeticService;
import com.bank.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class DiabeticController {

    @Autowired
    private DiabeticService diabeticService;

    @GetMapping("/")
    public String show(Model model){
        model.addAttribute("diabetics", diabeticService.getAll());
        return "index";
    }

    @GetMapping("/addnew")
    public String addNewEmployee(Model model) {
        model.addAttribute("diabetic", new Diabetic());
        return "add";
    }

    @PostMapping("/save")
    public String saveEmployee(
            @RequestParam("name") String name,
            @RequestParam("type") String type,
            @RequestParam("age") Integer age,
            @RequestParam("weight") float weight,
            @RequestParam("height") float height,
            @RequestParam("picture") MultipartFile file) {

        Diabetic diabetic = new Diabetic();
        diabetic.setName(name);
        diabetic.setType(DiabeticType.valueOf(type));
        diabetic.setAge(age);
        diabetic.setWeight(weight);
        diabetic.setHeight(height);

        if (!file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uploadDir = "src/main/resources/static/" +  "img/";

            try {
                FileUploadUtil.saveFile(uploadDir, fileName, file);
            } catch (IOException e) {
                e.printStackTrace();
            }

            diabetic.setPicture(fileName);
        }

        diabeticService.save(diabetic);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Diabetic diabetic = diabeticService.getDiabeticById(id);
        model.addAttribute("diabetic", diabetic);
        return "update";
    }
    @PostMapping("/update")
    public String updateDiabetic(@ModelAttribute("diabetic") Diabetic diabetic) {
        diabeticService.update(diabetic);
        return "redirect:/";
    }


    @PostMapping("/update/{id}")
    public String updateDiabetic(@PathVariable(value = "id") long id, @ModelAttribute("diabetic") Diabetic diabetic) {
        diabetic.setId(id);
        diabeticService.save(diabetic);
        return "redirect:/";
    }

    @GetMapping("/deleteDiabetic/{id}")
    public String deleteThroughId(@PathVariable(value = "id") Long id) {
        diabeticService.delete(id);
        return "redirect:/";
    }

}
