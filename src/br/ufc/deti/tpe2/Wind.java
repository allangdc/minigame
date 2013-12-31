package br.ufc.deti.tpe2;

import java.util.Random;

public class Wind 
{
	private double force;
	
	public Wind()
	{
		NewForce();
	}
	
	public void NewForce()
	{
		Random x = new Random();
		force = x.nextDouble()*100 - 50; 
	}
	
	public double getForce()
	{
		return force;
	}
}
