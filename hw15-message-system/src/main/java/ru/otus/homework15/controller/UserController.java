package ru.otus.homework15.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.homework15.front.FrontendService;
import ru.otus.homework15.model.User;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final FrontendService frontendService;

    public UserController(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

//    @GetMapping("/")
//    public String index() {
//        return "index.html";
//    }

    @GetMapping("/user/list")
    public String userListView(Model model) {
        //? получаю коллекцию юзеров, но на хтлм коллекция не отображается
        frontendService.getAllUsers(users -> model.addAttribute("users", users));
        return "userList.html";
    }

    @GetMapping({"/", "/user/create"})
    public String userCreateView(Model model) {
        model.addAttribute("user", new User());
        return "userCreate.html";
    }

    @PostMapping("/user/save")
    public RedirectView saveUser(@ModelAttribute User user) {
        frontendService.saveUser(user, data -> logger.info("user was create{}", data));
        return new RedirectView("/user/list", true);
    }
}
