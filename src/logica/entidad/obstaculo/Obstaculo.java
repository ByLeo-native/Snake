package logica.entidad.obstaculo;

import logica.entidad.Entidad;
import logica.entidad.criatura.Criatura;
import logica.tablero.Tablero;

public abstract class Obstaculo extends Entidad {

	public Obstaculo(int x, int y, int dx, int dy, Tablero t) {
		super(x, y, dx, dy, t);
	}

	public void afectar(Criatura c) {
		c.morir();
		this.miTablero.avisoDeFinDeJuego();
	}
	
	public boolean esChocable() {
		return true;
	}
}

