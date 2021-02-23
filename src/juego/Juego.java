package juego;

import java.awt.Rectangle;
import java.util.Random;

import javax.sound.sampled.Clip;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;  

public class Juego extends InterfaceJuego
{
	private Entorno entorno;
	Bird bird;
	Arma arma;
	Fondo fondo;
	Obstaculo obstaculo;
	Obstaculo segundoObstaculo;
	Obstaculo tercerObstaculo;
	Obstaculo cuartoObstaculo;
	Obstaculo quintoObstaculo;
	Obstaculo sextoObstaculo;
	Obstaculo septimoObstaculo;
	Obstaculo octavoObstaculo;
	Fruta fruta;
	Fruta frutilla;
	Carne hamburguesa;
	Carne carne;
	Carne pizza;
	Proyectil proyectil;
	Puntaje puntos;
	Entorno en;
	Clip sound;
	Clip colision;
	
	
	Juego(){
		
		entorno = new Entorno(this, "Vegan Bird", 800, 600,true);
		bird = new Bird(200,400);
		fondo = new Fondo();
		arma = new Arma(195,385); 
		obstaculo = new Obstaculo("images/obstacle_abajo.png",800,400,133,361);
		segundoObstaculo = new Obstaculo("images/obstacle_arriba.png",800,-100,138,361);
		tercerObstaculo = new Obstaculo("images/obstacle_abajo.png",1300,500,138,361);
		cuartoObstaculo = new Obstaculo("images/obstacle_arriba.png",1300,0,138,361);
		quintoObstaculo = new Obstaculo("images/obstacle_abajo.png",1800,500,138,361);
		sextoObstaculo = new Obstaculo("images/obstacle_arriba.png",1800,quintoObstaculo.y-500,138,361);
		fruta = new Fruta ("images/fruit1.gif",randomRange(600,800),randomRange(100,600),30,30,5);
		carne = new Carne ("images/carneposta.png",randomRange(600,800),randomRange(100,600),30,30,-5);
		hamburguesa = new Carne ("images/hamburger.gif",randomRange(600,800),randomRange(100,600),30,30,-5);
		pizza = new Carne ("images/pizza2.gif",randomRange(600,800),randomRange(100,600),30,30,-5);
		frutilla = new Fruta ("images/fruit2.gif",randomRange(600,800),randomRange(100,600),30,30,5);
		proyectil = new Proyectil (arma.x,arma.y);
		puntos = new Puntaje(0);
		sound = Herramientas.cargarSonido("sound/sound.wav");
		colision = Herramientas.cargarSonido("sound/rayo_hit.wav");
		
		Obstaculo[] obstaculos = {obstaculo,segundoObstaculo,tercerObstaculo,cuartoObstaculo,quintoObstaculo,sextoObstaculo};
		Carne[] carnes = {carne,hamburguesa,pizza};
		Fruta[] frutas = {fruta,frutilla};
		
		for (int i = 0; i<obstaculos.length;i++) {
			for (int j = 0; j<frutas.length;j++) {
				if(obstaculos[i].seTocan(frutas[j])) {
					frutas[j].x = frutas[j].x +200;
				}
			}
			
		}
		for (int i = 0; i<obstaculos.length;i++) {
			for (int j = 0; j<carnes.length;j++) {
				if(obstaculos[i].seTocan(carnes[j])) {
					carnes[j].x = carnes[j].x +200;
				}
			}
		}
		
		
		entorno.iniciar();
		
	}
	
	
	public void tick(){
		//Inicia la musica.
		sound.start();
		//Se dibujan los objetos.
		dibujarEnPantalla();
		//Movimiento de objetos.
		movimientoObjetos(4);
		obstaculosRandom();
		objetosRandom();
		//Eventos del pajaro y los proyectiles.
		eventosBird_Proyectil();
		//Se transforma el alimento y suma puntos.
		transformarAlimento_Puntos();
		//Cuando se consume alimentos.
		colisionAlimentos_Puntos();
		//Se termina el juego. 
		gameOver();
		
		puntos.mostrarPuntaje(entorno,100,150);
	
	}


	/**
	 * Dibuja todos los objetos.
	 */
	void dibujarEnPantalla(){
		fondo.dibujarFondo(entorno);
		obstaculo.dibujarseImagenArriba(entorno);
		segundoObstaculo.dibujarseImagenAbajo(entorno);
		tercerObstaculo.dibujarseImagenArriba(entorno);
		cuartoObstaculo.dibujarseImagenAbajo(entorno);
		quintoObstaculo.dibujarseImagenArriba(entorno);
		sextoObstaculo.dibujarseImagenAbajo(entorno);
		arma.dibujarArma(entorno);
		carne.dibujarseImagen(entorno,0.1);
		fruta.dibujarseImagen(entorno,0.07);
		hamburguesa.dibujarseImagen(entorno, 0.1);
		frutilla.dibujarseImagen(entorno, 0.1);
		pizza.dibujarseImagen(entorno, 0.1);
		bird.dibujarse(entorno);
	}
	
