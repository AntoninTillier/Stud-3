package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Commentaire;
import beans.User;

public class CommentaireDAO extends DAO<Commentaire> {

    public CommentaireDAO(Connection conn) {
        super(conn);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean create(Commentaire obj) {
        boolean v = false;
        try {
            String strSql = "INSERT INTO comment(postId,userId, value,date) VALUES (?,?,?,?)";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setLong(1, obj.getPostId());
            statement.setLong(2, obj.getUserId());
            statement.setString(3, obj.getValue());
            statement.setString(4, obj.getDate());
            statement.execute();
            v = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public boolean delete(Commentaire obj) {
        boolean b = false;
        try {
            String strSql = "DELETE FROM comment WHERE id=?";
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateEmail(String email, long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateMdp(String mdp, String email) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Commentaire find(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Commentaire findId(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Commentaire> getAll() {
        ArrayList<Commentaire> res = new ArrayList<Commentaire>();
        try {
            ResultSet result = this.connect
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                    .executeQuery("SELECT * FROM comment");
            while (result.next()) {
                Commentaire article = new Commentaire(result.getLong("id"), result.getLong("postId"),
                        result.getLong("userId"), result.getString("value"), result.getString("date"));
                res.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ArrayList<Commentaire> findAll(String contenue) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(long idPost) {
        boolean b = false;
        try {
            String strSql = "DELETE FROM comment WHERE postID=?";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setLong(1, idPost);
            statement.execute();
            b = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public boolean update(Commentaire obj) {
        boolean v = false;
        try {
            String strSql = "UPDATE comment SET value=? WHERE id=?";
            PreparedStatement statement = connect.prepareStatement(strSql);
            statement.setString(1, obj.getValue());
            statement.setLong(2, obj.getId());
            statement.execute();
            v = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public boolean createFriend(Commentaire obj, long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ArrayList<Commentaire> findFriends(Commentaire obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean deleteAll(User obj) {
        boolean b = false;
        try {
            String strSql = "DELETE FROM comment WHERE userId=?";
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ArrayList<String> getAllHistory(Commentaire obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean createHistory(Commentaire obj, String date, String value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteHystory(Commentaire obj) {
        // TODO Auto-generated method stub
        return false;
    }
}