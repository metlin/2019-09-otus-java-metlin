package ru.otus.homework9.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.homework9.Id;
import ru.otus.homework9.database.DbExecutor;
import ru.otus.homework9.sessionmanager.SessionManager;
import ru.otus.homework9.sessionmanager.SessionManagerJdbc;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class JDBCTemplateDAOImpl<T> implements JDBCTemplateDAO<T> {

    private static Logger logger = LoggerFactory.getLogger(JDBCTemplateDAOImpl.class);

    private final SessionManagerJdbc sessionManager;
    private final DbExecutor<T> dbExecutor;

    public JDBCTemplateDAOImpl(SessionManagerJdbc sessionManager, DbExecutor<T> dbExecutor) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
    }

    @Override
    public void create(T objectData) {
        if (checkIdAnnotation(objectData)) {
            String script = "insert into " + objectData.getClass().getSimpleName() + "(";
            List<String> list = new ArrayList<>();

            Field[] fields = objectData.getClass().getDeclaredFields();
            for(Field field : fields) {
                field.setAccessible(true);
                if (!field.isAnnotationPresent(Id.class)) {
                    script += field.getName() + ",";
                    try {
                        list.add(field.get(objectData).toString());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            script = script.substring(0, script.length() - 1) + ") values(?,?) ";

            try {
                dbExecutor.insertRecord(getConnection(), script, list);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(T objectData) {
        if(checkIdAnnotation(objectData)) {
            List<String> params = new ArrayList<>();
            String fieldName = "";
            String script = "update " + objectData.getClass().getSimpleName() + " set ";
            Field[] fields = objectData.getClass().getDeclaredFields();
            for(Field field : fields) {
                field.setAccessible(true);
                if (!field.isAnnotationPresent(Id.class)) {
                    script += field.getName() + "=";
                    try {
                        if (field.get(objectData) instanceof String) {
                            script += "'" + field.get(objectData).toString() + "',";
                        } else {
                            script += field.get(objectData).toString() + ",";
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    fieldName = field.getName();
                    try {
                        params.add(field.get(objectData).toString());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            script = script.substring(0, script.length() - 1) + " where " + fieldName + " = ? ";

            try {
                dbExecutor.updateRecord(getConnection(), script, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createOrUpdate(T objectData) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<T> load(long id, Class<T> clazz) {
        if (checkIdAnnotation(clazz)) {
            String fieldName = "";
            String script = "select ";
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields) {
                field.setAccessible(true);
                script += field.getName() + ",";
                if (field.isAnnotationPresent(Id.class)) {
                    fieldName = field.getName();
                }
            }

            script = script.substring(0, script.length() - 1) + " from " + clazz.getSimpleName() + " where " + fieldName + " = ? ";

            try {
                return dbExecutor.selectRecord(getConnection(), script, id, resultSet -> {
                    try {
                        if (resultSet.next()) {
                            T instance = clazz.getDeclaredConstructor().newInstance();
                            Field[] fields1 = clazz.getDeclaredFields();
                            for(Field field : fields1) {
                                field.setAccessible(true);
                                field.set(instance, resultSet.getObject(field.getName()));
                            }

                            return instance;
                        }
                    } catch (SQLException | NoSuchMethodException e) {
                        logger.error(e.getMessage(), e);
                    } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

        return Optional.empty();
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }

    private boolean checkIdAnnotation(T objectData) {
        Field[] fields = objectData.getClass().getDeclaredFields();
        return checkFields(fields);
    }

    private boolean checkIdAnnotation(Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        return checkFields(fields);
    }

    private boolean checkFields(Field[] fields) {
        for(Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Id.class)) {
                return true;
            }
        }

        return false;
    }
}
