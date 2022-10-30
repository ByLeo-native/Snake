package logica.entidad.consumible.powerUp;

import logica.entidad.consumible.Consumible;
import logica.tablero.Tablero;

public abstract class PowerUp extends Consumible {

	public PowerUp(int x, int y, int dx, int dy, Tablero t, int p, int b) {
		super(x, y, dx, dy, t, p, b);
	}

}
