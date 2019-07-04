package ws;

import java.util.Arrays;
import java.util.Random;

public class SumTest {

	public static void main(String[] args) {
		// 190517 workshop ③ : Sum Test

		/*
		 * 1. 100개의 정수형 배열에 1~100까지의 숫자를 난수로 발생 시킨다 (중복 허용하지 않음) 
		 * 2. 짝수 번째에 있는 정수들만 50개의 배열에 저장 
		 * 3. 홀수 번째에 있는 정수들만 50개의 배열에 저장 
		 * 4. 짝수 번째 배열의 첫번째 숫자와 홀수 번째 배열의 마지막 숫자를 합산, 
		 * 다시 짝수 번째 두번째 숫자는 홀수 번째 배열의 마지막에서 두번째 숫자와의 합산 ....... 
		 * 이런식으로 합산한 결과를 출력 하시오
		 */

		// 1. 100개의 정수형 배열에 1~100까지의 숫자를 난수로 발생
		int num[] = new int[100];

		Random r = new Random();
		int rr = 0; // 랜덤으로 뽑은 수
		for (int i = 0; i < num.length; i++) {
			rr = r.nextInt(100) + 1; // 1~100까지의 숫자의 난수 발생
			num[i] = rr;
			for (int j = 0; j < i; j++) { // 중복검사
				if (num[j] != num[i]) { // num[i]은 지금, num[j]는 비교대상
					num[i] = rr; // 만약 중복이 되지 않으면 뽑은 수인 rr을 저장함
				} else {
					i--;
					break; // i번째 배열 재검사 위하여 for문 앞으로 보냄
				}
			}

		}

		System.out.println("1~100까지의 난수를 저장한 배열");
		System.out.println(Arrays.toString(num));

		System.out.println("");

		// 2. 짝수 번째에 있는 정수들끼리 배열에 저장
		// 3. 홀수 번째에 있는 정수들끼리 배열에 저장

		int a[] = new int[50]; // 짝수번째 정수들 저장하는 배열
		int b[] = new int[50]; // 홀수번째 정수를 저장하는 배열

		for (int i = 0; i < num.length; i++) {
			if (i % 2 == 0) { // 짝수
				a[(i / 2)] = num[i];
			} else if (i % 2 == 1) { // 홀수
				b[(i / 2)] = num[i];
			}
		}

		System.out.println("짝수 번째에 있는 정수를 저장한 배열 : ");
		System.out.println(Arrays.toString(a));
		System.out.println("");
		System.out.println("홀수 번째에 있는 정수를 저장한 배열 : ");
		System.out.println(Arrays.toString(b));
		System.out.println("");

		// 4. 짝수 1번 + 홀수 50번, 짝수 2번 + 홀수 49번 ... 덧셈한 배열 표시

		int sum[] = new int[50]; // 합산 값을 저장할 배열 sum

		for (int i = 0; i < a.length; i++) {
			sum[i] = a[i] + b[49 - i];
		}

		System.out.println("최종 합산한 배열 : ");
		System.out.println(Arrays.toString(sum));

	}
}
