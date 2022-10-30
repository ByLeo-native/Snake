package logica.hilos;

import java.util.Timer;
import java.util.TimerTask;

import logica.juego.Juego;

public class Contador extends Timer {
	protected int segundosJuego = 0;
	protected int minutosJuego = 0;
	protected Timer temporizador;
	protected TimerTask contar;
	protected Juego logica;
	
	public Contador(Juego logica){
		temporizador = new Timer();
		this.logica = logica;
		contar = new TimerTask() {
		/**
		 * Comienza el ciclo donde se cuenta el tiempo de juego.	
		 */
		public void run() { 
			formatoTiempo();
			segundosJuego++;
			if(segundosJuego==60) {
				segundosJuego=0;
				minutosJuego++;
			}
		}
		
		};
	}
	
	protected void formatoTiempo() {
		if (minutosJuego<10 && segundosJuego<10) {
			logica.setTiempo("0"+String.valueOf(minutosJuego)+":0"+String.valueOf(segundosJuego));
		} else if(minutosJuego<10) {
			logica.setTiempo("0"+String.valueOf(minutosJuego)+":"+String.valueOf(segundosJuego));
	    } else if(segundosJuego<10) {
	    	  logica.setTiempo(String.valueOf(minutosJuego)+":0"+String.valueOf(segundosJuego));
	    } else {
	    	  logica.setTiempo(String.valueOf(minutosJuego)+":"+String.valueOf(segundosJuego));
	    }
	}
	
	public void start() {
		temporizador.scheduleAtFixedRate(contar, 1000, 1000);
	}

	/**
	 * M�todo para finalizar el conteo de tiempo de juego
	 */
	public void stop() {
		temporizador.cancel();
		contar.cancel();
	}

	public int getMinutos() {
		return minutosJuego;
	}
	
	public int getSegundos() {
		return segundosJuego;
	}
}
