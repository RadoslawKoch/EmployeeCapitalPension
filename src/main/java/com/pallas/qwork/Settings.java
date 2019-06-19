package com.pallas.qwork;

import com.pallas.qwork.errors.ErrorLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class Settings {
    
    @Bean
    public ErrorLogger getLogger(){
        return new ErrorLogger("C:/Users/RadoslawKoch/Desktop/log.txt");
    }
    
    @Scheduled(fixedDelay = 1000000, initialDelay = 1000000)
    public void cleanLog(){
        getLogger().cleanUp();
    }
}
