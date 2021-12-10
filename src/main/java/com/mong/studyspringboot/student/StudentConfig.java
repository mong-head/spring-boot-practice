package com.mong.studyspringboot.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Configuration
public class StudentConfig {

    // 서버 키자마자 데이터 넣기
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student mariam = new Student(
                "Yujin",
                "yujin@gmail.com",
                LocalDate.of(2000, Month.JANUARY, 5)
            );

            Student alex = new Student(
                    "alex",
                    "alex@gmail.com",
                    LocalDate.of(1996, Month.JANUARY, 20)
            );

            repository.saveAll(
                    Arrays.asList(mariam, alex)
            );
        };
    }
}
