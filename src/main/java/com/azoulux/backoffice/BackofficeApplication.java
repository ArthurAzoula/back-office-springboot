package com.azoulux.backoffice;

import com.azoulux.backoffice.repository.EmployeeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackofficeApplication implements CommandLineRunner {

	@Autowired
	private CustomProperties props;

	@Autowired
	private EmployeeProxy employeeProxy;

	public static void main(String[] args) {
		SpringApplication.run(BackofficeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("API URL: " + props.getApiUrl());
		System.out.println("Employees: " + employeeProxy.getEmployees());
	}
}
