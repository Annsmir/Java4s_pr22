package com.example.ex22;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan
@Configuration
@EnableAsync
@EnableAspectJAutoProxy
@EnableJpaRepositories
@EnableScheduling
public class BeanConfig {

}
