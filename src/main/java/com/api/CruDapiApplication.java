package com.api;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class CruDapiApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(CruDapiApplication.class, args);
//	}
//
//}


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.apiRepository") // Specify the package where your repositories are located
public class CruDapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CruDapiApplication.class, args);
    }
}
