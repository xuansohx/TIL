package calc;

public class Calc {
	// 190523 workshop ① 
	private int [] data;
	
	public Calc() {
	}
	
	public Calc(int ... data) { // 배열 표시 대신 ... 으로 표시할 수 있음
		this.data = data;
	}
	
	// 1. Sum
	public int sum() { 
		int sum = 0;
		for(int a : data) {
			 sum += a;
		}
		return sum;
	}
	
	public double avg() throws Exception {
		double avg = sum();
		if( avg <= 0) {
		throw new Exception();
		}
		avg = sum()/data.length; 
		return avg;
		}
	
	// 2. Sort Asc
	public int[] asort() { // 작은 수에서 큰 수로
		int a[] = data;
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a.length-1-i; j++) {
				if(a[j]>a[j+1]) {
					int temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}	
				}				
		}
		return a; // data를 a에 받아 결과로 출력
	}
	
	// 3. Sort Desc
	public int [] dsort() { // 큰 수에서 작은 수로
		int a[] = data;
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a.length-1-i; j++) {
				if(a[j]<a[j+1]) {
					int temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;	
				}
			}
		}
		return a;
	}

}
