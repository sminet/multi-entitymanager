package com.soprasteria.multientitymanager.controller;

import com.soprasteria.multientitymanager.model.Utilisateur;
import com.soprasteria.multientitymanager.repository.input.UtilisateurInputRepository;
import com.soprasteria.multientitymanager.repository.output.UtilisateurOutputRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class IndexController {
    private UtilisateurInputRepository inputRepository;
    private UtilisateurOutputRepository outputRepository;

    @GetMapping("input")
    public List<Utilisateur> input() {
        return inputRepository.findAll();
    }

    @GetMapping("output")
    public List<Utilisateur> output() {
        return outputRepository.findAll();
    }

    @Transactional
    @GetMapping("copy")
    public void copy() {
        var input = inputRepository.findAll();
        inputRepository.deleteAll(input);

        outputRepository.saveAll(input);
    }
}
