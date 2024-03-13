package com.kzvo.ptmbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ScopedProxyMode;

@SpringBootApplication
@ComponentScan(scopedProxy = ScopedProxyMode.TARGET_CLASS, basePackages = "com.*")
public class PtmBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PtmBackendApplication.class, args);
    }

}
