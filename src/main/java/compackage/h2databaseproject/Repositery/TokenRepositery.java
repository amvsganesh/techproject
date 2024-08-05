package compackage.h2databaseproject.Repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import compackage.h2databaseproject.Entity.Subjects;
import compackage.h2databaseproject.Entity.Token;

@Repository
public interface TokenRepositery extends JpaRepository<Token,Long>{

	@Query("SELECT t FROM Token t WHERE t.tokenusername = :username AND t.isloggedout = false")
	public List<Token> findAllTokensByUsername(String username);

	public Token findByTokenstring(String token);

	


	

}
