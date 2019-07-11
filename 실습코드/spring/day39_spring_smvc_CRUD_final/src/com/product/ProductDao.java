package com.product;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.mapper.ProductMapper;
import com.vo.Product;


@Repository("pdao")
public class ProductDao implements Dao<Integer, Product> {
	
	@Autowired // Spring에 의해 자동적으로 주입되도록
    ProductMapper pm;
	
	@Override
	public void insert(Product v) throws Exception {
		pm.insert(v);
		
	}

	@Override
	public void update(Product v) throws Exception {
		pm.update(v);
		
	}

	@Override
	public void delete(Integer k) throws Exception {
		pm.delete(k);
		
	}

	@Override
	public Product select(Integer k) throws Exception {
		return pm.select(k);
	}

	@Override
	public ArrayList<Product> select() throws Exception {
		return pm.selectall();
	}

}
