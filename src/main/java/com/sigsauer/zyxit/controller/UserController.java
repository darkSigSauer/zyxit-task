package com.sigsauer.zyxit.controller;

import com.sigsauer.zyxit.domain.User;
import com.sigsauer.zyxit.repository.UserRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/")
    public String home(@RequestParam(required = false, defaultValue = "") Long r, Model model) {
        if(r != null) {
            userRepository.delete(userRepository.getOne(r));
            return "redirect:/";
        }


            model.addAttribute("users", userRepository.findAll());
            return "home";
    }

    @PostMapping("/")
    public String add(@RequestParam @NotNull String username, @RequestParam @NotNull String password, Model model) {
        if(username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userRepository.save(user);
        }
        model.addAttribute("users", userRepository.findAll());
        return "home";
    }

}
