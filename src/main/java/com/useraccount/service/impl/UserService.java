package com.useraccount.service.impl;

import com.useraccount.dao.StorageUser;
import com.useraccount.dao.impl.UserStorage;
import com.useraccount.model.Bill;
import com.useraccount.model.User;
import com.useraccount.service.ServiceUser;

import java.util.List;

public class UserService implements ServiceUser {

    private final UserStorage userStorage;
    private final BillService billService;

    public UserService(UserStorage userStorage, BillService billService) {
        this.userStorage = userStorage;
        this.billService = billService;
    }

    @Override
    public User addUser(String name, String login) {
        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user = userStorage.add(user);
        return user;
    }

    @Override
    public User findUserByLogin(String login) {
        User userByLogin = userStorage.findByLogin(login);
        if (userByLogin != null) {
            List<Bill> bills = billService.findBillsByUser(userByLogin);
            userByLogin.setBills(bills);
        }
        return userByLogin;
    }

    @Override
    public int removeUser(String removeUserLogin) {
        User user = findUserByLogin(removeUserLogin);
        int idRemoveUser = user.getId();
        userStorage.remove(idRemoveUser);
        return idRemoveUser;
    }
}
