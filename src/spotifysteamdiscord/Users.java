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
public class Users {

    private String username, password;
    private boolean estado;
    private RandomAccessFile usermusica, userjuego, userchat;
    private File mf;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
        estado = true;

        try {
            mf = new File(username);
            mf.mkdir();

            usermusica = new RandomAccessFile(username + "/musica" + username + ".std", "rw");
            userjuego = new RandomAccessFile(username + "/juegos" + username + ".std", "rw");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public Users() {

    }

    boolean añadirMusicaUser(Musica m, Users u) throws IOException {
        u.usermusica.seek(0);

        while (u.usermusica.getFilePointer() < u.usermusica.length()) {
            long posicionInicio = u.usermusica.getFilePointer();
            String tempmusica = u.usermusica.readUTF();

            if (tempmusica.equals(m.getTitulo())) {
                u.usermusica.readUTF();
                u.usermusica.readUTF();
                u.usermusica.readUTF();
                u.usermusica.readUTF();
                u.usermusica.readUTF();
                boolean estadoActual = u.usermusica.readBoolean();

                if (!estadoActual) {
                    long posicionEstado = u.usermusica.getFilePointer() - 1;
                    u.usermusica.seek(posicionEstado);
                    u.usermusica.writeBoolean(true);
                    return true;
                }

                return false;
            }

            u.usermusica.readUTF();
            u.usermusica.readUTF();
            u.usermusica.readUTF();
            u.usermusica.readUTF();
            u.usermusica.readUTF();
            u.usermusica.readBoolean();
        }

        u.usermusica.seek(u.usermusica.length());
        u.usermusica.writeUTF(m.getTitulo());
        u.usermusica.writeUTF(m.getArtista());
        u.usermusica.writeUTF(m.getAlbum());
        u.usermusica.writeUTF(m.getRutaArchivo());
        u.usermusica.writeUTF(m.getRutaImagen());
        u.usermusica.writeUTF(m.getDuracion());
        u.usermusica.writeBoolean(m.getEstado());
        return true;
    }

    boolean eliminarMusicaUser(String titulo, Users u) throws IOException {
        u.usermusica.seek(0);

        while (u.usermusica.getFilePointer() < u.usermusica.length()) {
            long posicionInicio = u.usermusica.getFilePointer();
            String tempmusica = u.usermusica.readUTF();

            if (tempmusica.equals(titulo)) {
                u.usermusica.readUTF();
                u.usermusica.readUTF();
                u.usermusica.readUTF();
                u.usermusica.readUTF();
                u.usermusica.readUTF();

                long posicionBooleano = u.usermusica.getFilePointer();
                u.usermusica.seek(posicionBooleano);
                u.usermusica.writeBoolean(false);

                return true;
            }

            u.usermusica.readUTF();
            u.usermusica.readUTF();
            u.usermusica.readUTF();
            u.usermusica.readUTF();
            u.usermusica.readUTF();
            u.usermusica.readBoolean();
        }

        return false;
    }

    String[] obtenerNombresCancionesUser(Users u) throws IOException {
        u.usermusica.seek(0);
        int contador = 0;

        while (u.usermusica.getFilePointer() < u.usermusica.length()) {
            u.usermusica.readUTF();
            u.usermusica.readUTF();
            u.usermusica.readUTF();
            u.usermusica.readUTF();
            u.usermusica.readUTF();
            u.usermusica.readUTF();
            boolean estado = u.usermusica.readBoolean();
            if (estado) {
                contador++;
            }
        }

        String[] nombresCanciones = new String[contador];
        u.usermusica.seek(0);

        int index = 0;
        while (u.usermusica.getFilePointer() < u.usermusica.length()) {
            String titulo = u.usermusica.readUTF();
            u.usermusica.readUTF();
            u.usermusica.readUTF();
            u.usermusica.readUTF();
            u.usermusica.readUTF();
            u.usermusica.readUTF();
            boolean estado = u.usermusica.readBoolean();

            if (estado) {
                nombresCanciones[index++] = titulo;
            }
        }

        return nombresCanciones;
    }

    boolean añadirJuegoUser(Juegos j, Users u) throws IOException {
        u.userjuego.seek(0);

        while (u.userjuego.getFilePointer() < u.userjuego.length()) {
            long posicionInicio = u.userjuego.getFilePointer();
            String tempmusica = u.userjuego.readUTF();

            if (tempmusica.equals(j.getTitulo())) {
                u.userjuego.readUTF();
                u.userjuego.readUTF();
                u.userjuego.readUTF();
                u.userjuego.readUTF();
                u.userjuego.readUTF();
                boolean estadoActual = u.userjuego.readBoolean();

                if (!estadoActual) {
                    long posicionEstado = u.userjuego.getFilePointer() - 1;
                    u.userjuego.seek(posicionEstado);
                    u.userjuego.writeBoolean(true);
                    return true;
                }

                return false;
            }

            u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readBoolean();
        }

        u.userjuego.seek(u.userjuego.length());
        u.userjuego.writeUTF(j.getTitulo());
        u.userjuego.writeUTF(j.getRutaArchivo());
        u.userjuego.writeUTF(j.getRutaImagen());
        u.userjuego.writeUTF(j.getDesarrollador());
        u.userjuego.writeUTF(j.getGenero());
        u.userjuego.writeUTF(j.getFechaLanzamiento());
        u.userjuego.writeBoolean(j.getEstado());
        return true;
    }

    boolean eliminarJuegosUser(String titulo, Users u) throws IOException {
        u.userjuego.seek(0);

        while (u.userjuego.getFilePointer() < u.userjuego.length()) {
            long posicionInicio = u.userjuego.getFilePointer();
            String tempjuego = u.userjuego.readUTF();

            if (tempjuego.equals(titulo)) {
                u.userjuego.readUTF();
                u.userjuego.readUTF();
                u.userjuego.readUTF();
                u.userjuego.readUTF();
                u.userjuego.readUTF();

                long posicionBooleano = u.userjuego.getFilePointer();
                u.userjuego.seek(posicionBooleano);
                u.userjuego.writeBoolean(false);

                return true;
            }

            u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readBoolean();
        }

        return false;
    }

    String[] obtenerNombresJuegosUser(Users u) throws IOException {
        u.userjuego.seek(0);
        int contador = 0;

        while (u.userjuego.getFilePointer() < u.userjuego.length()) {
            u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readUTF();
            boolean estado = u.userjuego.readBoolean();
            if (estado) {
                contador++;
            }
        }

        String[] nombresJuegos = new String[contador];
        u.userjuego.seek(0);

        int index = 0;
        while (u.userjuego.getFilePointer() < u.userjuego.length()) {
            String titulo = u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readUTF();
            u.userjuego.readUTF();
            boolean estado = u.userjuego.readBoolean();

            if (estado) {
                nombresJuegos[index++] = titulo;
            }
        }

        return nombresJuegos;
    }

    public RandomAccessFile getUsermusica() {
        return usermusica;
    }

    public RandomAccessFile getUserjuego() {
        return userjuego;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getEstado() {
        return estado;
    }

    public String getRutaJuego() {
        return mf.getAbsolutePath();
    }

}
