package logica.entidad.obstaculo;

import grafico.entidadGrafica.obstaculo.CabezaSnakeGrafica;
import logica.tablero.Tablero;

public class CabezaSnake extends Cuerpo {

	public CabezaSnake(int x, int y, Tablero t) {
		super(x, y, t);
		this.miEntidadGrafica = new CabezaSnakeGrafica(x, y);
	}

}
