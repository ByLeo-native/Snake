package logica.entidad.criatura;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		this.miTablero.posicionarCriatura(3);
		this.cantDeIncrementos = 0;
		this.velocidad = 3;
	}
	
	public void morir() {
		this.estaViva = false;
	}
	
	public boolean estaViva() {
		return this.estaViva;
	}
	
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
		Cuerpo cuerpoActual = it.hasNext() ? it.next() : null;
		int celdaActualEnX = cuerpoActual.getPosX();
		int celdaActualEnY = cuerpoActual.getPosY();
		int posicionActualEnX = cuerpoActual.getEntidadGrafica().getPos().x;
		int posicionActualEnY = cuerpoActual.getEntidadGrafica().getPos().y;
		int posicionNuevaEnX = posicionActualEnX + this.sentidoHorizontal*this.velocidad;
		int posicionNuevaEnY = posicionActualEnY + this.sentidoVertical*this.velocidad;
		
		while(it.hasNext()) {
			celdaActualEnX = cuerpoActual.getPosX();
			celdaActualEnY = cuerpoActual.getPosY();
			
			cuerpoActual.setPosX(Math.floorDiv(posicionNuevaEnX, cuerpoActual.getEntidadGrafica().getWidthUnaCelda()));
			cuerpoActual.setPosY(Math.floorDiv(posicionNuevaEnY, cuerpoActual.getEntidadGrafica().getHeightUnaCelda()));
			
			cuerpoActual.getEntidadGrafica().cambiarPos(posicionNuevaEnX, posicionNuevaEnY);
			
			int celdaViejaEnX = celdaActualEnX;
			int celdaViejaEnY = celdaActualEnY;
			celdaActualEnX = cuerpoActual.getPosX();
			celdaActualEnY = cuerpoActual.getPosY();
			
			if(celdaViejaEnX != celdaActualEnX || celdaViejaEnY != celdaActualEnY) {
				this.miTablero.cambioDeCelda(celdaViejaEnX, celdaViejaEnY, celdaActualEnX, celdaActualEnY, cuerpoActual);
			}
			
			cuerpoActual = it.next();
			//Hago que la posicion de un cuerpo sea la posicion del cuerpo anterior
			posicionNuevaEnX = posicionActualEnX;
			posicionNuevaEnY = posicionActualEnY;
			posicionActualEnX = cuerpoActual.getEntidadGrafica().getPos().x;
			posicionActualEnY = cuerpoActual.getEntidadGrafica().getPos().y;
		}
		
		if(this.cantDeIncrementos>0) {
			this.crecer(posicionNuevaEnX, posicionNuevaEnY);
			this.cantDeIncrementos--;
		}
	}
	
	public void debeCrecer(int cant) {
		this.cantDeIncrementos += cant;
	}
	
	public boolean doblar(int sH, int sV) {
		boolean pudoDoblar = false;
		
		if(this.puedeDoblar(sH, sV)) {
			this.sentidoHorizontal = sH;
			this.sentidoVertical = sV;
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
	
	public void setDireccion(int sH, int sV) {
		if(this.puedeDoblar(sH, sV)) {
			this.sentidoHorizontal = sH;
			this.sentidoVertical = sV;
		}
	}
	
	protected boolean tieneQueCrecer() {
		return this.cantDeIncrementos > 0;
	}
	
	protected boolean puedeDoblar(int sH, int sV) {
		boolean puedeDoblarHorizontal = (this.sentidoHorizontal == 0 && this.sentidoVertical != 0 && sH != 0 && sV == 0);
		boolean puedeDoblarVertical = (this.sentidoHorizontal != 0 && this.sentidoVertical == 0 && sH == 0 && sV != 0);
		
		return puedeDoblarHorizontal || puedeDoblarVertical;
	}
	
}
