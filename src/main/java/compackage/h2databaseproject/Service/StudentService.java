package compackage.h2databaseproject.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import compackage.h2databaseproject.Entity.Student;
import compackage.h2databaseproject.Repositery.StudentRepositery;
import compackage.h2databaseproject.configuration.SecurityConfiguration;

@Service
public class StudentService implements UserDetailsService {

   private final StudentRepositery studentRepositery;
	private final PasswordEncoder pass;
	@Autowired
	public StudentService(@Lazy StudentRepositery studentRepositery, @Lazy PasswordEncoder pass) {
		super();
		this.studentRepositery = studentRepositery;
		this.pass = pass;
	}
	
	public ResponseEntity<String> saveStudent(Student student) {
		 try {
		student.setPassword(pass.encode(student.getPassword()));	
		Student ss=studentRepositery.save(student);
		String message ="The given details already exists of studentname : "+student.getStudentname()+", username : "+student.getUsername()+",  password also should be unique \n"+"TRY USING DIFFRENT CREDENTIALS";
		if(ss!=null) {
		return ResponseEntity.status(HttpStatus.OK).body("The given details registered succesfully for studentname : "+student.getStudentname());
		}else
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
		 }catch (DataIntegrityViolationException e) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("The given details already exists of studentname : "+student.getStudentname()+", username : "+student.getUsername()+",  password also should be unique \n"+"TRY USING DIFFRENT CREDENTIALS");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The given details already exists of studentname : "+student.getStudentname()+", username : "+student.getUsername()+",  password also should be unique \n"+"TRY USING DIFFRENT CREDENTIALS");
	        }
	}

	public List<Student> getall() {
		
		return studentRepositery.findAll();
	}

	public Optional<Student> findByName(String username) {
		// TODO Auto-generated method stub
		return studentRepositery.findByUsername(username);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Student> opt = findByName(username);

		
		if(opt.isEmpty()) {
			throw new UsernameNotFoundException("User with email: " +username +" not found");
		}
		Student user =opt.get();	
			   List<SimpleGrantedAuthority> authorities = Arrays.stream(user.getRoles().split(","))
		                .map(role -> "ROLE_" + role.trim().toUpperCase()) // Ensure correct prefix
		                .map(SimpleGrantedAuthority::new)
		                .collect(Collectors.toList());

		        User abcd= new org.springframework.security.core.userdetails.User(
		                username,
		                user.getPassword(),
		                authorities);
		    
		        return abcd;
		    
		
	}
	
}
