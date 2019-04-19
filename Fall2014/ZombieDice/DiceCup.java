//49 LOC
import java.util.Random;

public class DiceCup 
{
	private Random rand = new Random();
	private Dice[] cup;
	DiceCup()//Default Constructor
	{
		cup = new Dice[13];
		for(int i=0;i<6;i++)
			cup[i] = new Dice(0);//populate green dice
		for(int i=6;i<10;i++)
			cup[i] = new Dice(1);//populate yellow dice
		for(int i=10;i<13;i++)
			cup[i] = new Dice(2);//populate red dice
	}
	
	public Dice[] grabThree(Dice[] hand)
	{
		//Grabs 3 dice out of the cup, ensuring that runners are kept in the hand 
		//and that all the dice grabbed are unique dice from the cup, emulating reality.
		int[] num = {27,27,27};//Initialize array with numbers longer than cup.
		int g = 0;
		int y = 6;
		int r = 10;
		boolean[] run = new boolean[3];
		for(int i = 0; i < hand.length; i++) //check for runners, set their value equal to the position of a similar colored die in cup[]
		{
			if(hand[i].getFace() == 1)
			{
				run[i] = true;	//runner was found at position i;
				switch(hand[i].getColor()) //Find color of runner
				{
					case 0: num[i] = g; g++; break;	//set die to be equal to a die of similar color.
					case 1: num[i] = y; y++; break; //g,y,and r represent where the Green, yellow, and red
					case 2: num[i] = r; r++; break; //dice start on the array.
					default: break;
				}
			}
		}
		for(int i = 0; i < 3; i++)
		{
			if(!run[i])//for all non runner dice
			{
				num[i] = rand.nextInt(13);//get new dice.
				switch(i)//check to make sure new die is a unique value in the array.
				{
					case 0:
						while(num[i] == num[1]||num[i] == num[2])//compare against the other 2 values in num[]
							num[i] = rand.nextInt(13);//if not unique, get new number
						break;
					case 1:
						while(num[i] == num[0]||num[i] == num[2])
							num[i] = rand.nextInt(13);
						break;
					case 2:
						while(num[i] == num[1]||num[i] == num[0])
							num[i] = rand.nextInt(13);
						break;
				}
			}
		}
		//fill hand with calculated dice.
		hand[0] = cup[num[0]];
		hand[1] = cup[num[1]];
		hand[2] = cup[num[2]];
		return hand;
	}
	
	public void rollThree(Dice[] hand)//roll 3 dice
	{
		for(int i=0;i<3;i++)
			hand[i].Roll();
	}
}
