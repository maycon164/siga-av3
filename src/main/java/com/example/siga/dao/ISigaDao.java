package com.example.siga.dao;

import com.example.siga.model.AlunoNotas;
import com.example.siga.model.AlunoSituacao;

import java.util.List;

public interface ISigaDao {

    public List<AlunoNotas> getNotasByTurma(String turma);


}
