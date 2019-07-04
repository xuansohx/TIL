package test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import com.FactoryBiz;
import com.ProductsBiz;

import frame.Biz;
import vo.Factory;
import vo.Join;
import vo.Products;

public class UserInsert {
	public static void main(String[] args) {
		Products p = new Products(75, "가방", "에어백", "30", null, 2000, 7000, 10);
		// Factory f = new Factory("30", "NIKE", "JEJU");
		Scanner sc = new Scanner(System.in);
		Biz<Integer, Products> biz = new ProductsBiz();
		Biz<String, Factory> biz2 = new FactoryBiz();
		ArrayList<Products> pin = null;
		ArrayList<Factory> fin = null;
		int cin = 0;
		try {
			pin = biz.get();
			fin = biz2.get();
			Join jj = new Join(fin, pin);
			for (Factory tmp : fin)
				System.out.println(tmp);
			System.out.println(jj.numb1());

		} catch (Exception e) {
			e.printStackTrace();
		}
		int a = pin.size();
		int b = fin.size();
	}

}
