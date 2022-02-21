package br.com.grace.dao;

import br.com.grace.model.Estudante;
import br.com.grace.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class EstudanteDao {

    public void create(Estudante e) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO estudante (nome,senha,matricula)VALUES(?,?,?)");
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getSenha());
            stmt.setInt(3, e.getMatricula());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean login(int matricula, String senha) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Estudante estudante = new Estudante(0, "","", 0);
        try {
            stmt = con.prepareStatement("SELECT * FROM estudante WHERE  senha LIKE '%" + senha + "%' AND matricula LIKE '%" + matricula + "%'");
            rs = stmt.executeQuery();
            rs.first();

            estudante.setMatricula(rs.getInt("matricula"));
            estudante.setNome(rs.getString("nome"));
            estudante.setSenha(rs.getString("senha"));
            return estudante != null;
        } catch (SQLException ex) {
            System.err.println("ERROR");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return false;
    }

    public ArrayList<Estudante> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Estudante> estudantes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM estudante");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Estudante estudante = new Estudante(0, "","", 0);
                estudante.setMatricula(rs.getInt("matricula"));
                estudante.setNome(rs.getString("nome"));
                estudante.setSenha(rs.getString("senha"));
                estudantes.add(estudante);
            }

        } catch (SQLException ex) {
            System.err.println("ERROR");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return estudantes;
    }

}
