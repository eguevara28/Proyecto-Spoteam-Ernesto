package spotifysteamdiscord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ChatGeneral extends JFrame {

    private JList<String> listaMensajes;
    private DefaultListModel<String> modeloListaMensajes;
    private JTextField campoMensaje;
    private JButton botonEnviar;
    private ManejoChat manejoChat;

    public ChatGeneral() {
        manejoChat = new ManejoChat();

        setTitle("Chat General");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloListaMensajes = new DefaultListModel<>();
        listaMensajes = new JList<>(modeloListaMensajes);
        listaMensajes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaMensajes.setLayoutOrientation(JList.VERTICAL);

        JScrollPane scrollLista = new JScrollPane(listaMensajes);

        campoMensaje = new JTextField();
        botonEnviar = new JButton("Enviar");

        JPanel panelInput = new JPanel();
        panelInput.setLayout(new BorderLayout());
        panelInput.add(campoMensaje, BorderLayout.CENTER);
        panelInput.add(botonEnviar, BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(scrollLista, BorderLayout.CENTER);
        add(panelInput, BorderLayout.SOUTH);

        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensaje = campoMensaje.getText().trim();
                if (!mensaje.isEmpty()) {
                    try {
                        manejoChat.escribirMensaje(ManejoUsuarios.userlogged.getUsername(), mensaje);

                        actualizarListaMensajes();

                        campoMensaje.setText("");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error al enviar el mensaje: " + ex.getMessage());
                    }
                }
            }
        });

        JButton botonRegresar = new JButton("Regresar");
        panelInput.add(botonRegresar, BorderLayout.WEST);

        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
        actualizarListaMensajes();
    }

    private void actualizarListaMensajes() {
        try {
            String[] mensajes = manejoChat.obtenerMensajes();

            modeloListaMensajes.clear();

            for (String mensaje : mensajes) {
                modeloListaMensajes.addElement(mensaje);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la lista de mensajes: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ChatGeneral();
    }
}
