package fr.pizzeria.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BatchPizzaDaoJdbcTemplate {
private JdbcTemplate jdbcTemplate;

@Autowired
public BatchPizzaDaoJdbcTemplate(DataSource dataSource){
	jdbcTemplate = new JdbcTemplate(dataSource);
}
}
