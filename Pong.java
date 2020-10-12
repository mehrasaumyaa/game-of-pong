
/* Name: Saumyaa Mehra and Mahika Vajpeyi
 * File: Pong.java
 * Description: Main class with the code for the game of pong
 * */ 

import java.awt.event.*;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class Pong extends GraphicsProgram
{
	private BallObject ball; 
	private Paddle upperPaddle;				// the upper paddle controlled by the computer
	private Paddle lowerPaddle;				// the lower paddle controlled by the user 
	
	private GLabel label;							// displayed when user loses the game
	
	//timer that makes the ball and the upper paddle move and helps in smooth movement of the lower paddle
	private SwingTimer t;
	
	//variable controlling movement of the ball along the x-axis when it bounces off the paddles or edges
	private int a;
	//variable controlling movement of the ball along the y-axis when it bounces off the paddles or edges
	private int b;
	
	private boolean HitLeftEdge;			//checks if the ball hit the left egde
	private boolean HitRightEdge; 		//checks if the ball hit the right edge
	
	private boolean left;							//checks if the left arrow key is pressed
	private boolean right;						//checks if the right arrow key is pressed
	
	
	@Override
	public void run()
	{
		ball = new BallObject();
		ball.setLocation(95,95);
		upperPaddle = new Paddle();
		upperPaddle.setLocation(95,5);
		lowerPaddle= new Paddle();
		lowerPaddle.setLocation(95,195);
		
		add(ball);
		add(upperPaddle);
		
		add(lowerPaddle);
		
		t =new SwingTimer(25,this);
		
		addKeyListeners();
		addMouseListeners();
		
		a=2;													//to initially move the ball 2 pixels along the x-axis
		b=2;													//to initially move the ball 2 pixels along the y-axis
		
		HitLeftEdge=false;
		HitRightEdge=false;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{ 
		if(left==true && lowerPaddle.getX()>=15)//limits set to make sure the paddle does not go off the screen
		{
			lowerPaddle.move(-10, 0);
		}
		if(right==true && lowerPaddle.getX()<=185)//limits set to make sure the paddle does not go off the screen
		{
			lowerPaddle.move(10, 0);
		}
		ball.update(a,b);
		
		//moves the ball up and to the right after it bounces off the lower paddle until it hits the right edge of the applet
		if(ball.getBounds().intersects(lowerPaddle.getBounds()))
		{
			if(ball.getX()+5 < 200 && HitRightEdge == false)
			{
				HitLeftEdge = false;
				
				a=2;											//moves ball up and to the right
				b=-2;
			}
		}
		
		//moves the ball down and to the left after it bounces off the upper paddle until it hits the left edge of the applet
		if(ball.getBounds().intersects(upperPaddle.getBounds()))
		{
			
			if(ball.getX() > 0 && HitLeftEdge == false)
			{
				HitRightEdge = false;	
				a=-2;									//move ball down and to the left
				b=2;
			}
			
			
			//changes the direction of the ball after it bounces off the upper paddle four times
			if(ball.getX() > 0 && (HitLeftEdge ==false))
			{
				HitRightEdge=false;
				a=-2;
				b=2;
			} 								
			
		}
		
		
		//moves the ball down and to the right after it bounces off the left edge of the applet
		if(ball.getX()-5 == 0)
		{
			HitLeftEdge = false;	
			a=2;										//moves down and to the right
			b=2;
		}
		
		
		//moves the ball up and to the left after it bounces off the right edge of the applet
		if(ball.getX()+5 == 200)
		{
			HitRightEdge = true;
			a=-2;									//moves up and to the left
			b=-2;
		}
		
		
		//makes sure that the upper paddle traks the ball
		if((ball.getX()<=195)&&(ball.getX()>=5))
		{
			upperPaddle.move(ball.getX());
		}
		
		
		//displays the message that the user lost if the ball hits the bottom of the applet
		if(ball.getY()+5>=200)
		{
			ball.setLocation(ball.getX(),200);//lets the ball be where it hit the bottom of the applet after the user loses
			label = new GLabel("Sorry! You lost! Please try again!",10,95);
			add(label);
			t.stop();						//stops the ball after the user loses
		}
	}
	 
	
	//controlling movement of lower paddle
	@Override
	public void keyPressed(KeyEvent k)
	{
		if(k.getKeyCode() == KeyEvent.VK_LEFT)
		{
			right=false;
			left=true;
		}
		else if(k.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			left=false;
			right=true;
		}
	}
	
	
	@Override
	public void keyReleased(KeyEvent k)
	{
		if(k.getKeyCode()==KeyEvent.VK_LEFT)
		{
			left=false;
		}
		if(k.getKeyCode()== KeyEvent.VK_RIGHT)
		{
			right=false;
		}
	}
	@Override
	public void mousePressed(MouseEvent e)
	{
		t.start();
	}
}

