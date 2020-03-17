package ru.otus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.hibernate.SessionFactory;
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
import ru.otus.webserver.helpers.FileSystemHelper;
import ru.otus.webserver.server.UsersWebServer;
import ru.otus.webserver.server.UsersWebServerImpl;
import ru.otus.webserver.services.TemplateProcessor;
import ru.otus.webserver.services.TemplateProcessorImpl;
import ru.otus.webserver.services.UserAuthService;
import ru.otus.webserver.services.UserAuthServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.otus.webserver.server.SecurityType.FILTER_BASED;

public class Main {
    private static final int WEB_SERVER_PORT = 8090;
    private static final String TEMPLATES_DIR = "/templates/";
    private static final String HASH_LOGIN_SERVICE_CONFIG_NAME = "realm.properties";
    private static final String REALM_NAME = "AnyRealm";

    public static void main(String[] args) throws Exception {
        String hashLoginServiceConfigPath = FileSystemHelper.localFileNameOrResourceNameToFullPath(HASH_LOGIN_SERVICE_CONFIG_NAME);

        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", User.class, AddressDataSet.class, PhoneDataSet.class);
        SessionManager sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoImpl(sessionManager);
        HwCache<Long, User> cache = new MyCache<>();
        UserService userService = new UserServiceImpl(userDao, cache);
        UserAuthService userAuthServiceForFilterBasedSecurity = new UserAuthServiceImpl(saveAndGetAllUsers(userService));
        LoginService loginServiceForBasicSecurity = new HashLoginService(REALM_NAME, hashLoginServiceConfigPath);
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);

        UsersWebServer usersWebServer = new UsersWebServerImpl(WEB_SERVER_PORT,
                FILTER_BASED,
                userAuthServiceForFilterBasedSecurity,
                loginServiceForBasicSecurity,
                userDao,
                gson,
                templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }

    private static List<User> saveAndGetAllUsers(UserService userService) {
        List<User> allUsers = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            User user = new User("Ivan" + i, 10 + i, "user" + i, "1111");
            user.setAddress(new AddressDataSet("Lenina street, " + i));
            user.setPhoneNumber(List.of(new PhoneDataSet("12345" + i), new PhoneDataSet("234" + i)));
            long userId = userService.saveUser(user);
            User user1 = userService.getTemplate(userId);
            allUsers.add(user1);
        }

        return allUsers;
    }
}
