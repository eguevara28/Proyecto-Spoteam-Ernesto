/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifysteamdiscord;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ernes
 */
public class MenuAplicacion extends JFrame {

    JPanel pantallota;
    JPanel centro;

    ManejoMusica mm = new ManejoMusica();
    ManejoJuegos mj = new ManejoJuegos();
    ManejoUsuarios mu = new ManejoUsuarios();

    public MenuAplicacion() {
        initComponents();
    }

    public void initComponents() {
        setTitle("Spoteam: "+ManejoUsuarios.userlogged.getUsername());
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pantallota = new JPanel();
        pantallota.setSize(1000, 750);
        centro = new JPanel();
        pantallota.setLayout(new BorderLayout());
        centro.setLayout(new GridLayout(0, 2, 10, 10));

        if (ManejoUsuarios.esAdmin) {
            JButton bagm = new JButton("Agregar Musica");
            JButton bagj = new JButton("Agregar Juego");
            JButton bvu = new JButton("Ver Usuarios");
            centro.add(bagm);
            centro.add(bagj);
            centro.add(bvu);

            bvu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ListaUsuarios lu=new ListaUsuarios();
                    lu.setVisible(true);
                }
            });

            bagm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String nombre = JOptionPane.showInputDialog("Escriba el nombre de la cancion:");
                        String artista = JOptionPane.showInputDialog("Escriba el nombre de artista:");
                        String album = JOptionPane.showInputDialog("Escriba el album de la cancion:");
                        String duracion = JOptionPane.showInputDialog("Escriba la duracion de la cancion: (Formato: Minutos:Segundos)");
                        JFileChooser fileChoosermp3 = new JFileChooser();
                        fileChoosermp3.setFileFilter(new FileNameExtensionFilter("Archivos MP3", "mp3"));
                        fileChoosermp3.setDialogTitle("Selecciona un archivo mp3");
                        fileChoosermp3.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        if (fileChoosermp3.showOpenDialog(MenuAplicacion.this) != JFileChooser.APPROVE_OPTION) {
                            return;
                        }
                        String rutamp3bsoluta = fileChoosermp3.getSelectedFile().getAbsolutePath();
                        JFileChooser fileChooserImg = new JFileChooser();
                        fileChooserImg.setFileFilter(new FileNameExtensionFilter("Archivos JPG", "jpg", "png"));
                        fileChooserImg.setDialogTitle("Selecciona una Imagen");
                        fileChooserImg.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        if (fileChooserImg.showOpenDialog(MenuAplicacion.this) != JFileChooser.APPROVE_OPTION) {
                            return;
                        }
                        String rutaimgabsoluta = fileChooserImg.getSelectedFile().getAbsolutePath();
                        Musica m = new Musica(nombre, rutamp3bsoluta, rutaimgabsoluta, artista, album, duracion);
                        boolean verificar = mm.añadirMusica(m);
                        if (verificar) {
                            JOptionPane.showMessageDialog(null, "Se ha agregado la cancion al catalogo exitosamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se ha logrado añadir la cancion");
                        }
                    } catch (Exception er) {
                        er.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error: " + er.getMessage());
                    }
                }
            });

            bagj.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String nombre = JOptionPane.showInputDialog("Escriba el nombre del juego:");
                        String desarrolladora = JOptionPane.showInputDialog("Escriba el nombre de la desarrolladora:");
                        String genero = JOptionPane.showInputDialog("Escriba del genero del juego:");
                        String fecha = JOptionPane.showInputDialog("Escriba la Fecha de Lanzamiento: ");
                        JFileChooser fileChooserImg = new JFileChooser();
                        fileChooserImg.setFileFilter(new FileNameExtensionFilter("Archivos JPG", "jpg", "png"));
                        fileChooserImg.setDialogTitle("Selecciona un archivo jpg o png");
                        fileChooserImg.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        if (fileChooserImg.showOpenDialog(MenuAplicacion.this) != JFileChooser.APPROVE_OPTION) {
                            return;
                        }
                        String rutajpgabsoluta = fileChooserImg.getSelectedFile().getAbsolutePath();
                        Juegos j = new Juegos(nombre, ManejoUsuarios.userlogged.getRutaJuego(), rutajpgabsoluta, genero, desarrolladora, fecha);
                        boolean verificar = mj.añadirJuegos(j);
                        if (verificar) {
                            JOptionPane.showMessageDialog(null, "Se agrego el juego al catalogo");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se ha podido agregar el juego");
                        }
                    } catch (Exception er) {
                        JOptionPane.showMessageDialog(null, "Error: " + er.getMessage());
                    }
                }
            });
        }

        JButton bmm = new JButton("Mi Musica");
        JButton bmj = new JButton("Mis Juegos");
        JButton bcm = new JButton("Catalogo de Musica");
        JButton bcj = new JButton("Catalogo de Juegos");
        JButton bmp = new JButton("Mi Perfil");
        JButton bcg = new JButton("Chat");
        JButton bcs = new JButton("Cerrar Sesion");
        
        bmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MiPerfil cm;
                cm = new MiPerfil();
                cm.setVisible(true);
                MenuAplicacion.this.dispose();
            }
        });

        bcg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChatGeneral cm;
                cm = new ChatGeneral();
                cm.setVisible(true);
            }
        });

        bcs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mu.desloggear();
                ManejoUsuarios.esAdmin=false;
                MenuAplicacion.this.dispose();
                MenuInicial mi = new MenuInicial();
                mi.setVisible(true);
            }
        });

        bcj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CatalogoJ cm;
                try {
                    cm = new CatalogoJ();
                    cm.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MenuAplicacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        bmj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BibliotecaJ cm;
                try {
                    cm = new BibliotecaJ();
                    cm.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MenuAplicacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        bcm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CatalogoM cm;
                try {
                    cm = new CatalogoM();
                    cm.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MenuAplicacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        bmm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BibliotecaU bu;
                try {
                    bu = new BibliotecaU();
                    bu.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MenuAplicacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        centro.add(bmm);
        centro.add(bmj);
        centro.add(bcm);
        centro.add(bcj);
        centro.add(bmp);
        centro.add(bcg);
        centro.add(bcs);
        pantallota.add(centro, BorderLayout.CENTER);
        add(pantallota);
        MenuAplicacion.this.setVisible(true);
    }

    public static void main(String[] args) {
        MenuAplicacion ma = new MenuAplicacion();
        ma.setVisible(true);
    }

    public static String getLastTwoFolders(String path) {
        String separator = File.separator.equals("\\") ? "\\\\" : File.separator;

        String[] parts = path.split(separator);

        int length = parts.length;
        if (length >= 3) {
            return parts[length - 3] + File.separator + parts[length - 2] + File.separator + parts[length - 1];
        } else if (length == 2) {
            return parts[0] + File.separator + parts[1];
        } else {
            return path;
        }
    }

}
