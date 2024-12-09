/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifysteamdiscord;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author ernes
 */
public class MenuInicial extends JFrame {
    
    JPanel pantalla;
    CardLayout cardLayout;
    ManejoUsuarios mu=new ManejoUsuarios();
    
    public MenuInicial(){
        setSize(600,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardLayout=new CardLayout();
        pantalla=new JPanel(cardLayout);
        pantalla.setSize(600,600);
        
        //Panel del Menu
        JPanel menu=new JPanel();
        menu.setLayout(null);
        
        JLabel titulo=new JLabel("Bienvenido a Spoteam");
        titulo.setBounds(210, 90, 170, 50);
        titulo.setFont(new Font("Arial", Font.PLAIN,16));
        menu.add(titulo);
        
        JButton botonis=new JButton("Iniciar Sesion");
        botonis.setBounds(190, 170, 200,60);
        menu.add(botonis);
        
        JButton botonrg=new JButton("Registrar");
        botonrg.setBounds(190, 300, 200,60);
        menu.add(botonrg);
        
        botonis.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                cardLayout.show(pantalla, "InicioSesion");
            }
                 });
        
        botonrg.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                cardLayout.show(pantalla,"Registro");
            }
                 });
        
        
        //Parte Iniciar Sesion
        JPanel InicioSesion=new JPanel();
        InicioSesion.setLayout(null);
        
        JLabel titulois=new JLabel("Bienvenido a Spoteam");
        titulois.setBounds(210, 90, 170, 50);
        titulois.setFont(new Font("Arial", Font.PLAIN,16));
        InicioSesion.add(titulois);
        
        JLabel ingreseu=new JLabel("Ingrese su Usuario");
        ingreseu.setBounds(190, 160, 170, 50);
        ingreseu.setFont(new Font("Arial", Font.PLAIN,16));
        InicioSesion.add(ingreseu);
        
        JTextField campouser=new JTextField();
        campouser.setBounds(190, 200, 170, 50);
        InicioSesion.add(campouser);
        
        JLabel ingresec=new JLabel("Ingrese su Contraseña");
        ingresec.setBounds(190, 240, 170, 50);
        ingresec.setFont(new Font("Arial", Font.PLAIN,16));
        InicioSesion.add(ingresec);
        
        JPasswordField campocontra=new JPasswordField();
        campocontra.setBounds(190, 280, 170, 50);
        InicioSesion.add(campocontra);
        
        JButton botoni=new JButton("Ingresar");
        botoni.setBounds(190, 350, 170, 50);
        InicioSesion.add(botoni);
        
        botoni.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                try{
                String username=campouser.getText();
                String password=campocontra.getText();
                Users u=new Users(username, password);
                if(mu.iniciarSesion(u)){
                    JOptionPane.showMessageDialog(null, "Se inicio sesion correctamente");
                    mu.loggear(u);
                    campouser.setText("");
                    campocontra.setText("");
                    MenuAplicacion a=new MenuAplicacion();
                    a.setVisible(true);
                    MenuInicial.this.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "No existe esta cuenta");
                    campouser.setText("");
                    campocontra.setText("");
                }
                }catch(Exception er){
                    JOptionPane.showMessageDialog(null, "Error encontrado: "+er.getMessage());
                }
                
            }
                 });
        
        JButton botonregresar=new JButton("Regresar");
        botonregresar.setBounds(190, 400, 170, 50);
        InicioSesion.add(botonregresar);
        
        botonregresar.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                cardLayout.show(pantalla, "MenuBotones");
            }
                 });
        
        //Parte Registro
        JPanel Registro=new JPanel();
        Registro.setLayout(null);
        
        JLabel titulorg=new JLabel("Bienvenido a Spoteam");
        titulorg.setBounds(210, 90, 170, 50);
        titulorg.setFont(new Font("Arial", Font.PLAIN,16));
        Registro.add(titulorg);
        
        JLabel ingreseur=new JLabel("Ingrese su Usuario");
        ingreseur.setBounds(190, 160, 170, 50);
        ingreseur.setFont(new Font("Arial", Font.PLAIN,16));
        Registro.add(ingreseur);
        
        JTextField campouserr=new JTextField();
        campouserr.setBounds(190, 200, 170, 50);
        Registro.add(campouserr);
        
        JLabel ingresecr=new JLabel("Ingrese su Contraseña");
        ingresecr.setBounds(190, 240, 170, 50);
        ingresecr.setFont(new Font("Arial", Font.PLAIN,16));
        Registro.add(ingresecr);
        
        JPasswordField campocontrar=new JPasswordField();
        campocontrar.setBounds(190, 280, 170, 50);
        Registro.add(campocontrar);
        
        JButton botonr=new JButton("Ingresar");
        botonr.setBounds(190, 350, 170, 50);
        Registro.add(botonr);
        
        JButton botonregresarr=new JButton("Regresar");
        botonregresarr.setBounds(190, 400, 170, 50);
        Registro.add(botonregresarr);
        
        botonr.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                try{
                String username=campouserr.getText();
                String password=campocontrar.getText();
                Users u=new Users(username, password);
                if(mu.añadirUsuario(u)){
                    JOptionPane.showMessageDialog(null, "Se creo la cuenta con exito");
                    campouserr.setText("");
                    campocontrar.setText("");
                    mu.loggear(u);
                    MenuAplicacion a=new MenuAplicacion();
                    a.setVisible(true);
                    MenuInicial.this.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Ya existe esta cuenta");
                }
                }catch(Exception er){
                    JOptionPane.showMessageDialog(null, "Error encontrado: "+er.getMessage());
                }
            }
                 });
        
        botonregresarr.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                cardLayout.show(pantalla, "MenuBotones");
            }
                 });
    
        
        pantalla.add(menu,"MenuBotones");
        pantalla.add(InicioSesion,"InicioSesion");
        pantalla.add(Registro,"Registro");
        
        add(pantalla);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new MenuInicial().setVisible(true);
    }
}
