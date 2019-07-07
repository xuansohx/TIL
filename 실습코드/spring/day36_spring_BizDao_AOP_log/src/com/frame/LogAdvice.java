package com.frame;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

// LogAdvice에서 AOP 사용하기 위하여 아래 두 줄 추가
@Service
@Aspect
public class LogAdvice {
	public void printLog() {
		Date d = new Date();
		System.out.println(d + "[공통로그]비즈니스 로직 수행...");
	}

	@Before("execution(* com..Biz+.insert(..))") // () 안의 함수가 실행되면 Before도 실행되겠다는 의미
	public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		// client가 호출한 메소드의 시그니처(리턴타입, 이름, 매개변수)정보가 저장된 Signature 객체 리턴
		Object[] args = jp.getArgs();
		// client가 매소드를 호출할 때 넘겨준 인자 목록을 Object 배열로 리턴
		System.out.println(method + " : " + args[0]);
		System.out.println("[before]비즈니스 로직 수행...");
	}

	@AfterReturning(pointcut = "execution(* com..Biz+.select(..))", returning = "returnObj")
	// before에서는 pointcut을 사용할 수 없음
	// Object와 같은 이름을 사용해줘야됨
	public void afterLog(JoinPoint jp, Object returnObj) {
		// returnObj이 myspring.xml의 aop:after-returning 태그로 들어감
		System.out.println("[after]비즈니스 로직 수행...");
		System.out.println("Log Result: " + returnObj);
	}

	@AfterThrowing(pointcut = "execution(* com..Biz+.select(..))", throwing = "ex")
	public void exLog(JoinPoint jp, Exception ex) {
		Date d = new Date();
		System.out.println(d + "[EX로그]Exception...");
		System.out.println(ex.getMessage());
	}

	@Around("execution(* com..Biz+.select(..))")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		System.out.println("Before Action...");
		Object obj = pjp.proceed();
		System.out.println("After Action...");
		stopWatch.stop();

		System.out.println("Processing Time: " + stopWatch.getTotalTimeSeconds());

		return obj;

	}
}
