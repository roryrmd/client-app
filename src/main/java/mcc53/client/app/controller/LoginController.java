package mcc53.client.app.controller;

import mcc53.client.app.models.AuthResponse;
import mcc53.client.app.models.Login;
import mcc53.client.app.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String indexLogin() {
//        model.addAttribute("login", new Login());
        return "/login/index";
    }

    @PostMapping
    public @ResponseBody AuthResponse postLogin(@RequestBody Login login) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return loginService.loginRequest(login);
    }
}
