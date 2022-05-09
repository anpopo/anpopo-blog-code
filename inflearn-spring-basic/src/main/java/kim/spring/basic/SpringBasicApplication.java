package kim.spring.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// spring boot 의 내장 톰캣을 실행하면서 spring 서버가 실행 됨.
@SpringBootApplication
public class SpringBasicApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBasicApplication.class, args);
    }
}