	@SuppressWarnings("unused") 
	/**
	 * Cuando el pajaro colisiona con los obstaculos o con se va fuera de los limites de pantalla, el juego termina y se ejecuta una nueva ventana.
	 */
	void gameOver(){
		if (colisionPantalla() || colision(bird.dameRectangulo(),obstaculo.dameRectangulo()) || colision(bird.dameRectangulo(),segundoObstaculo.dameRectangulo()) || colision(bird.dameRectangulo(),tercerObstaculo.dameRectangulo())  || colision(bird.dameRectangulo(),cuartoObstaculo.dameRectangulo()) || colision(bird.dameRectangulo(),quintoObstaculo.dameRectangulo()) || colision(bird.dameRectangulo(),sextoObstaculo.dameRectangulo())){
			sound.stop();
			entorno.terminar();
			entorno.cerrar(false);
			//Guarda los puntos obtenidos y se los envia a la ventana de GameOver.
			Perdiste p = new Perdiste(puntos);
		}	
	}
	/**
	 * Se encarga de que el rayo transforme el alimento, y le suma puntos.
	 */
	void transformarAlimento_Puntos(){
		if (colision(proyectil.proyectilRectangulo(),carne.dameRectanguloCarne()) && (entorno.estaPresionada(entorno.TECLA_ESPACIO) && carne.valor!=fruta.valor)){
			Herramientas.play("sound/rayo_hit.wav");
            carne.transformar("images/fruit2.gif");
            proyectil.alLugar(bird.x, bird.y);
            carne.valor=fruta.valor;
            puntos.puntos+=3;
        }
		if (colision(proyectil.proyectilRectangulo(),pizza.dameRectanguloCarne()) && (entorno.estaPresionada(entorno.TECLA_ESPACIO)&& pizza.valor!=fruta.valor)){
			Herramientas.play("sound/rayo_hit.wav");
            pizza.transformar("images/fruit2.gif");
            proyectil.alLugar(bird.x, bird.y);
            pizza.valor=fruta.valor;
            puntos.puntos+=3;
        }
		if (colision(proyectil.proyectilRectangulo(),hamburguesa.dameRectanguloCarne()) && (entorno.estaPresionada(entorno.TECLA_ESPACIO)&& pizza.valor!=fruta.valor)){
			Herramientas.play("sound/rayo_hit.wav");
            hamburguesa.transformar("images/fruit2.gif");
            proyectil.alLugar(bird.x, bird.y);
            hamburguesa.valor=fruta.valor;
            puntos.puntos+=3;
            
        }
	}

	/** Controla cuando el pajaro consume un objeto Fruta o Carne, le suma o resta puntos y hace que el objeto consumido salga de pantalla. 
 	* 
 	*/
	void colisionAlimentos_Puntos(){
		if (colision(bird.dameRectangulo(), fruta.dameRectanguloFruta())){
            fruta.y+=1000;
            puntos.sumarPuntos(fruta.valor); 
        }
		if (colision(bird.dameRectangulo(), frutilla.dameRectanguloFruta())){
            frutilla.y+=1000;
            puntos.sumarPuntos(fruta.valor); 
        }
        if (colision(bird.dameRectangulo(), carne.dameRectanguloCarne())){
            carne.y+=1000;
            puntos.sumarPuntos(carne.valor); 
        }
        if (colision(bird.dameRectangulo(), hamburguesa.dameRectanguloCarne())){
        	hamburguesa.y+=1000;
            puntos.sumarPuntos(hamburguesa.valor); 
        }
        if (colision(bird.dameRectangulo(), pizza.dameRectanguloCarne())){
        	pizza.y+=1000;
            puntos.sumarPuntos(pizza.valor); 
		}
		//Evita que haya puntajes incoherentes. 
		if(puntos.puntos<0){
			puntos.puntos=0;
		}
	}

