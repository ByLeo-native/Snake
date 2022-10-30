package logica.entidad.consumible.alimento;

import grafico.entidadGrafica.consumible.alimento.NuggetGrafica;
import logica.tablero.Tablero;

public class Nugget extends Alimento {

	public Nugget(int x, int y, Tablero t) {
		super( x, y, 1, 1, t, 1, 2);
		this.miEntidadGrafica = new NuggetGrafica( x, y);
	}
	
}
