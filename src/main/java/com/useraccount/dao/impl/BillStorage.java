package com.useraccount.dao.impl;

import com.useraccount.dao.DaoFactory;
import com.useraccount.dao.StorageBill;
import com.useraccount.model.Bill;
import com.useraccount.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillStorage implements StorageBill {

    DaoFactory daoFactory ;

    @Override
    public Bill add(Bill bill) {
        try (Connection connect = daoFactory.getConnetion()) {
            String sql = "insert into bills ( bill_name, bill_balance, user_id) VALUES (?,?,?)";
            PreparedStatement psmt = connect.prepareStatement(sql);
            psmt.setString(1, bill.getName());
            psmt.setInt(2, bill.getBalance());
            psmt.setInt(3, bill.getUser().getId());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }

    @Override
    public List<Bill> getListOfElements() {
        List<Bill> billList = new ArrayList<>();
        try (Connection connection = daoFactory.getConnetion()) {
            Statement statement = connection.createStatement();
            String sql = "select * from bills left outer join users u on u.user_id = bills.user_id";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("bill_id");
                String name = resultSet.getString("bill_name");
                int balance = resultSet.getInt("bill_balance");
                int userId = resultSet.getInt("user_id");
                User user = new User(userId);
                Bill bill = new Bill(name, id, balance, user);
                billList.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billList;
    }

    @Override
    public Bill findBillFromId(int idBill) {
        Bill bill = null;
        try (Connection connection = daoFactory.getConnetion()) {
            String sglResultRequest = "select * from bills where bill_id = ?";
            PreparedStatement psmtResult = connection.prepareStatement(sglResultRequest);
            psmtResult.setInt(1, idBill);
            ResultSet resultSet = psmtResult.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("bill_id");
                String billname = resultSet.getString("bill_name");
                int balance = resultSet.getInt("bill_balance");
                bill = new Bill(id, billname, balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }

    @Override
    public void updateBill(Bill bill) {
        int balanceBill = bill.getBalance();
        int idBill = bill.getId();
        try (Connection connection = daoFactory.getConnetion()) {
            String sqlReqest = "update bills set bill_balance = ? where bill_id = ?";
            PreparedStatement psmt = connection.prepareStatement(sqlReqest);
            psmt.setInt(1, balanceBill);
            psmt.setInt(2, idBill);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
