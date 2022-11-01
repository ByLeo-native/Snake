package grafico.entidadGrafica.obstaculo;

import grafico.entidadGrafica.EntidadGrafica;

public abstract class ObstaculoGrafica extends EntidadGrafica {
	
	public ObstaculoGrafica( int x, int y, String ruta) {
		super(x, y, 1, 1, ruta);
	}
	
}
