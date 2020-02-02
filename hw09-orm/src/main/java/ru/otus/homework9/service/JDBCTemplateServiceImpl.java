package ru.otus.homework9.service;

import ru.otus.homework9.database.DbServiceException;
import ru.otus.homework9.sessionmanager.SessionManager;
import ru.otus.homework9.dao.JDBCTemplateDAO;

import java.util.Optional;

public class JDBCTemplateServiceImpl implements JDBCTemplateService {

   private JDBCTemplateDAO jdbcTemplateDAO;

   public JDBCTemplateServiceImpl(JDBCTemplateDAO jdbcTemplateDAO) {
        this.jdbcTemplateDAO = jdbcTemplateDAO;
   }

    @Override
    public <T> void saveTemplate(T template) {
        try (SessionManager sessionManager = jdbcTemplateDAO.getSessionManager()) {
            sessionManager.beginSession();
            try {
                jdbcTemplateDAO.create(template);
                sessionManager.commitSession();
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public <T> Optional getTemplate(long id, Class<T> clazz) {
        try (SessionManager sessionManager = jdbcTemplateDAO.getSessionManager()) {
            sessionManager.beginSession();
            try {
                return jdbcTemplateDAO.load(id, clazz);
            } catch (Exception e) {
                sessionManager.rollbackSession();
            }
        }

        return Optional.empty();
    }

    @Override
    public <T> void updateTemplate(T template) {
        try (SessionManager sessionManager = jdbcTemplateDAO.getSessionManager()) {
            sessionManager.beginSession();
            try {
                jdbcTemplateDAO.update(template);
                sessionManager.commitSession();
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }
}
