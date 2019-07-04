package ws;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lotto {

	public static void main(String[] args) {
		// 190516 workshop : 로또프로그램 구현하기
		
		/* 게임규칙
		1. 6개의 숫자 입력 (범위 : 1~10)
		2. 그 날의 당첨번호 6개 생성 (범위 : 1~10) 중복되지 않는 숫자 생성
		3. 당첨금 생성  (범위: 10억~100억)
		4. 입력 받은 숫자와 당첨번호 확인
		- 3개 맞으면 : 4등 (당첨금 5%)
		- 4개 맞으면 : 3등 (당첨금 10%)
		- 5개 맞으면 : 2등 (당첨금 20%)
		- 6개 맞으면 : 1등 (당첨금 50%)
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1~10의 숫자 중 여섯 개를 입력해주세요");
		
		// 6개의 로또 숫자 입력 (사용자)
		int a[] = new int[6]; 

		for(int i=0; i<a.length; i++) {
			int data = sc.nextInt();  // 숫자로 데이터 입력받으니까 바로 int로 받음
			if(0<=data && data<=10) {
				a[i] = data;
			}else
			{
				i--; //a[i] 번째 채우지 못하고 통과했으므로 다시 입력하기 위함
				System.out.print("다시 입력해주세요.\n");
				continue; // 다시 for문 처음으로
			}
				
		}
		
		// 사용자가 입력한 로또 입력 값 나타내기
		System.out.print("내 번호: ");
		for(int j=0; j<a.length; j++) {
			System.out.print(+a[j]+" ");
		}
		
		// 컴퓨터가 당첨번호생성(랜덤)
		int Lotto[] = new int[6]; // 컴퓨터가 뽑은 값 배열로 저장
		
		Random r = new Random();
		int com = 0;
		for(int i=0; i<Lotto.length; i++)
		{
			com = r.nextInt(10)+1; // (0,1,2,3,4,5,6,7,8,9) +1 값을 랜덤으로
			Lotto[i] = com;
			for(int j=0; j<i; j++)
			{
				if(Lotto[j] != Lotto[i]) //중복방지
				{
					Lotto[i] = com;
				}
				else
				{
					i--;
					break;
				}
			}
		}
		
		//컴퓨터가 뽑은 로또번호 나타내기(당첨번호)
		System.out.print("\n당첨 번호는:");
		for(int i=0; i<Lotto.length; i++) {
		System.out.print(+Lotto[i]+" ");
		}
		System.out.println();
		
		// 선택번호와 로또당첨번호비교
		int count =0;
		for(int i=0; i<a.length; i++) 
		{ 
			for(int c=0; c<Lotto.length; c++) 
			{ 
				if(a[i] == Lotto[c]) 
				{
					count++;
				} 
					 
			}
		}	
		
		// 당첨금 생성	
		long money = 0; // 총 당첨금
		long prize = 0; // 등수별 당첨금
		
		money = (r.nextInt(10)+1)*1000000000L;
		
		// 선택번호와 당첨번호에 따른 등수와 당첨금 결정
		switch(count) {
		case 3 :
			prize = (long)(money*5/100);
			System.out.println("등수: 4");
			break;
		case 4 :
			prize = (long)(money*10/100);
			System.out.println("등수: 3");
			break;
		case 5 :
			prize = (long)(money*20/100);
			System.out.println("등수: 2");
			break;
		case 6 :
			prize = (long)(money*50/100);
			System.out.println("등수: 1");
			break;
			
		default :
				System.out.println("꽝");
				break;
				
		}
	
		
		// 당첨금 결과정리
		System.out.printf("\n총 당첨금: %d원입니다.",money);
		System.out.printf("\n 당첨금: %d원입니다.",prize);
		
	
	}
}


