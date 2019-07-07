package com.sds;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("register.xml");
		// 파일시스템 내에 있는 xml 파일 호출 (컨테이너 생성)
		
		System.out.println("Spring Started");
		
		// UserOracleDao
		Biz biz = (Biz)factory.getBean("ub1"); 
		biz.register(); 
		
		// UserMariaDao
		Biz biz2 = (Biz)factory.getBean("ub2"); 
		biz2.register();
		


}
}
