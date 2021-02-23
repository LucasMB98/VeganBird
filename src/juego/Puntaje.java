package juego;
import java.awt.Color;

import entorno.Entorno;
import entorno.Finish;

public class Puntaje {
    int puntos;

    /**
     * Crea un contador de puntos.
     * @param puntos
     */
    Puntaje(int puntos){
        this.puntos= puntos;
    }

    void sumarPuntos(int mod){
        this.puntos = this.puntos+mod;
    }
    
    /**
     * Muestra el puntaje en la pantalla de GameOver.
     */
    void mostrarPuntaje(Finish entorno,int x, int y){
        entorno.cambiarFont("Arial", 70, Color.orange);
        entorno.escribirTexto("Score: "+this.puntos, x-50, y);
    } 
    
    /**
     * Muestra el puntaje en el juego.
     * @param entorno
     * @param x
     * @param y
     */
    void mostrarPuntaje(Entorno entorno,int x, int y){
        entorno.cambiarFont("Arial", 50, Color.WHITE);
        entorno.escribirTexto(""+this.puntos, x, y);
    }
}