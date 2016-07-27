package fmartinez.kata00.stringCalculator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringCalculatorAddTest {

	private StringCalculator calc;
	
	@Before
	public void setUp() throws Exception {
		calc = new StringCalculator();
	}

	@Test
	public void testAdd() {
		// No numbers returns 0.
		int i1 = calc.Add("");
		assertEquals(0, i1);
		
		// Single number.
		int i2 = calc.Add("9");
		assertEquals(9,i2);
		
		// Two numabers.
		int i3 = calc.Add("7,3");
		assertEquals(10, i3);
		
		// Any amount of numbers.
		int i4 = calc.Add("5,6,4,3,8,5,12,56,98,76,45,35");
		assertEquals(353,i4);
		
		// Alternate delimeter "\n"
		int i5 = calc.Add("12\n4,4");
		assertEquals(20,i5);
		
		// Allowing to choose delimeter.
		int i6 = calc.Add("//;\n50;10");
		assertEquals(60,i6);
		
		// Another delimeter test.
		int i7 = calc.Add("//[\n1[2[3");
		assertEquals(6,i7);
		
		// Ignore numbers higher than 1000.
		int i8 = calc.Add("1,15,3,245,1001,345432,32,64");
		assertEquals(360,i8);
		
		// Allow delimeters of more than one character.
		int i9 = calc.Add("//[***]\n1***3***2*****3");
		assertEquals(6,i9);
		
		// Implement multiple delimeters.
		int i10 = calc.Add("//[door][^][;^;*]\n2door2door^2d^2;^;*345x;^;*2");
		assertEquals(8,i10);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testAddThrowsExceptionWithNegatives() throws IllegalArgumentException{
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("negatives not allowed (-1,-5)");
		calc.Add("//^\n1^2^-1^2^-5");
	}

}
