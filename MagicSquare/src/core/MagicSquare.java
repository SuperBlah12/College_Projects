package core;
import java.util.Scanner;
import java.lang.Math;
/**
 *	<h1>Magic Square</h1>
 *  The MagicSquare program takes an input of N^2 integers and determines if it meets the 
 *  specifications of a "Magic Square".
 *  <p>
 *  The program will not allow the user to enter less then 9 integers. It will also disallow 
 *  entering a number of integers that is not some form of N^2.
 *	<p>
 *	After taking in the input, it will check to see if any values are repeated in the list.
 *  It will also check to see that every value between 1 and N^2 shows up once in the list.
 *  Lastly, it will check to see if the summation of the values in every row, column and 
 *  diagonal are all equal to the same value. If the list meets all the above requirements,
 *  the program will output "True", informing the user that the list makes a "Magic Square".
 *  Should the list fail any of the tests, it will output "False".
 *  
 *  @author Tyler Cole
 *  @version 1.0
 *  @since 2015-01-17
 */
public class MagicSquare
{
	//Program utilizes code written in previous classes.
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args)
	{
		//Get List
		List list = getInput();
		if(isSquare(list))
		{
			//Get Size
			double size = list.getSize();
			//Get Square Side
			int side = getSide(list,size);
			//Check for matching values and [1,side^2] values
			if(!numCheck(list))
				System.out.println("False");
			else
			{
				//get the value all rows, columns, etc sum up to.
				int magNum = getMagicNum(list,side);
				//Check rows, columns and diagonals.
				if(rowCheck(list,magNum,side) && columnCheck(list,magNum,side) && diagCheck(list,magNum,side))
					System.out.println("True");
				else
					System.out.println("False");
			}
		}
		else
			System.out.println("False");
	}
	
	/**
	 *	Returns a list of user-generated integers.
	 *	@return 		The list of numbers input by the user.
	 */
	public static List getInput()
	{
		List list = new List();
		do{
			System.out.print("Enter a number for the Magic Square: ");
			list.add(in.nextInt());
		}while(quit());
		return list;
	}
	
	/**
	 * Tests a list to see if its size is square.
	 * @param list	The list whose size is to be tested
	 * @return boolean value representing if the list length is square
	 */
	public static boolean isSquare(List list)
	{
		int sq = 3;
		while(list.getSize() <= (sq*sq))
		{
			if(list.getSize() == (sq*sq))
				return true;
			else
				sq++;
		}
		return false;
	}
	
	/**
	 * 	Retrieves the integer value of the length of the sides of the magic square.
	 *	@param list		A list object full of integers making up the magic square.
	 *  @param size		The double value of the length of the list.
	 *	@return			The integer value of the square root of the list's size.
	 */
	public static int getSide(List list, double size)
	{
		for(int i = 3; i <= Math.sqrt(size); i++)
		{
			if((i*i) == list.getSize())
				return i;
		}
		return 0;
	}
	
	/**
	 * 	Checks that all numbers in the magic square only appear once and are in the range 
	 *  from 1 to N^2.
	 *	@param list		A list object full of integers making up the magic square.
	 *  @return			A boolean based on if the list contains no doubles and contians one
	 *					of every number in the range [1,N^2].
	 */
	public static boolean numCheck(List list)
	{
		int check[] = new int[list.getSize()];
		for(int i = 0; i < list.getSize(); i++)
		{
			//Numbers > or < list.getSize() = fail
			if(list.get(i)>list.getSize() || list.get(i) < 0)
				return false;
			check[i] = i+1;
		}
		for(int i = 0; i < list.getSize(); i++)
		{
			int tally = 0;
			for(int a = 0; a < list.getSize(); a++)
			{
				if(check[i] == list.get(a))
				{
					tally++;
					if(tally > 1)
						return false;
				}
			}
		}
		return true;
	}

	/**
	 *	Gets the critical number of the magic square by adding the first N values of the list.
	 *	@param list		A list object full of integers making up the magic square.
	 *	@param N		An Integer representing the length of a side of the magic square.
	 *	@return			The integer comprised of the sum of the first N numbers of the list.
	 */
	public static int getMagicNum(List list, int N)
	{
		int magicNumber = 0;
		for(int i = 0; i < N; i++)
			magicNumber = magicNumber + list.get(i);
		return magicNumber;
	}
	
	/**
	 *	Sums up the numbers in each row to see if it matches the magic number.
	 * 	@param magicNum	The integer that all rows, columns and diagonals must add up to.
	 *	@param list		A list object full of integers making up the magic square.
	 *	@param N		An Integer representing the length of a side of the magic square.
	 *	@return			The boolean value based on if the sums of the rows match the 
	 *					magicNumber.
	 */
	public static boolean rowCheck(List list, int magicNum, int N)
	{
		for(int i = 0; i < N;i++)//Row incrementer
		{
			int testNum = 0;
			for(int a = 0; a < N; a++)//Column Incrementer
				testNum = testNum + list.get((i*N)+a);
			if(testNum != magicNum)
				return false;
		}
		return true;
	}
	
	/**
	 *	Sums up the numbers in each column to see if it matches the magic number.
	 * 	@param magicNum	The integer that all rows, columns and diagonals must add up to.
	 *	@param list		A list object full of integers making up the magic square.
	 *	@param N		An Integer representing the length of a side of the magic square.
	 *	@return			The boolean value based on if the sums of the columns match the 
	 *					magicNumber.
	 */
	public static boolean columnCheck(List list, int magicNum, int N)
	{
		for(int a = 0; a < N; a++)//Column Incrementer
		{
			int testNum = 0;
			for(int i = 0; i < N; i++)//Row Incrementer
				testNum = testNum + list.get((i*N)+a);
			if(testNum != magicNum)
				return false;
		}
		return true;
	}
	
	/**
	 *	Sums up the numbers in each diagonal to see if it matches the magic number.
	 * 	@param magicNum	The integer that all rows, columns and diagonals must add up to.
	 *	@param list		A list object full of integers making up the magic square.
	 *	@param N		An Integer representing the length of a side of the magic square.
	 *  @return			The boolean value based on if the sums of the diagonals match the 
	 *  				magicNumber.
	 */
	public static boolean diagCheck(List list, int magicNum, int N)
	{
		//left to right diagonal
		int testNum = 0;
		for(int i = 0; i < N;i++)//Rows and columns incremented simultaniously
		{
			testNum = testNum + list.get((i*N)+i);
		}
		if(testNum != magicNum)
				return false;
		//right to left diagonal
		int a = N - 1;
		testNum = 0;
		for(int i = 0; i < N;i++)//Row Incrementer
		{
			testNum = testNum + list.get((i*N)+a);
			a--;//Column Decrementer
		}
		if(testNum != magicNum)
				return false;
		return true;
	}
	
	/**
	 *	Asks the user if they'd like to quit and returns true/false based on their input.
	 *  @return The boolean value representing if the user would like to stop.
	 *  @since 2014-08-30
	 */
	public static boolean quit()
	{
		char yn;
		System.out.println("\nQuit? <y|n>: ");
		yn = in.next().charAt(0);
		if(yn == 'y' || yn == 'Y')
			return false;
		else if (yn == 'n' || yn == 'N')
			return true;
		else
			return quit();//Recursion
	}
}