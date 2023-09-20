package br.edu.unicesumar.dao;

import br.edu.unicesumar.domain.Conexao;
import br.edu.unicesumar.domain.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection conn;

    public UsuarioDAO(Connection connection) {
        this.conn = Conexao.getConexao();
    }

    public void salvar(Usuario dados) {
        String sql = "insert into usuario(nome, login, senha, email) values (?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, dados.getNome());
            preparedStatement.setString(2, dados.getLogin());
            preparedStatement.setString(3, dados.getSenha());
            preparedStatement.setString(4, dados.getEmail());

            preparedStatement.execute();

            JOptionPane.showMessageDialog(null, "Usuário Cadastrado");

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void buscar(String login, String senha) throws SQLException {
        
        String sql = "SELECT id, nome, login, senha , email from usuario where login = ? and senha = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,senha);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                JOptionPane.showMessageDialog(null,"Acesso Autorizado");
            } else {
                JOptionPane.showMessageDialog(null,"Acesso Negado");
            }
            conn.close();
            preparedStatement.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}