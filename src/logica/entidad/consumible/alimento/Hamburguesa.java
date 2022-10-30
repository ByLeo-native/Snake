package logica.entidad.consumible.alimento;

import grafico.entidadGrafica.consumible.alimento.HamburguesaGrafica;
import logica.tablero.Tablero;

public class Hamburguesa extends Alimento {

	public Hamburguesa(int x, int y, Tablero t) {
		super( x, y, 1, 1, t, 1, 2);
		this.miEntidadGrafica = new HamburguesaGrafica( x, y);
	}
	
}
