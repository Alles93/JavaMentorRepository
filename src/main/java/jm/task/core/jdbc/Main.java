package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("User1","Q", (byte) 13);
        userServiceImpl.saveUser("User2","W", (byte) 14);
        userServiceImpl.saveUser("User3","E", (byte) 24);
        userServiceImpl.saveUser("User4","R", (byte) 23);
        userServiceImpl.getAllUsers();
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();
    }
}
