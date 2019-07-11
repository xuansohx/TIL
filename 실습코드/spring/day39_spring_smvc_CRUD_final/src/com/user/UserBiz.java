package com.user;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.frame.Biz;
import com.frame.Dao;
import com.vo.User;


@Service("ubiz")
public class UserBiz implements Biz<String, User>{

	@Resource(name="udao") // UserBiz로 와서 다시 udao가 선언 된 UserDao로 감
	Dao<String, User> dao;
	
	// @Transactional // Transaction 처리를 통하여 중복되는 값 등 데이터가 잘못 들어가는 것을 방지 → Biz에서만 선언해줘도됨
	@Override
	public void register(User v) throws Exception {	
		dao.insert(v);
	}

	@Override
	public void modify(User v) throws Exception {
		dao.update(v);
		
	}

	@Override
	public void remove(String k) throws Exception {
		dao.delete(k); // biz에서 dao를 호출함
		
	}

	@Override
	public User get(String k) throws Exception {
		dao.select(k);	
		System.out.println("get실행");
		return dao.select(k);
	}

	@Override
	public ArrayList<User> get() throws Exception {
		
		return dao.select();
		
	}
	

}
