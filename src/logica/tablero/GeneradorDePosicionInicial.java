package logica.tablero;

import logica.entidad.Entidad;
import logica.entidad.criatura.Criatura;
import logica.entidad.obstaculo.CabezaSnake;
import logica.entidad.obstaculo.Cuerpo;

public class GeneradorDePosicionInicial {
	
	protected Tablero miTablero;
	protected Criatura miCriatura;
	protected int cantidad;
	protected int cantDeColumnas, cantDeFilas;
	
	public GeneradorDePosicionInicial(Tablero t, Criatura c, int cant) {
		this.miTablero = t;
		this.miCriatura = c;
		this.cantidad = cant;
		this.cantDeColumnas = this.miTablero.getGrilla()[0].length;
		this.cantDeFilas = this.miTablero.getGrilla().length;
	}
	
	public void posicionarCriatura() {
		boolean sePosiciono = false;
		
		while(!sePosiciono) {
			
			int i = (int)(Math.random()* (this.cantDeColumnas-1));
			int j = (int)(Math.random()* (this.cantDeFilas-1));
			
			if( !this.miTablero.miGrilla[i][j].getPrimeraEntidad().esChocable()) {
				if( this.noHayParedAIzquierda(i, j, cantidad -1) ) {
					this.agregarCuerpoAIzquierda(i, j, cantidad-1, this.miCriatura);
					sePosiciono = true;
					this.miCriatura.setDireccion(1, 0); //Direccion hacia la derecha
				} else if( this.noHayParedArriba(i, j, cantidad-1)) {
					this.agregarCuerpoArriba(i, j, cantidad-1, this.miCriatura);
					sePosiciono = true;
					this.miCriatura.setDireccion(0, 1); //Direccion hacia abajo
				} else if( this.noHayParedADerecha(i, j, cantidad-1)) {
					this.agregarCuerpoADerecha(i, j, cantidad-1, this.miCriatura);
					sePosiciono = true;
					this.miCriatura.setDireccion(-1, 0); //Direccion hacia la izquierda
				} else if( this.noHayParedAbajo(i, j, cantidad-1)) {
					this.agregarCuerpoAbajo(i, j, cantidad-1, this.miCriatura);
					sePosiciono = true;
					this.miCriatura.setDireccion(0, -1); //Direccion hacia arriba
				}
				
				if(sePosiciono) {
					CabezaSnake e = new CabezaSnake(j, i, this.miTablero);
					this.miCriatura.setCabeza(e);
					this.miTablero.getGrilla()[i][j].agregarEntidad(e);
				}
			}
		}
	}
	
	/**
	 * Verifica si existe un cierta cantidad de celdas no chocables de una celda dada
	 * @param i
	 * @param j
	 * @param cant
	 * @return
	 * @throws EmptyListException 
	 */
	protected boolean noHayParedAIzquierda(int i, int j, int cant) {
		boolean seCumple = false;
		boolean seEncontroConUnaPared = false;
		int cantExaminado = 0;
		
		while(!seCumple && !seEncontroConUnaPared) {
			if(!this.miTablero.getGrilla()[i][j - (cantExaminado+1)].getPrimeraEntidad().esChocable()) {
				cantExaminado++;
			} else {
				seEncontroConUnaPared = true;
			}
			
			if(cantExaminado == cant) {
				seCumple = true;
			}
		}
		return seCumple;
	}
	
	/**
	 * Verifica si existe un cierta cantidad de celdas no chocables a la derecha de una celda dada
	 * @param i
	 * @param j
	 * @param cant
	 * @return
	 * @throws EmptyListException 
	 */
	protected boolean noHayParedADerecha(int i, int j, int cant) {
		boolean seCumple = false;
		boolean seEncontroConUnaPared = false;
		int cantExaminado = 0;
		
		while(!seCumple && !seEncontroConUnaPared) {
			if(!this.miTablero.getGrilla()[i][j + (cantExaminado+1)].getPrimeraEntidad().esChocable()) {
				cantExaminado++;
			} else {
				seEncontroConUnaPared = true;
			}
			
			if(cantExaminado == cant) {
				seCumple = true;
			}
		}
		return seCumple;
	}
	
