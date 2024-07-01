package com.bank.controller;

import com.bank.model.Exercice;
import com.bank.service.ExerciceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercices")
public class ExerciceController {

    @Autowired
    private ExerciceService exerciceService;

    @GetMapping
    public List<Exercice> getAllExercices() {
        return exerciceService.getAllExercices();
    }
}
