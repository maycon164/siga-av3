package com.example.siga.dao;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.example.siga.db.DB;
import com.example.siga.model.AlunoFaltas;
import com.example.siga.model.AlunoNotas;
import com.example.siga.model.AlunoSituacao;
import com.example.siga.model.dto.UpdateNotaDTO;
import com.sun.tools.jconsole.JConsoleContext;
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


    @Override
    public int updateNotasByTurma(List<UpdateNotaDTO> notas){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            int updatedRows = 0;
            for(UpdateNotaDTO n: notas){
                String sql = "UPDATE notas " +
                        "SET nota = ?" +
                        "WHERE ra_aluno = ? " +
                        "AND codigo_disciplina = ?" +
                        "AND codigo_avaliacao = ?";
                ps = conn.prepareStatement(sql);
                ps.setDouble(1, n.getNota());
                ps.setString(2, n.getRa());
                ps.setString(3, n.getCodigoDisciplina());
                ps.setInt(4, n.getCodigoAvaliacao());
                updatedRows += ps.executeUpdate();
            }
            System.out.println("updated rows " + updatedRows);
            return updatedRows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AlunoFaltas> getFaltasByTurma(String turma, String data) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM vw_presencas " +
                    "WHERE data = ? " +
                    "AND sigla = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, data);
            ps.setString(2, turma);

            rs = ps.executeQuery();
            List<AlunoFaltas> lista = new ArrayList<>();
            while(rs.next()){
                lista.add(AlunoFaltas.fromResultSet(rs));
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
