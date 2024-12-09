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
public class ManejoJuegos{

    RandomAccessFile juegos;

    public ManejoJuegos() {
        try {
            File mf = new File("Juegos");
            mf.mkdir();
            juegos = new RandomAccessFile("Juegos/juegos.stp", "rw");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    boolean añadirJuegos(Juegos j) throws IOException {
        juegos.seek(0);

        while (juegos.getFilePointer() < juegos.length()) {
            String tempmusica = juegos.readUTF();

            if (tempmusica.equals(j.getTitulo())) {
                return false;
            }
        }

        juegos.seek(juegos.length());
        juegos.writeUTF(j.getTitulo());
        juegos.writeUTF(j.getRutaArchivo());
        juegos.writeUTF(j.getRutaImagen());
        juegos.writeUTF(j.getDesarrollador());
        juegos.writeUTF(j.getGenero());
        juegos.writeUTF(j.getFechaLanzamiento());
        juegos.writeBoolean(j.getEstado());
        return true;
    }

    Juegos buscarJuego(String tituloJuego) throws IOException {
        juegos.seek(0);

        while (juegos.getFilePointer() < juegos.length()) {
            String titulo = juegos.readUTF();
            String rutaArchivo = juegos.readUTF();
            String rutaImagen = juegos.readUTF();
            String desarrolladora = juegos.readUTF();
            String genero = juegos.readUTF();
            String fecha = juegos.readUTF();
            boolean estado=juegos.readBoolean();

            if (titulo.equalsIgnoreCase(tituloJuego) && estado) {
                return new Juegos(titulo, rutaArchivo, rutaImagen, desarrolladora, genero, fecha);
            }
        }

        return null;
    }

    String[] obtenerNombresJuegos() throws IOException {
        juegos.seek(0);
        int contador = 0;

        while (juegos.getFilePointer() < juegos.length()) {
            juegos.readUTF();
            juegos.readUTF();
            juegos.readUTF();
            juegos.readUTF();
            juegos.readUTF();
            juegos.readUTF();
            boolean estado=juegos.readBoolean();
            if(estado){
            contador++;
            }
        }

        String[] nombresCanciones = new String[contador];
        juegos.seek(0);

        int index = 0;
        while (juegos.getFilePointer() < juegos.length()) {
            String titulo = juegos.readUTF();
            juegos.readUTF();
            juegos.readUTF();
            juegos.readUTF();
            juegos.readUTF();
            juegos.readUTF();
            boolean estado=juegos.readBoolean();
            if(estado){
            nombresCanciones[index++] = titulo;
            }
        }

        return nombresCanciones;
    }

}
