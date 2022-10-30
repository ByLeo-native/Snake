package logica.entidad.consumible;

import logica.entidad.Entidad;
import logica.entidad.criatura.Criatura;
import logica.jugador.Jugador;
import logica.tablero.Tablero;

public abstract class Consumible extends Entidad {
	
	protected int puntaje;
	protected int bloques;

	public Consumible(int x, int y, int dx, int dy, Tablero t, int p, int b) {
		super(x, y, dx, dy, t);
		this.puntaje = p;
		this.bloques = b;
	}
	
	public int getPuntaje() {
		return this.puntaje;
	}
	
	public int getBloquesAAumentar() {
		return this.bloques;
	}
	
	public void afectar(Criatura c) {
		Jugador j = c.getJugador();
		j.setPuntaje( j.getPuntaje() + this.puntaje );
		c.crecer();
	}
	
	public boolean esChocable() {
		return false;
	}

}
