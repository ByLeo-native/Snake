package logica.entidad;

import grafico.entidadGrafica.EntidadGrafica;
import logica.entidad.criatura.Criatura;
import logica.tablero.Tablero;

public abstract class Entidad {
	protected int posX;
	protected int posY;
	protected int dimensionX;
	protected int dimensionY;
	protected EntidadGrafica miEntidadGrafica;
	protected Tablero miTablero;
	
	public Entidad(int x, int y, int dx, int dy, Tablero t) {
		this.posX = x;
		this.posY = y;
		this.dimensionX = dx;
		this.dimensionY = dy;
		this.miTablero = t;
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	public void setPosX(int x) {
		this.posX = x;
	}
	
	public void setPosY(int y) {
		this.posY = y;
	}
	
	public int dimensionX() {
		return this.dimensionX;
	}
	
	public int dimensionY() {
		return this.dimensionY;
	}
	
	public abstract void afectar(Criatura c);
	
	public abstract boolean esChocable();

	public EntidadGrafica getEntidadGrafica() {
		return this.miEntidadGrafica;
	}
}
