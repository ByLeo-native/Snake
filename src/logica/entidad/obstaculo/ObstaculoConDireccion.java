package logica.entidad.obstaculo;

import logica.direccion.Direccion;
import logica.tablero.Tablero;

public class ObstaculoConDireccion extends Obstaculo  {
	
	protected Direccion miDireccion;

	public ObstaculoConDireccion(int x, int y, int dx, int dy, Tablero t) {
		super(x, y, dx, dy, t);
		this.miDireccion = Direccion.DERECHA; //Defino por defecto
	}
	
	public Direccion getDireccion() {
		return this.miDireccion;
	}
	
	public void setDireccion(Direccion d) {
		this.miDireccion = d;
	}

}
