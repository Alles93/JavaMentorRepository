package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util worker = new Util();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users" + " (id INT PRIMARY KEY AUTO_INCREMENT," + " name varchar(30)," + " lastName varchar(35)," + " age INT )";
        try {
            Statement statement = worker.connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String query = "DROP TABLE users";
        try {
            Statement statement = worker.connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String query = "INSERT INTO users (name, lastName, age) VALUES (?,?,?)";
            PreparedStatement preparedStatement = worker.connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            int rows = preparedStatement.executeUpdate();
            System.out.println("Добавлен пользователь " + name + " " + lastName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM user WHERE id = " + id;
        try {
            Statement statement = worker.connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Удален пользователь с ID " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        Statement statement = null;
        try {
            statement = worker.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE users";
        try {
            Statement statement = worker.connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("База очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
