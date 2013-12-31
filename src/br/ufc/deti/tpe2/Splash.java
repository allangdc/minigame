package br.ufc.deti.tpe2;

import java.awt.Button;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

public class Splash extends JFrame 
{
	private JDesktopPane 	desktop;
	private Button			button;
	public Splash() 
	{
		setTitle("Splash");
		setSize(780, 439);
		setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		button = new Button("Iniciar");
		button.setBounds(340, 350, 100, 25);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				TFormMiniGame minigame = new TFormMiniGame();
				minigame.setVisible(true);
				Close();
			}
		});
		add(button);
		
		desktop = new JDesktopPane(){	
			private static final long serialVersionUID = 1L;
			Image im = (new ImageIcon(getClass().getResource("/br/ufc/deti/tpe2/image/telainicial.png"))).getImage();  
			
			public void paintComponent(Graphics g){
				g.drawImage(im,0,0,this);
			}
	    };
	    
	    add(desktop);
	    desktop.setSize(780,439);
	    //pack();
	}
	
	public void Close()
	{
		setVisible(false);
	}
}
