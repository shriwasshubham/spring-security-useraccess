package com.shubham.springsecurityuseraccess.config;

import com.shubham.springsecurityuseraccess.custom.CustomUsernamePasswordAuthenticationFilter;
import com.shubham.springsecurityuseraccess.custom.dto.JsonAuthenticationResponseWriterUtil;
import com.shubham.springsecurityuseraccess.custom.dto.LoginFailureResponse;
import com.shubham.springsecurityuseraccess.custom.dto.LoginSuccessResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  @Qualifier("customUserDetailsService")
  private UserDetailsService userDetailsService;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests().antMatchers("/", "/login", "/logout", "/h2").permitAll()
        .and()
        .authorizeRequests().antMatchers("/api/**").authenticated();

        http.headers().frameOptions().disable();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");


  }


  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public CustomUsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
    CustomUsernamePasswordAuthenticationFilter authFilter = new CustomUsernamePasswordAuthenticationFilter();
    authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    authFilter.setAuthenticationManager(authenticationManagerBean());
    authFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {

      @Override
      public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
          Authentication authentication) throws IOException, ServletException {

        log.info(
            "############ Inside SecurityConfiguration#onAuthenticationSuccess method ########");

        if (authentication.isAuthenticated()) {

          LoginSuccessResponse loginSuccessResponse = new LoginSuccessResponse();

          Object principal = authentication.getPrincipal();
          // get the details from authentication and put into loginResponse.

          if (principal instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User securityUserDetails = (org.springframework.security.core.userdetails.User) principal;

            loginSuccessResponse.setUsername(securityUserDetails.getUsername());
            try {
              response.setContentType("application/json");
              response.setStatus(HttpServletResponse.SC_OK);
              JsonAuthenticationResponseWriterUtil.writeJsonModelToHttpServletResponse(response,
                  loginSuccessResponse);
            } catch (AuthenticationServiceException e) {
              request.getSession().invalidate();
              response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
          }
        } else {
          throw new UnsupportedOperationException("Invalid Login");
        }

      }
    });
    authFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {

      @Override
      public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
          AuthenticationException exception) throws IOException, ServletException {

        log.info(
            "############ Inside SecurityConfiguration#onAuthenticationFailure method ########");

        LoginFailureResponse loginFailureResponse = new LoginFailureResponse();
        if (exception instanceof BadCredentialsException) {
          loginFailureResponse.setMessage(exception.getMessage());
        } else if (exception instanceof DisabledException) {
          loginFailureResponse.setMessage(exception.getMessage());
        } else if (exception instanceof AuthenticationServiceException) {
          loginFailureResponse.setMessage(exception.getMessage());
        }
        JsonAuthenticationResponseWriterUtil.writeJsonModelToHttpServletResponse(response,
            loginFailureResponse);
      }
    });

    return authFilter;
  }

}