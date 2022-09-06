package com.useraccount.factory;

import com.useraccount.dao.DaoFactory;
import com.useraccount.dao.StorageBill;
import com.useraccount.dao.StorageUser;
import com.useraccount.dao.impl.BillStorage;
import com.useraccount.dao.impl.UserStorage;
import com.useraccount.service.impl.BillService;
import com.useraccount.service.impl.UserService;
import com.useraccount.view.BillMenu;
import com.useraccount.view.UserMenu;

public class Factory {
    private static UserStorage userStorageInstance;
    private static BillStorage billStorageInstance;
    private static UserService userServiceInstance;
    private static BillService billServiceInstance;
    private static BillMenu billMenuInstance;
    private static UserMenu userMenuInstance;
    private static DaoFactory daoFactory;


    private Factory() {
    }

    public static DaoFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public static UserStorage getUserStorageInstance() {
        if (userStorageInstance == null) {
            userStorageInstance = new UserStorage(getDaoFactory());
        }
        return userStorageInstance;
    }

    public static BillStorage getBillStorageInstance() {
        if (billStorageInstance == null) {
            billStorageInstance = new BillStorage();
        }
        return billStorageInstance;
    }

    public static UserService getUserServiceInstance() {
        if (userServiceInstance == null) {
            userServiceInstance = new UserService(getUserStorageInstance(), getBillServiceInstance());
        }
        return userServiceInstance;
    }

    public static BillService getBillServiceInstance() {
        if (billServiceInstance == null) {
            billServiceInstance = new BillService(getBillStorageInstance());
        }
        return billServiceInstance;
    }

    public static BillMenu getBillMenuInstance() {
        if (billMenuInstance == null) {
            billMenuInstance = new BillMenu();
        }
        return billMenuInstance;
    }

    public static UserMenu getUserMenuInstance() {
        if (userMenuInstance == null) {
            userMenuInstance = new UserMenu();
        }
        return userMenuInstance;
    }
}
