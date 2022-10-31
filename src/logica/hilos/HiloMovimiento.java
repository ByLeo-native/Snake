package logica.hilos;

import logica.juego.Juego;

public class HiloMovimiento extends Thread {
	
	protected Juego juego;
	protected boolean execute;
	
	public HiloMovimiento(Juego j) {
		super();
		this.juego = j;
		this.execute = true;
	}
	
	public void run() { 
		while(execute) {
			this.juego.mover();
			try {
				Thread.sleep(180);
			} catch (InterruptedException e) {e.printStackTrace();}
			this.juego.permitirDoblar();
		}
	}
}
