package com.frame;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

public interface Biz<K,V> {
	@Transactional
	public void register(V v) throws Exception;
	@Transactional
	public void modify(V v) throws Exception;
	@Transactional // 여기에서 선언해주면 UserBiz에서는 안 해줘도 됨
	public void remove(K k) throws Exception;
	
	public V get(K k) throws Exception;
	public ArrayList<V> get() throws Exception;
	
}
