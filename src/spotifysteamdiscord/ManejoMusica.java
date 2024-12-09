/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifysteamdiscord;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

/**
 *
 * @author ernes
 */
public class ManejoMusica {

    RandomAccessFile musica;

    public ManejoMusica() {
        try {
            File mf = new File("Musica");
            mf.mkdir();
            musica = new RandomAccessFile("Musica/musica.stp", "rw");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public boolean añadirMusica(Musica m) throws IOException {
        musica.seek(0);

        while (musica.getFilePointer() < musica.length()) {
            String tempmusica = musica.readUTF();
            musica.readUTF();
            musica.readUTF();
            musica.readUTF();
            musica.readUTF();
            musica.readUTF();
            boolean estado = musica.readBoolean();

            if (tempmusica.equals(m.getTitulo())) {
                if (!estado) {
                    musica.seek(musica.getFilePointer() - 1);
                    musica.writeBoolean(true);
                    return true;
                }
                return false;
            }
        }

        musica.seek(musica.length());
        musica.writeUTF(m.getTitulo());
        musica.writeUTF(m.getArtista());
        musica.writeUTF(m.getAlbum());
        musica.writeUTF(m.getRutaArchivo());
        musica.writeUTF(m.getRutaImagen());
        musica.writeUTF(m.getDuracion());
        musica.writeBoolean(m.getEstado());
        return true;
    }

    Musica buscarMusica(String tituloCancion) throws IOException {
        musica.seek(0);

        while (musica.getFilePointer() < musica.length()) {
            String titulo = musica.readUTF();
            String artista = musica.readUTF();
            String album = musica.readUTF();
            String rutaArchivo = musica.readUTF();
            String rutaImagen = musica.readUTF();
            String duracion = musica.readUTF();
            boolean estado = musica.readBoolean();

            if (titulo.equalsIgnoreCase(tituloCancion) && estado) {
                return new Musica(titulo, rutaArchivo, rutaImagen, artista, album, duracion);
            }
        }

        return null;
    }

    String[] obtenerNombresCanciones() throws IOException {
        musica.seek(0);
        int contador = 0;

        while (musica.getFilePointer() < musica.length()) {
            musica.readUTF();
            musica.readUTF();
            musica.readUTF();
            musica.readUTF();
            musica.readUTF();
            musica.readUTF();
            boolean estado=musica.readBoolean();
            if(estado){
            contador++;
            }
        }

        String[] nombresCanciones = new String[contador];
        musica.seek(0);

        int index = 0;
        while (musica.getFilePointer() < musica.length()) {
            String titulo = musica.readUTF();
            musica.readUTF();
            musica.readUTF();
            musica.readUTF();
            musica.readUTF();
            musica.readUTF();
            boolean estado = musica.readBoolean();

            if (estado) {
                nombresCanciones[index++] = titulo;
            }
        }

        return nombresCanciones;
    }

}
