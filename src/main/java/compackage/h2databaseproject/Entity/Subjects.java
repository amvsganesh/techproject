package compackage.h2databaseproject.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

public class Subjects {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
	 @Column(name="subject_id")
	private Long subjectid;
	
	
	@Column(name="subject_name") 
	private String subjectname;

	public Long getId() {
		return subjectid;
	}

	public void setId(Long id) {
		this.subjectid = id;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public Subjects(Long id, String subjectname) {
		super();
		this.subjectid = id;
		this.subjectname = subjectname;
	}

	public Subjects() {
		super();
	}
	
	
}
