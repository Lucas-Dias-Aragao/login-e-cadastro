package br.edu.unicesumar.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/mapa";
    private static final String USER = "root";
    private static final String PASSWORD = "livro";

    private static Connection conn;

    public static Connection getConexao(){

        try{
            conn = DriverManager.getConnection(URL,
                    USER, PASSWORD);
            return conn;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
