package com.ayman.databaseexamplepostgresql;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@Log
public class DatabaseExamplePostgresqlApplication implements CommandLineRunner {

	private final DataSource dataSource;
	public DatabaseExamplePostgresqlApplication(final DataSource dataSource){
		this.dataSource=dataSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(DatabaseExamplePostgresqlApplication.class, args);
	}

	@Override
	public void run(final String... args){
		log.info("dataSource: "+ dataSource.toString());
		final JdbcTemplate restTemplate = new JdbcTemplate(dataSource);
		restTemplate.execute("select 1");
	}

}
