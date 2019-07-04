package ws2;

import java.util.Random;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		// 190520 workshop ② : 캔디크러쉬

		/*
		 * 1. 9 x 9 캔디판 생성 
		 * 2. 캔디판에 ABCDE와 같이 서로 다른 종류 4개 이상을 사용해서 랜덤으로 캔디판에 넣는다. 
		 * 3. 게임 시작 → 사용자가 판을 보고 바꾸고 싶어하는 알파벳의 좌표를 선택하고, 그 알파벳의 이웃 알파벳의 좌표를 입력해서 자리를 바꿈 
		 * ★단, 이웃한 문자만 바꿀 수 있음 이외의 경우 에러 메시지 출력★ 
		 * 
		 * 4. 3개의 문자가 이웃되면 없어지고 점수 카운트 추가
		 * 5. 이웃되서 없어진 빈칸은 랜덤으로 문자를 생성해서 넣기 
		 * 6, 성공, 실패 후에 게임 종료
		 */
		
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		int score = 0;
		
		
		// 1. 9x9 캔디판 생성
		
		char map[][] = new char [9][9]; // 9x9 2차원배열의 선언
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map.length; j++) {
				map[i][j] = (char)(r.nextInt(5)+65); // +65하면 대문자, +97하면 소문자
			}
		}
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				System.out.print(map[i][j]+"\t");
			}
			System.out.println();
		}
		
		System.out.println("");
		System.out.println("게임을 시작 할 준비가 되었습니다.");
//		System.out.println("원하시는 시작좌표를 입력해주세요. ex) 1,2,3,4 → (1 1), (2 3)");
		
		
		while(true) {
			System.out.println();
			
			while(true) { 
				// 3. 이웃한 문자 중 같은 문자가 세 개 이상 되면 소거되면서 점수++ 
				// & 랜덤한 다른 문자로 채워넣기
			for(int i=0; i<map.length; i++) {
				for(int j=0; j<map[i].length-2; j++) {
					if(map[i][j] == map[i][j+1] && map[i][j+1] == map[i][(j+2)]) {
						map[i][j] = (char)(r.nextInt(5)+65);
						map[i][j+1] = (char)(r.nextInt(5)+65);
						map[i][j+2] = (char)(r.nextInt(5)+65);
						score ++;
					}
					}
				}
			// 소거 후, 최종 완료 된 캔디판을 제시하며 다시 게임 시작	
			for(int i=0; i<map.length; i++) {
				for(int j=0; j<map[i].length; j++) {
					System.out.print(map[i][j]+"\t");
				}
				System.out.println();
			}
			break;
			}
			// 이 때 현재 점수도 함께 출력됨
			System.out.println("현재 점수 : "+score+"\n"); 
			
			// 기준 좌표 및 변경할 좌표 입력
			System.out.println("원하는 좌표를 입력해주세요. ex) 1,2,3,4 → (1 1), (2 3)");
			int data1 = sc.nextInt(); 
			int data2 = sc.nextInt();
			System.out.println(map[data1][data2]);
	
			int data3 = sc.nextInt();
			int data4 = sc.nextInt();
			char temp = 0;
			
			// 좌표변경

		if((data1 == data3) && (data4 == (data2)+1)){
			temp = map[data1][data2];
			map[data1][data2] = map[data3][data4];
			map[data3][data4] = temp;
			System.out.println(map[data3][data4]);
		}else if((data1 == data3) && (data4 == (data2)-1)) {
			temp = map[data1][data2];
			map[data1][data2] = map[data3][data4];
			map[data3][data4] = temp;
			System.out.println(map[data3][data4]);
		}else if((data2 == data4) && (data3 == (data1)+1)) {
			temp = map[data1][data2];
			map[data1][data2] = map[data3][data4];
			map[data3][data4] = temp;
			System.out.println(map[data3][data4]);
		}else if((data2 == data4) && (data3 == (data1)-1)) {
			temp = map[data1][data2];
			map[data1][data2] = map[data3][data4];
			map[data3][data4] = temp;
			System.out.println(map[data3][data4]);
		}else {
			System.out.println("이웃한 좌표의 값을 입력해주세요.");
			continue;
		}
		// 변경된 좌표의 값 반영하여 출력
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
			System.out.print(map[i][j]+"\t");
			}
			System.out.println();
		}
		
		}

	}

}
