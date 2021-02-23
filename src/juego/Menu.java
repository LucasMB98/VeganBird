package juego;

import java.awt.Color;
import javax.sound.sampled.Clip;
import entorno.Comienzo;
import entorno.Herramientas;
import entorno.InterfaceJuego;


public class Menu extends InterfaceJuego{
    private Comienzo app;
    Fondo fondo; 
    Fondo nombre;
    Clip musica;
    
    
    Menu(){
        app = new Comienzo(this, "Vegan Bird", 800, 600,true);
        fondo = new Fondo();
        nombre = new Fondo();
        musica = Herramientas.cargarSonido("sound/musicaMenu.wav"); 
        app.iniciar();
        
    }
    @SuppressWarnings("unused") 
    public void tick(){ 
    	musica.start();
        fondo.dibujarComienzo(app);
        nombre.dibujarNombreJuego(app);
        app.cambiarFont("Arial", 30, Color.BLACK);
        app.escribirTexto("Press N  to play NORMAL", 250,470 );
        app.escribirTexto("Press H  to play HARD", 250,500 );
        app.cambiarFont("Arial", 11, Color.black);
        app.escribirTexto("Created by: Emiliano Garcia, Lucas Battista and Tomas Artaza.",0,20 );
        
        //Se elige la dificultad del juego.
        if (app.estaPresionada(app.TECLA_N)) {
        	musica.stop();
            app.terminar();
            app.cerrar(false);
            
        	Juego juego = new Juego(); 
        } 
        
        if (app.estaPresionada(app.TECLA_H)) {
        	musica.stop();
            app.terminar();
            app.cerrar(false);

        	Juego2 juego = new Juego2();
        }
        
    }
   
   
    @SuppressWarnings("unused") 
	public static void main(String[] args){		
		Menu menu = new Menu(); 
	}

}