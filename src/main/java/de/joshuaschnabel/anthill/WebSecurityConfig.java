package de.joshuaschnabel.anthill;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private Environment environment;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //// @formatter:off
    http.authorizeRequests().antMatchers("/", "/home").permitAll()
        .and().formLogin().loginPage("/login").permitAll()
        .and().logout().permitAll();
    if(ArrayUtils.contains(environment.getActiveProfiles(), "dev"))
      http.authorizeRequests().antMatchers("/h2-console/**").permitAll();    
    http.authorizeRequests().antMatchers("/actuator/**").permitAll().anyRequest().authenticated();    
 // @formatter:on
  }

  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    UserDetails user =
        User.withDefaultPasswordEncoder().username("xxx").password("yyy").roles("USER").build();

    return new InMemoryUserDetailsManager(user);
  }
}
