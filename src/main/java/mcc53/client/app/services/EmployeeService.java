package mcc53.client.app.services;

import mcc53.client.app.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeeService {
    private RestTemplate restTemplate;

    @Value("${api.baseUrl}/employee")
    private String baseUrl;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Employee> getAll() {
        ResponseEntity<List<Employee>> res = restTemplate
                .exchange(baseUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Employee>>() {
                        });
        return res.getBody();
    }

    public Employee getById(Long id){
        ResponseEntity<Employee> res = restTemplate.getForEntity(baseUrl+"/"+id, Employee.class);

        return res.getBody();
    }

    public Employee create(Employee employee) {
        ResponseEntity<Employee> res =
                restTemplate.postForEntity(baseUrl, employee, Employee.class);
        return res.getBody();
    }
}
