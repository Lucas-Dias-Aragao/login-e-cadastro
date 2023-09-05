package domain.dao;

import domain.model.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class UsuarioDAO {
    private Connection conn;

    public UsuarioDAO(Connection connection) {
        this.conn = connection;
    }

    public void salvar(Usuario dados) {
        String sql = "insert into usuario(nome, login, senha, email) values (?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, dados.getNome());
            preparedStatement.setString(2, dados.getLogin());
            preparedStatement.setString(3, dados.getEmail());
            preparedStatement.setString(4, dados.getSenha());

            preparedStatement.execute();

            JOptionPane.showMessageDialog(null, "Usuário Cadastrado");

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void buscar(String login, String senha) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT id, nome, login, senha , email from usuario where login = ? and senha = ?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,senha);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                JOptionPane.showMessageDialog(null,"Acesso Autorizado");
            } else {
                JOptionPane.showMessageDialog(null,"Acesso Negado");
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            preparedStatement.close();
            conn.close();
        }
    }
}