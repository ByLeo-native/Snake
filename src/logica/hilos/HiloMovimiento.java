package logica.hilos;

import logica.juego.Juego;

public class HiloMovimiento implements Runnable {
	
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
			this.juego.getGui().repaint();
			try {
				Thread.sleep(1000/30);
			} catch (InterruptedException e) {e.printStackTrace();}
			this.juego.permitirDoblar();
		}
	}
	
	public void detener() {
		this.execute = false;
	}
}
