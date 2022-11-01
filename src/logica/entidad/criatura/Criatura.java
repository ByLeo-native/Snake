package logica.entidad.criatura;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import logica.direccion.Direccion;
import logica.entidad.obstaculo.CabezaSnake;
import logica.entidad.obstaculo.Cuerpo;
import logica.jugador.Jugador;
import logica.tablero.Tablero;

public class Criatura {
	
	protected Tablero miTablero;
	protected List<Cuerpo> cuerpo;
	protected Jugador miJugador;
	protected boolean estaViva;
	protected int sentidoHorizontal;
	protected int sentidoVertical;
	protected int cantDeIncrementos;
	protected int velocidad;

	public Criatura (Tablero t) {
		this.miTablero = t;
		this.cuerpo = new ArrayList<Cuerpo>();
		this.estaViva = true;
		this.velocidad = 3;
		this.miTablero.posicionarCriatura( this, 3);
		this.cantDeIncrementos = 0;
		this.sentidoHorizontal = this.getCuerpoEnPosicion(0).getDireccion().getDireccionEnX();
		this.sentidoVertical = this.getCuerpoEnPosicion(0).getDireccion().getDireccionEnY();
	}
	
	public void morir() {
		this.estaViva = false;
	}
	
	public boolean estaViva() {
		return this.estaViva;
	}
	
	/**
	 * Agrega un cuerpo en la celda asignada.
	 * @param x entero correspondiente a la columna de la celda.
	 * @param y entero correspondiente a la fila de la celda.
	 */
	public void crecer(int x, int y) {
		Cuerpo c = new Cuerpo( x, y, miTablero);
		this.cuerpo.add(c);
		this.miTablero.getGrilla()[y][x].agregarEntidad(c);
		this.cantDeIncrementos--;
	}
	
	public void mover() {
		Cuerpo cabeza = this.cuerpo.get(0);
		int posicionActualEnX = cabeza.getEntidadGrafica().getPos().x;
		int posicionActualEnY = cabeza.getEntidadGrafica().getPos().y;
		int posicionNuevaEnX = posicionActualEnX + this.sentidoHorizontal*this.velocidad;
		int posicionNuevaEnY = posicionActualEnY + this.sentidoVertical*this.velocidad;
		int celdaActualEnX = cabeza.getPosX();
		int celdaActualEnY = cabeza.getPosY();
		int celdaNuevaEnX = Math.floorDiv(posicionNuevaEnX, cabeza.getEntidadGrafica().getWidthUnaCelda());//Redondeo hacia abajo
		int celdaNuevaEnY = Math.floorDiv(posicionNuevaEnY, cabeza.getEntidadGrafica().getHeightUnaCelda());//Redondeo hacia abajo
		
		if(celdaActualEnX != celdaNuevaEnX || celdaActualEnY != celdaNuevaEnY) {
			this.miTablero.avisoDeMovimiento(celdaNuevaEnX, celdaNuevaEnY, this);
		}

		this.avanzar();
	}
	
