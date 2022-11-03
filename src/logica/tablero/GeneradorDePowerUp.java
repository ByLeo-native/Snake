package logica.tablero;

import logica.celda.Celda;
import logica.entidad.consumible.Consumible;
import logica.entidad.consumible.powerUp.Cafe;
import logica.entidad.consumible.powerUp.Cerveza;
import logica.entidad.consumible.powerUp.CocaCola;

public class GeneradorDePowerUp extends GeneradorDeConsumible {

	public GeneradorDePowerUp(Tablero t) {
		super(t);
	}
	
	@Override
	public Consumible obtenerConsumibleRandom(int row, int col, Celda[][] grilla) {

		Consumible consumibleARetornar = null;
		int rngconsumibleARetornar = (int)(Math.random()*100);
		
		if (rngconsumibleARetornar < 50) consumibleARetornar = new Cafe( col, row, this.miTablero);
		else if (rngconsumibleARetornar < 85) consumibleARetornar = new CocaCola( col, row, this.miTablero);
		else if (rngconsumibleARetornar < 101) consumibleARetornar = new Cerveza( col, row, this.miTablero);
		
		
		System.out.println("PowerUp En ("+col+", "+row+")");
		
		return consumibleARetornar;
	}

	@Override
	public boolean yaHayConsumible() {
		return this.miTablero.hayPowerUp;
	}

	@Override
	public void seAniadioConsumible() {
		this.miTablero.hayPowerUp = true;
	}

}
