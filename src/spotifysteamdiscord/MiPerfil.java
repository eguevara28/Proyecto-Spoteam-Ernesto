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

public class MiPerfil extends JFrame {

    private JLabel labelNombre;
    private JLabel labelEstado;
    private JButton botonDesactivar;
    private JButton botonRegresar;
    private ManejoUsuarios manejoUsuarios;

    public MiPerfil() {
        manejoUsuarios = new ManejoUsuarios();

        setTitle("Información de Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        labelNombre = new JLabel("Nombre: ");
        labelEstado = new JLabel("Estado: ");
        botonDesactivar = new JButton("Desactivar Cuenta");
        botonRegresar = new JButton("Regresar");

        setLayout(new GridLayout(4, 1, 10, 10));
        add(labelNombre);
        add(labelEstado);
        add(botonDesactivar);
        add(botonRegresar);

        if (ManejoUsuarios.userlogged != null) {
            labelNombre.setText("Nombre: " + ManejoUsuarios.userlogged.getUsername());
            labelEstado.setText("Estado: " + (ManejoUsuarios.userlogged.getEstado() ? "Activo" : "Desactivado"));
        }

        botonDesactivar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean resultado = manejoUsuarios.desactivarUsuario(ManejoUsuarios.userlogged.getUsername());
                    if (resultado) {
                        JOptionPane.showMessageDialog(null, "Cuenta desactivada exitosamente.");
                        labelEstado.setText("Estado: Desactivado");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el usuario.");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al desactivar la cuenta: " + ex.getMessage());
                }
            }
        });

        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MiPerfil();
    }
}
