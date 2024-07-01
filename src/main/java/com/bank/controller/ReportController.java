package com.bank.controller;

import com.bank.model.Report;
import com.bank.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/generateReport")
    public String generateCustomReport(@RequestParam("diabeticId") Long diabeticId, Model model) {
        Report report = reportService.generateCustomReport(diabeticId);
        model.addAttribute("report", report);
        return "report";
    }
}
