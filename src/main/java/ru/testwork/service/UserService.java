package ru.testwork.service;

import ru.testwork.entity.User;

import java.util.List;

/**
 * Created by alex on 09.05.2016.
 */
public interface UserService {
    public long createUser(User user);
    public User updateUser(User user);
    public void deleteUser(int id);
    public List<User> getAllUsers();
    public User getUser(int id);
    public List<User> getAllUser(String userName);
}
