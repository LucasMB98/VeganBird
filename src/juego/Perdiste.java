package juego;

import java.awt.Color;
import javax.sound.sampled.Clip;
import entorno.Finish;
import entorno.Herramientas;
import entorno.InterfaceJuego;


public class Perdiste extends InterfaceJuego{
    private Finish app;
    Fondo fondo;
    Puntaje puntos; 
    Clip fin;

    Perdiste(Puntaje puntaje){
        app = new Finish(this, "Vegan Bird", 800, 600,true);
        fondo = new Fondo();
        fin = Herramientas.cargarSonido("sound/loose.wav");
        puntos = puntaje;
        app.iniciar();
    }
    @SuppressWarnings("unused")
    public void tick(){
        fin.start();
        fondo.dibujarFinal(app);
        puntos.mostrarPuntaje(app, 350, 400); 
        app.cambiarFont("Arial", 30, Color.white);
        app.escribirTexto("Press N  to play NORMAL",10,500 );
        app.escribirTexto("or",410,500 );
        app.escribirTexto("Press H  to play HARD", 500,500 );
        
        //Se elige la dificultad del juego.
        if (app.estaPresionada(app.TECLA_N)) {
        	fin.stop();
            app.terminar();
            app.cerrar(false);
            
        	Juego juego = new Juego(); 
        } 
        
        if (app.estaPresionada(app.TECLA_H)) {
        	fin.stop(); 
            app.terminar();
            app.cerrar(false);
            
        	Juego2 juego = new Juego2();
        }
       
    }
    
}
