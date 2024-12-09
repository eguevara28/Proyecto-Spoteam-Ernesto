/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifysteamdiscord;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ernes
 */
public class Aplicacion extends JFrame {

    JPanel botones;
    JPanel centro;
    CardLayout cardLayout;

    public Aplicacion() {
        setSize(1500, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        botones = new JPanel(new GridLayout(1, 9));
        botones.setSize(1500, 800);

        //Botones del Menu
        JButton botonbibliotecaj = new JButton("Biblioteca de Juegos");
        JButton botonbibliotecam = new JButton("Biblioteca de Musica");
        JButton botoncatalogoj = new JButton("Catalogo de Juegos");
        JButton botoncatologom = new JButton("Catalogo de Musica");
        JButton botonchat = new JButton("Chat");
        JButton botonbuscar = new JButton("Buscar Personas");
        JButton botonmiperfil = new JButton("Mi Perfil");
        JButton botoncs = new JButton("Cerrar Sesion");
        JLabel taguser = new JLabel("@");
        if (ManejoUsuarios.esAdmin) {
            JButton botonañadirmusica = new JButton("Añadir Musica");
            JButton botonañadirjuego = new JButton("Añadir Juego");
            botones.add(botonañadirmusica);
            botones.add(botonañadirjuego);

            JPanel agm = new JPanel();
            agm.setSize(1500, 750);
            agm.setLayout(null);

            JLabel labelTitulo = new JLabel("Título:");
            JTextField textTitulo = new JTextField();
            labelTitulo.setBounds(700, 100, 150, 40);
            textTitulo.setBounds(700, 150, 150, 40);

            JLabel labelArtista = new JLabel("Artista:");
            JTextField textArtista = new JTextField();
            labelArtista.setBounds(700, 200, 150, 40);
            textArtista.setBounds(700, 250, 150, 40);

            JLabel labelDuracion = new JLabel("Duración:");
            JTextField textDuracion = new JTextField();
            labelDuracion.setBounds(700, 300, 150, 40);
            textDuracion.setBounds(700, 350, 150, 40);

            JLabel labelRuta = new JLabel("Ruta del archivo:");
            JTextField textRuta = new JTextField();
            labelRuta.setBounds(700, 400, 150, 40);
            textRuta.setBounds(700, 450, 150, 40);

            JButton botonRegistrar = new JButton("Registrar Canción");
            botonRegistrar.setBounds(700, 500, 150, 40);

            agm.add(labelTitulo);
            agm.add(textTitulo);

            agm.add(labelArtista);
            agm.add(textArtista);

            agm.add(labelDuracion);
            agm.add(textDuracion);

            agm.add(labelRuta);
            agm.add(textRuta);
            agm.add(botonRegistrar);

            botonañadirmusica.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(centro, "agm");
                }
            });

            JPanel agj = new JPanel();
            agj.setSize(1500, 750);
            agj.setLayout(null);

            JLabel labelNombre = new JLabel("Titulo :");
            JTextField textNombre = new JTextField();
            labelNombre.setBounds(700, 100, 150, 40);
            textNombre.setBounds(700, 150, 150, 40);

            JLabel labelDesarrollador = new JLabel("Desarrollador:");
            JTextField textDesarrollador = new JTextField();
            labelDesarrollador.setBounds(700, 200, 150, 40);
            textDesarrollador.setBounds(700, 250, 150, 40);

            JLabel labelGenero = new JLabel("Genero:");
            JTextField textGenero = new JTextField();
            labelGenero.setBounds(700, 300, 150, 40);
            textGenero.setBounds(700, 350, 150, 40);

            JLabel labelRutaA = new JLabel("Ruta del archivo:");
            JTextField textRutaA = new JTextField();
            labelRutaA.setBounds(700, 400, 150, 40);
            textRutaA.setBounds(700, 450, 150, 40);
            
            JLabel labelFecha = new JLabel("Fecha de Lanzamiento:");
            JTextField textFecha = new JTextField();
            labelFecha.setBounds(700, 500, 150, 40);
            textFecha.setBounds(700, 550, 150, 40);

            JButton botonRegistrarA = new JButton("Registrar Juego");
            botonRegistrarA.setBounds(700, 600, 150, 40);

            agj.add(labelTitulo);
            agj.add(textTitulo);

            agj.add(labelArtista);
            agj.add(textArtista);

            agj.add(labelDuracion);
            agj.add(textDuracion);

            agj.add(labelRuta);
            agj.add(textRuta);
            
            agj.add(labelFecha);
            agj.add(textFecha);
            agj.add(botonRegistrar);

            centro.add(agm, "agm");
            centro.add(agj,"agj");
        }

        JPanel panelblancotemporal = new JPanel();

        botones.add(botonbibliotecaj);
        botones.add(botonbibliotecam);
        botones.add(botoncatalogoj);
        botones.add(botoncatologom);
        botones.add(botonchat);
        botones.add(botonbuscar);
        botones.add(botonmiperfil);
        botones.add(botoncs);
        botones.add(taguser);

        centro.add(panelblancotemporal, "pb");

        add(botones);
        add(centro);
    }

    public static void main(String[] args) {
        new Aplicacion().setVisible(true);
    }
}
