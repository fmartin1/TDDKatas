package fmartinez.kata01.stringCalculator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringCalculatorAddTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAdd() {
		StringCalculator calc = new StringCalculator();
		int i1 = calc.Add("");
		assertEquals(0, i1);
		
		int i2 = calc.Add("9");
		assertEquals(9,i2);
		
		int i3 = calc.Add("7,3");
		assertEquals(10, i3);
		
		int i4 = calc.Add("5,6,4,3,8,5,12,56,98,76,45,35");
		assertEquals(353,i4);
		
		int i5 = calc.Add("12\n4,4");
		assertEquals(20,i5);
		
		int i6 = calc.Add("//;\n50;10");
		assertEquals(60,i6);
		
		int i7 = calc.Add("//[\n1[2[3");
		assertEquals(6,i7);
		
		int i8 = calc.Add("1,15,3,245,1001,345432,32,64");
		assertEquals(360,i8);
		
		int i9 = calc.Add("//[***]\n1***3***2*****3");
		assertEquals(6,i9);
		
		int i10 = calc.Add("//[door][^][;^;*]\n2door2door^2d^2;^;*345x;^;*2");
		assertEquals(8,i10);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testAddWithNegatives() throws IllegalArgumentException{
		StringCalculator calc = new StringCalculator();
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("negatives not allowed (-1,-5)");
		calc.Add("//^\n1^2^-1^2^-5");
	}

}
