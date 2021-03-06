package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        try {
            String query = "CREATE TABLE IF NOT EXISTS mydb.users" +
                    " (id INT PRIMARY KEY AUTO_INCREMENT," +
                    " name varchar(30)," +
                    " lastName varchar(35)," +
                    " age INT )";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.commit();
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }

    public void dropUsersTable()  {

        try {
            String query = "DROP TABLE IF EXISTS mydb.users";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.commit();
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String query = "INSERT INTO mydb.users (name, lastName, age) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Добавлен пользователь " + name + " " + lastName);
        } catch (SQLException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM mydb.users WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.commit();
            System.out.println("Удален пользователь с ID " + id);
        } catch (SQLException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM mydb.users";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                connection.commit();
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String query = "DELETE FROM mydb.users";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.commit();
            System.out.println("База очищена");
        } catch (SQLException e) {
            System.out.println("Ошибка " + e.getMessage());
        }

    }
}
