package juego;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {
	int x;
	int y;
	double angulo;
	Image img;
	

	/**
	 * Crea un proyectil, y carga una imagen, pero no la dibuja.
	 * @param x
	 * @param y
	 */
	Proyectil (int x, int y){
		this.x=x;
		this.y=y;
		this.img=Herramientas.cargarImagen("images/shot.gif");
	}
	
	/**
	 * Dibuja un proyectil en un 'entorno' especifico.
	 * @param entorno
	 */
	void dibujarProyectil(Entorno entorno){
		entorno.dibujarImagen(img, x, y,this.angulo,0.3);
	}
	/**
	* Modifica el angulo del proyectil.
	* @param modificador Si el modificador es positivo el arma se gira en sentido antihorario. Si el modificador es negativo, se movera en sentido horario.
	*/
	public void girar(double modificador){
		this.angulo = this.angulo + modificador;
	}
	/**
	 * Retoma su posicion en las coordenadas como parametros.
	 * @param b
	 * @param l
	 */
	void alLugar(int x,int y){
		this.x=x;
		this.y=y;
	}
	/**
	* Dibuja un circulo alrededor del objeto.
	* @param entorno Entorno a dibujar.
	* @param color Color del rectangulo.
	*/
	void dibujarRectangulo(Entorno entorno){
		entorno.dibujarRectangulo(x, y,30, 30, 0, Color.cyan);
	}
	/**
	 * Hace que se 'dispare' el proyectil.
	 */
	public void moverAdelante() {
		this.x += Math.cos(this.angulo)*16;
		this.y += Math.sin(this.angulo)*16;
	}

	/**
	* Metodo auxiliar para controlar la colision.
	* @return Retorna el rectangulo que rodea al objeto.
	*/
	Rectangle proyectilRectangulo(){
		Rectangle r = new Rectangle(this.x,this.y,30, 30);
		return r;
	}
	
	

}
