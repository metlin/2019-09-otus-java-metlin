package ru.otus.webserver.servlet;

import ru.otus.homework11.model.User;
import ru.otus.homework11.service.UserService;
import ru.otus.webserver.services.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

public class CreateUserServlet extends HttpServlet {

    private static final String CREATE_USER_PAGE = "createuser.html";

    private final UserService userService;
    private final TemplateProcessor templateProcessor;

    public CreateUserServlet(TemplateProcessor templateProcessor, UserService userService) {
        this.templateProcessor = templateProcessor;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(CREATE_USER_PAGE, Collections.emptyMap()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (name.isEmpty() || login.isEmpty() || password.isEmpty()) {
            throw new RuntimeException();
        }

        userService.saveUser(new User(name, login, password));

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("User: " + name + " created");
    }

}
