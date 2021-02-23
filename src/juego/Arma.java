package juego;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
	
public class Arma {
		int x;
		int y;
		double angulo;
		Image imagenArma;
		
		/**
		 * Construye un objeto Arma y carga una imagen.
		 * @param x posicion x del pajaro a construir.
		 * @param y posicion x del pajaro a construir.
		 */
		public Arma(int x, int y) 
		{
			this.x = x;
			this.y = y;
			imagenArma = Herramientas.cargarImagen("images/x.png"); 
		}
		

		/**
		 * Dibuja un objeto Arma en el 'entorno' pasado como parametro.
		 * @param entorno 
		 */
		void dibujarArma(Entorno entorno){
			entorno.dibujarImagen(imagenArma, x+30,y, angulo,0.08);
		}

		/**
		 * Modifica el angulo del arma.
		 * @param modificador Si el modificador es positivo el arma se gira en sentido antihorario. Si el modificador es negativo, se movera en sentido horario.
		 */
		public void girar(double modificador) 
		{
			this.angulo = this.angulo + modificador;
		}

		/**
		 * Fisica del objeto arma.
		 * @param modificador Si el valor ingresado es positivo el objeto caera. Y si el valor es negativo el objeto subirá.
		 */
		void gravedadArma(int modificador){
			this.y = this.y - modificador;
		}
		
		/**
		 * Modifica el valor de la Y del objeto Arma. 
		 */
		void moverArriba(){
			this.y=this.y - 5;
		}

		/**
		 * Dibuja un rectangulo alrededor del objeto.
		 * @param entorno Entorno a dibujar.
		 * @param color Color del rectangulo.
		 */
		void dibujarRectanguloArma(Entorno entorno, Color color){
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

