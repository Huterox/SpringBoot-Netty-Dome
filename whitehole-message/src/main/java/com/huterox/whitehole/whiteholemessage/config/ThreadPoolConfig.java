package com.huterox.whitehole.whiteholemessage.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "whitehole.thread")
@Component
@Data
public class ThreadPoolConfig {
    private Integer coreSize;
    private Integer maxSize;
    private Integer keepAliveTime;
}
