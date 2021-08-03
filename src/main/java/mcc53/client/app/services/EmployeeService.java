package mcc53.client.app.services;

import mcc53.client.app.models.Employee;
import mcc53.client.app.models.EmployeeCreate;
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

    @Value("${api.baseUrl}/user")
    private String baseUrl2;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Employee> getAll() {
        ResponseEntity<List<Employee>> res = restTemplate
                .exchange(baseUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Employee>>() {
                        });
//        System.out.println(res.getBody());
        return res.getBody();
    }

    public Employee getById(Long id){
        ResponseEntity<Employee> res = restTemplate
                .getForEntity(baseUrl+"/"+id, Employee.class);
        return res.getBody();
    }

    public EmployeeCreate create(EmployeeCreate employeeCreate) {
        ResponseEntity<EmployeeCreate> res =
                restTemplate.postForEntity(baseUrl2 + "/register", employeeCreate, EmployeeCreate.class);
        System.out.println(res.getBody().toString());
        return res.getBody();
    }

    public String update(Long id, Employee employee){
        restTemplate.put(baseUrl+"/"+id, employee, Employee.class);
        return "update success";
    }

    public String delete(Long id){
        restTemplate.delete(baseUrl+"/"+id, Employee.class);
        return "delete success";
    }
}
