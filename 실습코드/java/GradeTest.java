package ws;

import java.util.Arrays;
import java.util.Scanner;

import calc.Calc;

public class GradeTest {

	public static void main(String[] args) {
		
		int score[] = new int[4];
		Scanner sc = new Scanner(System.in);
		System.out.println("네 과목의 점수를 입력해주세요.");
		for(int i=0; i<score.length; i++) {
			score[i] = sc.nextInt();
		}
		Grade g = new Grade(score);
		System.out.println("점수의 합계 : "+g.sum()); // 합
		System.out.println("평균 : "+g.avg()); // 평균
		System.out.println("등급 : "+g.grade()); // 등급
		System.out.println("최고 점수 : "+g.max()); // 최고점
		System.out.println("최저 점수 : "+g.min()); // 최하점
		System.out.println(Arrays.toString(g.asort())); // 오름차순
		System.out.println(Arrays.toString(g.dsort())); // 내림차순
		
		sc.close();
		
	}
	

}