	protected void avanzar() {
		Iterator<Cuerpo> it = this.cuerpo.iterator();
		Direccion direccionDelAnterior = this.cuerpo.get(0).getDireccion();
		Cuerpo cuerpoActual = null;
		while(it.hasNext()) {
			cuerpoActual = it.next();
			Direccion direccionDelCuerpoActual = cuerpoActual.getDireccion();
			int posicionActualEnX = cuerpoActual.getEntidadGrafica().getPos().x;
			int posicionActualEnY = cuerpoActual.getEntidadGrafica().getPos().y;
			int posicionNuevaEnX = posicionActualEnX + direccionDelCuerpoActual.getDireccionEnX()*this.velocidad;
			int posicionNuevaEnY = posicionActualEnY + direccionDelCuerpoActual.getDireccionEnY()*this.velocidad;
			
			int celdaActualEnX = cuerpoActual.getPosX();
			int celdaActualEnY = cuerpoActual.getPosY();
			
			cuerpoActual.setPosX(Math.floorDiv(posicionNuevaEnX, cuerpoActual.getEntidadGrafica().getWidthUnaCelda())); //Establezco la celda horizontal
			cuerpoActual.setPosY(Math.floorDiv(posicionNuevaEnY, cuerpoActual.getEntidadGrafica().getHeightUnaCelda())); //Establezco la celda vertical
			cuerpoActual.getEntidadGrafica().cambiarPos(posicionNuevaEnX, posicionNuevaEnY); //Cambio la posicion del label
			
			this.cambioDeCelda(celdaActualEnX, celdaActualEnY, cuerpoActual);
			
			cuerpoActual.setDireccion(direccionDelAnterior);
		}
		
		
//		Iterator<Cuerpo> it = this.cuerpo.iterator();
//		Cuerpo cuerpoActual = it.hasNext() ? it.next() : null;
//		Direccion direccionDelCuerpoActual = cuerpoActual.getDireccion();
//		Direccion direccionDelAnterior = direccionDelCuerpoActual; // Como es la cabeza primero, no hay anterior.
//		int celdaActualEnX = cuerpoActual.getPosX();
//		int celdaActualEnY = cuerpoActual.getPosY();
//		int posicionActualEnX = cuerpoActual.getEntidadGrafica().getPos().x;
//		int posicionActualEnY = cuerpoActual.getEntidadGrafica().getPos().y;
//		int posicionNuevaEnX = posicionActualEnX + direccionDelCuerpoActual.getDireccionEnX()*this.velocidad;
//		int posicionNuevaEnY = posicionActualEnY + direccionDelCuerpoActual.getDireccionEnY()*this.velocidad;
//		System.out.println("PosNueva: "+posicionNuevaEnX+", "+posicionNuevaEnY);
//		while(it.hasNext()) {
//			celdaActualEnX = cuerpoActual.getPosX();
//			celdaActualEnY = cuerpoActual.getPosY();
//			//Cambio de posicion
//			cuerpoActual.setPosX(Math.floorDiv(posicionNuevaEnX, cuerpoActual.getEntidadGrafica().getWidthUnaCelda())); //Establezco la celda horizontal
//			cuerpoActual.setPosY(Math.floorDiv(posicionNuevaEnY, cuerpoActual.getEntidadGrafica().getHeightUnaCelda())); //Establezco la celda vertical
//			cuerpoActual.getEntidadGrafica().cambiarPos(posicionNuevaEnX, posicionNuevaEnY); //Cambio la posicion del label
//			//Verifico cambio de celda
//			int celdaViejaEnX = celdaActualEnX;
//			int celdaViejaEnY = celdaActualEnY;
//			celdaActualEnX = cuerpoActual.getPosX();
//			celdaActualEnY = cuerpoActual.getPosY();
//			
//			if(celdaViejaEnX != celdaActualEnX || celdaViejaEnY != celdaActualEnY) { //Si hubo un cambio de celda
//				this.miTablero.cambioDeCelda(celdaViejaEnX, celdaViejaEnY, celdaActualEnX, celdaActualEnY, cuerpoActual); //Pasar entidad a otra celda
//			}
//			
//			cuerpoActual.getEntidadGrafica().getLabel().repaint();
//			cuerpoActual = it.next();
//			//Hago que la posicion de un cuerpo sea la posicion del cuerpo anterior
//			
//			posicionActualEnX = cuerpoActual.getEntidadGrafica().getPos().x;
//			posicionActualEnY = cuerpoActual.getEntidadGrafica().getPos().y;
//			posicionNuevaEnX = posicionActualEnX;
//			posicionNuevaEnY = posicionActualEnY;
//		}
//		
//		if(this.cantDeIncrementos>0) {
//			this.crecer(posicionNuevaEnX, posicionNuevaEnY);
//			this.cantDeIncrementos--;
//		}
	}
	
	private void cambioDeCelda(int celdaViejaEnX, int celdaViejaEnY, Cuerpo cuerpo) {
		int celdaActualEnX = cuerpo.getPosX();
		int celdaActualEnY = cuerpo.getPosY();
		
		if(celdaViejaEnX != celdaActualEnX || celdaViejaEnY != celdaActualEnY) { //Si hubo un cambio de celda
			this.miTablero.cambioDeCelda(celdaViejaEnX, celdaViejaEnY, celdaActualEnX, celdaActualEnY, cuerpo); //Pasar entidad a otra celda
		}
	}

	public void debeCrecer(int cant) {
		this.cantDeIncrementos += cant;
	}
	
	public boolean doblar(Direccion d) {
		boolean pudoDoblar = false;
		
		if(this.puedeDoblar(d)) {
			this.getCuerpoEnPosicion(0).setDireccion(d);
			pudoDoblar = true;
		}
		
		return pudoDoblar;
	}
	
	public Jugador getJugador() {
		return this.miJugador;
	}
	
	public void setJugador(Jugador j) {
		this.miJugador = j;
	}
	
	public void setCabeza(CabezaSnake e) {
		this.cuerpo.add(0, e);
	}
	
	public void removeCabeza() {
		if(!this.cuerpo.isEmpty()) {
			this.cuerpo.remove(0);
		}
	}
	
	public void removerCola() {
		if(!this.cuerpo.isEmpty()) {
			this.cuerpo.remove(this.cuerpo.size() - 1);
		}
	}
	
	public Cuerpo getCuerpoEnPosicion(int index) {
		Cuerpo c = null;
		if(index >= 0 && index < this.cuerpo.size()) {
			c = this.cuerpo.get(index);
		}
		return c;
	}
	
	public int size() {
		return this.cuerpo.size();
	}
	
	protected boolean tieneQueCrecer() {
		return this.cantDeIncrementos > 0;
	}
	
	protected boolean puedeDoblar(Direccion d) {
		Direccion direccionDeLaCabeza = this.cuerpo.get(0).getDireccion();
		boolean puedeDoblarHorizontal = (direccionDeLaCabeza == Direccion.ARRIBA || direccionDeLaCabeza == Direccion.ABAJO) && ( d == Direccion.IZQUIERDA || d == Direccion.DERECHA);
		boolean puedeDoblarVertical = (direccionDeLaCabeza == Direccion.IZQUIERDA || direccionDeLaCabeza == Direccion.DERECHA) && ( d == Direccion.ARRIBA || d == Direccion.ABAJO);
		
		return puedeDoblarHorizontal || puedeDoblarVertical;
	}
	
}
