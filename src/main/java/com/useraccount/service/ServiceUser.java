package com.useraccount.service;

import com.useraccount.model.User;

public interface ServiceUser {

    User addUser(String name, String login);

    User findUserByLogin(String login);

    int removeUser(String login);

}
