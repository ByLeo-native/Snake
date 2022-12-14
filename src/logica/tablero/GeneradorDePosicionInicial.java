package logica.tablero;

import logica.direccion.Direccion;
import logica.entidad.criatura.Criatura;
import logica.entidad.obstaculo.CabezaSnake;

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
			
			if( !this.miTablero.miGrilla[j][i].getPrimeraEntidad().esChocable()) {
				if( this.noHayParedAIzquierda(i, j, cantidad -1) ) {
					this.agregarCuerpoAIzquierda(i, j, cantidad-1, this.miCriatura);
					this.definirDireccion(Direccion.DERECHA, miCriatura);
					sePosiciono = true;
				} else if( this.noHayParedArriba(i, j, cantidad-1)) {
					this.agregarCuerpoArriba(i, j, cantidad-1, this.miCriatura);
					this.definirDireccion(Direccion.ABAJO, miCriatura);
					sePosiciono = true;
				} else if( this.noHayParedADerecha(i, j, cantidad-1)) {
					this.agregarCuerpoADerecha(i, j, cantidad-1, this.miCriatura);
					this.definirDireccion(Direccion.IZQUIERDA, miCriatura);
					sePosiciono = true;
				} else if( this.noHayParedAbajo(i, j, cantidad-1)) {
					this.agregarCuerpoAbajo(i, j, cantidad-1, this.miCriatura);
					this.definirDireccion(Direccion.ARRIBA, miCriatura);
					sePosiciono = true;
				}
				
				if(sePosiciono) {
					CabezaSnake e = new CabezaSnake(i, j, this.miTablero);
					this.miCriatura.setCabeza(e);
					this.miTablero.getGrilla()[j][i].agregar(e);
					e.setDireccion(this.miCriatura.getCuerpoEnPosicion(1).getDireccion());
					System.out.println("Cabeza en ( "+i+", "+j+")");
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
			if(!this.miTablero.getGrilla()[j][i - (cantExaminado+1)].getPrimeraEntidad().esChocable()) {
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
			if(!this.miTablero.getGrilla()[j][i + (cantExaminado+1)].getPrimeraEntidad().esChocable()) {
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
			if(!this.miTablero.getGrilla()[j - (cantExaminado+1)][i].getPrimeraEntidad().esChocable()) {
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
			if(!this.miTablero.getGrilla()[j + (cantExaminado+1)][i].getPrimeraEntidad().esChocable()) {
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
			c.crecer( i - (cantAgregado+1), j);
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
			c.crecer( i + (cantAgregado+1), j);
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
			c.crecer( i, j -(cantAgregado+1));
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
			c.crecer( i, j+(cantAgregado+1));
			cantAgregado++;
		}
	}
	
	protected void definirDireccion(Direccion d, Criatura c) {
		for(int i=0; i< c.size(); i++) {
			c.getCuerpoEnPosicion(i).setDireccion(d);
		}
	}
}
