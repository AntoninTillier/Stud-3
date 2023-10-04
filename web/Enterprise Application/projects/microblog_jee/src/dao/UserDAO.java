package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import beans.User;

public class UserDAO extends DAO<User> {

    public UserDAO(Connection conn) {
        super(conn);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean create(User obj) {
        boolean v = false;
        try {
            String strSql = "INSERT INTO USERS(email,mdp, name, dateInscription) VALUES (?,?,?,?)";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setString(1, obj.getEmail());
            statement.setString(2, obj.getMotDePasse());
            statement.setString(3, obj.getNom());
            statement.setString(4, obj.getDateInscription());
            statement.execute();
            v = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public boolean delete(User obj) {
        boolean b = false;
        try {
            String strSql = "DELETE FROM users WHERE id=?";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setLong(1, obj.getId());
            statement.execute();
            b = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public boolean updateName(String name, String email) {
        boolean v = false;
        try {
            String strSql = "UPDATE USERS SET name=? WHERE email=?";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.execute();
            v = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public boolean updateEmail(String email, long id) {
        boolean v = false;
        try {
            String strSql = "UPDATE USERS SET email=? WHERE id=?";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setString(1, email);
            statement.setLong(2, id);
            statement.execute();
            v = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public boolean updateMdp(String mdp, String email) {
        boolean v = false;
        try {
            String strSql = "UPDATE USERS SET mdp=? WHERE email=?";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setString(1, mdp);
            statement.setString(2, email);
            statement.execute();
            v = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public User find(String email) {
        User user = null;
        try {
            String strSql = "SELECT * FROM USERS WHERE email =?";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if (result.first())
                user = new User(result.getLong("id"), result.getString("email"), result.getString("mdp"),
                        result.getString("name"), result.getString("dateInscription"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public ArrayList<User> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findId(long id) {
        User user = null;
        try {
            String strSql = "SELECT * FROM USERS WHERE id=?";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.first())
                user = new User(result.getLong("id"), result.getString("email"), result.getString("mdp"),
                        result.getString("name"), result.getString("dateInscription"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public ArrayList<User> findAll(String contenue) {
        ArrayList<User> res = new ArrayList<User>();
        try {
            String strSql = "SELECT * FROM USERS WHERE email LIKE ? OR name LIKE ?";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setString(1, contenue + "%");
            statement.setString(2, contenue + "%");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User u = new User(result.getLong("id"), result.getString("email"), "", result.getString("name"),
                        result.getString("dateInscription"));
                res.add(u);
            }
        } catch (

                SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean delete(long idPost) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(User obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean createFriend(User obj, long id) {
        boolean v = false;
        try {
            String strSql = "INSERT INTO friends(userId,otherId) VALUES (?,?)";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setLong(1, obj.getId());
            statement.setLong(2, id);
            statement.execute();
            v = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public ArrayList<User> findFriends(User obj) {
        ArrayList<User> res = new ArrayList<User>();
        try {
            String strSql = "SELECT * FROM friends WHERE userID=?";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setLong(1, obj.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User u = new User(result.getLong("otherID"), "", "", "", "");
                res.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.reverse(res);
        return res;
    }

    @Override
    public boolean deleteAll(User obj) {
        boolean b = false;
        try {
            String strSql = "DELETE FROM post WHERE idUser=?";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setLong(1, obj.getId());
            statement.execute();
            b = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public boolean deleteFriends(User obj) {
        boolean b = false;
        try {
            String strSql = "DELETE FROM friends WHERE userId=? OR otherId=?";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setLong(1, obj.getId());
            statement.setLong(2, obj.getId());
            statement.execute();
            b = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public ArrayList<String> getAllHistory(User obj) {
        ArrayList<String> res = new ArrayList<String>();
        try {
            String strSql = "SELECT * FROM history WHERE userId=?";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setLong(1, obj.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String s = result.getString("date") + " : " + result.getString("value");
                res.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean createHistory(User obj, String date, String value) {
        boolean v = false;
        try {
            String strSql = "INSERT INTO history(userId,date,value) VALUES (?,?,?)";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setLong(1, obj.getId());
            statement.setString(2, date);
            statement.setString(3, value);
            statement.execute();
            v = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public boolean deleteHystory(User obj) {
        boolean b = false;
        try {
            String strSql = "DELETE FROM history WHERE userId=?";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setLong(1, obj.getId());
            statement.execute();
            b = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }
}