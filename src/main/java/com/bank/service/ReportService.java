package com.bank.service;

import com.bank.model.*;
import com.bank.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ReportService {

    private static final Logger logger = Logger.getLogger(ReportService.class.getName());

    @Autowired
    private DiabeticRepository diabeticRepository;

    @Autowired
    private GlycemieRepository glycemieRepository;

    @Autowired
    private RepasRepository repasRepository;

    @Autowired
    private ProgramRepository programRepository;

    public Report generateCustomReport(Long diabeticId) {
        Diabetic diabetic = diabeticRepository.findById(diabeticId).orElse(null);
        List<Glycemie> glycemiaReadings = glycemieRepository.findByDiabetic_Id(diabeticId);
        List<Repas> meals = repasRepository.findByDiabetic_Id(diabeticId);
        List<Program> programs = programRepository.findByDiabetic_Id(diabeticId);

        logger.info("Diabetic: " + diabetic);
        logger.info("Glycemia Readings: " + glycemiaReadings);
        logger.info("Meals: " + meals);
        logger.info("Programs: " + programs);

        return Report.builder()
                .diabetic(diabetic)
                .glycemiaReadings(glycemiaReadings)
                .meals(meals)
                .programs(programs)
                .build();
    }
}
