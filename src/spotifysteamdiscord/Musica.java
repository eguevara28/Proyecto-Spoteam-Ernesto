/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifysteamdiscord;

/**
 *
 * @author ernes
 */
public class Musica extends Multimedia {

    private String artista;
    private String album;
    private String duracion;
    private boolean estado;
    
    public Musica(String titulo, String rutaArchivo,String rutaImagen, String artista, String album, String duracion) {
        super(titulo, rutaArchivo, rutaImagen);
        this.artista = artista;
        this.album = album;
        this.duracion = duracion;
        estado=true;
    }

    public String getArtista() {
        return artista;
    }

    public String getAlbum() {
        return album;
    }

    public String getDuracion() {
        return duracion;
    }
    
    public boolean getEstado(){
        return estado;
    }
}
