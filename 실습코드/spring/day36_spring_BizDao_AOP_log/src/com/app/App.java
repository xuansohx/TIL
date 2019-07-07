package com.app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Biz;
import com.vo.Product;
import com.vo.User;

public class App {

	public static void main(String[] args) {
		AbstractApplicationContext factory
		= new GenericXmlApplicationContext("myspring.xml");
		System.out.println("Spring Started");
		
		Biz<String, User> ubiz = (Biz<String, User>) factory.getBean("ubiz");
		Biz<String, Product> pbiz = (Biz<String, Product>) factory.getBean("pbiz");
		

	    // Insert
//		User user = new User("id01", "pwd01", "이말자");
//		try {
//		ubiz.insert(user);
//			System.out.println("Inserted Ok From app.");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		Product product = new Product("123", "name01", 1000);
//		try {
//		pbiz.insert(product);
//			System.out.println("Inserted Ok From app.");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		// Select
		User user = null;
		try {
			user = ubiz.select("id01");
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Product product = null;
		try {
			product = pbiz.select("123"); // select()가 String이라 오류발생 : type error
			System.out.println(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		factory.close();
	}

}







