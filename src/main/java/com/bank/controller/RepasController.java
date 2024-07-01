package com.bank.controller;

import com.bank.model.Diabetic;
import com.bank.model.Glycemie;
import com.bank.model.Repas;
import com.bank.service.DiabeticService;
import com.bank.service.GlycemieService;
import com.bank.service.RepasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/repas")
public class RepasController {
    @Autowired
    private RepasService repasService;

    @Autowired
    private DiabeticService diabeticService;

    @Autowired
    private GlycemieService glycemieService;

    @GetMapping
    public String listRepas(ModelMap modelMap) {
        List<Repas> repasList = repasService.getAllRepas();
        modelMap.addAttribute("listRepas", repasList);
        return "listRepas";
    }

//    @GetMapping("/new/{diabeticId}")
//    public String showNewRepasForm(@PathVariable Long diabeticId, Model model) {
//        Repas repas = new Repas();
//        Diabetic diabetic = diabeticService.getById(diabeticId);
//        model.addAttribute("repas", repas);
//        model.addAttribute("diabeticId", diabeticId);
//        return "addRepas";
//    }
@GetMapping("/new/{diabeticId}")
public String showNewRepasForm(@PathVariable Long diabeticId, Model model) {
    Repas repas = new Repas();
    Diabetic diabetic = diabeticService.getById(diabeticId);
    Glycemie glycemie = glycemieService.getLatestGlycemie();
    model.addAttribute("repas", repas);
    model.addAttribute("diabetic", diabetic);
    model.addAttribute("glycemie", glycemie);
    return "addRepas";
}


    @PostMapping("/new")
    public String saveRepas(@RequestParam("description") String description,
                            @RequestParam("carbohydrates") double carbohydrates,
                            @RequestParam("date") String date,
                            @RequestParam("diabeticId") Long diabeticId,
                            @RequestParam("glycemieId") Long glycemieId,
                            Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        Repas repas = new Repas();
        repas.setDescription(description);
        repas.setCarbohydrates(carbohydrates);
        repas.setDate(dateTime);
        Diabetic diabetic = diabeticService.getById(diabeticId);
        repas.setDiabetic(diabetic);
        if (glycemieId != null) {
            Glycemie glycemie = glycemieService.getById(glycemieId);
            repas.setGlycemie(glycemie);
        }
        repasService.saveRepas(repas);
        return "redirect:/repas";
    }


    @GetMapping("/delete/{id}")
    public String deleteRepas(@PathVariable Long id) {
        repasService.deleteRepasById(id);
        return "redirect:/repas";
    }

    @GetMapping("/byGlycemie/{glycemieId}")
    public String viewMealsByGlycemie(@PathVariable Long glycemieId, Model model) {
        Glycemie glycemie = glycemieService.getById(glycemieId);
        List<Repas> repasList = glycemie.getRepas();
        model.addAttribute("listRepas", repasList);
        return "listRepas";
    }
}
