package com.aq.dao;

import java.sql.Connection;

public class DAOFactory extends AbstractDAOFactory {
    protected static final Connection conn = ConnectionSingleton.getInstance();

    public DAO getUser() {
        return new UserDAO(conn);
    }
}