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
import java.io.FileInputStream;
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
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javazoom.jl.player.Player;

/**
 *
 * @author ernes
 */
public class BibliotecaU extends JFrame {

    ManejoMusica mm = new ManejoMusica();
    Users u = new Users();

    private Player player;
    private Thread playThread;

    JList<String> itemList;
    JLabel labelnombre;
    JLabel labelartista;
    JLabel labelalbum;
    JLabel labelduracion;
    JLabel photoLabel;

    public BibliotecaU() throws IOException {
        try {
            setTitle("Catalogo Musica");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(1, 2));

            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BorderLayout());
            String[] nombres = u.obtenerNombresCancionesUser(ManejoUsuarios.userlogged);
            itemList = new JList<>(nombres);

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
            photoLabel = new JLabel("");
            photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            photoLabel.setPreferredSize(new Dimension(200, 200));

            labelnombre = new JLabel("");
            labelartista = new JLabel("");
            labelalbum = new JLabel("");
            labelduracion = new JLabel("");

            JButton playButton = new JButton("Reproducir");
            JButton stopButton = new JButton("Detener");
            JButton deleteButton = new JButton("Eliminar de Mi Biblioteca");

            rightPanel.add(photoLabel);
            rightPanel.add(Box.createVerticalStrut(20));
            rightPanel.add(labelnombre);
            rightPanel.add(labelartista);
            rightPanel.add(labelalbum);
            rightPanel.add(labelduracion);
            rightPanel.add(Box.createVerticalStrut(20));
            rightPanel.add(playButton);
            rightPanel.add(stopButton);
            rightPanel.add(deleteButton);

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Musica m = mm.buscarMusica(itemList.getSelectedValue());
                        u.eliminarMusicaUser(m.getTitulo(), ManejoUsuarios.userlogged);
                        if (player != null) {
                            player.close();
                        }
                        if (playThread != null) {
                            playThread.interrupt();
                        }
                        refrescarlista();
                    } catch (Exception er) {
                        JOptionPane.showMessageDialog(null, "Error: " + er.getMessage());
                    }
                }
            });

            selectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Musica m = mm.buscarMusica(itemList.getSelectedValue());
                        String rutaImagen = "/" + m.getRutaImagen();
                        File imgFile = new File(rutaImagen);

                        if (imgFile.exists()) {
                            ImageIcon portada = new ImageIcon(rutaImagen);
                            photoLabel.setIcon(portada);
                        } else {
                            JOptionPane.showMessageDialog(null, "Imagen no encontrada: " + rutaImagen);
                        }
                        labelnombre.setText("Nombre: " + m.getTitulo());
                        labelartista.setText("Artista:" + m.getArtista());
                        labelalbum.setText("Album: " + m.getAlbum());
                        labelduracion.setText("Duracion: " + m.getDuracion());
                    } catch (Exception er) {
                        JOptionPane.showMessageDialog(null, "Error: " + er.getMessage());
                    }
                }
            });

            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (playThread != null && playThread.isAlive()) {
                        JOptionPane.showMessageDialog(null, "Ya se está reproduciendo una canción.");
                        return;
                    }

                    try {
                        Musica m = mm.buscarMusica(itemList.getSelectedValue());
                        if (m == null) {
                            JOptionPane.showMessageDialog(null, "No se encontró la música seleccionada.");
                            return;
                        }

                        playThread = new Thread(() -> {
                            try {
                                player = new Player(new FileInputStream(m.getRutaArchivo()));
                                player.play();
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Error al reproducir: " + ex.getMessage());
                            }
                        });

                        playThread.start();
                    } catch (Exception er) {
                        JOptionPane.showMessageDialog(null, "Error: " + er.getMessage());
                    }
                }
            });

            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (player != null) {
                        player.close();
                    }
                    if (playThread != null) {
                        playThread.interrupt();
                    }
                }
            });

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (player != null) {
                        player.close();
                    }
                    if (playThread != null) {
                        playThread.interrupt();
                    }
                    BibliotecaU.this.dispose();
                }
            });

            add(leftPanel);
            add(rightPanel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BibliotecaU frame;
            try {
                frame = new BibliotecaU();
                frame.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(CatalogoM.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void refrescarlista() throws IOException {
        String[] nombres = u.obtenerNombresCancionesUser(ManejoUsuarios.userlogged);
        photoLabel.setText(null);
        labelnombre.setText("");
        labelartista.setText("");
        labelalbum.setText("");
        labelduracion.setText("");
        itemList.setListData(nombres);
    }

}
