package grafico.entidadGrafica;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class EntidadGrafica {
	
	protected JLabel label;
	protected String ruta;
	protected final int widthOne=33;
	protected final int heightOne=33;
	protected final int width;
	protected final int height;
	protected Point pos;
	
	public EntidadGrafica(int x, int y, int ancho, int alto) {
		this.label = new JLabel();
		this.width = widthOne*ancho;
		this.height = heightOne*alto;
		this.pos = new Point(x*widthOne, y*heightOne);
	}
	
	public JLabel getLabel() {
		return this.label;
	}
	
	public void setLabel(JLabel l) {
		this.label = l;
	}
	
	public void updateImagen() {
		this.agregarImagen();
	}

	public Point getPos(){
		return pos;
	}
	public int getWidthUnaCelda(){
		return widthOne;
	}
	public int getHeightUnaCelda(){
		return heightOne;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	
	protected void agregarImagen() {
		ImageIcon img = new ImageIcon(this.ruta);
		this.label.setIcon(img);
	}
	
	/**
	 * Se setea la nueva posicion gráfica.
	 * @param newX : int
	 * @param newY : int
	 */
	public void cambiarPos(int newX, int newY){
		pos.setLocation(newX, newY);
		System.out.println("( "+newX+", "+newY+")");
		getLabel().setBounds(pos.x, pos.y, width, height);
	}
}
