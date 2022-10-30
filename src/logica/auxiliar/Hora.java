package logica.auxiliar;

public class Hora {
	private int minutos;
	private int segundos;
	private boolean esM = false;
	private boolean esI = false;
	
	public Hora(int m, int s) {
		minutos = m;
		segundos = s;
	}
	
	public int getMinutos() {
		return minutos;
	}
	
	public int getSegundos() {
		return segundos;
	}
	
	public void setMinutos(int m) {
		minutos = m;
	}
	
	public void setSegundos(int s) {
		segundos = s;
	}
	
	//es true si el tiempo del que llama es mayor
	public boolean esMayor(Hora h1) {
		if(h1.getMinutos() == minutos) {
			if(h1.getSegundos() > segundos) {
				esM = false;
			}else esM = true;
		}else if(h1.getMinutos() > minutos) {
				  esM = false;	
			  }else esM = true;
		
		return esM;
	}
	
	public boolean esIgual(Hora h1) {
		if(h1.getMinutos() == minutos) {
			if(h1.getSegundos() == segundos) {
				esI = true;
			}
		}
		return esI;
	}
	
	public String toString() {
		return minutos+":"+segundos;
	}
}