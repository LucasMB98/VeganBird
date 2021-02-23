package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import entorno.Entorno;
import entorno.Herramientas;


public class Carne {
	 	int x;
	    int y;
	    int ancho;
	    int alto;
		Image imagen;
		int valor;
		
	    /**
	     * Crea un objeto Carne.
	     * @param imagen Nombre + extension de archivo de la imagen.
	     * @param x Posicion x en pantalla.
	     * @param y Posicion y en pantalla.
		 * @param ancho Ancho de la imagen.
		 * @param alto Alto de la imagen.
		 * @param valor Valor del objeto carne.
	     */
	    Carne(String imagen,int x, int y,int ancho, int alto,int valor){
	        this.x=x;
	        this.y=y;
	        this.imagen = Herramientas.cargarImagen(imagen);
	        this.ancho = ancho;
	        this.alto = alto;
	        this.valor = valor;
}

	    /**
	     * Dibuja un rectangulo en un entorno especifico.
	     * @param entorno Entorno especifico.
	     */
	    void dibujarseRectangulo(Entorno entorno,int an,int al, Color c){
	        entorno.dibujarRectangulo(this.x, this.y,an,al,0, c); //En este metodo no se cambia la y, ya que no causa errores.
	    }
	    /**
		 * Dibuja el objeto en un entorno especifico, con posibilidad de redimensionar la imagen.
		 * @param entorno
		 * @param escala Escala de imagen.
		 */
	    void dibujarseImagen(Entorno entorno,double escala){
	        entorno.dibujarImagen(imagen,this.x, this.y,0, escala); //Se modifica la y, ya que el dibujador causa problemas de posicionamiento en pantalla.
	    }
		
		/**
		 * Cambia la imagen de un objeto carne, por otra pasada como parametro.
		 * @param imagen Direccion donde se encuentra la imagen.
		 */
		void transformar(String imagen){
			this.imagen = Herramientas.cargarImagen(imagen);
		} 

	    /**
		* Metodo auxiliar para controlar la colision.
		* @return Retorna el rectangulo que rodea al objeto.
		*/
	    Rectangle dameRectanguloCarne (){
	        Rectangle r = new Rectangle(this.x,this.y,ancho,alto);
	        return r;
	    }

	    /**
	     * Mueve el objeto, modificando su coordenada x.
	     * @param modificador Si es positivo el objeto se mueve para la izquierda. Cuando es negativo para la derecha.
	     */
	    void moverCarne(int modificador){
	        this.x = this.x - modificador;
		}		
	    
	    void seTocan(Obstaculo o){
	    	if (this.dameRectanguloCarne().intersects(o.dameRectangulo())) {
	    		this.x = this.x +200;
	    	}
	    	
	    }
	}