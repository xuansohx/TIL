package w3;

import java.util.Random;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) throws InterruptedException {
		// 190520 workshop ③ : 부루마블(2인용)-기본
	 
		/*
		 * 1.8X8 정방행렬을 만든다 (테두리만 표시) 
		 * 2.플레이어1,2에게 각각 포인트를 준다. (1000P) 
		 * 3.테두리에 1~100까지의 숫자를 랜덤하게 배열한다. 
		 * 4.각 플레이어가 주사위를 한번씩 던져 나온 숫자만큼 이동 
		 * 5.해당 위치에 있는 숫자만큼 포인트 차감 
		 * 6.계속해서 진행한 후 포인트가 0이하가 된 플레이어 패배
		 */

	  // 1. 8x8 game map 만듦
	  // 1~100의 숫자를 랜덤하게 배열
	  
	  int map[][] = new int[8][8]; // 8x8 map 배열 선언
	  Random r = new Random();
	  
	  for(int i=0; i<map.length; i++) {
	   for(int j=0; j<map[i].length; j++) {
	    map[i][j] = r.nextInt(100)+1;
	   }
	  }
	  
	  // 테두리에만 숫자를 배열해야 하므로 가운데는 표시 안함
	  for(int i=1; i<map.length-1; i++) {
	   for(int j=1; j<map[i].length-1; j++) {
	    map[i][j] = 0;
	   }
	  } 
	  
	  // game map 출력
	  for(int i=0; i<map.length; i++) {
	   for(int j=0; j<map[i].length; j++) {
	    System.out.print(map[i][j]+"\t");
	   }
	   System.out.println();
	  }
	 
	  
	  // 2. 플레이어에게 포인트 지급
	  int point1 = 1000;
	  int point2 = 1000; // 1,000포인트로 게임을 시작하니까 초기값이 1,000
	  System.out.println("게임을 시작합니다.");
	  
	  // 3. 게임 시작 (현재 포인트 표시, 주사위 굴려서 이동, 포인트차감)
	  
	  // player1과 player2의 시작위치는 map[0][0]
	   int a = 0; // player1의 시작위치
	   int b = 0; // map[a][b]
	   int c = 0; // player2의 시작위치
	   int d = 0; // map[c][d]
 
	  while(true) {
	   System.out.println("---------------------------------------------");
	   System.out.println("현재 포인트 : ");
	   System.out.println("player1 : "+ point1 +" "+"player2 : "+ point2);
	   System.out.println("---------------------------------------------");
	   
	   // player1 게임 시작
	   Scanner sc = new Scanner(System.in);
//	   Thread.sleep(1000);
	   System.out.println("player1 차례입니다. 엔터를 눌러 주사위를 굴러주세요.");
	   String start1 = sc.nextLine();
	   int data1 = r.nextInt(6)+1; // 시작을 누르면 랜덤으로 주사위 값이 나옴(1~6)
	   // 주사위의 값 'data1'만큼 이동하기
	   System.out.println("player1의 주사위 값 : "+data1); // 나온 주사위의 값
	   
	   if(a==0) { // →
		   if((b+data1)>map.length-1) { // ↓
			   int dist = map.length - 1 - b; 
			   a = (data1-dist);
			   b=7;
		   }else {
			   b += data1;
		   }
	   }
	   
	   else if(b==7) { // ↓
		   if((a+data1)>map.length-1) {
			   int dist = map.length -1 - a;
			   b = map.length-1-(data1-dist);
			   a=7;
		   } else {
			   a += data1;
		   }
	   }
	   
	   else if(a==7) {
		   if((b-data1)>=0){
			   b -= data1;
		   } else {
		   a = map.length-1-(data1-b);		
			   b=0;
		   }
	   }
	   
	   else if(b==0) {
		   if((a-data1)>=0) {
			   a -= data1;
		   } else {
			   b = map.length-1-(data1-a);
			   a=0;
		   }
	   }
	   
	   point1 -= map[a][b];
	   
	   System.out.println("player1의 현재 위치 : "+a+" "+b); // 현재 위치 표시
	   System.out.println("player1의 차감 포인트 : "+map[a][b]); // 현재 위치의 값
	   System.out.println("player1의 현재 포인트 : "+point1);
	   System.out.println();
	   
	   // plater2 게임 시작
//	   Thread.sleep(1000);
	   System.out.println("player2 차례입니다. 엔터를 눌러 주사위를 굴러주세요.");
	   String start2 = sc.nextLine();
	   int data2 = r.nextInt(6)+1;
	   System.out.println("player2의 주사위 값 : "+data2); // 나온 주사위의 값
	   // 주사위의 값 'data2'만큼 이동하기 (변수는 c와 d이용)
	   
	   if(c==0) { // →
		   if((d+data2)>map.length-1) { // ↓
			   int dist = map.length - 1 - d; 
			   c = (data2-dist);
			   d=7;
		   }else {
			   d += data2;
		   }
	   }
	   
	   else if(d==7) { // ↓
		   if((c+data2)>map.length-1) {
			   int dist = map.length -1 - c;
			   d = map.length-1-(data2-dist);
			   c=7;
		   } else {
			   c += data2;
		   }
	   }
	   
	   else if(c==7) {
		   if((d-data2)>=0){
			   d -= data2;
		   } else {
		   c = map.length-1-(data2-d);
			   d=0;
		   }
	   }
	   
	   else if(d==0) {
		   if((c-data2)>=0) {
			   c -= data2;
		   } else {
			   d = map.length-1-(data2-c);
			   c=0;
		   }
	   }
	   
	   
	   point2 -= map[c][d];
	   
	   System.out.println("player2의 현재 위치 : "+c+" "+d); // 현재 위치 표시
	   System.out.println("player2의 차감 포인트 : "+map[c][d]); // 현재 위치의 값
	   System.out.println("player2의 현재 포인트 : "+point2);
	   System.out.println();
	   
	   // 점수가 0 이하가 되는 사람이 있으면 게임이 종료되면서 게임 결과를 알림
	   if(point1 <= 0 || point2 <= 0) {
		   if(point1>point2) {
			   System.out.println("palyer2이 승리하였습니다."); 
		   }else {
			   System.out.println("palyer1이 승리하였습니다."); 
	   }
		   System.out.println("게임이 종료되었습니다."); 
		   sc.close();
		   break;
	  }
	  
	  
	  	  }

	}
}
