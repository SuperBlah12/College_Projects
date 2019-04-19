//58 LOC
public class Player 
{
	int score = 0;
	String name = "";
	boolean lastTurn = false;
	
	Dice[] hand = new Dice[3];
	Player()//Default Constructor
	{
		this.name = "Bob";
		for(int i = 0; i < 3; i++)
			hand[i] = new Dice();	//Sets dice to their default of Green Brains
	}
	Player(String name)//Name Constructor
	{
		this.name = name;
		for(int i = 0; i < 3; i++)
			hand[i] = new Dice();
	}
	void roll()//Rolls the players dice
	{
		for(Dice die : hand)
			die.Roll();
	}
	int getScore()
	{
		return this.score;
	}
	void setScore(int score)
	{
		this.score = score;
	}
	Dice[] getHand()
	{
		return this.hand;
	}
	void setHand(Dice[] hand)
	{
		this.hand = hand;
	}
	boolean lastTurn()//Gets last turn
	{
		return this.lastTurn;
	}
	void setLastTurn(boolean b)
	{
		this.lastTurn = b;
	}
	int getBrains()//Gets number of brains facing up on dice
	{
		int b = 0;
		for(int i=0;i<3;i++)
			if(hand[i].getFace() == 0)
				b++;
		return b;
	}
	int getShots()//Gets number of shots facing up on dice
	{
		int s = 0;
		for(int i=0;i<3;i++)
			if(hand[i].getFace() == 2)
				s++;
		return s;
	}
	int getRunners()//Gets number of runners facing up on dice
	{
		int r = 0;
		for(int i=0;i<3;i++)
			if(hand[i].getFace() == 1)
				r++;
		return r;
	}
	void resetHand()//Resets hand to default Green Brains
	{
		for(int i = 0; i < 3; i++)
			hand[i] = new Dice();
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public void reset() //Resets player to Initial settings
	{
		this.score = 0;
		for(int i = 0; i < 3; i++)
			hand[i] = new Dice();
		this.lastTurn = false;
	}
}
