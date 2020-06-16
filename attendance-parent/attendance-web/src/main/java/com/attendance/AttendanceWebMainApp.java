package com.attendance;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDubbo
public class AttendanceWebMainApp {
    public static void main(String[] args) {
        SpringApplication.run(AttendanceWebMainApp.class,args);
    }
}
