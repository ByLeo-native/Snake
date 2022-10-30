package logica.juego;

import GUI.GUI;
import interfaz.panel.PanelJuego;
import logica.hilos.Contador;
import logica.jugador.Jugador;
import logica.nivel.Nivel;

public class Juego {

	protected Jugador miJugador;
	protected Nivel nivel;
	protected int nivelActual;
	protected PanelJuego pnJuego;
	protected Contador contador;
	
	public Juego(PanelJuego panelJuego) {
		this.pnJuego = panelJuego;
		this.nivelActual = 1;
		this.nivel = new Nivel( this, this.nivelActual);
		this.miJugador = new Jugador(this, this.nivel.getCriatura());
	}
	
	public void iniciarPartida() {
		
	}
	
	public void finalizarPartida() {
		this.contador.stop();
		this.miJugador.definirTiempoJugado(contador.getMinutos(), contador.getSegundos());
	}
	
	public GUI getMiInterfaz() {
		return this.pnJuego.getVentana();
	}

	public void doblar(int tecla) {
		this.miJugador.doblar(tecla);
	}

	public int getPuntaje() {
		return this.miJugador.getPuntaje();
	}
	
	public void setNombreDelJugador(String nombre) {
		this.miJugador.setNombre(nombre);
	}

	public void setTiempo(String time) { //Delegar� el problema a la GUI
		this.pnJuego.getVentana().actualizarTimer(time);
	}

}
