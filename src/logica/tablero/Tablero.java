package logica.tablero;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import grafico.tableroGrafico.TableroGrafico;
import logica.celda.Celda;
import logica.entidad.Entidad;
import logica.entidad.consumible.Consumible;
import logica.entidad.consumible.alimento.Alimento;
import logica.entidad.consumible.powerUp.PowerUp;
import logica.entidad.criatura.Criatura;
import logica.nivel.Nivel;

public class Tablero {
	
	protected Nivel miNivel;
	protected Celda[][] miGrilla;
	protected Criatura miCriatura;
	protected TableroGrafico map;
	protected boolean hayComida, hayPowerUp;

	public Tablero(Nivel n) {
		this.miNivel = n;
		this.miGrilla = n.generarNivel(this);
		this.hayComida = false;
		this.hayPowerUp = false;
		this.miCriatura = new Criatura(this);
		this.aniadirConsumibles();
		this.map = new TableroGrafico(this);
	}
	
	public Nivel getNivel() {
		return this.miNivel;
	}

	public Criatura getCriatura() {
		return this.miCriatura;
	}
	
	public TableroGrafico getTableroGrafico() {
		return this.map;
	}
	
	public Celda[][] getGrilla() {
		return this.miGrilla;
	}
	
	public void posicionarCriatura( Criatura c, int cant) {
		GeneradorDePosicionInicial generador = new GeneradorDePosicionInicial(this, c, cant);
		generador.posicionarCriatura();
	}
	
	public void aniadirConsumibles() {
		List<GeneradorDeConsumible> lista = new ArrayList<GeneradorDeConsumible>();
		lista.add(new GeneradorDeComida(this));
		lista.add(new GeneradorDePowerUp(this));
		
		for( GeneradorDeConsumible g : lista) {
			g.generarConsumible();
		}
	}
	
	public void seConsumioConsumible(Consumible c) {
		Celda celda = this.miGrilla[c.getPosY()][c.getPosX()];
		celda.removerEntidad(c);
		this.map.actualizarImagenDeEntidadGrafica( c.getEntidadGrafica(), celda.getUltimaEntidad().getEntidadGrafica());
	}
	
	public boolean hayPowerUp() {
		return this.hayPowerUp;
	}
	
	public void seAniadioPowerUp() {
		this.hayPowerUp = true;
	}
	
	public void seConsumioPowerUp() {
		this.hayPowerUp = false;
	}
	
	public void seConsumioAlimento() {
		this.hayComida = false;
	}
	
	public boolean hayComida() {
		return this.hayComida;
	}
	
	public void seAniadioComida() {
		this.hayComida = true;
	}
	
	
	/**
	 * Aplica los efectos en la criatura de las entidades con las que se encuentra en la siguiente celda.
	 * @param i entero positivo de la posicion en x (columna) de la celda.
	 * @param j entero positivo de la posicion en y (fila) de la celda
	 */
	public void avisoDeMovimiento(int i, int j, Criatura c) {
		Celda siguienteCelda = this.miGrilla[j][i];
		Iterator<Entidad> iterator = siguienteCelda.getIterator();
		Entidad e = null;
		
		while(iterator.hasNext()) {
			e = iterator.next();
			e.afectar(c);
		}
	}
	
	public void avisoDeFinDeJuego() {
		this.miNivel.getJuego().finalizarPartida();
	}
	
	public void cambioDeCelda(int posXVieja, int posYVieja, int posXNueva, int posYNueva, Entidad e) {
		this.miGrilla[posYVieja][posXVieja].removerEntidad(e);
		this.miGrilla[posYNueva][posXNueva].agregarEntidad(e);
	}

}
