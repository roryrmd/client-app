package mcc53.client.app.controller;

import mcc53.client.app.models.Employee;
import mcc53.client.app.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("employees", employeeService.getAll());
        return "employee/index";
    }

    @GetMapping("/create")
    public String formEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee/form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.create(employee);
        return "redirect:/employee";
    }
}
