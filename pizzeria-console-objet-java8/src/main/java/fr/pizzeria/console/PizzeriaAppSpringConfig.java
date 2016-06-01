package fr.pizzeria.console;

import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.GenericFactoryImpl;
import fr.pizzeria.dao.pizza.IPizzaDao;

@Configuration
@ComponentScan("fr.pizzeria")
public class PizzeriaAppSpringConfig {
	
	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}
	
	@Bean
	public DaoFactory daoFactory(@Qualifier("pizzaDaoJdbc") IPizzaDao pizzaDao) {
		return new GenericFactoryImpl(pizzaDao, null, null);
	}
	
	@Bean
	public PropertyPlaceholderConfigurer props() {
		PropertyPlaceholderConfigurer co = new PropertyPlaceholderConfigurer();
		co.setLocation(new ClassPathResource("jdbc.properties"));
		return co;
	}
	
	@Bean
	public EntityManagerFactory emf() {
		return Persistence.createEntityManagerFactory("pizzeria-pu");
	}

}
