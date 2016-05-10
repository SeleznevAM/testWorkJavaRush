package ru.testwork.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.testwork.entity.User;
import ru.testwork.service.UserService;

import java.util.List;

/**
 * Created by alex on 09.05.2016.
 */
@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    public UserController(){
        System.out.println("UserController()");
    }

    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    @RequestMapping("createUser")
    public ModelAndView createUser(@ModelAttribute User user){
        logger.info("Createing user. Date " + user);
        return new ModelAndView("userForm");
    }

    @RequestMapping("editUser")
    public ModelAndView editUser(@RequestParam int id, @ModelAttribute User user){
        logger.info("Updaiting the User for the id " + id);
        user = userService.getUser(id);
        return new ModelAndView("userForm", "userObject", user);
    }

    @RequestMapping("saveUser")
    public ModelAndView saveUser(@ModelAttribute User user){
        logger.info("Saving the User. Date: "+user);
        if(user.getId() == 0){
            userService.createUser(user);
        }else {
            userService.updateUser(user);
        }
        return new ModelAndView("redirect:getAllUsers");
    }

    @RequestMapping("deleteUser")
    public ModelAndView deleteUser(@RequestParam int id){
        logger.info("Deleting the User by ID: " + id);
        userService.deleteUser(id);
        return new ModelAndView("redirect:getAllUsers");
    }

    @RequestMapping(value = {"getAllUsers", "/"})
    public ModelAndView getAllUsers(){
        logger.info("Getting the all Users");
        List<User> userList = userService.getAllUsers();
        return new ModelAndView("userList", "userList", userList);
    }

    @RequestMapping ("searchUser")
    public ModelAndView searchUser(@RequestParam("searchName") String searchName){
        logger.info("Searching the User " + searchName);
        List<User> userList = userService.getAllUser(searchName);
        return new ModelAndView("userList", "userList", userList);
    }
}
