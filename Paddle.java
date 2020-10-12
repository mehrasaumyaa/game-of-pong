/* Name: Saumyaa Mehra and Mahika Vajpeyi
 * File: Paddle.java
 * Description: represents compound object Paddle
 * */ 


import acm.graphics.*;
import java.awt.*;

public class Paddle extends GCompound

{
	private GRect paddle;
	public Paddle()
	{
		paddle =new GRect (-10,-5,20,10);
		paddle.setColor(Color.BLUE);
		paddle.setFilled(true);
		add(paddle);
	}
	public void move(double posOfBall)
	{
		//posOfBall takes in the x-coordinate of the ball and sets the location of the upper paddle accordingly. 
		// and so this helps the upper paddle in tracking the ball
		if((posOfBall<=190)||(posOfBall>=5))  
		{
			setLocation(posOfBall,5);
		}
	}
}
