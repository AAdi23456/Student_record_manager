package com.studentRecord.Reposatry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentRecord.Entity.Student;

@Repository
public interface StudentReosatry extends JpaRepository<Student, Integer> {

}
