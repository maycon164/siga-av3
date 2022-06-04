package com.example.siga.controller;

import com.example.siga.dao.SigaDao;
import com.example.siga.model.AlunoFaltas;
import com.example.siga.model.AlunoNotas;
import com.example.siga.model.AlunoSituacao;
import com.example.siga.model.dto.UpdateNotaDTO;
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

    @PostMapping("/notas/{turma}")
    public String updateNotasByTurma(@PathVariable("turma") String turma, @RequestBody List<UpdateNotaDTO> notas){
        try{
            int updatedRows = repository.updateNotasByTurma(notas);
            return updatedRows + " registros atualizados";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/faltas/{turma}")
    public List<AlunoFaltas> getFaltasByTurma(@PathVariable("turma") String turma, @RequestParam(name = "data") String data){
        System.out.println("turma: " + turma + " - data: " + data);
        return repository.getFaltasByTurma(turma, data);
    }

}
