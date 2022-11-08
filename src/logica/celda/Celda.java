package logica.celda;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import grafico.entidadGrafica.EntidadGrafica;
import logica.entidad.Entidad;

public class Celda {

	protected List<Entidad> entidades;
	
	public Celda() {
		this.entidades = new ArrayList<Entidad>();
	}
	
	public void agregar(Entidad e) {
		int indice = this.entidades.size();
		this.entidades.add( indice, e);
	}
	
	public void remover(Entidad e) {
		this.entidades.remove(e);
	}
	
	public Entidad getPrimeraEntidad() {
		return this.entidades.get(0);
	}
	
	public Iterator<Entidad> getIterator() {
		return this.entidades.iterator();
	}

	public Entidad getUltimaEntidad() {
		return this.entidades.get(this.entidades.size()-1);
	}
}
