package grafico.tableroGrafico;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

import javax.swing.JPanel;

import grafico.entidadGrafica.EntidadGrafica;
import logica.celda.Celda;
import logica.entidad.Entidad;
import logica.tablero.Tablero;

public class TableroGrafico extends JPanel {

	protected Tablero miTablero;
	protected int ancho;
	protected int alto;
	
	public TableroGrafico(Tablero t) {
		this.miTablero = t;
		this.setLayout(null);
		this.ancho = this.miTablero.getNivel().getJuego().getMiInterfaz().getAncho();
		this.alto = this.miTablero.getNivel().getJuego().getMiInterfaz().getAlto();
	}
	
	public void pintarTablero() {
		Celda [][] grilla = this.miTablero.getGrilla();
		for(int i = 0; i < grilla.length ; i++ ) {
			for( int j = 0; j < grilla[i].length ; j++ ) {
				this.add(grilla[i][j].getUltimaEntidad().getEntidadGrafica().getLabel());
			}
		}
	}
	
	public void addEntidadGrafica(EntidadGrafica e) {
		this.add(e.getLabel());
		this.repaint();
	}
	
	public void removeEntidadGrafica(EntidadGrafica e) {
		this.remove(e.getLabel());
		this.repaint();
	}
	
	public void update(EntidadGrafica e) {
		this.repaint();
	}
	
	protected class OyenteTeclado implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			int presiona=e.getKeyCode();
			int tecla = 0;
	        switch (presiona) 
	        {
	      		case KeyEvent.VK_UP:{tecla = 2;}break;
	            case KeyEvent.VK_LEFT: {tecla = -1;}break;
	            case KeyEvent.VK_RIGHT: {tecla = 1;}break;
	            case KeyEvent.VK_DOWN: {tecla = -2;}break;
	        }
	        miTablero.getNivel().getJuego().doblar(tecla);
		}

		@Override
		public void keyReleased(KeyEvent e) {}
	}
}
