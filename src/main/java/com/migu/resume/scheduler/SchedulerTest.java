package com.migu.resume.scheduler;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * 定时任务测试
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月5日 下午3:32:06
 * @version: V1.0
 */
@Component
public class SchedulerTest {
//	@Resource  
//	private IUserService userService;  
//	@Scheduled(cron = "0/20 * * * * ? ")
	public void run() {
//		userService.getUserById(1);
		System.out.println("==============::::"+new Date().getTime());
	}
}
