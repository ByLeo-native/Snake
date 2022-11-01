package logica.entidad.caminable;

import grafico.entidadGrafico.caminable.SuperficieGrafica;
import logica.entidad.criatura.Criatura;
import logica.tablero.Tablero;

public class Cesped extends EntidadCaminable {

	public Cesped(int x, int y, Tablero t) {
		super(x, y, 1, 1, t);
		this.miEntidadGrafica = new SuperficieGrafica(x,y);
	}

	@Override
	public void afectar(Criatura c) {}

}
