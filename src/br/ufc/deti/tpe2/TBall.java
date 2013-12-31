package br.ufc.deti.tpe2;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;

public class TBall extends JComponent 
{
	private int 	angle;
	private Wind	forcewind;
	private final double gravity = 9.8;
	private double 	velocity;
	private Point ptstart;
	private boolean	canexeplode;
	
	public TBall(Wind wind)
	{
		forcewind = wind;
		setSize(11, 11);
		velocity = 30;
		angle = 60;
		canexeplode = false;
	}
	
	public void setCanExplode()
	{
		canexeplode = true;
	}
	
	public boolean getCanExplode()
	{
		return canexeplode;
	}
	
	public void paintComponent(Graphics g)
	{
		g.fillOval(5, 5, 5, 5);
	}
	
	public void setAngle(int angle)
	{
		this.angle = angle;
	}
	
	public void setVelocity(double velocity)
	{
		this.velocity = velocity;
	}
	
	public void shoot(double time)
	{
		if(time==0)
		{
			ptstart = new Point();
			ptstart.setLocation(this.getLocation());
			ptstart.x += 28;
			ptstart.y += 27;
		}
		
		Point	pos;
		double	angle;
		angle = this.angle * Math.PI / 180;
		pos = getLocation();		
		pos.x = (int) (ptstart.x + 28-28 - velocity * Math.cos(angle) * time + forcewind.getForce()*time*time/10);
		pos.y = (int) (ptstart.y + 27-27 - velocity * Math.sin(angle)*time + gravity*time*time/2);
		setLocation(pos);
	}
}
