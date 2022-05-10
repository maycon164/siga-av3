package com.example.siga.model;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Data
public class AlunoFaltas {

    private String ra;
    private String nome;
    private String codigoDisciplina;
    private String siglaDisciplina;
    private Date data;
    private int presenca;


    public static AlunoFaltas fromResultSet(ResultSet rs){
        try {
            AlunoFaltas af = new AlunoFaltas();
            af.setRa(rs.getString("ra"));
            af.setNome(rs.getString("nome"));

            af.setCodigoDisciplina(rs.getString("codigo_disciplina"));
            af.setSiglaDisciplina(rs.getString("sigla"));

            af.setData(rs.getDate("data"));
            af.setPresenca(rs.getInt("presencas"));

            return af;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
