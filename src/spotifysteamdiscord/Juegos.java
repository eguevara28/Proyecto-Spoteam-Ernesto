/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifysteamdiscord;

/**
 *
 * @author ernes
 */
public class Juegos extends Multimedia {

    private String genero;
    private String desarrollador;
    private String fechaLanzamiento;
    private boolean estado;

    public Juegos(String titulo, String rutaArchivo, String rutaImagen, String genero, String desarrollador, String fechaLanzamiento) {
        super(titulo, rutaArchivo, rutaImagen);
        this.genero = genero;
        this.desarrollador = desarrollador;
        this.fechaLanzamiento = fechaLanzamiento;
        estado=true;
    }

    public String getGenero() {
        return genero;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }
    
    public boolean getEstado(){
        return estado;
    }
}
