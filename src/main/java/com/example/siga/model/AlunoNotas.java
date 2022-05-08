package com.example.siga.model;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class AlunoNotas {
    private String ra;
    private String nome;
    private String codigoDisciplina;
    private String siglaDisciplina;
    private int codigoAvaliacao;
    private String tipoAvaliacao;
    private double nota;

    public static AlunoNotas fromResultSet(ResultSet rs){
        try {
            AlunoNotas an = new AlunoNotas();
            an.setRa(rs.getString("ra"));
            an.setNome(rs.getString("nome"));

            an.setCodigoDisciplina(rs.getString("codigo_disciplina"));
            an.setSiglaDisciplina(rs.getString("sigla"));

            an.setCodigoAvaliacao(rs.getInt("codigo_avaliacao"));
            an.setTipoAvaliacao(rs.getString("tipo"));
            an.setNota(rs.getDouble("nota"));

            return an;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
