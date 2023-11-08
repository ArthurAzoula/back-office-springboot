package com.azoulux.backoffice.repository;

import com.azoulux.backoffice.CustomProperties;
import com.azoulux.backoffice.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class EmployeeProxy {

    @Autowired
    private CustomProperties props;

    public Iterable<Employee> getEmployees() {
        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                org.springframework.http.HttpMethod.GET,
                null,
                new org.springframework.core.ParameterizedTypeReference<Iterable<Employee>>() {}
                );
        log.debug("Get Employees call " + response.getStatusCode().toString());

        return response.getBody();

    }

    public Employee createEmployee(Employee e) {
    String baseApiUrl = props.getApiUrl();
    String createEmployeeUrl = baseApiUrl + "/employee";

    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<Employee> request = new HttpEntity<Employee>(e);
    ResponseEntity<Employee> response = restTemplate.exchange(
        createEmployeeUrl,
        HttpMethod.POST,
        request,
        Employee.class);

    log.debug("Create Employee call " + response.getStatusCode().toString());

    return response.getBody();
}

    public Employee updateEmployee(Employee employee) {
        String baseApiUrl = props.getApiUrl();
        String updateEmployeeUrl = baseApiUrl + "/employees/" + employee.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<Employee>(employee);
        ResponseEntity<Employee> response = restTemplate.exchange(
                updateEmployeeUrl,
                HttpMethod.PUT,
                request,
                Employee.class);

        log.debug("Update Employee call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public void deleteEmployee(int id) {
        String baseApiUrl = props.getApiUrl();
        String deleteEmployeeUrl = baseApiUrl + "/employees/" + id;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(deleteEmployeeUrl);

        log.debug("Delete Employee call");
    }

    public Employee getEmployee(int id) {
        String baseApiUrl = props.getApiUrl();
        String getEmployeeUrl = baseApiUrl + "/employees/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
                getEmployeeUrl,
                org.springframework.http.HttpMethod.GET,
                null,
                Employee.class
        );
        log.debug("Get Employee call " + response.getStatusCode().toString());

        return response.getBody();
    }
}
