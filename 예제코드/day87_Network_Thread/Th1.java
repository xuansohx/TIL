package day01;

// Sub Thread 
//Thread 만드는 방법① : Thread에서 상속 받아 만듦
class MyThread extends Thread{
	String name;
	
	public MyThread(String name) {
		this.name = name;
	} // Thread 생성할 때 이름 넣어줄 것임

	@Override
	public void run() {
		// Thread의 행위가 일어나는 곳
		for(int i=0; i<1000; i++) {
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			yield(); // 우선순위를 무시하고 t3가 동시다발적으로 많이 동작하는 것 방지?
			System.out.println(name+":"+i);
		}
	}
	
}

// Main Thread
public class Th1 {

	public static void main(String[] args) {
		MyThread t1 = new MyThread("T1");
		MyThread t2 = new MyThread("T2");
		MyThread t3 = new MyThread("T3");

		t1.setPriority(1); // t1 thread의 run()이 동작
		t2.setPriority(2);
		t3.setPriority(10);
		
		t1.start();
		t2.start();
		t3.start();
	}
}
