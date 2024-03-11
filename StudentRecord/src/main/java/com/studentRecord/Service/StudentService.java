package com.studentRecord.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentRecord.Entity.Student;
import com.studentRecord.Reposatry.StudentReosatry;

@Service
public class StudentService {
	@Autowired
	private StudentReosatry studentReosatry;

	public List<Student> getAllStudents(Integer rollNo) {
		if (rollNo != null) {
			Optional<Student> studentOptional = studentReosatry.findById(rollNo);
			if (studentOptional.isPresent()) {
				return Collections.singletonList(studentOptional.get());
			} else {
				return Collections.emptyList();
			}
		} else {
			return studentReosatry.findAll();
		}
	}

	public Boolean addStudents(List<Student> students) {
		if (!students.isEmpty()) {
			studentReosatry.saveAll(students);
			return true;
		}
		return false;
	}

	public Boolean deleteStudent(Integer rollNo) {
		Optional<Student> isStudentPresent = studentReosatry.findById(rollNo);
		if (isStudentPresent.isPresent()) {
			studentReosatry.deleteById(rollNo);
			return true;
		} else {
			return false;
		}

	}
	public Student updateStudent(Integer rollNo,Student updatedStudent) {
		Optional<Student> isStudentPresent = studentReosatry.findById(rollNo);
		if(isStudentPresent.isPresent()) {
			Student existingStudent=isStudentPresent.get();
			existingStudent.setName(updatedStudent.getName());
			existingStudent.setClassname(updatedStudent.getClassname());
			existingStudent.setStream(updatedStudent.getStream());
			return studentReosatry.save(existingStudent);
			
		}else {
			 throw new NoSuchElementException("Student with roll number " + rollNo + " not found");	
}
		
	}
}
