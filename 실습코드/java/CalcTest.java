package calc;

import java.util.Arrays;

public class CalcTest {

	public static void main(String[] args) throws Exception {
		int a [] = {5,3,7,6,2,1};
		Calc c = new Calc(a);
		System.out.println(c.sum());
		System.out.println(c.avg());
		System.out.println(Arrays.toString(c.asort()));
		System.out.println(Arrays.toString(c.dsort()));
		
	}

}
