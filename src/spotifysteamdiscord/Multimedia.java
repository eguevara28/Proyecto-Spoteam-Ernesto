package spotifysteamdiscord;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ernes
 */
public class Multimedia {

    private String titulo;
    private String rutaArchivo;
    private String rutaImagen;

    public Multimedia(String titulo, String rutaArchivo, String rutaImagen) {
        this.titulo = titulo;
        this.rutaArchivo = rutaArchivo;
        this.rutaImagen=rutaImagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }
    
    
}
