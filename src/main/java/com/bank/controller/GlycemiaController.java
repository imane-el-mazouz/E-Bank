package com.bank.controller;

import com.bank.enums.Level;
import com.bank.model.Conseil;
import com.bank.model.Diabetic;
import com.bank.model.Glycemie;
import com.bank.model.Repas;
import com.bank.service.ConseilService;
import com.bank.service.DiabeticService;
import com.bank.service.GlycemieService;
import com.bank.service.RepasService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/glycemie")
public class GlycemiaController {

    @Autowired
    private GlycemieService glycemieService;

    @Autowired
    private DiabeticService diabeticService;

    @Autowired
    private ConseilService conseilService;

    @Autowired
    private RepasService repasService;

    @GetMapping
    public String listGlycemies(ModelMap modelMap) throws JsonProcessingException {
        List<Glycemie> glycemies = glycemieService.getAllGlycemies();
        modelMap.addAttribute("listGlycemies", glycemies);

        // Convert Glycemie data to JSON for Chart.js
        List<String> labels = new ArrayList<>();
        List<Double> data = new ArrayList<>();
        for (Glycemie glycemie : glycemies) {
            labels.add(glycemie.getDate().toString());
            data.add(glycemie.getValue());
        }
        modelMap.addAttribute("labels", new ObjectMapper().writeValueAsString(labels));
        modelMap.addAttribute("data", new ObjectMapper().writeValueAsString(data));

        return "registrations";
    }

    @GetMapping("/new/{diabeticId}")
    public String showNewGlycemieForm(@PathVariable Long diabeticId, Model model) {
        Glycemie glycemie = new Glycemie();
        Diabetic diabetic = diabeticService.getById(diabeticId);
        glycemie.setDiabetic(diabetic);
        model.addAttribute("glycemie", glycemie);
        return "addGlycemie";
    }

    @PostMapping("/new")
    public String saveGlycemie(@RequestParam("value") double value,
                               @RequestParam("date") String date,
                               @RequestParam("unit") String unit,
                               @RequestParam("diabeticId") Long diabeticId,
                               Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        Level level = Level.fromValue(value);
        Glycemie glycemie = new Glycemie(value, dateTime, level, unit);
        Diabetic diabetic = diabeticService.getById(diabeticId);
        glycemie.setDiabetic(diabetic);

        glycemieService.saveGlycemie(glycemie);
        Optional<Conseil> conseil = conseilService.getConseilByLevel(level);
        model.addAttribute("conseil", conseil.orElse(new Conseil(level, level.getDefaultConseil())));
        return "viewConseil";
    }

    @GetMapping("/delete/{id}")
    public String deleteGlycemie(@PathVariable Long id) {
        glycemieService.deleteGlycemieById(id);
        return "redirect:/glycemie";
    }

    @GetMapping("/glycemie/{id}")
    public String viewGlycemies(@PathVariable Long id, Model model) {
        Diabetic diabetic = diabeticService.getDiabeticById(id);
        if (diabetic != null) {
            List<Glycemie> glycemies = diabetic.getAllGlycemies();
            model.addAttribute("listGlycemies", glycemies);
            model.addAttribute("diabetic", diabetic);
            return "registrations";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/conseil/{level}")
    public String getConseilByLevel(@PathVariable Level level, Model model) {
        Optional<Conseil> conseil = conseilService.getConseilByLevel(level);
        model.addAttribute("conseil", conseil.orElse(new Conseil(level, level.getDefaultConseil())));
        return "viewConseil";
    }

    @GetMapping("/chartDisplay")
    public String getGlucoseReadings(
            @RequestParam(value = "view", required = false, defaultValue = "all") String view,
            Model model) throws JsonProcessingException {

        List<Glycemie> glycemies = glycemieService.getAllGlycemies();
        List<String> labels = new ArrayList<>();
        List<Double> data = new ArrayList<>();

        if (view.equals("week")) {
            LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
            for (Glycemie glycemie : glycemies) {
                if (glycemie.getDate().isAfter(oneWeekAgo)) {
                    labels.add(glycemie.getDate().toString());
                    data.add(glycemie.getValue());
                }
            }
        } else if (view.equals("month")) {
            LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
            for (Glycemie glycemie : glycemies) {
                if (glycemie.getDate().isAfter(oneMonthAgo)) {
                    labels.add(glycemie.getDate().toString());
                    data.add(glycemie.getValue());
                }
            }
        } else {
            for (Glycemie glycemie : glycemies) {
                labels.add(glycemie.getDate().toString());
                data.add(glycemie.getValue());
            }
        }

        model.addAttribute("listGlycemies", glycemies);
        model.addAttribute("labels", new ObjectMapper().writeValueAsString(labels));
        model.addAttribute("data", new ObjectMapper().writeValueAsString(data));

        return "registrations";
    }

//    @GetMapping("/byGlycemie/{glycemieId}")
//    public String viewMealsByGlycemie(@PathVariable Long glycemieId, Model model) {
//        Glycemie glycemie = glycemieService.getById(glycemieId);
//        if (glycemie != null) {
//            List<Repas> repasList = glycemie.getRepas();
//            model.addAttribute("listRepas", repasList);
//            return "registrations"; // Adjust the view name as necessary
//        } else {
//            return "redirect:/";
//        }
//    }
@GetMapping("/byGlycemie/{glycemieId}")
public String viewMealsByGlycemie(@PathVariable Long glycemieId, Model model) {
    Glycemie glycemie = glycemieService.getById(glycemieId);
        List<Repas> repasList = glycemie.getRepas();
        model.addAttribute("listRepas", repasList);
        return "listRepas";

}

}
