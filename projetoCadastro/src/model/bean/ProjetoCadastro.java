package model.bean;

import connection.connectionFactory;
import javax.swing.*;
import javax.swing.JOptionPane.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjetoCadastro extends JFrame implements ActionListener
{
    public static void main(String[] args) 
    {
        ProjetoCadastro pc = new ProjetoCadastro();
        pc.show();
    }
    
    //Váriaveis
    JButton btnCadastrar, btnLimpar, btnSair;
    JLabel lblCodigo, lblNome, lblSobrenome, lblEmail, lblCPF, lblSexo;
    JTextField txtCodigo, txtNome, txtSobrenome, txtEmail, txtCPF;
    JPanel pnInputs, pnBotoes;
    JRadioButton rbSexoM,rbSexoF; 
    
    //Banco
    Connection con = null;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/projetojava";
    private static final String user = "root";
    private static final String password = "root";
    
    
    public ProjetoCadastro()
    {
        setTitle("Cadastro de Usuários");
        setSize(200, 470);
        //setLocation();
        
        Container ctn = getContentPane();
        ctn.setLayout(new FlowLayout());
        
        //Paineis
        pnInputs = new JPanel();
        pnInputs.setLayout(new GridLayout(14,1));
        
        pnBotoes = new JPanel();
        pnBotoes.setLayout(new GridLayout(3,1));
        
        //Inputs e labels
        lblCodigo = new JLabel("Código: ");
        pnInputs.add(lblCodigo);
        txtCodigo = new JTextField(15);
        pnInputs.add(txtCodigo);
        
        lblNome = new JLabel("Nome: ");
        pnInputs.add(lblNome);
        txtNome = new JTextField(15);
        pnInputs.add(txtNome);
        
        lblSobrenome = new JLabel("Sobrenome: ");
        pnInputs.add(lblSobrenome);
        txtSobrenome = new JTextField(15);
        pnInputs.add(txtSobrenome);
        
        lblEmail = new JLabel("E-Mail: ");
        pnInputs.add(lblEmail);
        txtEmail = new JTextField(15);
        pnInputs.add(txtEmail);
        
        lblCPF = new JLabel("CPF: ");
        pnInputs.add(lblCPF);
        txtCPF = new JTextField(15);
        pnInputs.add(txtCPF);
        
        lblSexo = new JLabel("Sexo");
        pnInputs.add(lblSexo);
        rbSexoM = new JRadioButton("Masculino");
        pnInputs.add(rbSexoM);
        rbSexoF = new JRadioButton("Feminino");
        pnInputs.add(rbSexoF);
        //Fim dos Inputs e labels
        
        //Botões
        btnCadastrar = new JButton("Cadastrar");
        pnBotoes.add(btnCadastrar);
        btnCadastrar.addActionListener(this);
         
        btnLimpar = new JButton("Limpar");
        pnBotoes.add(btnLimpar);
        btnLimpar.addActionListener(this);
        
        btnSair = new JButton("Sair");
        pnBotoes.add(btnSair);
        btnSair.addActionListener(this);
         
        //Adição de tudo ao container
        ctn.add(pnInputs);
        ctn.add(pnBotoes);
    }
    
    public String getNome()
    {
        return txtNome.getText();
    }
    
    public void setNome(String nome)
    {
        txtNome.setText(nome);
    }
    
    public void actionPerformed (ActionEvent e)
    {
        if (e.getSource() == btnSair)
        {
            System.exit(0);
        }
        
        if (e.getSource() == btnLimpar)
        {
           txtCodigo.setText("");
           txtNome.setText("");
           txtSobrenome.setText("");
           txtEmail.setText("");
           txtCPF.setText("");
           rbSexoM.setSelected(false);
           rbSexoF.setSelected(false);        
        }
        
        if (e.getSource() == btnCadastrar)
        {
            String txtSexo = "";
            
            if (rbSexoM.isSelected())
            {
                txtSexo = "m";
            }
            else if (rbSexoF.isSelected())
            {
                txtSexo = "f";
            }
            
            String sql = "insert into tblusuario(usrId, usrNome, usrSobrenome, usrEmail, usrCpf, usrSexo) values(null,";
            sql += "'" + txtNome.getText() + "', '" + txtSobrenome.getText() + "', '" +  txtEmail.getText() + "', '" + txtCPF.getText() + "', '" + "m')";
        
            Statement stmt = null;
            
            //System.err.println(con.toString());
            
            try 
            {
                Class.forName(driver); //Carrega o driver
                con = DriverManager.getConnection(url, user, password); //Retorna a conexão
                //Connection con = connectionFactory.getConnection();
                
                System.out.println("Inserindo dados...");
                stmt = con.createStatement();
                stmt.executeUpdate(sql);
                
                System.out.println("Dados inseridos com sucesso!");

                stmt.close();
                con.close();
            }
            catch (SQLException ex) 
            {
                System.err.println("Erro ao inserir: " + ex);
            } 
            catch (ClassNotFoundException ex) 
            {
                Logger.getLogger(ProjetoCadastro.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        
        }
    }
 
}
