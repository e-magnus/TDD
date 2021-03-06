package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {

	public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
    }

	@Test
	public void testEmptyString() {
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testOneNumber() {
		assertEquals(1, Calculator.add("1"));
	}

	@Test
	public void testTwoNumbers() {
		assertEquals(3, Calculator.add("1,2"));
	}	

	@Test
    public void testMultipleNumbers(){
    	assertEquals(6, Calculator.add("1,2,3"));
    }

	@Test
    public void testNumbersWithNewLine(){
    	assertEquals(3, Calculator.add("1\n2"));
    }

    @Test
    public void testNumbersWithNewLineAndComma(){
    	assertEquals(6, Calculator.add("1\n2,3"));
    }

	@Test(expected = IllegalArgumentException.class)
	public void teststringWithNegativeNumbers(){
		Calculator.add("1,-1");
	}

	@Test
	public void testStringWithNegativeNumberMessages()
	{
	  try
	  {
		Calculator.add("-1,2");
	  }
	  catch( final IllegalArgumentException ex )
	  {
	    final String msg = "Negatives not allowed: [-1]";
	    assertEquals(msg, ex.getMessage());
	  }
	}

	@Test
	public void TestStringWithManyNegativeNumberMessages()
	{
	  try
	  {
		Calculator.add("2,-4,3,-5");
	  }
	  catch( final IllegalArgumentException ex )
	  {
	    final String msg = "Negatives not allowed: [-4, -5]";
	    assertEquals(msg, ex.getMessage());
	  }
	}

	@Test
	public void testNumberSmallerThan1000(){
		assertEquals(1002, Calculator.add("1000,2"));
	}

	@Test
	public void testNumberLargerThan1000(){
		assertEquals(2, Calculator.add("1001,2"));
	}

	@Test
	public void testHasDelimiter(){
		assertEquals(true, Calculator.hasDelimiter("//"));
	}

	@Test
	public void testHasNoDelimiter(){
		assertEquals(false, Calculator.hasDelimiter("**"));
	}

	@Test
	public void testNewDelimiter(){
		assertEquals(true, Calculator.hasDelimiter("//;\n"));
	}

	@Test
	public void testTwoNumbersWithNewDelimiter() {
		assertEquals(3, Calculator.add("//;\n1;2"));
	}

	@Test
	public void testTwoNumbersWithNewDelimiter2() {
		assertEquals(3, Calculator.add("//x\n1x2"));
	}

}