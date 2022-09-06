package com.useraccount.dao;

import com.useraccount.model.Bill;

import java.util.List;

public interface StorageBill {

    Bill add(Bill bill);

    List<Bill> getListOfElements();

    Bill findBillFromId(int idBill);

    void updateBill(Bill bill);
}
