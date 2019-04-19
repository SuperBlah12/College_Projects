//29 LOC
import java.util.Random;

public class Dice 
{
	//0 = brain, 1== runner, 2 == shot
	private int color;
	private int face;
	private Random rand = new Random();
	Dice()//Default constructor
	{
		this.color = 0;
		this.face = 0;
	}
	Dice(int c)//Color constructor
	{
		this.color = c;
		this.face = 0;
	}
	int getColor()
	{
		return this.color;
	}
	int getFace()
	{
		return this.face;
	}
	//checks color of die, rolls accordingly.
	void Roll()
	{
		int max;
		int min;
		switch(color)
		{
			case 0:  min = 1; max = 6; break;
			case 1:  min = 2; max = 7; break;
			case 2:  min = 3; max = 8; break;
			default: min = 0; max = 0; break;
		}
		//[1,2,3] = brains [4,5] = run [6,7,8] = shot
		int die = rand.nextInt((max - min) + 1) + min;
		switch(die)
		{
			case 1: case 2: case 3: this.face = 0; break;
			case 4: case 5:         this.face = 1; break;
			case 6: case 7: case 8: this.face = 2; break;
			default:                this.face = 3; break;
		}
	}
}
