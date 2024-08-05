package compackage.h2databaseproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import compackage.h2databaseproject.Entity.AuthRequest;
import compackage.h2databaseproject.Entity.Student;
import compackage.h2databaseproject.Entity.Subjects;
import compackage.h2databaseproject.Entity.Token;
import compackage.h2databaseproject.SecurityService.SecurityService;
import compackage.h2databaseproject.Service.StudentService;
import compackage.h2databaseproject.Service.SubjectService;

@RestController
@RequestMapping("/user")

public class StudentController {
	@Autowired
	private StudentService studentservice;
	@Autowired
	private SubjectService subjectservice;
	@Autowired
	private SecurityService   securityservice;
	
	@GetMapping("/getstudent")
	public List<Student> user() {
		return studentservice.getall();
	}
	 
		@GetMapping("/getsubject")
		public List<Subjects> getsubject() {
			return subjectservice.getallSubjects();
		}
		@GetMapping("/gettokens")
		public List<Token> getokens() {
			return subjectservice.getalltokens();
		}
		
	
	@PostMapping("/save")
	public  ResponseEntity<String> saveStudent(@RequestBody Student student) {
	
	return studentservice.saveStudent(student);
	}
	

    @PostMapping("/jwttoken")
	public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) {
    	return securityservice.authenticateAndGenerateToken(authRequest);
		
    }

}