	/**
	 * Verifica si existe un cierta cantidad de celdas no chocables arriba de una celda dada
	 * @param i
	 * @param j
	 * @param cant
	 * @return
	 * @throws EmptyListException 
	 */
	protected boolean noHayParedArriba(int i, int j, int cant) {
		boolean seCumple = false;
		boolean seEncontroConUnaPared = false;
		int cantExaminado = 0;
		
		while(!seCumple && !seEncontroConUnaPared) {
			if(!this.miTablero.getGrilla()[i - (cantExaminado+1)][j].getPrimeraEntidad().esChocable()) {
				cantExaminado++;
			} else {
				seEncontroConUnaPared = true;
			}
			
			if(cantExaminado == cant) {
				seCumple = true;
			}
		}
		return seCumple;
	}
	
	/**
	 * Verifica si existe un cierta cantidad de celdas no chocables abajo de una celda dada
	 * @param i
	 * @param j
	 * @param cant
	 * @return
	 * @throws EmptyListException 
	 */
	protected boolean noHayParedAbajo(int i, int j, int cant) {
		boolean seCumple = false;
		boolean seEncontroConUnaPared = false;
		int cantExaminado = 0;
		
		while(!seCumple && !seEncontroConUnaPared) {
			if(!this.miTablero.getGrilla()[i + (cantExaminado+1)][j].getPrimeraEntidad().esChocable()) {
				cantExaminado++;
			} else {
				seEncontroConUnaPared = true;
			}
			
			if(cantExaminado == cant) {
				seCumple = true;
			}
		}
		return seCumple;
	}
	
	/**
	 * Agrega una cantidad de cuerpos a izquierda de una serpiente
	 * @param i coordenada en x de la celda referencia.
	 * @param j coordenada en y de la celda referencia.
	 * @param cant cantidad entera de cuerpos a agregar
	 * @param c criatura
	 */
	protected void agregarCuerpoAIzquierda(int i, int j, int cant, Criatura c) {
		int cantAgregado = 0;
		
		while(cantAgregado < cant) {
			c.crecer( i, j);
			cantAgregado++;
		}
	}
	
	/**
	 * Agrega una cantidad de cuerpos a derecha de una serpiente
	 * @param i coordenada en x de la celda referencia.
	 * @param j coordenada en y de la celda referencia.
	 * @param cant cantidad entera de cuerpos a agregar
	 * @param c criatura
	 */
	protected void agregarCuerpoADerecha(int i, int j, int cant, Criatura c) {
		int cantAgregado = 0;
		
		while(cantAgregado < cant) {
			c.crecer( i, j);
			cantAgregado++;
		}
	}
	
	/**
	 * Agrega una cantidad de cuerpos a derecha de una serpiente
	 * @param i coordenada en x de la celda referencia.
	 * @param j coordenada en y de la celda referencia.
	 * @param cant cantidad entera de cuerpos a agregar
	 * @param c criatura
	 */
	protected void agregarCuerpoArriba(int i, int j, int cant, Criatura c) {
		int cantAgregado = 0;
		
		while(cantAgregado < cant) {
			c.crecer( i, j);
			cantAgregado++;
		}
	}
	
	/**
	 * Agrega una cantidad de cuerpos a derecha de una serpiente
	 * @param i coordenada en x de la celda referencia.
	 * @param j coordenada en y de la celda referencia.
	 * @param cant cantidad entera de cuerpos a agregar
	 * @param c criatura
	 */
	protected void agregarCuerpoAbajo(int i, int j, int cant, Criatura c) {
		int cantAgregado = 0;
		
		while(cantAgregado < cant) {
			c.crecer( i, j);
			cantAgregado++;
		}
	}
}
