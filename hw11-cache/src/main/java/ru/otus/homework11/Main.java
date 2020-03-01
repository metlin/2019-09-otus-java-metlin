package ru.otus.homework11;

import org.hibernate.SessionFactory;
import ru.otus.homework11.cachehw.HWCacheDemo;
import ru.otus.homework11.cachehw.HwCache;
import ru.otus.homework11.cachehw.MyCache;
import ru.otus.homework11.dao.UserDao;
import ru.otus.homework11.dao.UserDaoImpl;
import ru.otus.homework11.hibernate.HibernateUtils;
import ru.otus.homework11.hibernate.sessionmanager.SessionManagerHibernate;
import ru.otus.homework11.model.AddressDataSet;
import ru.otus.homework11.model.PhoneDataSet;
import ru.otus.homework11.model.User;
import ru.otus.homework11.service.UserService;
import ru.otus.homework11.service.UserServiceImpl;
import ru.otus.homework11.sessionmanager.SessionManager;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        long time = System.nanoTime();

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", User.class, AddressDataSet.class, PhoneDataSet.class);
        SessionManager sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoImpl(sessionManager);
        HwCache<Long, User> cache = new MyCache<>();
        UserService userService = new UserServiceImpl(userDao, cache);

        for (int i = 0; i < 520; i++) {
            User user = new User("Ivan", 3);
            user.setAddress(new AddressDataSet("Lenina street"));
            user.setPhoneNumber(List.of(new PhoneDataSet("12345"), new PhoneDataSet("234")));

            long userId = userService.saveUser(user);
            Optional userOptional = userService.getTemplate(userId);
            System.out.println(userOptional);

            user.setName("Andrey");
            user.setAddress(new AddressDataSet("Gagarina street"));
            long userIdUpdate = userService.updateTemplate(user);
            Optional userOptionalUpdate = userService.getTemplate(userIdUpdate);
            System.out.println(userOptionalUpdate);
        }

        time = System.nanoTime() - time;
        System.out.printf("Elapsed %,9.3f ms\n", time/1_000_000.0);
    }
}
