package com.attendance;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDubbo
@MapperScan(basePackages = {"com.attendance.dao"})
public class AttendanceCompanyServiceMainApp {
    public static void main(String[] args) {
        SpringApplication.run(AttendanceCompanyServiceMainApp.class,args);
    }
}
