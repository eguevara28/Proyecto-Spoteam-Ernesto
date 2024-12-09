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
public class ManejoChat {

    RandomAccessFile chat;

    public ManejoChat() {
        try {
            File mf = new File("Chat");
            mf.mkdir();
            chat = new RandomAccessFile("Chat/chats.std", "rw");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void escribirMensaje(String remitente, String contenido) throws IOException {
        chat.seek(chat.length());
        String mensaje = remitente + ": " + contenido;
        chat.writeUTF(mensaje);
    }

    public String[] obtenerMensajes() throws IOException {
        chat.seek(0);
        int contador = 0;
        while (chat.getFilePointer() < chat.length()) {
            chat.readUTF();
            contador++;
        }

        String[] mensajes = new String[contador];
        chat.seek(0);

        int index = 0;
        while (chat.getFilePointer() < chat.length()) {
            mensajes[index++] = chat.readUTF();
        }

        return mensajes;
    }

}
