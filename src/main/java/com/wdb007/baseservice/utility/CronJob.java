package com.wdb007.baseservice.utility;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class CronJob {
    public final static long ONE_Minute =  60 * 1000;
    @Scheduled(fixedDelayString="${time-intervel}")
    public void fixedDelayJob(){
        System.out.println(new Date()+" >>6秒fixedDelay执行....");
    }

    @Scheduled(fixedRateString="${time-intervel}")
    public void fixedRateJob(){
        System.out.println(new Date()+" >>fixedRate执行....");
    }

    @Scheduled(cron="0 15 3 * * ?")
    public void cronJob(){
        System.out.println(new Date()+" >>cron执行....");
    }
    public String getString() {
    	return "";
    }

}

