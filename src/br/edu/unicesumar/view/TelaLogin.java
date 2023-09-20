package br.edu.unicesumar.view;

import br.edu.unicesumar.domain.Conexao;
import br.edu.unicesumar.dao.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class TelaLogin extends JFrame {
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JLabel lbSenha,lbLogin;
    private JButton btLogar, btCadastrar;
    private static TelaLogin frame;
    Connection connection;

    public TelaLogin(){
        inicializarComponentes();
        definirEventos();
        connection = Conexao.getConexao();

    }

    private void inicializarComponentes() {
        setTitle("Login");
        setBounds(0,0,300,250);
        setLayout(null);
        campoLogin = new JTextField(5);
        campoSenha = new JPasswordField(5);
        lbLogin = new JLabel("Login:");
        lbSenha = new JLabel("Senha:");
        btLogar = new JButton("Entrar");
        btCadastrar = new JButton("Cadastrar novo usuário");
        lbLogin.setBounds(30,30,90,25);
        lbSenha.setBounds(30,75,90,25);
        campoLogin.setBounds(80,30,160,25);
        campoSenha.setBounds(80,75,160,25);
        btLogar.setBounds(80,120,100,25);
        btCadastrar.setBounds(80,170,180,25);
        add(campoLogin);
        add(campoSenha);
        add(lbLogin);
        add(lbSenha);
        add(btLogar);
        add(btCadastrar);

    }

    private void definirEventos() {
        //Logar
        btLogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new UsuarioDAO(connection).buscar(campoLogin.getText(), campoSenha.getText());
                    campoLogin.setText("");
                    campoSenha.setText("");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Cadastrar
        btCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastrarUsuario cadastro = new TelaCadastrarUsuario();
                cadastro.abrir();
                frame.setVisible(false);
            }
        });
    }

    public static void abrir() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new TelaLogin();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((tela.width - frame.getSize().width) /2,
                        (tela.height - frame.getSize().height) / 2);
                frame.setVisible(true);
            }
        });
    }

}
