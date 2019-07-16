
package com.itmayiedu.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 
 * @classDesc: 功能描述:(Spring Cloud服务注册中心)

 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {
	public static void main(String[] args) {

		SpringApplication.run(EurekaServer.class, args);
	}
}
