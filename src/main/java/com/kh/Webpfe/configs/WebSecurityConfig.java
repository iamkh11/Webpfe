package com.kh.Webpfe.configs;

import com.kh.Webpfe.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author didin
 */
@Configuration
@EnableWebSecurity
@ComponentScan("com.kh.Webpfe.configs")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Bean
    public UserDetailsService mongoUserDetails() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = mongoUserDetails();
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/contact/**").permitAll()
                .antMatchers("/manchester-united/**").permitAll()
                .antMatchers("/news-football/**").permitAll()
                .antMatchers("/news-handball/**").permitAll()
                .antMatchers("/players-football/**").permitAll()
                .antMatchers("/players-handball/**").permitAll()
                .antMatchers("/staff-football/**").permitAll()
                .antMatchers("/staff-handball/**").permitAll()
                .antMatchers("/fixtures-foot/**").permitAll()
                .antMatchers("/fixtures-hand/**").permitAll()
                
                
                
                
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                
                
              
                
                .antMatchers("/dashboard/**").hasAuthority("ADMIN").anyRequest()
                
                .authenticated().and().csrf().disable().formLogin().successHandler(customizeAuthenticationSuccessHandler)
                .loginPage("/login").failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and().exceptionHandling();
        
        
       
        
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/app-assets/**","/asset/**", "/static/**", "/css/**", "/js/**", "/images/**, /**/favicon.ico ");
    }

}
