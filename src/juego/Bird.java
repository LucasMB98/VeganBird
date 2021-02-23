package juego;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;



public class Bird{
	int x;
	int y;
	Image img1;

	/**
	 * Construye un nuevo pajaro y carga una imagen.
	 * @param x posicion x del pajaro a construir.
	 * @param y posicion x del pajaro a construir.
	 */
	public Bird(int x, int y){
		this.x = x;
		this.y = y;
	
		img1 = Herramientas.cargarImagen("images/flappy.png");
	}
	
	/**
	 * Dibuja un pajaro en el entorno pasado como parametro.
	 * @param entorno
	 */
	public void dibujarse(Entorno entorno){
		entorno.dibujarImagen(img1, x, y, 0,0.09);
	}
	
	/**
	 * Fisica del objeto bird.
	 * @param modificador Si el valor ingresado es positivo el objeto caera. Y si el valor es negativo el objeto subirá.
	 */
	void gravedadBird(int modificador){
		this.y = this.y - modificador;
	}
	
	/**
	 * Modifica el valor de la Y del pajaro. 
	 */
	void moverArriba(){
		this.y=this.y - 5;
	}

	/**
	 * Dibuja un circulo alrededor del pajaro.
	 * @param entorno Entorno a dibujar.
	 * @param color Color del rectangulo.
	 */
	void dibujarRectanguloBird(Entorno entorno, Color color){
		entorno.dibujarRectangulo(this.x, this.y, 50, 50, 0, color);
	}
	
	/**
	* Metodo auxiliar para controlar la colision.
	* @return Retorna el rectangulo que rodea al objeto.
	*/
	Rectangle dameRectangulo(){
		Rectangle r = new Rectangle(this.x,this.y,50,50);
		return r;
	}
	
}
