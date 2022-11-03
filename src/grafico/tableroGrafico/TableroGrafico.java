package grafico.tableroGrafico;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import grafico.entidadGrafica.EntidadGrafica;
import logica.celda.Celda;
import logica.tablero.Tablero;

public class TableroGrafico extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Tablero miTablero;
	protected int ancho;
	protected int alto;
	protected JLabel fondo;
	protected final String dir = System.getProperty("user.dir");
	
	public TableroGrafico(Tablero t) {
		super();
		this.miTablero = t;
		this.setLayout(null);
		this.alto = this.miTablero.getNivel().getJuego().getMiInterfaz().getAlto();
		this.ancho = this.alto;
		this.setBounds( 0, 0, this.ancho, this.alto);
		
		this.pintarTablero();
		this.repaint();
		
		System.out.println("Ancho: "+this.ancho+", Alto: "+this.alto);
		this.fondo = new JLabel(new ImageIcon(dir+"/src/Assets/pasto.jpg"));
		this.fondo.setBounds( 0, 0, this.ancho, this.alto);
		this.add(fondo);
		this.agregarOyente();
		this.setFocusable(true);
		
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
	
	/**
	 * Actualiza la imagen de un entidad en un JLabel por la imagen de otra entidad.
	 * @param e1
	 * @param e2
	 */
	public void actualizarImagenDeEntidadGrafica(EntidadGrafica e1, EntidadGrafica e2) {
		e2.setLabel(e1.getLabel());
		e1.setLabel(null);
		e2.updateImagen();
		e2.getLabel().repaint();
	}
	
	public void update(EntidadGrafica e) {
		this.repaint();
	}
	
	protected void agregarOyente() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int presiona = e.getKeyCode();
				int tecla = 0;
		        switch (presiona) 
		        {
		      		case KeyEvent.VK_UP:{tecla = 2; System.out.println("Arriba");}break;
		            case KeyEvent.VK_LEFT: {tecla = -1; System.out.println("Izquierda");}break;
		            case KeyEvent.VK_RIGHT: {tecla = 1; System.out.println("Derecha");}break;
		            case KeyEvent.VK_DOWN: {tecla = -2; System.out.println("Abajo");}break;
		        }
		        miTablero.getNivel().getJuego().doblar(tecla);
			}
		});
	}
}
