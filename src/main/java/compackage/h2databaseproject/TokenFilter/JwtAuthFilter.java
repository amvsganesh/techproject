package compackage.h2databaseproject.TokenFilter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import compackage.h2databaseproject.SecurityService.JwtService;
import compackage.h2databaseproject.SecurityService.SecurityService;
import compackage.h2databaseproject.Service.StudentService;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	
	
	private final JwtService jwtService;
	private final  StudentService userDetailsService;
	
	@Autowired
	public JwtAuthFilter( @Lazy JwtService jwtService,@Lazy StudentService userDetailsService) {
		super();
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = null;
		String username = null;
		String header = request.getHeader("Authorization");
		
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7); 
            try {
                username = jwtService.extractUsername(token);
            } catch (SignatureException e) {              
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT signature.");
                return;
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token.");
                return;
            }
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    userToken.setDetails(new WebAuthenticationDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(userToken);
                }
            } catch (UsernameNotFoundException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found.");
                return;
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token.");
                return;
            }
        }

        filterChain.doFilter(request, response); // Continue with the filter chain
    }
}


