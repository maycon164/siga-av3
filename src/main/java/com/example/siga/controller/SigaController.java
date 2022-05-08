package com.example.siga.controller;

import com.example.siga.dao.SigaDao;
import com.example.siga.model.AlunoNotas;
import com.example.siga.model.AlunoSituacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/siga")
public class SigaController {

    @Autowired
    private SigaDao repository;

    @GetMapping("/notas/{turma}")
    public List<AlunoNotas> getNotasByTurma(@PathVariable("turma") String turma){
        List<AlunoNotas> lista = repository.getNotasByTurma(turma);
        return lista;
    }

}
