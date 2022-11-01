package logica.juego;

import GUI.GUI;
import interfaz.panel.PanelJuego;
import logica.hilos.Contador;
import logica.hilos.HiloMovimiento;
import logica.jugador.Jugador;
import logica.nivel.Nivel;

public class Juego {

	protected Jugador miJugador;
	protected Nivel nivel;
	protected int nivelActual;
	protected PanelJuego pnJuego;
	protected Contador contador;
	protected HiloMovimiento hilo;
	protected boolean yaDoblo;
	
	public Juego(PanelJuego panelJuego) {
		this.pnJuego = panelJuego;
		this.nivelActual = 1;
		this.nivel = new Nivel( this, this.nivelActual);
		this.miJugador = new Jugador(this, this.nivel.getCriatura());
		this.yaDoblo = false;
	}
	
	public void iniciarPartida() {
		this.contador = new Contador(this);
		
		hilo = new HiloMovimiento(this);
		hilo.run();
	}
	
	public void finalizarPartida() {
		this.contador.stop();
		this.miJugador.definirTiempoJugado(contador.getMinutos(), contador.getSegundos());
	}
	
	public void mover() {
		this.nivel.getCriatura().mover();
	}
	
	public GUI getMiInterfaz() {
		return this.pnJuego.getVentana();
	}
	
	public PanelJuego getGui() {
		return this.pnJuego;
	}

	public void doblar(int tecla) {
		if(!this.yaDoblo) {
			this.miJugador.doblar(tecla);
			this.yaDoblo = true;
		}
	}
	
	public void permitirDoblar() {
		this.yaDoblo = false;
	}

	public int getPuntaje() {
		return this.miJugador.getPuntaje();
	}
	
	public void setNombreDelJugador(String nombre) {
		this.miJugador.setNombre(nombre);
	}

	public void setTiempo(String time) { //Delegar� el problema a la GUI
		//this.pnJuego.getVentana().actualizarTimer(time);
	}

}
