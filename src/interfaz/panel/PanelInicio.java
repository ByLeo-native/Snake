package interfaz.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.GUI;

public class PanelInicio extends JPanel{
	private static final long serialVersionUID = 1L;
	private JButton[] botones;
	private JLabel fondo;
	private GUI ventana;
	protected final String dir = System.getProperty("user.dir");
	
	public PanelInicio(GUI v){
		ventana=v;
		setLayout(null);
		setBounds(0,0,v.getAncho()-15,v.getAlto()-40);
		
		fondo = new JLabel(new ImageIcon(dir+"/src/Assets/pasto.jpg"));
		fondo.setBounds(0,0, v.getAncho(),v.getAlto());
		fondo.setVisible(true);
		
		this.setFocusable(true);
		armarBotones();
		add(fondo);
	}
	
	private void armarBotones(){
		String[] command = {"Jugar","Informacion","Salir"};
		botones = new JButton[command.length];
		OyenteBotones oyente = new OyenteBotones();
		
		for(int i=0;i<botones.length;i++){
			botones[i] = new JButton();
			botones[i].setBorder(null);
			botones[i].setBorderPainted(false);
			botones[i].setContentAreaFilled(false);
			botones[i].setActionCommand(command[i]);
			botones[i].setBounds(200,(i+1)*100+45,300,150);
			botones[i].setFocusable(false);
			botones[i].addActionListener(oyente);
			System.out.println(dir+"/src/Assets/botones/"+command[i]+".gif");
			botones[i].setIcon(new ImageIcon(dir+"/src/Assets/botones/"+command[i]+".gif"));
			//botones[i].setRolloverIcon(new ImageIcon(dir+"IconosBotones/BotonInicio"+command[i]+"Entered.png"));
			add(botones[i]);
		}
	}
	
	public void juegoFinalizado(){
		ventana.cambiarJuego(null);
	}
	
	/**
	 * Solicita cambiar a otro panel.
	 * @param panel Panel con cual cambiar.
	 */
	protected void cambiar(JPanel panel){
		ventana.cambiar(this, panel);
	}
	
	private class OyenteBotones implements ActionListener {
		public void actionPerformed(ActionEvent e){
			String s=e.getActionCommand();
			switch(s){
				case("Jugar"):{
					PanelJuego g = ventana.getGUIJuego();
					if(g!=null){
						g.terminate();
					}
					g = new PanelJuego(ventana);
					g.ejecutar();
					//ventana.cambiarJuego(g);
					cambiar(g);
					g.iniciar();
					break;
				}
				case("Continuar"):{
					ventana.getGUIJuego().ejecutar();
					cambiar(ventana.getGUIJuego());
					break;
				}
				case("Informacion"):{
					cambiar(ventana.getInformacion());
					break;
				}
				case("Salir"):{
					PanelJuego g =ventana.getGUIJuego();
					if(g!=null)
						g.terminate();
					ventana.dispose();
					break;
				}
			}
		}
	}
}