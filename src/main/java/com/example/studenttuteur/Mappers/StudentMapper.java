package com.example.studenttuteur.Mappers;

import com.example.studenttuteur.DTOs.StudentDTO;
import com.example.studenttuteur.Entitys.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setNom(student.getNom());
        dto.setPrenom(student.getPrenom());
        dto.setAge(student.getAge());
        return dto;
    }

    public Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setNom(dto.getNom());
        student.setPrenom(dto.getPrenom());
        student.setAge(dto.getAge());
        return student;
    }
}
