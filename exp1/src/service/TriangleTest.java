package service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import service.Triangle;

@RunWith(value = Parameterized.class)
public class TriangleTest {
	
	private boolean result;
	private int testNumber;
	
	public  TriangleTest(boolean result, int testNumber) {
		this.result = result;
		this.testNumber = testNumber;
	}
	@Parameters(name="{index}: ifconsist({1}) = {0}")
	public static Iterable<Object[]> data1(){
		return Arrays.asList(new Object[][] {
			{false,-1},
			{true,2},
			{false,9},
			{true,11},
			{false,14},
			{false,18},
			{true,28},
			{true,58},
			{true,83}
		});
	}
	@Test
	public void test() {
		assertEquals(result,Triangle.ifconsist(testNumber));
	}
}
