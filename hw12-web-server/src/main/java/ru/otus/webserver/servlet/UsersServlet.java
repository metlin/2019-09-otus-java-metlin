package ru.otus.webserver.servlet;

import ru.otus.homework11.model.User;
import ru.otus.homework11.service.UserService;
import ru.otus.webserver.services.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersServlet extends HttpServlet {
    private static final String USERS_PAGE_TEMPLATE = "users.html";
    private static final String GET_ALL_USERS = "allUsers";

    private final UserService userService;
    private final TemplateProcessor templateProcessor;

    public UsersServlet(TemplateProcessor templateProcessor, UserService userService) {
        this.templateProcessor = templateProcessor;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();
        List<User> allUsers = userService.getAllUsers();
        paramsMap.put(GET_ALL_USERS ,allUsers);
        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(USERS_PAGE_TEMPLATE, paramsMap));
    }
}
