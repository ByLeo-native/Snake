package logica.entidad.consumible.alimento;

import logica.entidad.consumible.Consumible;
import logica.tablero.Tablero;

public abstract class Alimento extends Consumible {

	public Alimento(int x, int y, int dx, int dy, Tablero t, int p, int b) {
		super(x, y, dx, dy, t, p, b);
	}

}
