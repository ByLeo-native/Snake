package logica.nivel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import logica.celda.Celda;
import logica.entidad.Entidad;
import logica.entidad.caminable.Cesped;
import logica.entidad.criatura.Criatura;
import logica.entidad.obstaculo.Pared;
import logica.juego.Juego;
import logica.tablero.Tablero;

public class Nivel {
	
	protected Juego juego;
	protected int indice;
	protected Tablero miTablero;
	
	public Nivel(Juego j, int i) {
		this.juego = j;
		this.indice = i;
		this.miTablero = new Tablero(this);
	}
	
	public Celda[][] generarNivel() {
		Celda[][] nivelGenerado = new Celda[20][20];
		String ruta = "src/Assets/Nivel_"+this.indice+".txt";
		BufferedReader lector;
		try {
			lector = new BufferedReader(new FileReader(ruta));
			for(int fila= 0;fila<20;fila++) {
				String linea = lector.readLine();
				for(int columna = 0; columna<20; columna++) {
					Celda celda = new Celda();
					Entidad e;
					char caracterAInsertar = linea.charAt(0);
					System.out.print(caracterAInsertar);
					if( linea.length() != 1) {
						linea = linea.substring(2);
					}
					
					if(caracterAInsertar=='P') {
						e = new Pared(columna, fila, this.miTablero); 
						celda.agregarEntidad(e);
					} //Genera espacio vacío
					else {
						e = new Cesped(columna, fila, this.miTablero);
						celda.agregarEntidad(e);
					}
					nivelGenerado[fila][columna] = celda;
				}
				System.out.println();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return nivelGenerado;
	}

	public Juego getJuego() {
		return this.juego;
	}
	
	public Criatura getCriatura() {
		return this.miTablero.getCriatura();
	}
	
}
