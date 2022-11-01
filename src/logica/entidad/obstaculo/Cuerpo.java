package logica.entidad.obstaculo;

import grafico.entidadGrafica.obstaculo.CuerpoGrafica;
import logica.tablero.Tablero;

public class Cuerpo extends ObstaculoConDireccion {
	
	public Cuerpo(int x, int y, Tablero t) {
		super(x, y, 1, 1, t);
		this.miEntidadGrafica = new CuerpoGrafica(x, y);
	}

}
