package com.example.demo.student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
	private static List<Student> STUDENTS = Arrays.asList(
			new Student(1, "James Bond"),
			new Student(2, "Maria Jones"),
			new Student(3, "Anna Smith")
			);
	
	@GetMapping
	public List<Student> getAllStudents(){
		return STUDENTS;
	}
	
	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		List<Student> copyStudents = new ArrayList<>(STUDENTS);
		copyStudents.add(student);
		STUDENTS = copyStudents;
		System.out.println(student);
	}
	
	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(@PathVariable("studentId") Integer studentId) {
		List<Student> copyStudents = new ArrayList<>(STUDENTS);
		copyStudents.removeIf(student -> student.getStudentId() == studentId);
		STUDENTS = copyStudents;
		System.out.println(studentId);
	}
	
	@PutMapping(path = "{studentId}")
	public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
		List<Student> copyStudents = new ArrayList<>(STUDENTS);
		copyStudents.removeIf(st -> st.getStudentId() == studentId);
		copyStudents.add(student);
		STUDENTS = copyStudents;
		System.out.println(String.format("%s %s", studentId, student));
	}
}
