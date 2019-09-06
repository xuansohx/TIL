> day82 배운 내용 : GitHub 특강 (강동주 강사님) - Git Branch | Merge 

## 1. Git Branch

:heavy_check_mark: Branch를 통한 코드 관리 → Branching & Pull Request (실제 협업에서 사용)

:heavy_check_mark: branch로 생성 된 코드는 완전히 다른 세계

:heavy_check_mark: git branch 시각화 : https://git-school.github.io/visualizing-git/



현재 가지고 있는 branch 확인 & 내가 어떤 branch에 있는지 확인

```
git branch
```

> 기본 branch는 'master' / commit을 해줘야 기본 branch 생성

새로운 branch 생성

```
git branch [브랜치 이름]
```

branch 이동

```
git switch [브랜치 이름]
git checkout [브랜치 이름] → 예전에 쓰던 명령어
```

branch 통합

```
git merge [브랜치 이름]
```

> 처음 생성된 master 브랜치와 생성된 브랜치는 갑을관계가 아닌 동등한 관계 
>
> ∴ 언제든 master 브랜치에서 새로운 브랜치로 옮겨갈 수 있음 

branch 삭제

```
git branch -d [브랜치 이름]
```



## 2. Merge 시나리오

새로운 index.html 파일 생성 

- `!`+`Tab` 로 기본 틀 기본 완성

- `ol>li*3` + `Tab`로 리스트 쉽게 만들 수 있음

branch 작업 하기 전에 깨끗한 상태 유지

```
git reset --hard [recent commit hash]
git reset --hard HEAD
```

```
git rm --cached [파일명]
```

[학습 참고 사이트 - Git flow merge toast](https://meetup.toast.com/posts/122)

### 2-1. Fast-forward merge

- new 브랜치를 만들어 commit

- master 브랜치로 전환하여 `git merge new`

- 지금과 같은 경우, master에서 new를 생성한 후 new 브랜치만 commit 

  > master 브랜치는 그대로

  ▶ 이 경우 master의 상태만 앞당기면 되므로 간단하게 merge가 가능 (**Fast-forward merge**)

- branch는 일회용이므로, 분기 후 merge 했으면 delete 

```
git branch -d new
```



### 2-2. Auto-merge (conflict 없는 merge)

- neo 브랜치 만들면서 바로 이동

```
git switch -c neo
```

- master 브랜치로 'README.md' 수정 & new 브랜치로 'index.html' 수정 

- master 브랜치로 접속하여 `git merge neo` 

  → 각각의 브랜치가 서로 다른 파일을 수정하였기 때문에 바로 merge 되지 않음

- vim 화면에서 `wq` 누르고 나옴 → conflict 발생하지 않으면 merge 진행

- branch와 merge의 history 보기

```
git log --oneline
git log --oneline --graph
```



### 2-3. Merge with conflict

> 같은 파일의 같은 부분을 수정하여 conflict이 발생하는 시나리오

- master 브랜치로 'README.md' 파일 수정
- conflict 브랜치 생성 후 이동 → master가 수정한 'README.md'의 같은 부분에 내용 추가
- 다시 master 브랜치에서 `git merge conflict`
- conflict 발생하면 `cat` 혹은 VScode로 확인(Ctrl+Z)하여 파일 수정 → conflict 해결



## 3. branch-pr

:christmas_tree: day82 workshop 

- master 브랜치는 그대로 두고,  leader와 slave 브랜치를 만들어 작업

```
git switch -c leader
git switch -c slave
```

- index.html 파일과 README.md 파일을 만든 후, 각각 다른 파일 작업
- master 권한이 아닌 branch 권한으로 push 

```
git push origin leader
git push origin slave
```

> Collaborators 권한을 줘야 push 가능

- master에게 New Pull Request 요청 필요 → master가 확인 후 merge 진행



>  Settings에서 rule을 정하여 검토를 받아야만 push 및 merge가 가능하도록 설정 가능

> master 브랜치 사용 못 하도록 설정할 수도 있음

<br>

:heavy_check_mark: Git 사용법 공부하고 정리해두기 ▶ [git사용법정리](https://github.com/xuansohx/study/blob/master/%EC%95%88%EC%86%8C%ED%98%84/git%EC%82%AC%EC%9A%A9%EB%B2%95%EC%A0%95%EB%A6%AC.md)