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

	public Criatura (Tablero t) {
		this.miTablero = t;
		this.cuerpo = new ArrayList<Cuerpo>();
		this.estaViva = true;
		this.miTablero.posicionarCriatura(3);
		this.cantDeIncrementos = 0;
	}
	
	public void morir() {
		this.estaViva = false;
	}
	
	public boolean estaViva() {
		return this.estaViva;
	}
	
	public void crecer() {
		this.cantDeIncrementos++;
	}
	
	public void mover() {
		int posicionEnXDeLaSiguienteCelda = this.cuerpo.get(0).getPosX() + this.sentidoHorizontal;
		int posicionEnYDeLaSiguienteCelda = this.cuerpo.get(0).getPosY() + this.sentidoVertical;
		this.miTablero.avisoDeMovimiento(posicionEnXDeLaSiguienteCelda, posicionEnYDeLaSiguienteCelda);
		
		
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
	
	protected void avanzar() {
		Iterator<Cuerpo> iterator = this.cuerpo.iterator();
		Cuerpo c = iterator.hasNext() ? iterator.next() : null;
		while(iterator.hasNext()) {
			
		}
	}

}
