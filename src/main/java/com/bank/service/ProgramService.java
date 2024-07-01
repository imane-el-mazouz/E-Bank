package com.bank.service;

//import com.diabetestracker.dto.ProgramDTO;
import com.bank.model.Exercice;
import com.bank.model.Glycemie;
import com.bank.model.Program;
import com.bank.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    public List<Program> getAllPrograms(Long id) {
        return programRepository.getAllProgramsById(id);
    }

    public Program getProgramById(Long id) {
        return programRepository.findById(id).orElse(null);
    }

    public Program saveProgram(Program program) {
        return programRepository.save(program);
    }

    public void deleteProgram(Long id) {
        programRepository.deleteById(id);
    }
    public void setExercice(Program program, Exercice exercice) {
        program.setExercice(exercice);
    }


    public void setGlycemie(Program program, Glycemie glycemie) {
        program.setGlycemie(glycemie);
    }
}
