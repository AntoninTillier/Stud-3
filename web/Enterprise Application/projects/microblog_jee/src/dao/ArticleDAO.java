package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import beans.Article;
import beans.User;

public class ArticleDAO extends DAO<Article> {

    public ArticleDAO(Connection conn) {
        super(conn);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean create(Article obj) {
        boolean v = false;
        try {
            String strSql = "INSERT INTO post(idUser,titre, value, image,date) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setLong(1, obj.getIdUser());
            statement.setString(2, obj.getTitre());
            statement.setString(3, obj.getValue());
            statement.setString(4, obj.getImage());
            statement.setString(5, obj.getDate());
            statement.execute();
            v = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public boolean delete(Article obj) {
        boolean b = false;
        try {
            String strSql = "DELETE FROM post WHERE id=?";
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
        return false;
    }

    @Override
    public boolean updateEmail(String email, long id) {
        return false;
    }

    @Override
    public boolean updateMdp(String mdp, String email) {
        return false;
    }

    @Override
    public Article find(String name) {
        return null;
    }

    @Override
    public ArrayList<Article> getAll() {
        ArrayList<Article> res = new ArrayList<Article>();
        try {
            ResultSet result = this.connect
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery("SELECT * FROM post");
            while (result.next()) {
                Article article = new Article(result.getLong("id"), result.getLong("idUser"), result.getString("titre"),
                        result.getString("value"), result.getString("image"), result.getString("date"));
                res.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.reverse(res);
        return res;
    }

    @Override
    public Article findId(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Article> findAll(String contenue) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(long idPost) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(Article obj) {
        boolean v = false;
        try {
            String strSql = "UPDATE post SET titre=?, value=?, image=? WHERE id=?";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setString(1, obj.getTitre());
            statement.setString(2, obj.getValue());
            statement.setString(3, obj.getImage());
            statement.setLong(4, obj.getId());
            statement.execute();
            v = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public boolean createFriend(Article obj, long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ArrayList<Article> findFriends(Article obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean deleteAll(User obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteFriends(User obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ArrayList<String> getAllHistory(Article obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean createHistory(Article obj, String date, String value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteHystory(Article obj) {
        // TODO Auto-generated method stub
        return false;
    }
}