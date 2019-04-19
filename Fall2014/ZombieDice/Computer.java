//10 LOC
public class Computer extends Player 
{
	int number = 0;
	boolean risk = false; //Determines strategy of Computer
	Computer(int number, boolean risk)//Computer constructor
	{
		this.number = number;
		this.risk = risk;
	}
	void setRisk(boolean risk)
	{
		this.risk = risk;
	}
	boolean getRisk()
	{
		return this.risk;
	}
}
