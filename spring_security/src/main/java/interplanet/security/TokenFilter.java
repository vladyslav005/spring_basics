package interplanet.security;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtCore jwtCore;

    @Autowired
    private UserDetailsService userDetailsService;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = null;
        String username = null;
        UserDetails userDetails = null;
        UsernamePasswordAuthenticationToken authentication = null;

        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                jwt = authHeader.substring(7);
                System.out.println("token " + jwt);

                if (jwt != null) {
                    try  {
                        username = jwtCore.getNameFromJwtToken(jwt);
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.setStatus(403);
                    }

                    if (username != null) {
                        userDetails = userDetailsService.loadUserByUsername(username);
                    }

                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        userDetails = userDetailsService.loadUserByUsername(username);
                        authentication = ( new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword()));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }


        } catch (Exception e) {
            response.setStatus(403);
        }

        filterChain.doFilter(request, response);
    }
}
