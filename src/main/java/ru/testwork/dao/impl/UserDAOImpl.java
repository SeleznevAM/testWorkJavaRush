package ru.testwork.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.testwork.dao.UserDAO;
import ru.testwork.entity.User;
import ru.testwork.util.HibernateUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 09.05.2016.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    public UserDAOImpl(){
        System.out.println("UserDAOImpl");
    }


    @Autowired
    private HibernateUtil hibernateUtil;

    @Override
    public int createUser(User user) {
        return (int) hibernateUtil.create(user);
    }

    @Override
    public User updateUser(User user) {
        return hibernateUtil.update(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = new User();
        user.setId(id);
        hibernateUtil.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return hibernateUtil.fetchAll(User.class);
    }

    @Override
    public User getUser(int id) {
        return hibernateUtil.fetchById(id, User.class);
    }

    @Override
    public List<User> getAllUser(String userName) {
        String query = "SELECT e.* FROM Users e WHERE e.name like '%" + userName + "%'";
        List<Object[]> userObjects = hibernateUtil.fetchAll(query);
        List<User> users = new ArrayList<User>();
        for (Object[] userObject : userObjects ){
            User user = new User();
            int id = ((Integer)userObject[0]).intValue();
            String name = (String) userObject[1];
            int age = (int)userObject[2];
            boolean admin = (boolean)userObject[3];
            Date createDate = (Date) userObject[4];
            user.setId(id);
            user.setName(name);
            user.setAge(age);
            user.setAdmin(admin);
            user.setCreateDate(createDate);
            users.add(user);
        }
        System.out.println(users);
        return users;
    }
}
