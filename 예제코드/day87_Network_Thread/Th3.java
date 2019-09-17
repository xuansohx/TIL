package day01;

public class Th3 {

	public static void main(String[] args) {
		// Class 만들지 않고, Runnable로 r 객체 만든 후 thread 생성
		Runnable r = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("R:" + i);
				}

			}
		};

		Runnable r2 = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("R:" + i);
				}

			}
		};

		// 두 개의 Thread 하나의 Group으로 묶음
		ThreadGroup tg1 = new ThreadGroup("TG1");
		tg1.setMaxPriority(3); // 그룹으로 관리하면 한번에 관리하기 용이
		new Thread(tg1, r, "th1").start();
		new Thread(tg1, r2, "th1").start();

	}

}
