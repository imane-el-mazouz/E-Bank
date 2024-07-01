package com.bank.service;

import com.bank.model.Exercice;
import com.bank.repository.ExerciceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciceService {

    @Autowired
    private ExerciceRepository exerciceRepository;

    public List<Exercice> getAllExercices() {
        return exerciceRepository.findAll();
    }

    public Exercice getExerciceById(Long id) {
        return exerciceRepository.findById(id).orElse(null);
    }

    public List<Exercice> getExercicesByDiabeticId(Long diabeticId) {
        return exerciceRepository.findByDiabetic_Id(diabeticId);
    }
}
