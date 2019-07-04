package ws1;

import java.util.Random;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		// 190520 workshop ① : 숫자야구

		/*
		 * 1. 랜덤 4자리 숫자 받기 (0~9) 중복 x 
		 * 2. 사용자가 4자리 숫자 입력 
		 * 3. 숫자가 있으면 ball, 자리도 같으면 strike
		 * 4. 아예 안맞으면 out 
		 * 5. 완전히 일치하게 되면 홈런으로 게임 끝.
		 * 
		 * ex) 
		 * 랜덤숫자 : 1639 
		 * 사용자숫자 : 2698 => 1S1B 
		 * 사용자숫자 : 4578 => out 
		 * 사용자숫자 : 2439 => 2S
		 * 사용자숫자 : 2461 => 2B
		 */

		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		System.out.println("숫자야구 게임이 시작됩니다.");
		System.out.println("1~9까지의 네 자리의 숫자를 입력해주세요.");

		int b = 0; // 숫자만 동일한 ball
		int s = 0; // 숫자와 자리 둘 다 동일한 strike
		int o = 0; // 아예 안 맞으면 out

		// 1. 사용자가 4자리 숫자 입력
		int user[] = new int[4]; // 사용자가 입력하는 4자리 숫자를 저장하는 배열
		for (int u = 0; u < user.length; u++) { // 사용자가 입력한 숫자를 차례대로 배열에 저장
			int data = sc.nextInt(); // 바로 숫자값을 입력 받을 수 있으므로, 변환하지 않고 nextInt 사용
			if (0 <= data && data <= 9) {
				user[u] = data;
			} else {
				u--; // 배열의 모든 칸을 채워야하므로 채우지 못 하였으면 다시
				System.out.println("다시 입력해주세요.\n");
				continue; // 다시 for문 시작으로 이동
			}
		}

		System.out.println("사용자가 입력한 숫자 : ");
		for (int k = 0; k < user.length; k++) {
			System.out.print(user[k] + " ");
		}

		System.out.println(" ");

		// 2. 랜덤 4자리 숫자 받기
		int com[] = new int[4]; // 컴퓨터가 입력 받는 숫자를 저장하는 배열
		int num = 0;
		for (int i = 0; i < com.length; i++) {
			num = r.nextInt(10); // 0부터 9까지의 숫자를 랜덤으로 입력
			com[i] = num; // 랜덤으로 뽑은 수를 배열 com에 저장
			for (int j = 0; j < i; j++) { // 중복방지검사 →
				if (com[j] != com[i])
				// 원래 저장되어있던 배열 com[j] 값과 새로 들어온 값 com[i]를 비교하여 저장여부 결정
				{
					com[i] = num;
				} else {
					i--;
					break;
				}
			}
		}

		System.out.println("컴퓨터의 랜덤숫자 : ");
		for (int z = 0; z < user.length; z++) {
			System.out.print(com[z] + " ");
		}

		System.out.println(" ");

		// 3. 사용자가 입력한 숫자와 컴퓨터의 숫자 비교

		for (int m = 0; m < com.length; m++) {
			for (int n = 0; n < user.length; n++) {
				if (com[m] == user[n] && m == n) { // 숫자와 자리 비교
					s++; // 숫자와 자리가 모두 같으면 strike++
				} else if (com[m] == user[n] && m != n) {
					b++; // 숫자만 같으면 ball++
				}
			}
		}

		if (s == 0 && b == 0) {
			System.out.println("OUT");
		} else {
			System.out.println("게임 결과 : ");
			System.out.println(s + " Strike " + b + " Ball ");
		}

		if (s == 4) {
			System.out.println("홈런입니다.");
		}

		sc.close();

	}
}
