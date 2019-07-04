package ws;

import java.util.Arrays;
import java.util.Random;

public class Vote {

	public static void main(String[] args) {
		// 190517 workshop ① : 반장후보투표

		/* 게임규칙 
		 * 1번부터 5번까지의 반장후보 선거 결과 총 50명의 투표 인원이 5명에게 투표를 진행 하였다.
		 * 
		 * 1. 가장 많이 투표를 받은 후보의 정보를 출력 하시오 
		 * 2. 가장 적게 투표를 박은 후보의 정보를 출력 하시오
		 * 3. 투표 결과 가장 표를 가장 많이 받은 순으로 정렬 하여 출력 하고 
		 * 4. 투표 결과 가장 표를 적게 받은 순으로 정렬 하여 출력 하시오
		 */

		// 50명의 사람들이 뽑은 투표 값을 랜덤으로 배열 vote에 저장
		int vote[] = new int[50];
		Random r = new Random(); // 랜덤을 사용하기 위한 선언
		for (int i = 0; i < vote.length; i++) { // for문을 통해 각 칸에 랜덤으로 숫자를 대입
			vote[i] = r.nextInt(5) + 1; // (0,1,2,3,4) +1로 설정 → 1,2,3,4,5번 투표
		}

		System.out.println("50명의 투표내용 : ");
		System.out.println(Arrays.toString(vote)); // 배열을 쭉 나타냄
		System.out.println("--------------------");

		// 각 번호 당 몇 명이 투표를 진행했는지 나타내는 count 배열
		int count[] = new int[6]; // 투표한 값 나타내는 배열
		for (int i = 0; i < vote.length; i++) { // 배열 vote를 읽어들여 어떤 값에 투표를 했는지 읽는 for문
			for (int k = 1; k < count.length; k++) { // 배열 count을 읽어 카운트를 하기 위함
				if (vote[i] == k) {
					count[k]++;
				}
			}

		}
		System.out.println("각 번호 당 투표인원 : ");
		for (int k = 1; k < count.length; k++) {
			System.out.println(k + "번" + " : " + count[k] + "명");
			// count 배열의 값을 나타냄 → 몇 번에 몇 명이 표시했는지 나타냄
			// 41번 줄의 k는 몇 번째 배열인지, count[k]는 k번째 배열의 값이 무엇인지 나타냄
		}
		System.out.println("--------------------");

		// 가장 많이 표를 받은 후보의 정보 출력 : 배열 내 최대값 구하기
		// 가장 적은 표를 받은 후보의 정보 출력 : 배열 내 최소값 구하기

		int max = count[1]; // 최대최소를 구하기 위하여 기준 값을 정해야 됨
		int min = count[1]; // → 배열의 맨 앞의 값인 count[0]를 기준으로 정하고 뒤이 수와 차례차례 비교함

		for (int i = 1; i < count.length; i++) { // count 배열을 다 읽는 for문
			if (count[i] > max) { // count[i]의 값과 그 앞의 값을 계속 비교하여 최대값을 선정함
				max = count[i];
			} else if (count[i] < min) { // count[i]의 값과 이전 값 비교하여 최소값 선정
				min = count[i]; // 비교하여 작은 값을 min에 저장하여 최종적으로 남은 min 값을 최소로 출력
			}
		}

		System.out.println("가장 많이 받은 표의 값 : " + max);
		System.out.println("가장 적게 받은 표의 값 : " + min);
		System.out.println("--------------------");

		// 투표 결과를 표를 가장 많이 받은 순으로 정렬 (내림차순)

		for (int i = 1; i < count.length; i++) { // 순서를 정하기 위하여 count 배열을 모두 읽어들임
			for (int j = 1; j < count.length - 1 - i; j++) { // 이미 읽은 자리와 비교한 자리의 값은 비교하지 않아도 되므로 '-1-i'가 필요함
				if (count[j] < count[j + 1]) {
					int temp = count[j]; // 자리 바꾸기 위하여 임시변수인 temp 만듦
					count[j] = count[j + 1]; // count[j+1]의 값이 더 크므로 앞 자리인 count[j]에 값을 저장하며 앞으로 이동
					count[j + 1] = temp; // 앞서 저장해 둔 temp(count[j]값을 뒤로 보내리 위하여 count[j+1]에 저장함

				}
			}
		}

		System.out.println("가장 많이 받은 표 순으로 정렬 : ");
		for (int i = 1; i < count.length; i++) {
			System.out.print(count[i] + " ");
		}

		System.out.println("");

		// 투표 결과를 표를 가장 적게 받은 순으로 정렬 (오름차순)

		for (int i = 1; i < count.length; i++) {
			for (int j = 1; j < count.length - 1 - i; j++) {
				if (count[j] > count[j + 1]) { // count[j-1]이 되면 오류뜸 → j=0일 때, count[0]과 count[-1]을 비교할 수 없으므로!
					int temp = count[j + 1];
					count[j + 1] = count[j];
					count[j] = temp;

				}
			}
		}

		System.out.println("가장 적게 받은 표 순으로 정렬 : ");
		for (int i = 1; i < count.length; i++) {
			System.out.print(count[i] + " ");
		}

		System.out.println("");

	}

}
