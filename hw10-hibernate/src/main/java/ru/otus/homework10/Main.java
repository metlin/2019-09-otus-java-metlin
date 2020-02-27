package ru.otus.homework10;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.otus.homework10.dao.UserDao;
import ru.otus.homework10.dao.UserDaoImpl;
import ru.otus.homework10.hibernate.HibernateUtils;
import ru.otus.homework10.hibernate.sessionmanager.SessionManagerHibernate;
import ru.otus.homework10.model.AddressDataSet;
import ru.otus.homework10.model.PhoneDataSet;
import ru.otus.homework10.model.User;
import ru.otus.homework10.service.UserService;
import ru.otus.homework10.service.UserServiceImpl;
import ru.otus.homework10.sessionmanager.SessionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", User.class, AddressDataSet.class, PhoneDataSet.class);
        SessionManager sessionManager = new SessionManagerHibernate(sessionFactory);

        UserDao userDao = new UserDaoImpl(sessionManager);
        UserService userService = new UserServiceImpl(userDao);
        User user = new User(1, "Ivan", 3);
        user.setAddress(new AddressDataSet("Lenina street"));

        user.setPhoneNumber(List.of(new PhoneDataSet("12345"), new PhoneDataSet("234")));

        userService.saveUser(user);
        Optional userOptional = userService.getTemplate(1, User.class);
        System.out.println(userOptional);

        user.setName("Andrey");
        user.setAddress(new AddressDataSet("Gagarina street"));
        userService.updateTemplate(user);
        Optional userOptionalUpdate = userService.getTemplate(1, User.class);
        System.out.println(userOptionalUpdate);
    }
}
