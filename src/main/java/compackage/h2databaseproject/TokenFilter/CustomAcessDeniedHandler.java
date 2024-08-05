package compackage.h2databaseproject.TokenFilter;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class CustomAcessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
	      
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");       
        String errorMessage = "{\"error\": \"Access Denied: You do not have permission to access this resource.\"}";
        response.getWriter().write(errorMessage);
    }

	}

