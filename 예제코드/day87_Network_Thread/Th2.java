package day01;

// Sub Thread 
// Thread 만드는 방법② : implements Runnable
/* Java에서는 단일상속 밖에 안 되므로 여러 개의 Thread 못 만들어 
 * ∴ 여러 개의 Thread 만들기 위하여 interface 통하여 만듦 */
class MyThread2 implements Runnable{
	String name;
	
	public MyThread2(String name) {
		this.name = name;
	} // Thread 생성할 때 이름 넣어줄 것임

	@Override
	public void run() {
		// Thread의 행위가 일어나는 곳
		for(int i=0; i<30; i++) {
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name+":"+i);
		}
	}
	
}

// Main Thread
public class Th2 {

	public static void main(String[] args) {
		// Interface에서 받았을 땐, Thread 객체 아래 방식으로 함
		Thread t1 = new Thread(new MyThread2("T1"));
		Thread t2 = new Thread(new MyThread2("T2"));
		t1.start();
		t2.start();


	}

}
