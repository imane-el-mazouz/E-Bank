package com.bank.service;

import com.bank.enums.Level;
import com.bank.model.Conseil;
import com.bank.repository.ConseilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConseilService {
    @Autowired
    private ConseilRepo conseilRepo;

    @Autowired
    private GlycemieService glycemieService;

    public Optional<Conseil> getConseilByLevel(Level level) {
        return conseilRepo.findByLevel(level);
    }


    public void saveConseil(Conseil conseil) {
        conseilRepo.save(conseil);
    }

    public java.lang.Object getAllConseils() {
        return null;
    }

    public void deleteConseilById(java.lang.Long id) {
    }

    public Optional<Conseil> getConseilById(Long id) {
        return conseilRepo.findById(id);
    }
}
