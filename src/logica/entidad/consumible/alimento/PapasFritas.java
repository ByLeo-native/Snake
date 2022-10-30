package logica.entidad.consumible.alimento;

import grafico.entidadGrafica.consumible.alimento.PapaFritasGrafica;
import logica.tablero.Tablero;

public class PapasFritas extends Alimento {

	public PapasFritas(int x, int y, Tablero t) {
		super( x, y, 1, 1, t, 1, 2);
		this.miEntidadGrafica = new PapaFritasGrafica( x, y);
	}
	
}
