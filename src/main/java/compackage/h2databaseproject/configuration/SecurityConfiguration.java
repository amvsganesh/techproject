package compackage.h2databaseproject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



import compackage.h2databaseproject.SecurityService.SecurityService;
import compackage.h2databaseproject.Service.StudentService;
import compackage.h2databaseproject.TokenFilter.CustomAcessDeniedHandler;
import compackage.h2databaseproject.TokenFilter.JwtAuthFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	
	private final StudentService studentservice;
	
	private final JwtAuthFilter authFilter;
	
	private  final CustomAcessDeniedHandler deniedhandler;
	
	@Autowired
	 public SecurityConfiguration(@Lazy StudentService studentservice, @Lazy JwtAuthFilter authFilter,
				CustomAcessDeniedHandler deniedhandler) {
			
			this.studentservice = studentservice;
			this.authFilter = authFilter;
			this.deniedhandler = deniedhandler;
		}
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	   http   	   
           .authorizeHttpRequests(request -> request
               .requestMatchers( "/user/save", "/user/jwttoken", "/h2-console/**").permitAll()
               .requestMatchers("/user/getstudent").hasAnyAuthority("ROLE_ADMIN")
               .requestMatchers("/user/getsubject").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
               .requestMatchers("/user/gettokens").hasAnyAuthority("ROLE_ADMIN")
               .anyRequest().authenticated())
           .exceptionHandling(e->e.accessDeniedHandler(deniedhandler)
                              .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
           .sessionManagement()
           .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
           .and()
           .authenticationProvider(authenticationProvider())
           .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
           .csrf(csrf -> csrf.disable()) // Disable CSRF protection for testing
           .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
       return http.build();
    
    }
	
	

	@Bean
	    public AuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(studentservice);
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	      
	        return authenticationProvider;
	    }
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	    	return configuration.getAuthenticationManager();
	    }
}
