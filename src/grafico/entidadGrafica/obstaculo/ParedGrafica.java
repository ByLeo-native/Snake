package grafico.entidadGrafica.obstaculo;

public class ParedGrafica extends ObstaculoGrafica {

	public ParedGrafica( int x, int y) {
		super( x, y, System.getProperty("user.dir")+"/Assets/Pared.png");
	}
}
