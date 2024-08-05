package compackage.h2databaseproject.Repositery;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import compackage.h2databaseproject.Entity.Student;

@Repository
public interface StudentRepositery extends JpaRepository<Student,Long>  {

	public Optional<Student> findByUsername(String username);

}
