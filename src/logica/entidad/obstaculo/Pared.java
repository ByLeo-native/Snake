package logica.entidad.obstaculo;

import grafico.entidadGrafica.obstaculo.ParedGrafica;
import logica.tablero.Tablero;

public class Pared extends Obstaculo {
	
	public Pared(int x, int y, Tablero t) {
		super( x, y, 1, 1, t);
		this.miEntidadGrafica = new ParedGrafica(x, y);
	}
}
