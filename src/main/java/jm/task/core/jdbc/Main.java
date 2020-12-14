package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("11", "Q", (byte) 13);
        userServiceImpl.saveUser("21", "W", (byte) 14);
        userServiceImpl.saveUser("31", "E", (byte) 24);
        userServiceImpl.saveUser("41", "R", (byte) 23);
        userServiceImpl.getAllUsers();
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();
    }
}
