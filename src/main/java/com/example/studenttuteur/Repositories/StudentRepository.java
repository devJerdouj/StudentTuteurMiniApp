package com.example.studenttuteur.Repositories;

import com.example.studenttuteur.Entitys.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
