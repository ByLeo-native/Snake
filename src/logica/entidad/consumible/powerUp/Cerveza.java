package logica.entidad.consumible.powerUp;

import grafico.entidadGrafica.consumible.powerUp.CervezaGrafica;
import logica.tablero.Tablero;

public class Cerveza extends PowerUp {

	public Cerveza( int x, int y, Tablero t) {
		super( x, y, 1, 1, t, 1, 2);
		this.miEntidadGrafica = new CervezaGrafica( x, y);
	}
	
}
