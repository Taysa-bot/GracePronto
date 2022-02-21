
package br.com.grace.dao;

import br.com.grace.connection.ConnectionFactory;
import br.com.grace.model.Evento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AgendaDao {
    public ArrayList<Evento> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Evento> Agenda = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT *,D.nome as nomeDisciplina FROM evento as E, disciplina as D where E.iddisciplina=D.iddisciplina AND E.status LIKE 'Em progresso'");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Evento evento = new Evento("", "", "", "",0, "", "","");
                evento.setId((rs.getInt("idevento")));
                evento.setTipo(rs.getString("tipo"));
                evento.setNome(rs.getString("nome"));
                evento.setData(rs.getString("Data"));
                evento.setStatus(rs.getString("status"));
                evento.setDisciplina(rs.getString("nomeDisciplina"));
                evento.setHora(rs.getString("hora"));
                evento.setFrequencia(rs.getString("frequencia"));
                evento.setAssunto(rs.getString("assunto"));
                Agenda.add(evento);
            }
        } catch (SQLException ex) {
            System.err.println("ERROR");
            
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return Agenda;
    }
}
