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
		
		init();
		
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
	
	protected void init() {
		//armarPanelDelCampoDelJuego();
//		armarIngresoDeEntrada();
//		armarPanelDelTiempoYPuntuacion();
//		armarLabelsDelPanelDelTiempo();
//		armarLabelsDePuntuacion();
//		armarPanelDeRanking();
//		armarComponentesDeRanking();
//		armarComponentesDeGameOver();
//		armarOyenteDelBotonFinalizar();
//		armarOyenteBotonIniciar();
	}
	
	public void actualizarPuntuacion(String score) {
		lblScore.setText(score);
	}
	
	public void actualizarTimer(String time) {
		lblTimer.setText(time);
	}
	
	public void mostrarPuntuacion(String scores) {
		String[] scoreArray = scores.split(";");
		for(int i=0;i<scoreArray.length;i++) {
			labelsRanking[i].setText(scoreArray[i]);
		}
	}
	
	public TableroGrafico getTableroGrafico() {
		return this.pnlCampoDeJuego;
	}
	
	protected void iniciarPartida() {
		modificarComponentesAlIniciarPartida();
		logica.iniciarPartida();
		logica.setNombreDelJugador(txtPlayer.getText());
	}
	
	public void finalizarPartida() {
		this.modificarComponentesAlFinalizarPartida(false);
	}
	
//	protected void armarPanelDelCampoDelJuego() {
//		pnlCampoDeJuego = new TableroGrafico();
//		pnlCampoDeJuego.setBackground(Color.WHITE);
//		pnlCampoDeJuego.setBounds(10, 10, 764, 764);
//		getContentPane().add(pnlCampoDeJuego);
//	}
	
	protected void armarIngresoDeEntrada() {
		JLabel lblTitle = new JLabel("SNAKE");
		lblTitle.setForeground(Color.RED);
		lblTitle.setFont(new Font("Terminator Two", Font.PLAIN, 35));
		lblTitle.setBounds(916, 24, 132, 32);
		getContentPane().add(lblTitle);
		
		txtPlayer = new JTextField(15);
		txtPlayer.setText("Player");
		txtPlayer.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtPlayer.setBounds(1012, 93, 164, 38);
		getContentPane().add(txtPlayer);
		txtPlayer.setColumns(10);
		
		lblNombre = new JLabel("Ingresar Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre.setBounds(852, 94, 150, 38);
		getContentPane().add(lblNombre);
		
		btnIniciar = new JButton("Iniciar Partida");
		btnIniciar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnIniciar.setBackground(Color.GREEN);
		btnIniciar.setBounds(1012, 142, 164, 38);
		getContentPane().add(btnIniciar);
	}
	
	protected void armarPanelDelTiempoYPuntuacion() {
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(885, 232, 291, 104);
		getContentPane().add(panel);
		panel.setLayout(null);
	}
	
	protected void armarLabelsDelPanelDelTiempo() {
		lblTiempoDeJuego = new JLabel("Tiempo de juego:");
		lblTiempoDeJuego.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTiempoDeJuego.setBounds(10, 62, 159, 32);
		panel.add(lblTiempoDeJuego);
		
		lblTimer = new JLabel("00:00");
		lblTimer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTimer.setBounds(156, 62, 127, 32);
		panel.add(lblTimer);
	}
	
	protected void armarLabelsDePuntuacion() {
		lblPuntuacion = new JLabel("Puntuaci\u00F3n:");
		lblPuntuacion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPuntuacion.setBounds(10, 10, 127, 32);
		panel.add(lblPuntuacion);

		lblScore = new JLabel("000000");
		lblScore.setHorizontalAlignment(SwingConstants.RIGHT);
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblScore.setBounds(156, 10, 127, 32);
		panel.add(lblScore);
	}
	
	protected void armarPanelDeRanking() {
		pnlRankings = new JPanel();
		pnlRankings.setBackground(Color.LIGHT_GRAY);
		pnlRankings.setBounds(798, 472, 378, 228);
		pnlRankings.setVisible(false);
		getContentPane().add(pnlRankings);
		pnlRankings.setLayout(new GridLayout(5, 3, 1, 1));
	}
	
	protected void armarComponentesDeRanking() {
		for (int i=0; i<labelsRanking.length;i++) {
			labelsRanking[i] = new JLabel("lblScore" +i);
			labelsRanking[i].setFont(new Font("Tahoma", Font.PLAIN, 18));
			labelsRanking[i].setBounds(125*i, 45*i , 125, 45);
			pnlRankings.add(labelsRanking[i]);
		}
		
		btnRankings = new JButton("Ver Rankings");
		btnRankings.setVisible(false);
		btnRankings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRankings.setBounds(1026, 710, 150, 32);
		getContentPane().add(btnRankings);
		
	}
	
	protected void armarComponentesDeGameOver() {
		lblGameOver = new JLabel("GAME OVER");
		lblGameOver.setForeground(Color.RED);
		lblGameOver.setFont(new Font("Terminator Two", Font.PLAIN, 35));
		lblGameOver.setBounds(885, 350, 239, 32);
		lblGameOver.setVisible(false);
		getContentPane().add(lblGameOver);
		
		btnFinalizar = new JButton("Forzar final de juego");
		btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFinalizar.setBounds(961, 404, 215, 38);
		btnFinalizar.setVisible(false);
		getContentPane().add(btnFinalizar);
	}
	
	protected void armarOyenteDelBotonFinalizar() {
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarComponentesAlFinalizarPartida(true);
			}
		});
	}
	
	protected void armarOyenteBotonIniciar() {
		btnIniciar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				iniciarPartida();
			}
		});
	}
	
	protected void modificarComponentesAlIniciarPartida() {
		lblGameOver.setVisible(false);
		btnIniciar.setVisible(false);
		txtPlayer.setVisible(false);
		lblNombre.setVisible(false);
		btnRankings.setVisible(false);
		btnFinalizar.setVisible(true);
	}
	
	protected void modificarComponentesAlFinalizarPartida(boolean esPorBoton ) {
		lblGameOver.setVisible(true);
		btnFinalizar.setVisible(false);
		btnIniciar.setVisible(true);
		txtPlayer.setVisible(true);
		lblNombre.setVisible(true);
		btnRankings.setVisible(true);
		
		if( esPorBoton ) {
			logica.finalizarPartida();
		}
	}
	
//	protected void armarOyenteDeBotonDeRanking() {
//	btnRankings.addActionListener(new ActionListener() {
//		public void actionPerformed(ActionEvent e) {
//			logica.getPuntaje();
//			pnlRankings.repaint();
//			mostrarRankings();
//		} 
//		protected void mostrarRankings() {
//			if (!pnlRankings.isVisible()) {
//				pnlRankings.setVisible(true);
//			} else pnlRankings.setVisible(false);
//		}
//	});
//}
}

