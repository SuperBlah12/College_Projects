package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.List;
import core.MagicSquare;

public class testing 
{
	public List good = new List();
	public List numFail = new List();
	public List sumFail = new List();
	public List inFail = new List();

	@Test
	public void testInputFail() 
	{
		inFail.add(4); inFail.add(9); inFail.add(2); 
		inFail.add(3); inFail.add(5); inFail.add(7); 
		int actual = squareTest(inFail);
		assertEquals(actual,1);
	}
	
	@Test
	public void testNumbersFail() 
	{
		numFail.add(1); numFail.add(2); numFail.add(3);
		numFail.add(1); numFail.add(2); numFail.add(3);
		numFail.add(1); numFail.add(2); numFail.add(3);
		int actual = squareTest(numFail);
		assertEquals(actual,2);
	}
	
	@Test
	public void testSumFail() 
	{
		sumFail.add(1); sumFail.add(2); sumFail.add(3);
		sumFail.add(4); sumFail.add(5); sumFail.add(6);
		sumFail.add(7); sumFail.add(8); sumFail.add(9);
		int actual = squareTest(sumFail);
		assertEquals(actual,3);
	}
	
	@Test
	public void testPerfectSquare() 
	{
		good.add(4); good.add(9); good.add(2); 
		good.add(3); good.add(5); good.add(7); 
		good.add(8); good.add(1); good.add(6);
		int actual = squareTest(good);
		assertEquals(actual,0);
	}
	
	public int squareTest(List list)
	{
		boolean sqr = MagicSquare.isSquare(list);
			if(sqr == false)
				return 1;
		boolean num = MagicSquare.numCheck(list);
			if(num == false)
				return 2;
		int s = MagicSquare.getSide(list,list.getSize());
		int mn = MagicSquare.getMagicNum(list,s);
		if(MagicSquare.rowCheck(list, mn, s) && MagicSquare.columnCheck(list, mn, s) && MagicSquare.diagCheck(list, mn, s))
			return 0;
		else
			return 3;
	}
	
}

