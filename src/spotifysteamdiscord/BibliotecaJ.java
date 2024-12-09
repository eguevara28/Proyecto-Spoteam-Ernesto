package spotifysteamdiscord;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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

public class BibliotecaJ extends JFrame {

    ManejoJuegos mj = new ManejoJuegos();
    Users u = new Users();
    JList<String> itemList;
    JLabel photoLabel;
    JLabel labelnombre;
    JLabel labelartista;
    JLabel labelalbum;
    JLabel labelduracion;
    JLabel labelRuta;

    public BibliotecaJ() throws IOException {
        try {
            setTitle("Catálogo de Música");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(1, 2));

            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BorderLayout());
            String[] nombres = u.obtenerNombresJuegosUser(ManejoUsuarios.userlogged);
            itemList = new JList<>(nombres);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));

            JButton selectButton = new JButton("Ver Información");
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
            labelRuta = new JLabel("");

            JButton deleteButton = new JButton("Eliminar de Mi Biblioteca");

            rightPanel.add(photoLabel);
            rightPanel.add(Box.createVerticalStrut(20));
            rightPanel.add(labelnombre);
            rightPanel.add(labelartista);
            rightPanel.add(labelalbum);
            rightPanel.add(labelduracion);
            rightPanel.add(labelRuta);
            rightPanel.add(Box.createVerticalStrut(20));
            rightPanel.add(deleteButton);

            deleteButton.addActionListener(e -> {
                try {
                    if (itemList.getSelectedValue() != null) {
                        Juegos m = mj.buscarJuego(itemList.getSelectedValue());
                        u.eliminarJuegosUser(m.getTitulo(), ManejoUsuarios.userlogged);
                        refrescarlista();
                        JOptionPane.showMessageDialog(null, "Juego eliminado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, selecciona un juego para eliminar.");
                    }
                } catch (Exception er) {
                    JOptionPane.showMessageDialog(null, "Error: " + er.getMessage());
                }
            });

            selectButton.addActionListener(e -> {
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
                    labelnombre.setText("Título: " + j.getTitulo());
                    labelartista.setText("Desarrolladora: " + j.getDesarrollador());
                    labelalbum.setText("Género: " + j.getGenero());
                    labelduracion.setText("Fecha de Lanzamiento: " + j.getFechaLanzamiento());
                    labelRuta.setText("Ruta de Instalacion: "+j.getRutaArchivo());
                } catch (Exception er) {
                    JOptionPane.showMessageDialog(null, "Error: " + er.getMessage());
                }
            });

            backButton.addActionListener(e -> BibliotecaJ.this.dispose());

            add(leftPanel);
            add(rightPanel);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void refrescarlista() throws IOException {
        String[] nombres = u.obtenerNombresJuegosUser(ManejoUsuarios.userlogged);
        itemList.setListData(nombres);
        photoLabel.setText("");
        labelnombre.setText("");
        labelartista.setText("");
        labelalbum.setText("");
        labelduracion.setText("");
        labelRuta.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                BibliotecaJ frame = new BibliotecaJ();
                frame.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(BibliotecaJ.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
