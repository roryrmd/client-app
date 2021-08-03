package mcc53.client.app.controller;

import mcc53.client.app.models.Employee;
import mcc53.client.app.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("employees", employeeService.getAll());
        return "home/employees/index";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        model.addAttribute("employee", employeeService.getById(id));
        return "home/employees/get-by-id";
    }

    @GetMapping("/add")
    public String getForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "home/employees/create-emp";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.create(employee);
        return "redirect:/employees";
    }
}
