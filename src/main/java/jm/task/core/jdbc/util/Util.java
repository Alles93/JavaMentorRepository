package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String HOST = "jdbc:mysql://localhost:3306/users?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "22111993";


    public Util() {
    }

    //TODO JDBC
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            System.out.println("Подключение успешно");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Ошибка соединения");
        }
        return connection;
    }

    //TODO HIBER
    private static SessionFactory sessionFactory;

    static {
        try {
            Properties properties = new Properties();
            //  properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            properties.setProperty(Environment.HBM2DDL_AUTO, "update");
            properties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
            properties.setProperty(Environment.USER, "root");
            properties.setProperty(Environment.PASS, "22111993");
            properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/users?serverTimezone=Europe/Moscow&amp");
            sessionFactory = new org.hibernate.cfg.Configuration()
                    .addProperties(properties)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
}
