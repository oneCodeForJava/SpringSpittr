package spittr.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import spittr.encoder.MD5Encoder;
import spittr.security.SpitterUserService;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("user").password("password").authorities("USER").and()
//		.withUser("admin").password("password").authorities("USER", "ADMIN");
//	}
	
/*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}*/

/*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select username, password, enabled from Spitter where username = ?")
			.authoritiesByUsernameQuery("select username, role from Spitter where username = ?")
			.passwordEncoder(new MD5Encoder());
	}*/
/*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication()
			.userSearchBase("ou=people")
			.userSearchFilter("(uid={0})")
			.groupSearchBase("ou=groups")
			.groupSearchFilter("member={0}")
			.contextSource()
				.root("dc=habuma,dc=com")
				.ldif("classpath:users.ldif");
	}*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new SpitterUserService());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.formLogin().loginPage("/login")
			.and()
			.authorizeRequests()
			.antMatchers("/spitters/me").hasAnyAuthority("ROLE_SPITTER")
			.antMatchers(HttpMethod.GET, "/spittles").hasAnyAuthority("ROLE_SPITTER")
			.anyRequest().permitAll()
			.and()
			.requiresChannel()
				.antMatchers("/spitter/form").requiresSecure()
			.and()
			.rememberMe()
				.tokenValiditySeconds(2419200)
				.key("spittrKey")
			.and()
			.logout()
				.logoutSuccessUrl("/");
		
	}
	
	
}
