package entorno;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private Timer timer;
    private Graphics2D g2d;
    private InterfaceJuego juego;
    private boolean[] teclas;
    private boolean iniciado;

    public Board(InterfaceJuego j)
    {
    	iniciado = false;
    	juego = j;
    	teclas = new boolean[256];
    	
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);        
    }
    
    public void iniciar()
    {
    	iniciado = true;
        timer = new Timer(10, this);
        timer.start();        
    }
    
    //Metodo para terminar con la ejecucion del timer.
    public void terminar(){
    	iniciado = false;
        timer = new Timer(10,this);
        timer.stop();         
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        g2d = (Graphics2D)g;
        
        try
        {
        	if (iniciado)
        		juego.tick();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        g2d = null;
    }

    // Llamado cuando salta el timer
    public void actionPerformed(ActionEvent e)
    {
        repaint();  
    }

    private class TAdapter extends KeyAdapter
    {
        public void keyReleased(KeyEvent e)
        {
        	int key = e.getKeyCode();
        	if( 0 <= key && key < teclas.length )
        		teclas[key] = false;
        }

        public void keyPressed(KeyEvent e)
        {
        	int key = e.getKeyCode();
        	if( 0 <= key && key < teclas.length )
        		teclas[key] = true;
        }
    }
    
    public Graphics2D getG2D()
    {
    	return g2d;
    }
    
    public boolean[] getKeys()
    {
    	return teclas;
    }
}