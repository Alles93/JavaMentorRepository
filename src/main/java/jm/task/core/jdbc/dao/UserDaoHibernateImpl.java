package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    Session session;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String query = "CREATE TABLE IF NOT EXISTS users" +
                    " (id INT PRIMARY KEY AUTO_INCREMENT," +
                    " name varchar(30)," +
                    " lastName varchar(35)," +
                    " age INT )";
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
            System.out.println("База данных создана");
        }
        catch (Exception e) {
            System.out.println("Ошибка " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String query = "DROP TABLE users";
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            System.out.println("Ошибка " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("User с именем –  " + name + " добавлен в базу данных");
        } catch (Exception e) {
            System.out.println("Ошибка " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String query = "DELETE FROM user WHERE id = " + id;
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            System.out.println("Ошибка " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка " + e.getMessage());
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {

        try {
            session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String query = "TRUNCATE TABLE users";
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
            System.out.println("База очищена");
        } catch (Exception e) {
            System.out.println("Ошибка " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
