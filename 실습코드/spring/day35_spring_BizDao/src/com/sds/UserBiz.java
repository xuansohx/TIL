package com.sds;

public class UserBiz {
	Dao dao;

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public void register() {
		dao.insert();
	}
}
