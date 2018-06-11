package kr.co.ksr1.commom.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TaskSchedule {
	
	@Scheduled(fixedDelay = 100000*5)
	public void test() {
		log.debug("나도나도 실행되게 해죠=======>");
	}
	
}
