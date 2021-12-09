package com.mong.studyspringboot.student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

// @Component // 가장 기본적인 annotation
@Service // component 에서 추가적으로 뭔가 더 해주는 것은 없음 그치만 service layer에 사용하기
public class StudentService {

    public List<Student> getStudents() {
        // return Arrays.asList("Hello","world");  // Java 9 이상 : List.of("Hello","World");
        return Arrays.asList(
                new Student(
                        1L,
                        "Yujin",
                        "yujin@gmail.com",
                        LocalDate.of(2000, Month.JANUARY, 5),
                        21
                )
        );
    }
}
