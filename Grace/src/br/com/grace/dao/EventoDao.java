
package br.com.grace.dao;

import br.com.grace.model.Evento;
import br.com.grace.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class EventoDao {

    public void create(Evento f) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO evento (tipo,nome,data,status,iddisciplina,hora,frequencia,assunto)VALUES(?,?,?,?,?,?,?,?)");
            stmt.setString(1, f.getTipo());
            stmt.setString(2, f.getNome());
            stmt.setString(3, f.getData());
            stmt.setString(4, f.getStatus());
            stmt.setInt(5, f.getIdDisciplina());
            stmt.setString(6, f.getHora());
            stmt.setString(7, f.getFrequencia());
            stmt.setString(8,f.getAssunto());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<Evento> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Evento> festas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM evento");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Evento festa = new Evento("", "", "", "", 0, "", "","");
                festa.setId((rs.getInt("idevento")));
                festa.setTipo(rs.getString("tipo"));
                festa.setNome(rs.getString("nome"));
                festa.setData(rs.getString("Data"));
                festa.setStatus(rs.getString("status"));
                festa.setIdDisciplina(rs.getInt("iddisciplina"));
                festa.setHora(rs.getString("hora"));
                festa.setFrequencia(rs.getString("frequencia"));
                festa.setAssunto(rs.getString("assunto"));
                festas.add(festa);
            }
        } catch (SQLException ex) {
            System.err.println("ERROR");
            
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return festas;
    }

    public void update(Evento f) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE evento SET tipo =?,nome=?,Data=?,hora=?,iddisciplina=?,frequencia=?,status=?,assunto=? WHERE idevento = ?");
            stmt.setString(1, f.getTipo());
            stmt.setString(2, f.getNome());
            stmt.setString(3, f.getData());
            stmt.setString(4, f.getHora());
            stmt.setInt(5, f.getIdDisciplina());
            stmt.setString(6, f.getFrequencia());
            stmt.setString(7, f.getStatus());
            stmt.setString(8, f.getAssunto());
            stmt.setInt(9,f.getId());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void delete(Evento f) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM evento WHERE idevento = ?");
            stmt.setInt(1, f.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
}
