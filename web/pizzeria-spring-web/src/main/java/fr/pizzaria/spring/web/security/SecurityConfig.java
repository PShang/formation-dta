package fr.pizzaria.spring.web.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication()

				.withUser("bob")

				.password("secret")

				.roles("USER");

	}

}