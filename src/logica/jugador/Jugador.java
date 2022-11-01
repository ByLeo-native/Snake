package logica.jugador;

import logica.auxiliar.Hora;
import logica.direccion.Direccion;
import logica.entidad.criatura.Criatura;
import logica.juego.Juego;

public class Jugador {

	protected Criatura miCriatura;
	protected Juego juego;
	protected int puntaje;
	protected String nombre;
	protected Hora tiempoJugado;
	
	public Jugador(Juego j, Criatura c) {
		this.juego = j;
		this.miCriatura = c;
		c.setJugador(this);
		this.puntaje = 0;
		this.nombre = "";
		this.tiempoJugado= new Hora(0,0);
	}
	
	public int getPuntaje() {
		return this.puntaje;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setPuntaje(int p) {
		this.puntaje = p;
	}
	
	public void setNombre(String n) {
		this.nombre = n;
	}

	public void doblar(int tecla) {
		if(tecla == 2) {
			this.arriba();
		} else if (tecla == 1) {
			this.derecha();
		} else if (tecla == -2) {
			this.abajo();
		} else if (tecla == -1) {
			this.izquierda();
		}
	}
	
	public void definirTiempoJugado(int minutos, int segundos) {
		this.tiempoJugado.setMinutos(minutos);
		this.tiempoJugado.setSegundos(segundos);
	}
	
	protected void izquierda() {
		this.miCriatura.doblar(Direccion.IZQUIERDA);
	}

	protected void abajo() {
		this.miCriatura.doblar(Direccion.ABAJO);
	}

	protected void derecha() {
		this.miCriatura.doblar(Direccion.DERECHA);
	}

	protected void arriba() {
		this.miCriatura.doblar(Direccion.ARRIBA);
	}

}
