package interfaz.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelLateral extends JPanel{
	protected static final long serialVersionUID = 1L;
	protected JLabel puntaje, tiempo, levelProgress, levelProgressText, levelText, numberLevel;
	protected final String dir = "src/Sprites/";
	protected ImageIcon[] cantHordas, progress, numLevel;
	protected int hordaActual=0, actualLevel=0;
	protected PanelJuego miPanelJuego;
	protected JLabel lblTiempoDeJuego, lblTimer, lblPuntuacion, lblScore;
	
	public PanelLateral (int AnchoVentana, int AltoVentana, PanelJuego g){
		super();
		this.setBackground(Color.GRAY);
		this.setLayout(null);
		
		miPanelJuego=g;
		this.setBounds( 9*AnchoVentana/12, 0, 3*AnchoVentana/12, AltoVentana);
		
		this.armarPanel();
		//this.backButton();

	}
	
	protected void armarPanel() {
		lblPuntuacion = new JLabel("Puntuaci\u00F3n:");
		lblPuntuacion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPuntuacion.setBounds(10, 10, 127, 32);
		this.add(lblPuntuacion);

		lblScore = new JLabel("000000");
		lblScore.setHorizontalAlignment(SwingConstants.RIGHT);
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblScore.setBounds(156, 10, 127, 32);
		this.add(lblScore);
		
		lblTiempoDeJuego = new JLabel("Tiempo de juego:");
		lblTiempoDeJuego.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTiempoDeJuego.setBounds(10, 62, 159, 32);
		this.add(lblTiempoDeJuego);
		
		lblTimer = new JLabel("00:00");
		lblTimer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTimer.setBounds(156, 62, 127, 32);
		this.add(lblTimer);
	}

	protected void backButton(){
		JButton back = new JButton();
		back.setBorder(null);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setFocusable(false);
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				miPanelJuego.getJuego().finalizarPartida();;
				miPanelJuego.cambiar(miPanelJuego.getVentana().getInicio());
			}
		});
		back.setBounds(20,0,60,60);
		back.setIcon(new ImageIcon(dir+"IconosBotones/BackMenu.png"));
		back.setRolloverIcon(new ImageIcon(dir+"IconosBotones/BackMenuEntered.png"));
		back.setPressedIcon(new ImageIcon(dir+"IconosBotones/BackMenuSelected.png"));
		add(back);
	}

	public void actualizarLevelProgress(int numEne, int numEneMax){
		int barraBuscada= (numEne*90)/numEneMax;
		barraBuscada = barraBuscada/6;
		levelProgress.setIcon(progress[barraBuscada]);
	}
	
	public void actualizarPuntaje(int puntaje){
		this.puntaje.setText("Puntaje: "+puntaje);		
	}
	
	public void actualizarTemporizador(String tiempo){
		this.lblTimer.setText(tiempo);
	}

	public void nextNumLevel(){
		actualLevel++;
		numberLevel.setIcon(numLevel[actualLevel]);
	}
}
