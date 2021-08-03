package mcc53.client.app.controller;

import mcc53.client.app.models.Employee;
import mcc53.client.app.models.EmployeeCreate;
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
        model.addAttribute("employees", employeeService.getById(id));
        return "home/employees/get-by-id";
    }

    @GetMapping("/add")
    public String getForm(Model model){
        EmployeeCreate employeeCreate = new EmployeeCreate();
        model.addAttribute("employee", employeeCreate);
        return "home/employees/create-emp";
    }

    @PostMapping("/save")
    public String saveEmployee(EmployeeCreate employeeCreate){
        employeeService.create(employeeCreate);
        return "redirect:/employees";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model){
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        return "/home/employees/update";
    }

    @PostMapping("update/{id}")
    public String saveForm(@PathVariable("id") Long id, @ModelAttribute("employee") Employee employee){
        employeeService.update(id, employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id){
        employeeService.delete(id);
        return "redirect:/employees";
    }
}
