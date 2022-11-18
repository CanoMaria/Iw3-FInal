package iua.edu.ar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import iua.edu.ar.auth.PersistenceUserDetailService;
import iua.edu.ar.authentication.CustomTokenAuthenticationFilter;
import iua.edu.ar.model.account.IUserBusiness;
import iua.edu.ar.security.IAuthTokenBusiness;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private IAuthTokenBusiness authTokenBusiness;

	@Autowired
	private IUserBusiness userBusiness;

	@Autowired
	private UserDetailsService userDetailService;

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
		http.csrf().disable().authorizeRequests()

				.antMatchers("/v2/api-docs").permitAll()
				.antMatchers("/configuration/ui").permitAll()
				.antMatchers("/swagger-resources/**").permitAll()
				.antMatchers("/configuration/security").permitAll()
				.antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/swagger-ui/*").permitAll()
				.antMatchers("/webjars/**").permitAll()
				// .antMatchers(HttpMethod.POST, "/login*").permitAll()
				.antMatchers("/index.html").permitAll()
				.antMatchers("/favicon.png").permitAll().antMatchers("/ui/**")
				.permitAll().antMatchers("/").permitAll()
				// .antMatchers("/productos*").permitAll();
				// //.hasRole("ADMIN");
				.antMatchers("/test*").hasAnyRole("ADMIN", "USER");
				//.anyRequest().authenticated();

//		http.addFilterAfter(new CustomTokenAuthenticationFilter(authTokenBusiness, userBusiness),
//				UsernamePasswordAuthenticationFilter.class);
//
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

}
