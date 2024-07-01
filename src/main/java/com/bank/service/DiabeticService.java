package com.bank.service;

import com.bank.exception.DiabeticNotFoundException;
import com.bank.model.Diabetic;
import com.bank.model.Glycemie;
import com.bank.repository.DiabeticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiabeticService {

    @Autowired
    private DiabeticRepository diabeticRepository;
    public Diabetic update(Diabetic diabetic) {
        return diabeticRepository.save(diabetic);
    }

    public List<Diabetic> getAll() {
        return diabeticRepository.findAll();
    }

    public Diabetic save(Diabetic diabetic) {
        return diabeticRepository.save(diabetic);
    }

    public Diabetic getById(Long id) {
        return diabeticRepository.findById(id).orElseThrow(DiabeticNotFoundException::new);
    }

    public void delete(Long id) {
        diabeticRepository.findById(id).orElseThrow(DiabeticNotFoundException::new);
        diabeticRepository.deleteById(id);
    }

    public Diabetic getDiabeticById(Long id) {
        return diabeticRepository.findById(id).orElse(null);
    }

    public List<Glycemie> getAllGlycemiesByDiabeticId(Long diabeticId) {
        Diabetic diabetic = diabeticRepository.findById(diabeticId).orElse(null);
        if (diabetic != null) {
            return diabetic.getAllGlycemies();
        } else {
            return new ArrayList<>();
        }


    }
}