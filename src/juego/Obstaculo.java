package juego;

import entorno.Entorno;
import entorno.Herramientas;
import java.awt.Image;
import java.awt.Color;
import java.awt.Rectangle;


public class Obstaculo{
    int x;
    int y;
    int ancho;
    int alto;
    Image imagen;

    /**
     * Crea un obstaculo.
     * @param imagen Nombre + extension de la imagen.
     * @param x Posicion x en pantalla.
     * @param y Posicion y en pantalla. y < 400 el objeto sube. y > 400 el objeto baja.
     * @param ancho Ancho de la imagen
     * @param alto Alto de la imagen
     */
    Obstaculo(String imagen,int x, int y,int ancho, int alto){
        this.x=x;
        this.y=y;
        this.imagen = Herramientas.cargarImagen(imagen);
        this.ancho = ancho; 
        this.alto = alto;
    }

    /**
     * Dibuja un rectangulo auxiliar para saber en que posicion se encuentra la hitbox.
     * @param entorno
     */
    void dibujarseRectangulo(Entorno entorno){
        entorno.dibujarRectangulo(this.x, this.y, ancho,50,0, Color.blue);
    }

    /**
     * Dibuja la imagen de un obstaculo en un entorno especifico.
     * @param entorno Entorno especifico.
     */
    void dibujarseImagenArriba(Entorno entorno){
        entorno.dibujarImagen(imagen,this.x, this.y+150,0, 1); //Se modifica la y, ya que el dibujador causa problemas de posicionamiento en pantalla.
    }

    /**
     * Dibuja la imagen de un obstaculo en un entorno especifico.
     * @param entorno Entorno especifico.
     */
    void dibujarseImagenAbajo(Entorno entorno){
        entorno.dibujarImagen(imagen,this.x, this.y-50,0, 1); //Se modifica la y, ya que el dibujador causa problemas de posicionamiento en pantalla.
    }

    /**
     * Metodo auxiliar.
     * @return Devuelve un rectangulo con exactamente el tamaño del obstaculo.
     */
    Rectangle dameRectangulo (){
        Rectangle r = new Rectangle(this.x-30,this.y,ancho,alto);
        return r;
    }

    /**
     * Mueve el obstaculo.
     * @param modificador Si es positivo el objeto se mueve para la izquierda. Cuando es negativo para la derecha.
     */
    void moverObstaculo(int modificador){
        this.x = this.x - modificador;
    }
    
    boolean seTocan(Fruta f) {
    	if (this.dameRectangulo().contains(f.dameRectanguloFruta()) || this.dameRectangulo().intersects(f.dameRectanguloFruta())) {
    		return true;
    	}
    	return false;
    }
    
    
    boolean seTocan(Carne c) {
    	if (this.dameRectangulo().contains(c.dameRectanguloCarne()) || this.dameRectangulo().intersects(c.dameRectanguloCarne())){
    		return true;
    	}
    	return false;
    }

}