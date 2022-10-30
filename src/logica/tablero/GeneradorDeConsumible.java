package logica.tablero;

import logica.celda.Celda;
import logica.entidad.consumible.Consumible;

public abstract class GeneradorDeConsumible {
	
	protected Tablero miTablero;
	
	public GeneradorDeConsumible(Tablero t) {
		this.miTablero = t;
	}
	
	public void generarConsumible() {
			
			Celda[][] grilla = this.miTablero.getGrilla();
			
			int row = 0; 
			int col = 0; 
			
			while(!this.yaHayConsumible()) {
				row = (int)(Math.random() * 19); 
				col = (int)(Math.random() * 19); 
				
				if(!grilla[row][col].getPrimeraEntidad().esChocable()) {
					
					Consumible morfi = this.obtenerConsumibleRandom(row, col, grilla);
					
					grilla[row][col].agregarEntidad(morfi);
					this.seAniadioConsumible();
			}
		}
	}
	
	/**
	 * Consulta si en el tablero ya hay el correspondiente consumible.
	 * @return Verdadero si el tablero tiene el correspondiente consumible, falso en caso contrario.
	 */
	public abstract boolean yaHayConsumible();
	
	/**
	 * Modifica el valor del parametro que indica la existencia del consumible en el tablero
	 */
	public abstract void seAniadioConsumible();
	
	public abstract Consumible obtenerConsumibleRandom(int row, int col, Celda[][] grilla);
}
