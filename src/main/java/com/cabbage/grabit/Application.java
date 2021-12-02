package com.cabbage.grabit;

import com.cabbage.grabit.domain.user.Giver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public Giver dummyGiver(){
//
//        return Giver.builder()
//                .name("할명수")
//                .company("무한상사")
//                .email("audtn@gmail.com")
//                .businessNum("1234523422")
//                .picture("default.jpg")
//                .build();
//    }
}
