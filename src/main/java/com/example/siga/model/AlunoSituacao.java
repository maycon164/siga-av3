package com.example.siga.model;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class AlunoSituacao {

    private String ra;
    private String nome;
    private double nota1;
    private double nota2;
    private double mediaFinal;
    private String situacao;


    public static AlunoSituacao fromResultSet(ResultSet rs){
        try {
            AlunoSituacao aluno = new AlunoSituacao();
            aluno.setRa(rs.getString("ra"));
            aluno.setNome(rs.getString("nome"));
            aluno.setNota1(rs.getDouble("nota1"));
            aluno.setNota2(rs.getDouble("nota2"));
            aluno.setMediaFinal(rs.getDouble("mediaFinal"));
            aluno.setSituacao(rs.getString("situacao"));
            return aluno;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
