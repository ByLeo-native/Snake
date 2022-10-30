package logica.tablero;

import logica.celda.Celda;
import logica.entidad.consumible.Consumible;
import logica.entidad.consumible.alimento.Cupcake;
import logica.entidad.consumible.alimento.Hamburguesa;
import logica.entidad.consumible.alimento.Nugget;
import logica.entidad.consumible.alimento.PapasFritas;
import logica.entidad.consumible.alimento.Taco;

public class GeneradorDeComida extends GeneradorDeConsumible {

	public GeneradorDeComida(Tablero t) {
		super(t);
	}

	@Override
	public Consumible obtenerConsumibleRandom(int row, int col, Celda[][] grilla) {

		Consumible consumibleARetornar = null;
		int rngconsumibleARetornar = (int)(Math.random()*100);
		
		if (rngconsumibleARetornar < 31) consumibleARetornar = new Cupcake( row, col, this.miTablero);
		else if (rngconsumibleARetornar < 55) consumibleARetornar = new Nugget(row, col, this.miTablero);
		else if (rngconsumibleARetornar < 75) consumibleARetornar = new PapasFritas(row, col, this.miTablero);
		else if (rngconsumibleARetornar < 90) consumibleARetornar = new Taco(row, col, this.miTablero);
		else if (rngconsumibleARetornar < 101) consumibleARetornar = new Hamburguesa(row, col, this.miTablero);
		
		System.out.println("Comida En ("+col+", "+row+")");
		
		return consumibleARetornar;
	}

	@Override
	public boolean yaHayConsumible() {
		return this.miTablero.hayComida;
	}

	@Override
	public void seAniadioConsumible() {
		this.miTablero.hayComida = true;
	}

}
