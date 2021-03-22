package de.joshuaschnabel.anthill.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  // private Environment environment;
  // private AuthenticationProvider basicAuthenticationProvider;
  // private TokenAuthenticationProvider tokenAuthenticationProvider;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    // auth.authenticationProvider(this.basicAuthenticationProvider);
    // auth.authenticationProvider(this.tokenAuthenticationProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("*").permitAll();
    http.csrf().disable();
    // boolean devmode = ArrayUtils.contains(environment.getActiveProfiles(), "dev");
//    //@formatter:off
//    if(!devmode) {
//      http.cors().and()
//      .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//      .and().headers().cacheControl().disable();
//    }
//    http
//      .sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    if(devmode) {
//      http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
//    }
//    http.authorizeRequests().antMatchers("*").permitAll()
//      .and().addFilterBefore(this.authTokenFilter, BasicAuthenticationFilter.class)
//      .authorizeRequests().antMatchers("/api/admin/**", "/api/auth/token").hasAnyRole("ADMIN")       
//      .and().authorizeRequests().antMatchers("/api/auth/login").authenticated().and().httpBasic()     
//      .and().authorizeRequests().antMatchers("/actuator/**").permitAll().anyRequest().authenticated();    
//    // @formatter:on
  }
}
