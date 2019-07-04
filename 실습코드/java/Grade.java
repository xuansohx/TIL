package ws;

public class Grade {
//	4과목의 점수를 입력 받는다.국어, 영어, 수학, 과학
//	입력 예: 89 92 89 100
//
//	입력 받은 점수를 이용하여 다음을 출력 하시오
//	1. 합을 출력 하시오
//	2. 평균을 출력 하시오
//	3. 평균이 90점 이상이면 A, 80점 이상이면 B, 70점 이상이면 C, 60점 이상이면 D, 이하면 F
//	4. 최고점의 과목을 출력 하시오
//	5. 최하점의 과목을 출력 하시오
//	6. 오름차순 정렬 
//	7. 내림차순 정렬
	
	 private int [] data;

	 public Grade() {
	 }

	 public Grade(int[] data) {
	  this.data = data;
	 }
	 
	 // 1. 합
	 public int sum() {
		 int sum = 0;
		 for(int a:data) {
			sum += a; 
		 }
		 return sum;
	 }
	 
	 // 2. 평균
	 public double avg() {
		 double avg = sum();
		 avg = sum() / data.length;
		 return avg;
	 }
	 
	 // 3. 점수에 따른 평점
	 public String grade() {
		 String grade = "";
		 if(avg()>=90){ 
			 grade = "A";
		 }else if(avg()>=80) {
			 grade = "B";
		 }else if(avg()>=70) {
			 grade = "C";
		 }else if(avg()>=60) {
			 grade = "D";
		 }else {
			 grade = "F";
		 }
		 return grade;
	 }
	 
	 // 4. 최고점을 갖는 과목
	 public String max() {
		 int a[] = data;
		 int max = a[0];
		 int b = 0;
		 for(int i=0; i<a.length;i++) {
			 if(max<a[i]) {
				 max = a[i];
				 b = i;
			 }
		 }
		 switch(b) {
		 case 0:
			 return "국어";
		 case 1 :
			 return "영어";
		 case 2 :
			 return "수학";
		 default : 
			 return "과학";
		 }
		
	 }
	 
	 // 5. 최하점을 갖는 과목
	 public String min() {
		 int a[] = data;
		 int min = a[0];
		 int b = 0;
		 for(int i=0; i<a.length; i++) {
			 if(a[i]<min) {
				 min = a[i];
				 b = i;
			 }
		 }
		 switch(b) {
		 case 0:
			 return "국어";
		 case 1 :
			 return "영어";
		 case 2 :
			 return "수학";
		 default : 
			 return "과학";
		 }
	 }
	 
	 // 6. 오름차순 정렬
	 public int[] asort() {
		 int a[] = data;
		 for(int i=0; i<a.length; i++) {
			 for(int j=0; j<a.length-1-i; j++) {
				 if(a[j+1]<a[j]) {
					 int temp = a[j];
					 a[j] = a[j+1];
					 a[j+1] = temp;
				 }
			 }
		 }
		 return a;
	 }
	 // 7. 내림차순 정렬
	 public int[] dsort() {
		 int a[] = data;
		 for(int i=0; i<a.length; i++) {
			 for(int j=0; j<a.length-1-i; j++) {
				 if(a[j]<a[j+1]) {
					 int temp = a[j];
					 a[j] = a[j+1];
					 a[j+1] = temp;
				 }
			 }
		 }
		 return a;
	 }
	

}
