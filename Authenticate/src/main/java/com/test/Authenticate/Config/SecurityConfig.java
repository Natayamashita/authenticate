package com.test.Authenticate.Config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.test.Authenticate.Services.CustomUserDetailsService;
		
@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests()
//                .anyRequest().authenticated();
//        http
//                .formLogin();
//
//        return http.build();
//    }

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	    http
//		    .authorizeHttpRequests()
//            .mvcMatchers("/login").permitAll()
//	        .anyRequest().authenticated();
//	    http
//	            .formLogin()
//	            .loginPage("/login");
//
//	    return httpSecurity
//	            .csrf(csrf -> csrf.disable())
//	            .authorizeHttpRequests(auth -> auth
//	                .requestMatchers("/token/**").permitAll()
//	                .anyRequest().authenticated()
//	            )
//	}
	private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, NoOpPasswordEncoder noOpPasswordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(noOpPasswordEncoder);
        return authenticationManagerBuilder.build();
    }
    
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	            .authorizeHttpRequests()
	            .requestMatchers(new AntPathRequestMatcher("/login"), new AntPathRequestMatcher("/register")).permitAll()
	            .anyRequest().authenticated();
	    http
		    .formLogin(formLogin ->
		    formLogin
	        .loginPage("/login")
	        .defaultSuccessUrl("/home", true)	 
	);
	    
	    return http.build();
	}
	
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http.authorizeHttpRequests().requestMatchers("/**").hasRole("USER").and().formLogin();
//            return http.build();
//    }
	
    @SuppressWarnings("deprecation")
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}
