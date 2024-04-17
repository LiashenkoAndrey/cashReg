package com.cashReg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import com.cashReg.dao.SQLExecutor;
import com.cashReg.models.Order;
import com.cashReg.models.User;
import com.cashReg.conrollers.UserController;


public class CashRegister {

        private static volatile CashRegister instance;
        private List<User> users;
        private final Warehouse warehouse = Warehouse.getInstance();

        public void setOrders(List<Order> orders) {
                this.orders = orders;
        }

        private List<Order> orders;
        private UserController userController;
        private User currentUser;
        private SQLExecutor sqlExecutor = new SQLExecutor();

        public static synchronized CashRegister getInstance(){
                if(instance == null){
                        synchronized(CashRegister.class){
                                if(instance == null){
                                        instance = new CashRegister();
                                }
                        }
                }
                return instance;
        }

        private CashRegister(){}

        public void addOrder(Order order){
                orders.add(order);
        }

        public User getCurrentUser() {
                return currentUser;
        }

        public void setCurrentUser(User currentUser) {
                this.currentUser = currentUser;
        }

        public List<User> getUsers() {
                return users;
        }

        public void setUsers(List<User> users) {
                this.users = users;
        }

        public Warehouse getWarehouse() {
                return warehouse;
        }

        public List<Order> getOrders() {
                return orders;
        }

        public UserController getUserController() {
                return userController;
        }

        public void setUserController(UserController userController) {
                this.userController = userController;
        }
}

