package logica.entidad.consumible.powerUp;

import grafico.entidadGrafica.consumible.powerUp.CocaColaGrafica;
import logica.tablero.Tablero;

public class CocaCola extends PowerUp {

	public CocaCola( int x, int y, Tablero t) {
		super( x, y, 1, 1, t, 1, 2);
		this.miEntidadGrafica = new CocaColaGrafica( x, y);
	}
	
}
