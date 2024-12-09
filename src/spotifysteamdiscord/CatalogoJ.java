/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifysteamdiscord;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author ernes
 */
public class CatalogoJ extends JFrame {

    ManejoJuegos mj = new ManejoJuegos();
    Users u = new Users();

    public CatalogoJ() throws IOException {
        try {
            setTitle("Catalogo Juegos");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(1, 2));

            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BorderLayout());
            String[] nombres = mj.obtenerNombresJuegos();
            JList<String> itemList = new JList<>(nombres);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));

            JButton selectButton = new JButton("Seleccionar");
            JButton backButton = new JButton("Regresar");
            buttonPanel.add(selectButton);
            buttonPanel.add(backButton);
            leftPanel.add(new JScrollPane(itemList), BorderLayout.CENTER);
            leftPanel.add(buttonPanel, BorderLayout.SOUTH);

            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
            JLabel photoLabel = new JLabel("");
            photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            photoLabel.setPreferredSize(new Dimension(200, 200));

            JLabel labelnombre = new JLabel("Titulo: ");
            JLabel labelartista = new JLabel("Desarrolladora: ");
            JLabel labelalbum = new JLabel("Genero: ");
            JLabel labelduracion = new JLabel("Fecha de Lanzamiento: ");

            JButton addButton = new JButton("Agregar");

            rightPanel.add(photoLabel);
            rightPanel.add(Box.createVerticalStrut(20));
            rightPanel.add(labelnombre);
            rightPanel.add(labelartista);
            rightPanel.add(labelalbum);
            rightPanel.add(labelduracion);
            rightPanel.add(Box.createVerticalStrut(20));
            rightPanel.add(addButton);

            selectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Juegos j = mj.buscarJuego(itemList.getSelectedValue());
                        String rutaImagen = "/" + j.getRutaImagen();
                        File imgFile = new File(rutaImagen);

                        if (imgFile.exists()) {
                            ImageIcon portada = new ImageIcon(rutaImagen);
                            photoLabel.setIcon(portada);
                        } else {
                            JOptionPane.showMessageDialog(null, "Imagen no encontrada: " + rutaImagen);
                        }
                        labelnombre.setText("Titulo: " + j.getTitulo());
                        labelartista.setText("Desarrolladora:" + j.getDesarrollador());
                        labelalbum.setText("Genero: " + j.getGenero());
                        labelduracion.setText("Fecha de Lanzamiento: " + j.getFechaLanzamiento());
                    } catch (Exception er) {
                        JOptionPane.showMessageDialog(null, "Error: " + er.getMessage());
                    }
                }
            });

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Juegos j = mj.buscarJuego(itemList.getSelectedValue());
                        boolean verificar=u.añadirJuegoUser(j, ManejoUsuarios.userlogged);
                        if(verificar){
                            JOptionPane.showMessageDialog(null, "Se ha agregado este juego a tu biblioteca");
                        }
                    } catch (Exception er) {
                        JOptionPane.showMessageDialog(null, "Error: " + er.getMessage());
                    }
                }
            });

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        CatalogoJ.this.dispose();
                    } catch (Exception er) {
                        JOptionPane.showMessageDialog(null, "Error: " + er.getMessage());
                    }
                }
            });

            add(leftPanel);
            add(rightPanel);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CatalogoJ frame;
            try {
                frame = new CatalogoJ();
                frame.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(CatalogoM.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
