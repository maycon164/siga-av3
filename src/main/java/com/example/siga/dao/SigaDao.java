package com.example.siga.dao;

import com.example.siga.db.DB;
import com.example.siga.model.AlunoNotas;
import com.example.siga.model.AlunoSituacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SigaDao implements ISigaDao{

    private Connection conn;

    @Autowired
    public SigaDao(){
        conn = DB.getConnection();
    }

    @Override
    public List<AlunoNotas> getNotasByTurma(String turma) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from vw_nota where sigla = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, turma);
            rs = ps.executeQuery();
            List<AlunoNotas> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(AlunoNotas.fromResultSet(rs));
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
