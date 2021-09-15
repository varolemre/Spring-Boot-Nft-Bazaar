package com.nftbazaar.demo.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

@Configuration
public class JwtTokenFilter extends OncePerRequestFilter {

    private static List<String> skipFilterUrls = Arrays.asList("/", "/*.html", "/register", "/**/*.html", "/**/*.css", "/**/*.js");

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return skipFilterUrls.stream().anyMatch(url -> new
                AntPathRequestMatcher(url).matches(request));
    }


    private static Logger log = LoggerFactory.getLogger(JwtTokenFilter.class);

    private JwtTokenProvider tokenProvider;

    public JwtTokenFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("JwtTokenFilter : doFilterInternal");
        String token = request.getHeader("Authorization");

        if (token != null) {
            try {
                token = token.substring(7);
                Claims claims = tokenProvider.getClaimsFromToken(token);
                if (!claims.getExpiration().before(new Date())) {
                    Authentication authentication = tokenProvider.getAuthentication(claims.getSubject());
                    if (authentication.isAuthenticated()) {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (RuntimeException e) {
                try {
                    SecurityContextHolder.clearContext();
                    response.setContentType("application/json");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().println(
                            new JSONObject().put("exception", "expired or invalid JWT token " + e.getMessage()));
                } catch (IOException | JSONException e1) {
                    e1.printStackTrace();
                }
                return;
            }
        } else {
            log.info("first time so creating token using UserResourceImpl - authenticate method");
        }
        filterChain.doFilter(request, response);
    }

}
