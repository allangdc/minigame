package br.ufc.deti.tpe2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class Tank extends JComponent 
{
	private int life;
	private BufferedImage image = null;
	private final int imgHeight = 45;
	private final int imgWidth = 57;
	
	private int angle, group, position;
	
	public Tank(int group)
	{
		if(group <= 1)
			this.group = group;
		else
			this.group = 1;
		
		life = 100;
		angle = 90;
		position=0;
		setSize(new Dimension(imgWidth, imgHeight));
		setImage(6,3);
	}
	
	public int getLife()
	{
		return life;
	}
	
	public void LostLife()
	{
		life -= 5;
		if(life==0)
		{
			JOptionPane.showMessageDialog(null, "Final de Jogo!!!");
			setVisible(false);
		}
	}
	
	public void setAngle(int angle)
	{
		this.angle = angle;
		setTankImage();
	}
	
	public int getAngle()
	{
		return angle;
	}
	
	public void MoveTank()
	{
		if(position==0)
			position=1;
		else
			position=0;
		
		setTankImage();
	}
	
	private void setTankImage()
	{
		int x;
		x = angle / 15;
		setImage(x, group*2 + position);
	}
	
	private void setImage(int valuex, int valuey) 
	{
		BufferedImage aux = null;
		try 
		{
			aux  = ImageIO.read(getClass().getResource("image/Tank2.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		image = aux.getSubimage(valuex * imgWidth+1, valuey * imgHeight+1, imgWidth-2, imgHeight-2);
		repaint();
	}
	
	public void paintComponent(Graphics g) 
	{
		g.drawImage(image, 0, 0, this);
	}
}
