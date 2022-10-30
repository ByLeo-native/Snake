package logica.entidad.consumible.alimento;

import grafico.entidadGrafica.consumible.alimento.CupcakeGrafica;
import logica.tablero.Tablero;

public class Cupcake extends Alimento {

	public Cupcake(int x, int y, Tablero t) {
		super( x, y, 1, 1, t, 1, 2);
		this.miEntidadGrafica = new CupcakeGrafica( x, y);
	}
	
}
