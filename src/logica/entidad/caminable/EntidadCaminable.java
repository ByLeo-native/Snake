package logica.entidad.caminable;

import logica.entidad.Entidad;
import logica.entidad.criatura.Criatura;
import logica.tablero.Tablero;

public abstract class EntidadCaminable extends Entidad {

	public EntidadCaminable(int x, int y, int dx, int dy, Tablero t) {
		super(x, y, dx, dy, t);
	}
	
	public void afectar(Criatura c) {}

	public boolean esChocable() {
		return false;
	}
}
