package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com"})
@ComponentScan({"com"})
@EnableJpaRepositories(basePackages="com.bean")
@EntityScan(basePackages={"com"})
public class Application {

	
	/**
	 * Starter project of spring boot+jpa+hibernate+dbcp2+mysql+slg4j+log4j2+spring junit
	 * Sweta Pawar
	 * @param args
	 */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
