package com.useraccount.service.impl;

import com.useraccount.MyException;
import com.useraccount.dao.StorageBill;
import com.useraccount.model.Bill;
import com.useraccount.model.User;
import com.useraccount.service.ServiceBill;

import java.util.ArrayList;
import java.util.List;

public class BillService implements ServiceBill {

    private final StorageBill billStorage;

    public BillService(StorageBill billStorage) {
        this.billStorage = billStorage;
    }

    @Override
    public void addBill(String billName, int billBalance, User user) {
        Bill bill = new Bill();
        bill.setName(billName);
        bill.setBalance(billBalance);
        bill.setUser(user);
        billStorage.add(bill);
    }

    @Override
    public List<Bill> findBillsByUser(User findUser) {
        List<Bill> billsList = new ArrayList<>();
        List<Bill> billList = billStorage.getListOfElements();
        for (Bill billInList : billList) {
            int idUser = findUser.getId();
            if (billInList.getUser().getId() == idUser) {
                billsList.add(billInList);
            }
        }
        return billsList;
    }

    @Override
    public Bill sumBalanceTransaction(int idBill, int sumDigit) {
        Bill bill = billStorage.findBillFromId(idBill);
        int billBalance = bill.getBalance();
        int sumBillBalance = billBalance + sumDigit;
        bill.setBalance(sumBillBalance);
        billStorage.updateBill(bill);
        return bill;
    }

    @Override
    public Bill reduceBalance(int idBill, int reduceDigit) throws MyException {
        Bill bill = billStorage.findBillFromId(idBill);
        int billBalance = bill.getBalance();
        int reduceBillBalance = billBalance - reduceDigit;
        if (reduceBillBalance < 0) {
            throw new MyException();
        } else {
            bill.setBalance(reduceBillBalance);
            billStorage.updateBill(bill);
        }
        return bill;
    }
}