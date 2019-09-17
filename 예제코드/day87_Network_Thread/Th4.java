package day01;

class SaveThread extends Thread{
	public void run() {
		while(true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			save(); // 2초에 한 번씩 SAVE 하도록 설정
		}
	}
	
	public void save() {
		System.out.println("SAVE..");
	}
}

public class Th4 {

	public static void main(String[] args) {
		SaveThread st = new SaveThread();
		st.setDaemon(true);
		st.start();
		for(int i=0; i<20; i++) {
			try {
				Thread.sleep(500); // 1.2초에 한 번씩 작업
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}
	
	}

}
