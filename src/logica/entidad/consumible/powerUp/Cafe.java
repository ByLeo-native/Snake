package logica.entidad.consumible.powerUp;

import grafico.entidadGrafica.consumible.powerUp.CafeGrafica;
import logica.tablero.Tablero;

public class Cafe extends PowerUp {

	public Cafe( int x, int y, Tablero t) {
		super( x, y, 1, 1, t, 1, 2);
		this.miEntidadGrafica = new CafeGrafica( x, y);
	}
	
}
