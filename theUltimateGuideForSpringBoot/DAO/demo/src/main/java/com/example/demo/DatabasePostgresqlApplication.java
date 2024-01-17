package com.example.demo;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication

public class DatabasePostgresqlApplication  {

	public static void main(String[] args) {

		SpringApplication.run(DatabasePostgresqlApplication.class, args);
	}




}


