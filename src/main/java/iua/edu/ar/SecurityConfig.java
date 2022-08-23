package iua.edu.ar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import iua.edu.ar.auth.PersistenceUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private PersistenceUserDetailService persistenceUserDetailService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(persistenceUserDetailService);
//		auth.inMemoryAuthentication()
//			.withUser("user").password(passwordEncoder().encode("123")).roles("USER")
//			.and()
//			.withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/anonymous*").anonymous()
			.antMatchers("/login*").permitAll()
			.antMatchers("/logout*").permitAll()
			.anyRequest().authenticated()
			.and()
			//httpBasic();
			.formLogin()
			.loginPage("/login.html")
			.loginProcessingUrl("/dologin")
			.defaultSuccessUrl("/index.html")
			.failureUrl("/login.html?error=true")
			.and()
			.logout()
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/login.html");
			
	}

}
