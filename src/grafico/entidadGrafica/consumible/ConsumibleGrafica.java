package grafico.entidadGrafica.consumible;

import grafico.entidadGrafica.EntidadGrafica;

public abstract class ConsumibleGrafica extends EntidadGrafica {

	public ConsumibleGrafica (int x, int y, String ruta) {
		super( x, y, 1, 1);
		this.ruta = ruta;
		this.agregarImagen();
	}
	
}
