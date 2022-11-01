package grafico.entidadGrafico.caminable;

public class SuperficieGrafica extends CaminableGrafica {
	
	public SuperficieGrafica( int x, int y) {
		super( x, y, System.getProperty("user.dir")+"/src/Assets/algo.jpg");
	}
}
