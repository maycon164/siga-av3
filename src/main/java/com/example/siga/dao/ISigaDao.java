package com.example.siga.dao;

import com.example.siga.model.AlunoFaltas;
import com.example.siga.model.AlunoNotas;
import com.example.siga.model.AlunoSituacao;
import com.example.siga.model.dto.UpdateNotaDTO;
import com.example.siga.model.dto.UpdatePresencaDTO;

import java.util.List;

public interface ISigaDao {

    public List<AlunoNotas> getNotasByTurma(String turma);
    public List<AlunoFaltas> getFaltasByTurma(String turma, String data);

    public int updateNotasByTurma(List<UpdateNotaDTO> notas);
    public int updatePresencasByTurma(List<UpdatePresencaDTO> presencas);
}
