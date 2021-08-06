package mcc53.client.app.controller;

import mcc53.client.app.models.Department;
import mcc53.client.app.models.Employee;
import mcc53.client.app.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("departments", departmentService.getAll());
        return "departments/index";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        model.addAttribute("departments", departmentService.getById(id));
        return "departments/detail-department";
    }

    @GetMapping("/form-department")
    public String getForm(Model model){
        Department department = new Department();
        model.addAttribute("departments",department);
        return "departments/create-dept";
    }

    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute("departments") Department department){
        departmentService.create(department);
        return "redirect:/department";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model){
        Department department = departmentService.getById(id);
        model.addAttribute("departments", department);
        return "/departments/update";
    }

    @PostMapping("update/{id}")
    public String saveForm(@PathVariable("id") Long id,
                           @ModelAttribute("departments") Department department){
        departmentService.update(id, department);
        return "redirect:/department";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id){
        departmentService.delete(id);
        return "redirect:/department";
    }
}