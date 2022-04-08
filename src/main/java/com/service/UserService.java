package com.service;

import com.dao.UserDao;
import com.entity.UserEntity;

import javax.persistence.Persistence;
import java.util.List;

public class UserService {

    private UserDao userDao;

    public UserService() {
        try {
            userDao = new UserDao(Persistence.createEntityManagerFactory("default"));
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addUser(UserEntity newUser) {
        userDao.create(newUser);
    }

    public void updateUser(UserEntity updatedUser) {
        userDao.update(updatedUser);
    }

    public List<UserEntity> getAllUsers() {
        return userDao.findAll();
    }

    //for login
    public UserEntity findUser(String user, String pass) throws Exception {

        List<UserEntity> users = userDao.find(user);
        if(users.size()==0) {
            throw new Exception("User not found");
        }

        UserEntity u = users.get(0);

        if(pass.compareTo(u.getPassword()) !=0 ) {
            throw new Exception("Password does not match");
        }
        return u;
    }


}