	/**
	 * Controla los movimientos del pajaro y la direccion de los proyectiles.
	 */
	void eventosBird_Proyectil(){
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA)){  //Evento
			bird.moverArriba();         
			arma.moverArriba();
			
		}else{
			bird.gravedadBird(-3);
			arma.gravedadArma(-3);
		}

		if (entorno.estaPresionada(entorno.TECLA_ABAJO)){
			bird.gravedadBird(-5);
			arma.gravedadArma(-5);
		}
		if (entorno.estaPresionada(entorno.TECLA_ESPACIO)){
			proyectil.dibujarProyectil(entorno);
			proyectil.moverAdelante();

		}else{
			proyectil.alLugar(arma.x,arma.y);
			
		}
			
		if (entorno.estaPresionada(entorno.TECLA_W)){
			arma.girar(Herramientas.radianes(-1));
			proyectil.girar(Herramientas.radianes(-1));
		}

		if (entorno.estaPresionada(entorno.TECLA_S)){
			arma.girar(Herramientas.radianes(1));
			proyectil.girar(Herramientas.radianes(1));
		}
			
			
		
	}

	/**
	 * Cuando los objetos se van de pantalla, se crea otro modificando su coordenada y.
	 * 
	 */
	void objetosRandom(){
		if (fruta.x<=-100) {    
			int numero;
			numero = randomRange(200,500);
			fruta = new Fruta("images/fruit1.gif",1000,numero,30,30,5);
		} 
		if (frutilla.x<=-100) {    
			int numero;
			numero = randomRange(200,500);
			frutilla = new Fruta("images/fruit2.gif",1000,numero,30,30,5);
		} 
		 
		if (carne.x<=-100) { 
			int n;
			n = randomRange(200,500);
			carne = new Carne("images/carneposta.png",800,n,30,30,-5);   
		}
	
		
		if (hamburguesa.x<=-100) { 
			int n;
			n = randomRange(200,500);
			hamburguesa = new Carne("images/hamburger.gif",800,n,30,30,-5);   
		}
		if (pizza.x<=-100) { 
			int n;
			n = randomRange(200,500);
			pizza = new Carne("images/pizza2.gif",800,n,30,30,-5); 
		}
		
	}

	/**
	 * Cuando el obstaculo se va de pantalla, crea otro modificando su coordenada Y.
	 * 
	 */
	void obstaculosRandom(){
        if (obstaculo.x<=-100 && segundoObstaculo.x<=-100) {
            int numer=0;
            numer = randomRange(300,600); 
            obstaculo = new Obstaculo("images/obstacle_abajo.png",1500,numer,133,361);
            segundoObstaculo = new Obstaculo("images/obstacle_arriba.png",1500,numer-500,138,361);
        } 
        if (tercerObstaculo.x<=-100 && cuartoObstaculo.x<=-100) {
            int n;
            n = randomRange(300,550);
            tercerObstaculo = new Obstaculo("images/obstacle_abajo.png",obstaculo.x+500,n,133,361);
            cuartoObstaculo = new Obstaculo("images/obstacle_arriba.png",obstaculo.x+500,n-500,138,361);          
		}
		
		if (quintoObstaculo.x<=-100 && sextoObstaculo.x<=-100) {
            int nu=0;
            nu = randomRange(300,600); 
            quintoObstaculo = new Obstaculo("images/obstacle_abajo.png",obstaculo.x+1000,nu,133,361);
            sextoObstaculo = new Obstaculo("images/obstacle_arriba.png",obstaculo.x+1000,nu-500,138,361);
        }
           
    }

	/**
	 * Mueve objetos en pantalla.
	 * @param modificador Si el modificador es negativo el objeto ira hacia la izquierda, si es positivo ira hacia la derecha.
	 */
	void movimientoObjetos(int modificador){
		carne.moverCarne(modificador); 
		fruta.moverFruta(modificador); 
		hamburguesa.moverCarne(modificador);
		frutilla.moverFruta(modificador);
		pizza.moverCarne(modificador);
		movimientoObstaculo(obstaculo,modificador);
		movimientoObstaculo(segundoObstaculo, modificador);
		movimientoObstaculo(tercerObstaculo,modificador);
		movimientoObstaculo(cuartoObstaculo,modificador);
		movimientoObstaculo(quintoObstaculo,modificador);
		movimientoObstaculo(sextoObstaculo,modificador);
		
		
	}

	/**
	 * Mueve un objeto obstaculo.
	 * @param o
	 * @param modificador Si el modificador es positivo se mueve para la derecha. Si el modificador es negativo para la izquierda.
	 */
	public void movimientoObstaculo(Obstaculo o,int modificador){
		o.moverObstaculo(modificador);
	}
	
	/**
	 * Indica cuando el objeto se va de la pantalla.
	 * @return
	 */
	boolean colisionPantalla(){
		Rectangle r1 = new Rectangle (entorno.ancho(),entorno.alto());
		Rectangle r2 = new Rectangle (bird.x,bird.y,50,50);
		if (! r2.intersects(r1)){
			return true;
		}
		return false;
	}
	

	/**
	 * Indica cuando dos rectangulos se tocan.
	 * @param r1 
	 * @param r2
	 * @return
	 */
	boolean colision(Rectangle r1, Rectangle r2){
		if (r1.intersects(r2)){
			return true;
		}
		return false;
	}
	
	/**
	 * Devuelve un numero random en el rango solicitado.
	 * @param a Inicio de rango.
	 * @param b Final de rango.
	 * @return
	 */
	private int randomRange(int a, int b){
        Random r = new Random(); 
        int n;
        n = (int) (r.nextDouble() * (b-a) + a);
        return n;
	}

}
