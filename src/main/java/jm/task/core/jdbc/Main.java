package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("User01", "Q", (byte) 13);
        userServiceImpl.saveUser("User02", "W", (byte) 14);
        userServiceImpl.saveUser("User03", "E", (byte) 24);
        userServiceImpl.saveUser("User04", "R", (byte) 23);
        userServiceImpl.getAllUsers();
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();

    }
}
