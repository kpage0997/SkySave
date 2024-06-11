package com.csc340.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public void createUser(User user) {
        userService.saveUser(user);
    }

    @GetMapping("/username-exists")
    public String usernameExists() {
        return "username-exists";
    }

    @GetMapping("/results")
    public String results() {
        return "results";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contactus";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/")
    public String index() {
        return "HomePage";
    }

    @GetMapping("/HomePage")
    public String homePage() {
        return "HomePage";
    }

    @GetMapping("/home")
    public String home() {
        return "HomePage";
    }
}
