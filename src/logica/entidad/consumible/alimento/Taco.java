package logica.entidad.consumible.alimento;

import grafico.entidadGrafica.consumible.alimento.TacoGrafica;
import logica.tablero.Tablero;

public class Taco extends Alimento {

	public Taco(int x, int y, Tablero t) {
		super(x, y, 1, 1, t, 1, 2);
		this.miEntidadGrafica = new TacoGrafica( x, y);
	}
	
}
