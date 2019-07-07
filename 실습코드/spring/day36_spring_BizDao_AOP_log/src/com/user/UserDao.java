package com.user;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.vo.User;
@Repository("udao")
public class UserDao implements Dao<String, User> {

	@Override
	public void insert(User v) throws Exception {
		System.out.println(v+" User Inserted...");
	}

	@Override
	public void update(User v) throws Exception {
		System.out.println(v+" User Updated...");
	}

	@Override
	public void delete(String k) throws Exception {
		System.out.println(k+" User Deleted...");
	}

	@Override
	public User select(String k) throws Exception {
		System.out.println(k+" User Selected...");
		return new User("id01", "pwd01", "ÀÌ¸»¼÷");
	}

	@Override
	public ArrayList<User> select() throws Exception {
		ArrayList<User> ulist = new ArrayList<>();
		ulist.add(new User("id01", "pwd01", "ÀÌ¸»¼÷"));
		ulist.add(new User("id02", "pwd02", "±è¸»¼÷"));
		ulist.add(new User("id03", "pwd03", "Á¤¸»¼÷"));
		ulist.add(new User("id04", "pwd04", "Àå¸»¼÷"));
		return ulist;
	}

}






