package mcc53.client.app.controller;

import mcc53.client.app.models.Department;
import mcc53.client.app.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    public String getAll(Model model) {
//        model.addAttribute("datas", departmentService.getAll());
        return "/departments/index";
    }

    @GetMapping("/get-all")
    public @ResponseBody List<Department> getAll() {
        return departmentService.getAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Department getById(@PathVariable("id") Long id){
        return departmentService.getById(id);
    }

    @PostMapping("/save")
    public @ResponseBody Department saveDepartment(@RequestBody Department department){
        return departmentService.create(department);
    }

    @PutMapping("/{id}")
    public @ResponseBody String update(@PathVariable("id") Long id, @RequestBody Department department) {
        return departmentService.update(id,department);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String delete(@PathVariable("id") Long id) {
        return departmentService.delete(id);
    }

    @PostMapping("/logout")
    public @ResponseBody String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/logout";
    }

}

//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable("id") Long id){
//        departmentService.delete(id);
//        return "redirect:/department";
//    }

//
//    @GetMapping("/{id}")
//    public @ResponseBody Department getById(@PathVariable("id") Long id) {
//        return departmentService.getById(id).getPayLoad();
//    }

//    @GetMapping
//    public String getAll(Model model) {
//        model.addAttribute("departments", departmentService.getAll());
//        return "departments/index";
//    }

//    @GetMapping("/{id}")
//    public String getById(@PathVariable("id") Long id, Model model){
//        model.addAttribute("departments", departmentService.getById(id));
//        return "departments/detail-department";
//    }

//    @GetMapping("/form-department")
//    public String getForm(Model model){
//        Department department = new Department();
//        model.addAttribute("departments",department);
//        return "departments/create-dept";
//    }

//    @GetMapping("/update/{id}")
//    public String updateForm(@PathVariable("id") Long id, Model model){
//        Department department = departmentService.getById(id);
//        model.addAttribute("departments", department);
//        return "/departments/update";
//    }
//
//    @PostMapping("update/{id}")
//    public String saveForm(@PathVariable("id") Long id,
//                           @ModelAttribute("departments") Department department){
//        departmentService.update(id, department);
//        return "redirect:/department";
//    }
