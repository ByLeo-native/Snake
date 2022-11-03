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
		if(this.estaViva()) {
			this.avanzar();
		}
	}
	
	protected void avanzar() {
		if(this.estaViva()) {
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
				
				int celdaAntesDeCambiarEnX = cuerpoActual.getPosX();
				int celdaAntesDeCambiarEnY = cuerpoActual.getPosY();
				
				//Como el point lo tengo en la esquina superior izquierda de cada entidadGrafica, lo muevo al lado donde tiene la direccion el cuerpo
				Direccion direccionDelCuerpo = cuerpoActual.getDireccion();
				int sentidoH, sentidoV;
				if(direccionDelCuerpo == Direccion.DERECHA || direccionDelCuerpo == Direccion.ABAJO) {
					sentidoH = direccionDelCuerpo.getDireccionEnX();
					sentidoV = direccionDelCuerpo.getDireccionEnY();
				} else {
					sentidoH = 0;
					sentidoV = 0;
				}
				
				
				int celdaDespuesDeCambiarEnX = Math.floorDiv(posicionNuevaEnX + sentidoH*cuerpoActual.getEntidadGrafica().getWidth(), cuerpoActual.getEntidadGrafica().getWidthUnaCelda());//Redondeo hacia abajo;
				int celdaDespuesDeCambiarEnY = Math.floorDiv(posicionNuevaEnY + sentidoV*cuerpoActual.getEntidadGrafica().getHeight(), cuerpoActual.getEntidadGrafica().getHeightUnaCelda());//Redondeo hacia abajo;
				
				
				if(celdaAntesDeCambiarEnX != celdaDespuesDeCambiarEnX || celdaAntesDeCambiarEnY != celdaDespuesDeCambiarEnY) {
					if(cuerpoActual.equals(this.getCuerpoEnPosicion(0))) {
						this.miTablero.avisoDeMovimiento(celdaDespuesDeCambiarEnX, celdaDespuesDeCambiarEnY, this);
					}
					
					this.miTablero.cambioDeCelda(celdaAntesDeCambiarEnX, celdaAntesDeCambiarEnY, celdaDespuesDeCambiarEnX, celdaDespuesDeCambiarEnY, cuerpoActual);
					System.out.println("Celda Actual: ("+celdaAntesDeCambiarEnX+", "+celdaAntesDeCambiarEnY+")");
					System.out.println("Celda Nueva: ("+celdaDespuesDeCambiarEnX+", "+celdaDespuesDeCambiarEnY+")");
					
				}
				
				cuerpoActual.setPosX(celdaDespuesDeCambiarEnX); //Establezco la celda horizontal
				cuerpoActual.setPosY(celdaDespuesDeCambiarEnY); //Establezco la celda vertical
				cuerpoActual.getEntidadGrafica().cambiarPos(posicionNuevaEnX, posicionNuevaEnY); //Cambio la posicion del label
				
				
				cuerpoActual.setDireccion(direccionDelAnterior);
			}
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
