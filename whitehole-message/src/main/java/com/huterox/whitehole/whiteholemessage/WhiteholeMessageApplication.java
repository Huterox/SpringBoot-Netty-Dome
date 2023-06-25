package com.huterox.whitehole.whiteholemessage;

import com.huterox.whitehole.whiteholemessage.annotation.EnableNettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.huterox.whitehole.whiteholemessage.dao")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.huterox.whiteholecould.feign.user"})
@EnableTransactionManagement
@EnableNettyServer
public class WhiteholeMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiteholeMessageApplication.class, args);
    }

}
