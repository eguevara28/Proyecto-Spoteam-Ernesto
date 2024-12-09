/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifysteamdiscord;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author ernes
 */
public class ManejoUsuarios {

    RandomAccessFile users;
    public static Users userlogged = null;
    static boolean esAdmin;

    public ManejoUsuarios() {
        try {
            File mf = new File("Usuarios");
            mf.mkdir();
            users = new RandomAccessFile("Usuarios/users.std", "rw");
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    void loggear(Users u) {
        userlogged = u;
    }

    void desloggear() {
        userlogged = null;
    }

    boolean añadirUsuario(Users u) throws IOException {
        users.seek(0);

        while (users.getFilePointer() < users.length()) {
            String tempuser = users.readUTF();
            users.readUTF();
            users.readBoolean();

            if (tempuser.equals(u.getUsername())) {
                return false;
            }
        }

        users.seek(users.length());
        users.writeUTF(u.getUsername());
        users.writeUTF(u.getPassword());
        users.writeBoolean(u.getEstado());
        return true;
    }

    boolean iniciarSesion(Users u) throws IOException {
        users.seek(0);

        while (users.getFilePointer() < users.length()) {
            String tempuser = users.readUTF();
            String temppassword = users.readUTF();
            boolean estado = users.readBoolean();

            if (tempuser.equals(u.getUsername()) && tempuser.equals("Ernesto") && temppassword.equals(u.getPassword()) && estado) {
                esAdmin = true;
                return true;
            }

            if (tempuser.equals(u.getUsername()) && temppassword.equals(u.getPassword()) && estado) {
                esAdmin = false;
                return true;
            }
        }

        return false;
    }

    public String[] obtenerUsuariosActivos() throws IOException {
        users.seek(0);
        int contador = 0;

        while (users.getFilePointer() < users.length()) {
            users.readUTF();
            users.readUTF();
            boolean estado = users.readBoolean();

            if (estado) {
                contador++;
            }
        }

        String[] usuariosActivos = new String[contador];
        users.seek(0);

        int index = 0;
        while (users.getFilePointer() < users.length()) {
            String username = users.readUTF();
            users.readUTF();
            boolean estado = users.readBoolean();

            if (estado) {
                usuariosActivos[index++] = username;
            }
        }

        return usuariosActivos;
    }

    public boolean desactivarUsuario(String username) throws IOException {
        users.seek(0);

        while (users.getFilePointer() < users.length()) {
            String tempuser = users.readUTF();
            String temppassword = users.readUTF();
            boolean estado = users.readBoolean();

            if (tempuser.equals(username)) {
                long posicionActual = users.getFilePointer();
                users.seek(posicionActual - 1);
                users.writeBoolean(false);
                return true;
            }
        }
        return false;
    }
    
public boolean reactivarUsuario(String username, String password) throws IOException {
    users.seek(0);

    while (users.getFilePointer() < users.length()) {
        String tempuser = users.readUTF();
        String temppassword = users.readUTF();
        boolean estado = users.readBoolean();

        if (tempuser.equals(username) && temppassword.equals(password)) {
            if (!estado) {
                long posicionActual = users.getFilePointer();
                users.seek(posicionActual - 1);
                users.writeBoolean(true);
                return true;
            } else {
                return false;
            }
        }
    }
    return false;
}



}
