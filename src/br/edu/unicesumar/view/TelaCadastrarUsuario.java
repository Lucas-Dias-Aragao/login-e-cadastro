package br.edu.unicesumar.view;

import br.edu.unicesumar.domain.Conexao;
import br.edu.unicesumar.dao.UsuarioDAO;
import br.edu.unicesumar.domain.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class TelaCadastrarUsuario extends JFrame {
    private JTextField campoNome, campoLogin, campoEmail;
    private JPasswordField campoSenha;
    private JLabel lbSenha, lbLogin, lbNome, lbEmail, lbTitulo;
    private JButton btSalvar;
    private static TelaCadastrarUsuario frame;
    Connection connection;

    public TelaCadastrarUsuario(){
        inicializarComponentes();
        definirEventos();
        connection = Conexao.getConexao();
    }

    private void inicializarComponentes() {
        setTitle("Cadastrar Usuário");
        setBounds(0,0,300,300);
        setLayout(null);
        campoNome = new JTextField(5);
        campoLogin = new JTextField(5);
        campoSenha = new JPasswordField(5);
        campoEmail = new JTextField(5);
        lbTitulo = new JLabel("Cadastrar novo usuário");
        lbNome = new JLabel("Nome:");
        lbLogin = new JLabel("Login:");
        lbSenha = new JLabel("Senha:");
        lbEmail = new JLabel("E-mail:");
        btSalvar = new JButton("Salvar");
        lbTitulo.setBounds(10,20,200,40);
        lbNome.setBounds(30,60,90,25);
        lbLogin.setBounds(30,100,90,25);
        lbSenha.setBounds(30,140,90,25);
        lbEmail.setBounds(30,180,70,25);
        campoNome.setBounds(80,60,160,25);
        campoLogin.setBounds(80,100,160,25);
        campoSenha.setBounds(80,140,160,25);
        campoEmail.setBounds(80,180,160,25);
        btSalvar.setBounds(80,220,100,25);
        add(lbTitulo);
        add(campoNome);
        add(campoLogin);
        add(campoSenha);
        add(campoEmail);
        add(lbNome);
        add(lbLogin);
        add(lbSenha);
        add(lbEmail);
        add(btSalvar);
    }

    private void definirEventos() {
        btSalvar.addActionListener(e -> {

            if(validaCampos()){
                Usuario usuario = new Usuario(campoNome.getText(), campoLogin.getText(),
                        campoSenha.getText(), campoEmail.getText());
                new UsuarioDAO(connection).salvar(usuario);
                frame.dispose();
            }

        });
    }
    public void abrir() {
        SwingUtilities.invokeLater(() -> {
            frame = new TelaCadastrarUsuario();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation((tela.width - frame.getSize().width) /2,
                    (tela.height - frame.getSize().height) / 2);
            frame.setVisible(true);
        });
    }

    private Boolean validaCampos(){
        if(campoNome.getText().equals("") || campoEmail.getText().equals("") ||
                campoLogin.getText().equals("") || campoSenha.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Por favor, preencha todos os campos!");
            return false;
        } else {
            return true;
        }
    }
}
