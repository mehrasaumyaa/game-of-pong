/* Name: Saumyaa Mehra and Mahika Vajpeyi
 * File: BallObject.java
 * Description: represents compound object Ball
 * */ 

import java.awt.*;
import acm.graphics.*;


public class BallObject extends GCompound
{
	private GOval ball;
	
	boolean verticalBounce; 				//tracks when the ball bounces off the paddles
	boolean horizontalBounce; 			//tracks when the ball bounces off the left and the right edges of the applet
	
	public BallObject()
	{
		ball = new GOval(-5,-5,10,10);
		ball.setFilled(true);
		ball.setColor(Color.red);
		add(ball);	
	}
	
	public void update(double x,double y)
	{
		move(x,y);
		
		if(getX()+5>=200)
		{
			horizontalBounce=true;						//when ball bounces off the right wall
		}
		
		else if(getX()-5<=0)
		{ 
			horizontalBounce=false;						//when ball bounces off the left wall
		}
		
		{ 	
			verticalBounce=false;							// bounces off the lower paddle
		}
	}
	
}

