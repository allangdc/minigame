package br.ufc.deti.tpe2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class TFormMiniGame extends JFrame implements KeyListener 
{
	private static final long serialVersionUID = 1L;
	private Tank 			tank1, tank2;
	private TBall 			ball;
	private Wind			wind; 
	private double  		tempo;
	private int				activetank;
	private JProgressBar	velocitybar;
	private JProgressBar	windbar;
	private JProgressBar	lifetank1, lifetank2; 
	private JLabel			velocityindicator;
	private JDesktopPane 	desktop;
	private JLabel			nametank1, nametank2, velocitywindlabel;
	private JLabel			valuelife1, valuelife2;
	private JLabel			imgLeft, imgRight;
	
	
	public TFormMiniGame()
	{
		setTitle("Tank of War");
		setSize(800, 600);
		//setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		
		desktop = new JDesktopPane(){	
			private static final long serialVersionUID = 1L;
			Image im = (new ImageIcon(getClass().getResource("/br/ufc/deti/tpe2/image/fundo.png"))).getImage();   
			public void paintComponent(Graphics g){
				g.drawImage(im,0,0,this);
			}
	    };
	    
	    imgLeft = new JLabel();
	    imgLeft.setIcon( new ImageIcon(getClass().getResource("/br/ufc/deti/tpe2/image/esquerda.png")) );
	    imgLeft.setBounds(460, 40, 12, 12);
	    imgLeft.setVisible(false);
	    desktop.add(imgLeft);
	    
	    imgRight = new JLabel();
	    imgRight.setIcon( new ImageIcon(getClass().getResource("/br/ufc/deti/tpe2/image/direita.png")) );
	    imgRight.setBounds(460, 40, 12, 12);
	    imgRight.setVisible(false);
	    desktop.add(imgRight);
		
		tank1 = new Tank(0);
		tank1.setLocation(100, 540);
		tank1.setAngle(180);
		desktop.add(tank1);
		
		nametank1 = new JLabel("Tanque Vermelho");
		nametank1.setBounds(165, 10, 180, 20);
		nametank1.setFont(new Font("Arial", Font.BOLD, 14));
		nametank1.setForeground(Color.BLACK);
		desktop.add(nametank1);
		
		valuelife1 = new JLabel("100");
		valuelife1.setBounds(265, 35, 180, 20);
		valuelife1.setFont(new Font("Arial", Font.BOLD, 14));
		valuelife1.setForeground(Color.YELLOW);
		desktop.add(valuelife1);
		
		valuelife2 = new JLabel("100");
		valuelife2.setBounds(510, 35, 180, 20);
		valuelife2.setFont(new Font("Arial", Font.BOLD, 14));
		valuelife2.setForeground(Color.YELLOW);
		desktop.add(valuelife2);
		
		velocitywindlabel = new JLabel("Velocidade do Vento");
		velocitywindlabel.setBounds(330, 60, 180, 20);
		velocitywindlabel.setFont(new Font("Arial", Font.BOLD, 14));
		velocitywindlabel.setForeground(Color.BLACK);
		desktop.add(velocitywindlabel);

		tank2 = new Tank(1);
		tank2.setLocation(700, 540);
		tank2.setAngle(0);
		desktop.add(tank2);
		
		nametank2 = new JLabel("Tanque Azul");
		nametank2.setBounds(525, 10, 180, 20);
		nametank2.setFont(new Font("Arial", Font.BOLD, 14));
		nametank2.setForeground(Color.BLACK);
		desktop.add(nametank2);
		
		velocitybar = new JProgressBar();
		velocitybar.setBounds(50, 80, 100, 20);
		desktop.add(velocitybar);
		
		velocityindicator = new JLabel("Intensidade do Tiro");
		velocityindicator.setBounds(50, 60, 180, 20);
		velocityindicator.setFont(new Font("Arial", Font.BOLD, 14));
		velocityindicator.setForeground(Color.BLACK);
		desktop.add(velocityindicator);
		
		windbar = new JProgressBar();
		windbar.setBounds(320, 35, 110, 20);
		desktop.add(windbar);
	
		
		lifetank1 = new JProgressBar();
		lifetank1.setBounds(145, 35, 85, 20);
		lifetank1.setValue(100);
		desktop.add(lifetank1);
		
		lifetank2 = new JProgressBar();
		lifetank2.setBounds(570, 35, 70, 20);
		lifetank2.setValue(100);
		desktop.add(lifetank2);
				
		wind = new Wind();
		wind.NewForce();
		windbar.setValue( (int) Math.abs(wind.getForce())*2 );
		if(wind.getForce() > 0)
		{
			imgRight.setVisible(true);
			imgLeft.setVisible(false);
		}
		else if(wind.getForce() < 0)
		{
			imgRight.setVisible(false);
			imgLeft.setVisible(true);
		}
		
		
		
		addKeyListener(this);
		desktop.setSize(800,600);
		getContentPane().add(desktop);
		this.setSize(805, 620);
		//pack();
	}

	
	public void ActionTank(String tecla, final Tank tank)
	{
		if(tecla == "Right" | tecla == "Direita")
		{
			Point pos = tank.getLocation();
			pos.x += 5;
			tank.MoveTank();
			tank.setLocation(pos);
			//setTitle( tecla );
		}else if(tecla == "Left" | tecla == "Esquerda")
		{
			Point pos = tank.getLocation();
			pos.x -= 5; 
			tank.MoveTank();
			tank.setLocation(pos);
		}else if(tecla == "Up" | tecla == "Acima")
		{
			int angle = tank.getAngle();
			if(angle < 180)
				angle += 15;	
			tank.setAngle(angle);
		}else if(tecla == "Down" | tecla == "Abaixo")
		{
			int angle = tank.getAngle();
			if(angle > 0)
				angle -= 15;
			tank.setAngle(angle);
		}else if(tecla == "Equals" | tecla == "Igual")
		{
			if(velocitybar.getValue() < 100)
				velocitybar.setValue( velocitybar.getValue() + 10 );
		}else if(tecla == "Menos" | tecla == "Minus")
		{
			if(velocitybar.getValue() > 0)
				velocitybar.setValue( velocitybar.getValue() - 10 );
		}else if(tecla == "Enter")
		{
			if(activetank==0)
				activetank = 1;
			else
				activetank = 0;
			
			ball = new TBall(wind);
			ball.setLocation( tank.getLocation() );
			desktop.add(ball);
			tempo = 0;
			
			ball.setAngle(tank.getAngle());
			ball.setVelocity( velocitybar.getValue() );
						
			Thread x = new Thread()
			{				
				public void run()
				{
					while( ball.getLocation().y < tank.getLocation().y + 40 )
					{ 
						if( !BallOnTank(ball, tank) )
							ball.setCanExplode();
						
						if(ball.getCanExplode())
						{
							if( BallOnTank(ball, tank1) )
							{
									tank1.LostLife();
									lifetank1.setBackground(Color.YELLOW);
							}
							
							if( BallOnTank(ball, tank2) )
							{
									tank2.LostLife();
									lifetank2.setBackground(Color.YELLOW);
							}
						}
						
						lifetank1.setValue( tank1.getLife() );
						valuelife1.setText( String.valueOf( tank1.getLife() ) );
						lifetank2.setValue( tank2.getLife() );
						valuelife2.setText( String.valueOf( tank2.getLife() ) );
						
						ball.shoot(tempo);
						tempo+=0.1;
						try{
							Thread.sleep(50);
						} catch (InterruptedException e) {e.printStackTrace();}
					}
					lifetank1.setBackground(Color.RED);
					lifetank2.setBackground(Color.RED);
					
					
					wind.NewForce();
					windbar.setValue( (int) Math.abs(wind.getForce())*2 );
					if(wind.getForce() > 0)
					{
						imgRight.setVisible(true);
						imgLeft.setVisible(false);
					}
					else if(wind.getForce() < 0)
					{
						imgRight.setVisible(false);
						imgLeft.setVisible(true);
					}
					
					//setTitle( String.valueOf(wind.getForce()) );
				}
			};
			x.start();
		}
	}
	
	private boolean BallOnTank(TBall myball, Tank mytank)
	{
		if( (myball.getLocation().x >= mytank.getLocation().x) &&
				(myball.getLocation().x <= mytank.getLocation().x + mytank.getSize().width) &&
				(myball.getLocation().y >= mytank.getLocation().y) &&
				(myball.getLocation().y <= mytank.getLocation().y + mytank.getSize().height)
			  )
		{
				return true;
		}
		else
				return false;
	}
	
	@Override
	public void keyPressed(KeyEvent event) 
	{
		@SuppressWarnings("static-access")
		String tecla = event.getKeyText(event.getKeyCode());
		//setTitle( tecla );
		if(activetank==0)
			ActionTank(tecla, tank1);
		else
			ActionTank(tecla, tank2);
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
