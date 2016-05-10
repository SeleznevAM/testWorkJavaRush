package ru.testwork.dao;

import ru.testwork.entity.User;

import java.util.List;

public interface UserDAO {
    public int createUser(User user);
    public User updateUser(User user);
    public void deleteUser(int id);
    public List<User> getAllUsers();
    public User getUser(int id);
    public List<User> getAllUser(String userName);
}
