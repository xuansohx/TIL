package forwhile;

import java.util.Random;
import java.util.Scanner;

public class Game4 {

	public static void main(String[] args) {
		// 190515 workshop : 가위바위보 게임 구현하기
		
		// 1. 가위바위보 게임을 구현 한다.
		// 2. 본인이 게임을 나가려면 "q"를 입력 하면 끝난다
		// 3. 게임을 진행 한 회수와 이긴 회수를 출력 한다.
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1(가위), 2(바위), 3(보) 숫자를 입력해주세요");
		System.out.println("게임종료를 원하면 q를 눌러주세요");
		
		// 컴퓨터 (랜덤)
		Random r = new Random(); // 랜덤함수사용
		int com = 0; // 컴퓨터의 변수 선언 및 초기화
		com = r.nextInt(3)+1; // 0,1,2의 +1 값 출력 → 1,2,3
		
		int a=0; // 게임진행
		int b=0; // 몇번 이겼는지
		
		while(true) {
			String data = sc.nextLine(); // 사용자가 가위바위보 입력
			
			if(data.equals("q")) {
				System.out.println("Bye..");
				System.out.println("게임진행 "+a+"회"+" 이긴게임 "+b+"회");
				sc.close();
				break; // while문 종료시키기 위함
			}else { // 사용자가 가위바위보를 진행한다고 선택
				int user = Integer.parseInt(data); // 사용자가 입력한 데이터 숫자로 저장
				if(user>=4 || user<=0) {
					System.out.println("다시 입력해주세요");
					continue; // 다시 while문 시작으로 돌아감
				}else
				{
				switch(user-com) {
				
			case 0 : // 무승부
			a++;
			System.out.println("게임진행 "+a+"회"+" 이긴게임 "+b+"회");
			System.out.println("게임종료를 원하면 q를 눌러주세요");
			break;
			
			case -1: case 2: // 컴퓨터 승리
				a++;
				System.out.println("게임진행 "+a+"회"+" 이긴게임 "+b+"회");
				System.out.println("게임종료를 원하면 q를 눌러주세요");
				break;
				
		case 1: case -2: //사용자 승리
			a++;
			b++;
			System.out.println("게임진행 "+a+"회"+" 이긴게임 "+b+"회");
			System.out.println("게임종료를 원하면 q를 눌러주세요");
			break;
		}
			}
		}
	}
		sc.close();
}
	
}







