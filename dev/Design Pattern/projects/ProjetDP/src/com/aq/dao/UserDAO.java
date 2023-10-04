package com.aq.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aq.User;

public class UserDAO extends DAO<User> {

    public UserDAO(Connection conn) {
        super(conn);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean create(User obj) {
        boolean v = false;
        try {
            Statement statement = connect.createStatement();
            statement.execute("INSERT INTO User(name,niveaux) VALUES ('" + obj.getName() + "','" + obj.getNiveaux() + "')");
            v = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public boolean delete(User obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(User obj) {
        boolean v = false;
        try {
            Statement statement = connect.createStatement();
            statement.execute("UPDATE User SET niveaux=" + obj.getNiveaux() + " WHERE name=" + "\"" + obj.getName() + "\"");
            v = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public User find(String name) {
        User user = new User();
        try {
            ResultSet result = this.connect
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery("SELECT * FROM User WHERE name =" + "\"" + name + "\"");
            if (result.first())
                user = new User(result.getInt("id"), name, result.getInt("niveaux"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String[] bestUser() {
        String[] res = new String[10];
        try {
            ResultSet result = this.connect
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery("SELECT * FROM User ORDER BY niveaux DESC");
            int count = 0;
            while (count < 10) {
                result.next();
                res[count] = result.getString("name") + ";" + result.getInt("niveaux");
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}