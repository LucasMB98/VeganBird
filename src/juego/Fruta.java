package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import entorno.Entorno;
import entorno.Herramientas;


public class Fruta {
	 	int x;
	    int y;
	    int ancho;
	    int alto;
		Image fruta;
		int valor;
	    
	    /**
	     * Crea un obstaculo.
	     * @param imagen Nombre + extension de la imagen.
	     * @param x Posicion x en pantalla.
	     * @param y Posicion y en pantalla.
		 * @param ancho Ancho de la imagen.
		 * @param alto Alto de la imagen.
		 * @param valor Valor del objeto fruta.
	     */
	   Fruta(String imagen,int x, int y,int ancho, int alto,int valor){
	        this.x=x;
	        this.y=y;
	        this.fruta = Herramientas.cargarImagen(imagen);
	        this.ancho = ancho; 
	        this.alto = alto;
 			this.valor = valor;
	   }

	
	    /**
	     * Dibuja la imagen de un obstaculo en un entorno especifico.
	     * @param entorno Entorno especifico.
	     */
	   void dibujarseRectangulo(Entorno entorno,int an,int al, Color c){
	        entorno.dibujarRectangulo(this.x, this.y, an,al,0, c); 
	    }
		
		/**
		 * Dibuja el objeto en un entorno especifico, con posibilidad de redimensionar la imagen.
		 * @param entorno
		 * @param escala Escala de imagen.
		 */
	    void dibujarseImagen(Entorno entorno,double escala){
	        entorno.dibujarImagen(fruta,this.x, this.y,0, escala);
	    }

	    /**
	     * Metodo auxiliar.
	     * @return Devuelve un rectangulo con exactamente el tamaño del obstaculo.
	     */
	    Rectangle dameRectanguloFruta (){
	        Rectangle r = new Rectangle(this.x,this.y,ancho,alto);
	        return r;
	    }

	    /**
	     * Mueve el objeto, modificando su coordenada x.
	     * @param modificador Si es positivo el objeto se mueve para la izquierda. Cuando es negativo para la derecha.
	     */
	    void moverFruta(int modificador){
	        this.x = this.x - modificador; 
	    }
	    
	    void seTocan(Obstaculo o){
	    	if (this.dameRectanguloFruta().intersects(o.dameRectangulo())) {
	    		this.x = this.x +200;
	    	}
	    	
	    }
	    
	}
