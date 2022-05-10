package com.example.siga.controller;

import com.example.siga.dao.SigaDao;
import com.example.siga.model.AlunoFaltas;
import com.example.siga.model.AlunoNotas;
import com.example.siga.model.AlunoSituacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/siga")
public class SigaController {

    @Autowired
    private SigaDao repository;

    @GetMapping("/notas/{turma}")
    public List<AlunoNotas> getNotasByTurma(@PathVariable("turma") String turma){
        return repository.getNotasByTurma(turma);
    }

    @GetMapping("/faltas/{turma}")
    public List<AlunoFaltas> getFaltasByTurma(@PathVariable("turma") String turma, @RequestParam(name = "data") String data){
        System.out.println("turma: " + turma + " - data: " + data);
        return repository.getFaltasByTurma(turma, data);
    }

}
