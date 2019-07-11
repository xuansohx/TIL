package com.app;

import java.util.ArrayList;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Biz;
import com.vo.Product;
import com.vo.User;

public class App {

	public static void main(String[] args) {

		AbstractApplicationContext factory = 
				new GenericXmlApplicationContext("myspring.xml");
		System.out.println("spring started");

		Biz<String, User> biz = (Biz<String, User>) factory.getBean("ubiz");
		// ubiz가 있는 UserBiz로 찾아감
		Biz<Integer, Product> pbiz = (Biz<Integer, Product>) factory.getBean("pbiz");
		
		User user = null;
		Product product = null;

		// User Insert
//		try {
//			biz.register(new User("id07","pwd07","안소오"));	
//			System.out.println("Inserted ..");
//			
//		} catch (Exception e) {
//			System.out.println("Inserted error..");
//			e.printStackTrace();
//		}

        // Product Insert
//		try {
//		pbiz.register(new Product("cherry", 4000, "img04"));	
//		System.out.println("Inserted ..");
//		
//	} catch (Exception e) {
//		System.out.println("Inserted error..");
//		e.printStackTrace();
//	}
//		
		

		// User Delete
//		try {
//			ubiz.remove("id07");
//			System.out.println("Deleted ..");
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
		
        // Product Delete
//		try {
//		pbiz.remove(103);
//		System.out.println("Deleted ..");
//	}catch (Exception e) {
//		e.printStackTrace();
//	}
		

		
		// User Update
//		try {
//			System.out.println("Update Completed ..");
//			ubiz.modify("id01");
//		}catch (Exception e) {
//			e.printStackTrace();
//		}

		
		// User Select
//		try {
//			user = ubiz.get("id02");
//			System.out.println(user);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		// Product Select
//		try {
//			product = pbiz.get(100);
//			System.out.println(product);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		
		// User SelectAll
		ArrayList<User> list = null;
		try {
			list = biz.get();
			System.out.println("SelectAll completed ..");
			for (User u : list) {
				System.out.println(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
        // Product SelectAll	
//		ArrayList<Product> plist = null;
//		try {
//			plist = pbiz.get();
//			System.out.println("SelectAll completed ..");
//			for (Product p : plist) {
//				System.out.println(p);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		factory.close();

	}

}
