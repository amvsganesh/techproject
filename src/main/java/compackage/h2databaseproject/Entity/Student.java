package compackage.h2databaseproject.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Student {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="student_id")
	private Long studentid;
	
	@Column(name="Student_name",nullable=false,unique=true) 
	private String studentname;
	
	@Column(name="user_name",nullable=false,unique=true)
	  
	private String username;
	
	@Column(name="pass_word",nullable=false,unique=true)
	private String password;
	
	@Column(name="add_ress")
	private String address;
	
	@Column(name="roles_details")
	private String roles;
	
	
	@OneToMany(targetEntity=Subjects.class,cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true)
	@JoinColumn(referencedColumnName="student_id",name="student_subject")
	 private List<Subjects> subjects;


	public Long getStudentid() {
		return studentid;
	}


	public void setStudentid(Long studentid) {
		this.studentid = studentid;
	}


	public String getStudentname() {
		return studentname;
	}


	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getRoles() {
		return roles;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	}


	public List<Subjects> getSubjects() {
		return subjects;
	}


	public void setSubjects(List<Subjects> subjects) {
		this.subjects = subjects;
	}


	public Student(Long studentid, String studentname, String username, String password, String address,
			String roles, List<Subjects> subjects) {
		super();
		this.studentid = studentid;
		this.studentname = studentname;
		this.username = username;
		this.password = password;
		this.address = address;
		this.roles = roles;
		this.subjects = subjects;
	}


	public Student() {
		super();
	}
	
	
	
}
