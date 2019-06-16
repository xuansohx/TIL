# Git 사용법

Git 사용 및 명령어 정리

## Ⅰ. Git 명령어 순서

프로젝트를 처음 생성할 때

```
git init
git add [파일명]
git commit -m "넣을 메시지"
git push -u origin master
```

## Ⅱ. 이미 만들어진 프로젝트에 Git 올리기

```
git add [추가 할 파일 또는 폴더의 이름] : 추가할 파일 또는 폴더를 스테이징(commit 할 준비)
git add . : 현재 디렉토리 내의 모든 변경사항 스테이징
git commit -m "메시지"
git push -u origin master
```

## Ⅲ. 이미지 파일 저장 및 업로드

- '서식 → 이미지'를 통해 원하는 이미지 선택
- 현재 파일의 위치를 시작으로 상대주소 값 입력 | 환경설정에서 상대적 위치 사용 선택 가능
- 컴퓨터 상 절대주소가 아닌 상대주소는 ' / ' 로 구분함 : (../Image/UML/Company(E-M).jpg)

## Ⅳ. 기타 명령어

- clone : Git에 있는 데이터 컴퓨터로 불러올 때 사용
- mkdir : 디렉토리 생성
- status : 현재 상태 확인 → 변경사항 여부
- ls : 폴더 내 파일 리스트 정렬
- rm : 제거
- touch : 파일 생성
- pwd : 현재 위치 확인
- cd : 데이터 공간 이동 → cd .. (상위폴더), cd GitHub (원하는 폴더 접속)
- reset HEAD^ : commit 삭제 → '^'의 개수가 몇 개의 commit을 삭제할 것인지 결정
- commit --amend : commit message 변경하기
