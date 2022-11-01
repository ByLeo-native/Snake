package grafico.entidadGrafica;

import java.awt.Image;
import java.awt.Point;

import javax.swing.Icon;
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
	
	public EntidadGrafica(int x, int y, int ancho, int alto, String ruta) {
		this.label = new JLabel();
		this.width = widthOne*ancho;
		this.height = heightOne*alto;
		this.pos = new Point(x*widthOne, y*heightOne);
		this.label.setBounds(this.pos.x, this.pos.y, this.width, this.height);
		this.ruta = ruta;
		this.agregarImagen();
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
		ImageIcon imgIcon = new ImageIcon(this.ruta);
        Image imgEscalada = imgIcon.getImage().getScaledInstance(this.width,
                this.height, Image.SCALE_SMOOTH);
        Icon iconoEscalado = new ImageIcon(imgEscalada);
        this.label.setIcon(iconoEscalado);
		
	}
	
	/**
	 * Se setea la nueva posicion gráfica.
	 * @param newX : int
	 * @param newY : int
	 */
	public void cambiarPos(int newX, int newY){
		pos.setLocation(newX, newY);
		getLabel().setBounds(pos.x, pos.y, width, height);
	}
}
