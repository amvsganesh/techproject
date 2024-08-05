package compackage.h2databaseproject.SecurityService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import compackage.h2databaseproject.Entity.AuthRequest;
import compackage.h2databaseproject.Entity.Student;
import compackage.h2databaseproject.Entity.Token;
import compackage.h2databaseproject.Repositery.TokenRepositery;
import compackage.h2databaseproject.Service.StudentService;


@Service
public class SecurityService  {
	
	
	@Autowired
	private AuthenticationManager  authenticationManager;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private StudentService studentservice;
	@Autowired
	private  TokenRepositery  tokenservice; 
	
	

	
	public ResponseEntity<String> authenticateAndGenerateToken(AuthRequest authRequest) {
	String token1;
	try {
	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	setTheTokensToTrue(authRequest.getUsername());
	if(authentication.isAuthenticated()&& authRequest.getUsername()!=null) {
		 token1= jwtService.generateToken(authRequest.getUsername());
		 Token savedthetoken=savetoken(token1,authRequest.getUsername());
		 return ResponseEntity.status(HttpStatus.OK).body(token1);
		}else {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("please provide correct USERNAME and PASSWORD");
	}}
	catch (AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("please provide correct USERNAME and PASSWORD");
    } catch (Exception e) {
    	   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("please provide correct USERNAME and PASSWORD");
    }
	
}





	private void setTheTokensToTrue(String username) {
		List<Token> retreivedAllToken=tokenservice.findAllTokensByUsername(username);
		if(!retreivedAllToken.isEmpty()) {
			retreivedAllToken.forEach(t->t.setIsloggedout(true));
		}
		tokenservice.saveAll(retreivedAllToken);
	
	}





	private Token savetoken(String token1, String username) {
	Token savetoken = new  Token();
	savetoken.setTokenstring(token1);
	savetoken.setTokenusername(username);
	savetoken.setIsloggedout(false);
	 return tokenservice.save(savetoken);		
	}

	

	
}
