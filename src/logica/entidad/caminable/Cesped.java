package logica.entidad.caminable;

import logica.entidad.criatura.Criatura;
import logica.tablero.Tablero;

public class Cesped extends EntidadCaminable {

	public Cesped(int x, int y, Tablero t) {
		super(x, y, 1, 1, t);
	}

	@Override
	public void afectar(Criatura c) {}

}
