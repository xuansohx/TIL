package ws2;

public class MobileTest {

	public static void main(String[] args) {

		// 각각의 Mobile 객체 생성
		// 생성된 객체의 정보 출력
		Mobile m[] = new Mobile[2];
		m[0] = new Ltab("Ltab", 500, "AP-01");
		m[1] = new Otab("Otab", 1000, "AND-20");

		for(Mobile mm : m) {
			System.out.println(mm.toString());
		}

		// 각각의 Mobile 객체에 10분씩 충전
		// 10분 충전 후 객체 정보 출력
		System.out.println("----------------------------------------");
		System.out.println("10분충전");
		
		for(Mobile mm : m) {
			mm.charge(10);
		}
		
		for(Mobile mm : m) {
			System.out.println(mm.toString());
		}

		// 각각의 Mobile 객체에 5분씩 통화
		// 5분 통화 후 객체 정보 출력
		System.out.println("----------------------------------------");
		System.out.println("5분통화");
		
		for(Mobile mm : m) {
			mm.operate(5);
		}
		
		for(Mobile mm : m) {
			System.out.println(mm.toString());
		}


	}

}
