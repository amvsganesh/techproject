package compackage.h2databaseproject.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import compackage.h2databaseproject.Entity.Student;
import compackage.h2databaseproject.Entity.Subjects;
@Repository
public interface SubjectRepositery extends JpaRepository<Subjects,Long> {

}
