package ru.otus.homework9;

import ru.otus.homework9.dao.JDBCTemplateDAO;
import ru.otus.homework9.dao.JDBCTemplateDAOImpl;
import ru.otus.homework9.database.DataSourceH2;
import ru.otus.homework9.database.DbExecutor;
import ru.otus.homework9.model.Account;
import ru.otus.homework9.model.User;
import ru.otus.homework9.service.JDBCTemplateService;
import ru.otus.homework9.service.JDBCTemplateServiceImpl;
import ru.otus.homework9.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new DataSourceH2();
        Main demo = new Main();
        demo.createTable(dataSource);

        //user
        SessionManagerJdbc sessionManager = new SessionManagerJdbc(dataSource);
        DbExecutor<User> dbExecutor = new DbExecutor<>();
        JDBCTemplateDAO<User> jdbcTemplate = new JDBCTemplateDAOImpl<>(sessionManager, dbExecutor);
        JDBCTemplateService jdbcTemplateService = new JDBCTemplateServiceImpl(jdbcTemplate);
        User user = new User(1, "Ivan", 3);
        jdbcTemplateService.saveTemplate(user);
        Optional userOptional = jdbcTemplateService.getTemplate(1, User.class);
        System.out.println(userOptional);

        user.setName("Andrey");
        jdbcTemplateService.updateTemplate(user);
        Optional userOptionalUpdate = jdbcTemplateService.getTemplate(1, User.class);
        System.out.println(userOptionalUpdate);

        //account
        demo.createTableAccount(dataSource);
        SessionManagerJdbc sessionManagerAccount = new SessionManagerJdbc(dataSource);
        DbExecutor<Account> dbExecutorAccount = new DbExecutor<>();
        JDBCTemplateDAO<Account> jdbcTemplateAccount = new JDBCTemplateDAOImpl<>(sessionManagerAccount, dbExecutorAccount);
        JDBCTemplateService jdbcTemplateServiceAccount = new JDBCTemplateServiceImpl(jdbcTemplateAccount);
        Account account = new Account(1, "asd", new BigDecimal(123.00));
        jdbcTemplateService.saveTemplate(account);
        Optional accountOptional = jdbcTemplateServiceAccount.getTemplate(1, Account.class);
        System.out.println(accountOptional);

        account.setType("qwe");
        account.setRest(new BigDecimal(321.00));
        jdbcTemplateServiceAccount.updateTemplate(account);
        Optional accountOptionalUpdate = jdbcTemplateServiceAccount.getTemplate(1, Account.class);
        System.out.println(accountOptionalUpdate);
    }

    private void createTable(DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pst = connection.prepareStatement(
                     "create table user(id long auto_increment, name varchar(255), age int(3))")) {
            pst.executeUpdate();
        }
        System.out.println("table created");
    }

    private void createTableAccount(DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pst = connection.prepareStatement(
                     "create table account(no long auto_increment, type varchar(255), rest number)")) {
            pst.executeUpdate();
        }
        System.out.println("table created");
    }
}
