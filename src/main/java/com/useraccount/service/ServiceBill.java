package com.useraccount.service;

import com.useraccount.model.Bill;
import com.useraccount.model.User;

import java.util.List;

public interface ServiceBill {

    void addBill(String billName, int billBalance, User user);

    List<Bill> findBillsByUser(User user);

    Bill sumBalanceTransaction(int idBill, int sumDigit);

    Bill reduceBalance(int idBill, int reduceDigit);
}
