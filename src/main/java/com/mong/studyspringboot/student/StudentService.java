package com.mong.studyspringboot.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// @Component // 가장 기본적인 annotation
@Service // component 에서 추가적으로 뭔가 더 해주는 것은 없음 그치만 service layer에 사용하기
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        // return Arrays.asList("Hello","world");  // Java 9 이상 : List.of("Hello","World");
//        return Arrays.asList(
//                new Student(
//                        1L,
//                        "Yujin",
//                        "yujin@gmail.com",
//                        LocalDate.of(2000, Month.JANUARY, 5),
//                        21
//                )
//        );
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException(
                    "student with id " + studentId + "does not exists.");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional // update가능한지 보고 가능하지 않으면 rollback
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id" + studentId + " does not exists."
                ));

        if(name != null
                && name.length() > 0
                && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email != null
                && email.length() > 0
                && !Objects.equals(student.getEmail(),email)){
            student.setEmail(email);
        }
    }
}
