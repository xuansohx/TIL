package com.frame;

import java.util.ArrayList;

public interface Search<K,V> {
	public ArrayList<V> search(K k); // 조회를 할 때는 담아야 됨 (Key 값을 넣으면 k에 해당하는 결과 Value가 Return)
	

}
