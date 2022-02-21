package br.com.grace.dao;

import br.com.grace.model.Disciplina;
import br.com.grace.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DisciplinaDao {
    
    public void create(Disciplina m) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO disciplina (nome,assunto)VALUES(?,?)");
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getAssunto());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Disciplina> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM disciplina");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Disciplina disciplina = new Disciplina("", "");
                disciplina.setId(rs.getInt("iddisciplina"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setAssunto(rs.getString("assunto"));
                disciplinas.add(disciplina);
            }
        } catch (SQLException ex) {
            System.err.println("ERROR");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return disciplinas;
    }
    
    public void update(Disciplina m) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE disciplina SET nome = ? ,assunto=? WHERE iddisciplina = ?");
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getAssunto());
            stmt.setInt(3, m.getId());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public void delete(Disciplina m) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM disciplina WHERE iddisciplina = ?");
            stmt.setInt(1, m.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
}
