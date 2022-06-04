package com.example.siga.model.dto;

import lombok.Data;

import java.sql.ResultSet;

@Data
public class UpdateNotaDTO {

    public String ra;
    public int codigoAvaliacao;
    public String codigoDisciplina;
    public double nota;

}
