/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifysteamdiscord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ListaUsuarios extends JFrame {
    
    private JList<String> listaUsuarios;
    private DefaultListModel<String> modeloListaUsuarios;
    private JButton botonRegresar;
    private ManejoUsuarios manejoUsuarios;

    public ListaUsuarios() {
        manejoUsuarios = new ManejoUsuarios();
        setTitle("Usuarios Activos");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloListaUsuarios = new DefaultListModel<>();
        listaUsuarios = new JList<>(modeloListaUsuarios);
        listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaUsuarios.setLayoutOrientation(JList.VERTICAL);

        JScrollPane scrollLista = new JScrollPane(listaUsuarios);
        botonRegresar = new JButton("Regresar");

        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout());
        panelBoton.add(botonRegresar);

        setLayout(new BorderLayout());
        add(scrollLista, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
        cargarUsuariosActivos();
    }

    private void cargarUsuariosActivos() {
        try {
            String[] usuariosActivos = manejoUsuarios.obtenerUsuariosActivos();
            modeloListaUsuarios.clear();
            for (String usuario : usuariosActivos) {
                modeloListaUsuarios.addElement(usuario);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener los usuarios activos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ListaUsuarios();
    }
}

