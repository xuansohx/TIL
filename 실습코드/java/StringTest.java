package ws;

import java.util.Arrays;
import java.util.Scanner;

public class StringTest {

	public static void main(String[] args) {
		// 190517 workshop ② : String Test

		/*
		 * 1. 소문자로 된 문자열을 입력 받아 출력 한다. 
		 * 2. 입력 받은 문자열을 대문자로 만들어 순서를 꺼꾸로 하여 출력 한다. 
		 * 3. 2번에서 만들어진 문자열의 각 char에 +5를 하여 암호화 하여 출력 한다. 
		 * 4. 3번에서 만들어진 문자열을 복호화 하여 최초 입력 받은 문자열을 출력 한다.
		 */

		// 1. 문자열 입력 받기
		Scanner sc = new Scanner(System.in);
		System.out.println("소문자로 된 문자열을 입력해주세요.");
		String input = sc.nextLine(); // 문자입력대기

		char[] abc;
		abc = input.toCharArray(); // 입력 받은 문자열을 배열로 저장함
		System.out.println("입력하신 소문자로 된 문자열 : ");
		for (int i = 0; i < abc.length; i++) {
			System.out.print(abc[i]); // 배열로 저장 된 입력 받은 문자열 출력
		}

		System.out.println("");
		char a[] = abc; // 초기 입력 값 배열 데이터 저장

		// 2-1. 입력 받은 문자열을 대문자로 만들기

		for (int i = 0; i < abc.length; i++) {
			if (97 <= abc[i] && abc[i] <= 122) { // 소문자 a부터 z까지의 범위
				abc[i] = (char) (abc[i] - 32); // 소문자를 대문자로 변환
			}
		} // 여기서 abc[i]는 대문자열

		char b[] = abc; // 대문자로 바꾼 배열 데이터 저장

		// 2-2. 대문자로 받은 문자열을 저장한 배열을 거꾸로 출력하기

		char temp = 0;
		int j = abc.length - 1;

		for (int i = 0; i < abc.length / 2; i++, j--) {
			temp = abc[i];
			abc[i] = abc[j];
			abc[j] = temp;
		}

		System.out.println("대문자로 변환하여 역순으로 출력한 문자열 : ");
		for (int i = 0; i < abc.length; i++) {
			System.out.print(abc[i]);
		}

		System.out.println("");

		char c[] = abc; // 대문자 거꾸로 저장한 배열 데이터 저장

		// 3. 각 char에 +5 하여 암호화 하기

		for (int i = 0; i < abc.length; i++) {
			abc[i] = (char) (abc[i] + 5);
		} // char에 +5 하여 암호화

		System.out.println("각 문자에 '+5'를 하여 암호화 : ");
		for (int i = 0; i < abc.length; i++) {
			System.out.print(abc[i]);
		}

		System.out.println("");

		char d[] = abc; // +5 암호화 데이터 저장

		// 4. 복호화

		for (int i = 0; i < abc.length; i++) {
			abc[i] = (char) (abc[i] - 5);
		} // char에 다시 -5를 하여 복호화

		System.out.println("각 문자에 '-5'를 하여 복호화 : ");
		for (int i = 0; i < abc.length; i++) {
			System.out.print(abc[i]);
		}

		System.out.println("");

		char e[] = abc; // 복호화 데이터 저장

		// 숫자 다시 거꾸로

		char temp2 = 0;
		int k = abc.length - 1;

		for (int i = 0; i < abc.length / 2; i++, k--) {
			temp2 = abc[i];
			abc[i] = abc[k];
			abc[k] = temp2;
		}

		System.out.println("다시 역순으로 출력한 문자열 : ");
		for (int i = 0; i < abc.length; i++) {
			System.out.print(abc[i]);

		}

		System.out.println("");

		// 대문자를 다시 소문자로

		for (int i = 0; i < abc.length; ++i) {
			if (65 <= abc[i] && abc[i] <= 90) { // 대문자의 범위
				abc[i] = (char) (abc[i] + 32); // 소문자를 대문자로 변환
			}
		} // 여기서 다시 초기화 값으로

		System.out.println("초기 문자열로 복구 : ");
		for (int i = 0; i < abc.length; i++) {
			System.out.print(abc[i]);

			sc.close();
		}
	}
}
