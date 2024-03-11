package com.studentRecord.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentRecord.Entity.Student;
import com.studentRecord.Service.StudentService;

@RestController
@RequestMapping("/student")
public class HomeController {
	@Autowired
	private StudentService studentService;

	@GetMapping
	public List<Student> getStudents(@RequestParam(required = false) Integer id) {
		return studentService.getAllStudents(id);

	}

	@PostMapping
	public ResponseEntity<String> addStudents(@RequestBody(required = true) List<Student> student) {
		String responseMessage;
		if (studentService.addStudents(student)) {
			responseMessage = "Students addead successfully";
			return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
		}
		responseMessage = "Students data not addead";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);

	}

	@DeleteMapping
	public ResponseEntity<String> deleteStudent(@RequestParam(required = true) Integer rollNo) {
		String responseMessage;
		if (studentService.deleteStudent(rollNo)) {
			responseMessage = "Student data deleted successfully";
			return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
		} else {
			responseMessage = "Student data is not prsent";
			return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
		}
	}

	@PutMapping
	public Student updateById(@RequestParam Integer rollNo, @RequestBody Student updatedStudent) {
		return studentService.updateStudent(rollNo, updatedStudent);
	}

}
