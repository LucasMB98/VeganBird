package juego;
import java.awt.Image;
import entorno.Finish;
import entorno.Comienzo;
import entorno.Entorno;
import entorno.Herramientas;



public class Fondo {
    Image img;
    Image img2;
    Image nombre;
    int x = 400;
    int y = 300;

    
    /**
     * Carga una imagen de fondo.
     */
    Fondo (){
        img = Herramientas.cargarImagen("images/waterfall.gif");
        img2 = Herramientas.cargarImagen("images/gameover.gif");
        nombre = Herramientas.cargarImagen("images/veganNombre.png");
    }
    
    /**
     * Dibuja el fondo en un entorno especifico.
     * @param entorno
     */
    void dibujarFondo(Entorno entorno){
        entorno.dibujarImagen(img, x, y, 0.0, 2.3);
    }

    /**
     * Dibuja la imagen de GameOver.
     * @param entorno
     */
    void dibujarFinal(Finish entorno){
        entorno.dibujarImagen(img2, x, y, 0.0, 2);
    }
    
    /**
     * Dibuja el nombre del juego en el menu.
     * @param comienzo Ventana nueva.
     */
    void dibujarNombreJuego(Comienzo comienzo){
        comienzo.dibujarImagen(nombre, x, y, 0.0, 0.7);
    }
    
    /**
     * Dibuja fondo en el menu.
     * @param comienzo Ventana nueva.
     */
    void dibujarComienzo(Comienzo comienzo){
        comienzo.dibujarImagen(img, x, y, 0.0, 2.3);
    }




}