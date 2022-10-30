package interfaz.panel.derrota;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.GUI;


public class PanelDerrota extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel fondo;
	private GUI ventana;
	private final String dir = "src/Sprites/";	
	
	public PanelDerrota(GUI v){
		ventana=v;
		setLayout(null);
		setBounds(0,0,v.getAncho()-15,v.getAlto()-40);	
		
		fondo = new JLabel(new ImageIcon(dir+"GameOver.png"));
		fondo.setOpaque(true);
		fondo.setBackground(Color.black);
		fondo.setBounds(0,0,v.getAncho()-15,v.getAlto()-40);
		
		add(fondo);
		
		
		addMouseListener(new MouseAdapter() {
            private Color background;

            @Override
            public void mousePressed(MouseEvent e) {
               cambiar();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(background);
            }
        });
	}
	private void cambiar(){
		ventana.cambiar(this, ventana.getInicio());
	}
	
}
