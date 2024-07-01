package com.bank.controller;

import com.bank.model.Diabetic;
import com.bank.model.Exercice;
import com.bank.model.Glycemie;
import com.bank.model.Program;
import com.bank.service.DiabeticService;
import com.bank.service.ExerciceService;
import com.bank.service.GlycemieService;
import com.bank.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@Controller
@RequestMapping("/programs")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @Autowired
    private ExerciceService exerciceService;

    @Autowired
    private GlycemieService glycemieService;

    @Autowired
    private DiabeticService diabeticService;

    @GetMapping("/all/{diabeticId}")
    public String getAllPrograms(@PathVariable Long diabeticId, Model model) {
        model.addAttribute("programs", programService.getAllPrograms(diabeticId));
        return "program";
    }

    @GetMapping("/{id}")
    public String getProgramById(@PathVariable Long id, Model model) {
        model.addAttribute("program", programService.getProgramById(id));
        return "program";
    }

    @GetMapping("/addnew/{diabeticId}")
    public String addNewProgram(@PathVariable Long diabeticId, Model model) {
        Diabetic diabetic = diabeticService.getById(diabeticId);
        Glycemie glycemie = glycemieService.getLatestGlycemie();
        List<Exercice> exercices = exerciceService.getAllExercices();

        model.addAttribute("diabetic", diabetic);
        model.addAttribute("glycemie", glycemie);
        model.addAttribute("exercices", exercices);
        model.addAttribute("program", new Program());

        return "add-program";
    }

    @PostMapping("/save")
    public String saveProgram(@RequestParam("diabeticId") Long diabeticId,
                              @RequestParam("exercice") Long exerciceId,
                              @RequestParam("duration") Integer durationInMinutes,
                              @RequestParam("bloodSugarBefore") Float bloodSugarBefore,
                              @RequestParam("bloodSugarAfter") Float bloodSugarAfter) {
        Program program = new Program();

        Diabetic diabetic = diabeticService.getById(diabeticId);
        Exercice exercice = exerciceService.getExerciceById(exerciceId);
        Glycemie glycemie = glycemieService.getLatestGlycemie();

        program.setDiabetic(diabetic);
        programService.setExercice(program, exercice);
        programService.setGlycemie(program, glycemie);
        program.setDuration(new Time(durationInMinutes * 60 * 1000L));
        program.setBloodSugarBefore(bloodSugarBefore);
        program.setBloodSugarAfter(bloodSugarAfter);

        programService.saveProgram(program);
        return "redirect:/programs/all/" + diabeticId;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Program> updateProgram(@PathVariable Long id, @RequestBody Program updatedProgram) {
        Program program = programService.getProgramById(id);
        if (program != null) {
            updatedProgram.setId(id);
            return ResponseEntity.ok(programService.saveProgram(updatedProgram));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        if (programService.getProgramById(id) != null) {
            programService.deleteProgram(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
