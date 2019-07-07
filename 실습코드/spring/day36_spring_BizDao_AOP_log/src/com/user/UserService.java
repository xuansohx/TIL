package com.user;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.Biz;
import com.frame.Dao;
// AOP는 각각의 코드에 로그를 찍는 함수가 없어도 가능
import com.vo.User;

@Service("ubiz")
public class UserService implements Biz<String, User> {
	@Resource(name = "udao")
	Dao<String, User> dao;

	public void startBiz() {
		System.out.println("Start Biz ...");
	}

	public void endBiz() {
		System.out.println("End Biz ...");
	}

	public void setDao(Dao<String, User> dao) {
		this.dao = dao;
	}

	@Override
	public void insert(User v) throws Exception {

		dao.insert(v);
	}

	@Override
	public void update(User v) throws Exception {

		dao.update(v);

	}

	@Override
	public void delete(String k) throws Exception {
		dao.delete(k);

	}

	@Override
	public User select(String k) throws Exception {
		System.out.println("----- Biz Selected -----");
		// Thread.sleep(2800); // 2.8초 정도 delay time을 주어 Processing Time이 제대로 측정되는지 확인
//		if(k.equals("id00")) {
//			throw new Exception("Not Found Exception..");
//		}
		return dao.select(k);

	}

	@Override
	public ArrayList<User> select() throws Exception {
		return dao.select();
	}

}
