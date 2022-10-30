package logica.tablero;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import grafico.tableroGrafico.TableroGrafico;
import logica.celda.Celda;
import logica.entidad.Entidad;
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
		
		this.miGrilla = n.generarNivel();
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
	
	public Celda[][] getGrilla() {
		return this.miGrilla;
	}
	
	public void posicionarCriatura(int cant) {
		GeneradorDePosicionInicial generador = new GeneradorDePosicionInicial(this, this.miCriatura, cant);
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
	
	public boolean hayComida() {
		return this.hayComida;
	}
	
	public void seAniadioComida() {
		this.hayComida = true;
	}
	
	public void seConsumioComida() {
		this.hayComida = false;
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
	
	/**
	 * Aplica los efectos en la criatura de las entidades con las que se encuentra en la siguiente celda.
	 * @param i entero positivo de la posicion en x (columna) de la celda.
	 * @param j entero positivo de la posicion en y (fila) de la celda
	 */
	public void avisoDeMovimiento(int i, int j) {
		Celda siguienteCelda = this.miGrilla[j][i];
		Iterator<Entidad> iterator = siguienteCelda.getIterator();
		Entidad e = iterator.hasNext() ? iterator.next() : null;
		
		while(iterator.hasNext()) {
			e.afectar(miCriatura);
			e = iterator.next();
		}
	}

}
