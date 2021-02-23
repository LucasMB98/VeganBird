package entorno;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;



public class Finish extends JFrame{
    
    private static final long serialVersionUID = 1L;
	private Board board;
    private InterfaceJuego juego;
    public final char TECLA_ENTER = 10;
    public final char TECLA_H = 72;
  	public final char TECLA_N = 78;


	/**
     * Construye una ventana de GameOver.
     * @param juego -
     * @param titulo Un titulo para la ventana del juego
     * @param ancho El ancho de la ventana
     * @param alto El alto de la ventana
	 * @param b Si es true, la ventana estara abierta, si es false la ventana se escondera.
     */
    public Finish(InterfaceJuego juego, String titulo, int ancho, int alto,boolean b){
		this.juego = juego;		
		board = new Board(this.juego);
		board.setSize(ancho, alto);
		
//		this.setLayout(new BorderLayout());		
        add(board, BorderLayout.CENTER);
        this.pack();        
        Insets ins = this.getInsets();
        setSize(ancho + ins.left + ins.right, alto + ins.bottom + ins.top);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle(titulo);
		cerrar(b);
    }

	//Metodo publico para esconder la pantalla.
	public void cerrar(boolean b){
		setVisible(b);
	}

    public void iniciar()
	{
		board.iniciar();
	}

    //Termina con la ejecucion de todos los eventos en ventana.
    public void terminar()
	{
		board.terminar();
	}

    public void dibujarImagen(Image imagen, double x, double y, double angulo, double escala)
	{
		dibujarImagenConCentro(imagen, x, y, imagen.getWidth(null)/2, imagen.getHeight(null)/2, angulo, escala);
	}


    public void dibujarImagenConCentro(Image imagen, double x, double y, double centro_x, double centro_y, double angulo, double escala)
	{
		Graphics2D g2d = board.getG2D();
		
		if( g2d == null )
			return;
		
        AffineTransform transform = AffineTransform.getTranslateInstance(x, y);
        transform.concatenate( AffineTransform.getRotateInstance(angulo) );
        transform.concatenate( AffineTransform.getTranslateInstance(-escala*centro_x, -escala*centro_y) );
        if (escala != 1.0)
        	transform.concatenate( AffineTransform.getScaleInstance(escala, escala) );

        g2d.drawImage(imagen, transform, null);
	}

    public void escribirTexto(String texto, double x, double y)
	{
		Graphics2D g2d = board.getG2D();
		
		if( g2d == null )
			return;
		
        g2d.drawString(texto, (int) x, (int) y);
    }
	
	/**
	 * Cambia la fuente para las proximas escrituras de texto.
	 * @param font El nombre de la fuente
	 * @param tamano El tamaño para las letras del texto
	 * @param color El color del texto
	 */
	public void cambiarFont(String font, int tamano, Color color)
	{
		Graphics2D g2d = board.getG2D();
		
		if( g2d == null )
			return;
		
		g2d.setColor(color);
		g2d.setFont(new Font(font, Font.PLAIN, tamano));
    }
	
	
	public boolean estaPresionada(char key)
	{
		boolean[] keys = board.getKeys();
		if( key < 0 || key >= keys.length )
			throw new RuntimeException( "Error! Se consulta si la tecla " + (int) key + " esta presionada, pero esa tecla no existe." );
		
		return keys[key];
	}




}