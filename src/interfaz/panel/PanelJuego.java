package interfaz.panel;

import logica.juego.*;
import javax.swing.JPanel;
import GUI.GUI;
import interfaz.panel.derrota.PanelDerrota;
import interfaz.panel.victoria.PanelVictoria;

public class PanelJuego extends JPanel {
	private static final long serialVersionUID = 1L;
	private static int AnchoVentana, AltoVentana;
	private PanelLateral panelLateral;
	private Juego miJuego;
	private GUI ventana;
	protected final String dir = "src/Sprites/";

	/**
	 * Create the frame.
	 */
	public PanelJuego(GUI v){
		ventana=v;
		AnchoVentana= 1200; AltoVentana= 720;
		setBounds(0, 0, AnchoVentana, AltoVentana);
		this.setLayout(null);
		this.setFocusable(true);
	}
	public JPanel getPanelPrincipal(){
		return this;
	}
	
	public void iniciar() {
		this.miJuego.iniciarPartida();
	}
	
	public void finalizar(){
		ventana.getInicio().juegoFinalizado();
	}
	
	public void ejecutar(){
		if(this.miJuego==null){
			this.miJuego = new Juego(this);
			panelLateral = new PanelLateral(AnchoVentana,AltoVentana,this);
			this.add(panelLateral);
		}
	}
	public void cambiar(JPanel panel){
		ventana.cambiar(this, panel);
	}
	public int getAncho(){
		return AnchoVentana;
	}
	public int getAlto(){
		return AltoVentana;
	}
	public void ganar(){
		ventana.cambiar(this, new PanelVictoria(ventana));
	}
	public void perder(){
		ventana.cambiar(this, new PanelDerrota(ventana));
	}
	public void terminate(){
		if(this.miJuego!=null)
			this.miJuego.finalizarPartida();
	}
	public Juego getJuego(){
		return this.miJuego;
	}
	public GUI getVentana(){
		return ventana;
	}
	public PanelLateral getPanelLateral(){
		return panelLateral;
	}
}

