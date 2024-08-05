package compackage.h2databaseproject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import compackage.h2databaseproject.Entity.Student;
import compackage.h2databaseproject.Entity.Subjects;
import compackage.h2databaseproject.Entity.Token;
import compackage.h2databaseproject.Repositery.SubjectRepositery;
import compackage.h2databaseproject.Repositery.TokenRepositery;

@Service
public class SubjectService {
	 @Autowired
	 private SubjectRepositery subjectrepositery;
	 @Autowired
	 private TokenRepositery tokenrepositery;
	public List<Subjects> getallSubjects() {
		// TODO Auto-generated method stub
		return subjectrepositery.findAll();
	}

	public List<Token> getalltokens() {
		// TODO Auto-generated method stub
		return tokenrepositery.findAll();
	}

}
