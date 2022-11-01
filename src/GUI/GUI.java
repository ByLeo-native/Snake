package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import grafico.tableroGrafico.TableroGrafico;
import interfaz.panel.PanelInformacion;
import interfaz.panel.PanelInicio;
import interfaz.panel.PanelJuego;
import logica.juego.Juego;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	ImageIcon image = new ImageIcon("src/Sprites/IconosBotones/Hojas.gif");
	protected static final int AnchoVentana=1200,AltoVentana=720;
	protected PanelInicio inicio;
	protected PanelJuego gui;
	
	protected Juego logica;
	protected TableroGrafico pnlCampoDeJuego;
	protected JTextField txtPlayer;
	protected JLabel lblScore;
	protected JLabel lblTimer;
	protected JPanel pnlRankings;
	protected JLabel[] labelsRanking = new JLabel[15]; 
	protected JPanel panel; 
	protected JLabel lblTiempoDeJuego;
	protected JButton btnRankings;
	protected JLabel lblGameOver;
	protected JButton btnFinalizar;
	protected JLabel lblPuntuacion; 
	protected JLabel lblNombre;
	protected JButton btnIniciar;
	protected JPanel informacion;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GUI() {
		super("Snake the game");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Assets/wall.png"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, this.AnchoVentana, this.AltoVentana);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		inicio = new PanelInicio(this);
		informacion = new PanelInformacion(this);
		contentPane.add(inicio);
	}
	
	/**
	 * Remueve un panel y aniade un nuevo panel al contentPane de la GUI.
	 * @param actual Panel actual en el contentPane de la GUI.
	 * @param nuevo Panel nuevo a agregar en el contentPane de la GUI.
	 */
	public void cambiar(JPanel actual, JPanel nuevo){
		contentPane.remove(actual);
		contentPane.add(nuevo);
		contentPane.repaint();
	}
	
	/**
	 * Devuelve el ancho de la ventana de la GUI.
	 * @return entero positivo correspondiente al ancho de la ventana.
	 */
	public int getAncho(){
		return AnchoVentana;
	}
	
	/**
	 * Devuelve el alto de la ventana de la GUI.
	 * @return entero positivo correspondiente al alto de la ventana.
	 */
	public int getAlto(){
		return AltoVentana;
	}
	
	public PanelJuego getGUIJuego(){
		return gui;
	}
	
	/**
	 * Establece que el panel recibido sea el panel asociado del juego.
	 * @param g PanelJuego que utilizara en la GUI.
	 */
	public void cambiarJuego(PanelJuego g){
		gui=g;
	}
	
	public PanelInicio getInicio(){
		return inicio;
	}
	
	public JPanel getInformacion() {
		return null;
	}
	
	public void mostrarPuntuacion(String scores) {
		String[] scoreArray = scores.split(";");
		for(int i=0;i<scoreArray.length;i++) {
			labelsRanking[i].setText(scoreArray[i]);
		}
	}
	
}

