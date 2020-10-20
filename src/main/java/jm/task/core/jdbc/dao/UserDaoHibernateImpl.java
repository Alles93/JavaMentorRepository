package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    Session session;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {

        session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String query = "CREATE TABLE IF NOT EXISTS mydb.users" +
                    " (id INT PRIMARY KEY AUTO_INCREMENT," +
                    " name varchar(30)," +
                    " lastName varchar(35)," +
                    " age INT )";
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
            System.out.println("База данных создана");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Ошибка " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String query = "DROP TABLE IF EXISTS mydb.users";
            session.createSQLQuery(query).addEntity(User.class).executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Ошибка " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("User с именем –  " + name + " добавлен в базу данных");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Ошибка " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            session.close();
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Ошибка " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            users = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Ошибка " + e.getMessage());
        } finally {
            session.close();
        }
        return users;

    }

    @Override
    public void cleanUsersTable() {
        session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String query = "DELETE FROM mydb.users";
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
            System.out.println("База очищена");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Ошибка " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
