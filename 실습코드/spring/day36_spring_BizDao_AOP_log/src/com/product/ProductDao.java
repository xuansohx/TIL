package com.product;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.vo.Product;

@Repository("pdao")
public class ProductDao implements Dao<String, Product> {

	@Override
	public void insert(Product v) throws Exception {
		System.out.println(v+" Product Inserted...");
	}

	@Override
	public void update(Product v) throws Exception {
		System.out.println(v+" Product Updated...");
	}

	@Override
	public void delete(String k) throws Exception {
		System.out.println(k+" Product Deleted...");
	}

	@Override
	public Product select(String k) throws Exception {
		System.out.println(k+" Product Selected...");
		return new Product("123", "name01", 1000);
	}

	@Override
	public ArrayList<Product> select() throws Exception {
		ArrayList<Product> plist = new ArrayList<>();
		plist.add(new Product("123", "name01", 1000));
		plist.add(new Product("456", "name02", 2000));
		plist.add(new Product("789", "name03", 3000));

		return plist;
	}

}






