package com.kob.backend03;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.jws.soap.SOAPBinding;
import java.net.PasswordAuthentication;

@SpringBootTest
class Backend03ApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("psch"));
        System.out.println(passwordEncoder.encode("pb"));
        System.out.println(passwordEncoder.encode("pc"));  //明文转密文
        System.out.println(passwordEncoder.encode("pe"));
        System.out.println(passwordEncoder.matches("pyxc","$2a$10$18Qn527GbjPqcqjUKaccMeJs9MlJQqeL82GGthcP4ZR.uIJU3CTMe"));//匹配
    }

}
