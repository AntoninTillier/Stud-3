package dao;

import java.sql.Connection;
import java.util.ArrayList;

import beans.User;

public abstract class DAO<T> {
    protected Connection connect = null;

    public DAO(Connection conn) {
        this.connect = conn;
    }

    /**
     * Méthode de création
     *
     * @param obj
     * @return boolean
     */
    public abstract boolean create(T obj);

    public abstract boolean createHistory(T obj, String date, String value);

    /**
     * Méthode pour effacer
     *
     * @param obj
     * @return boolean
     */
    public abstract boolean delete(T obj);

    public abstract boolean delete(long idPost);

    public abstract boolean deleteAll(User obj);

    public abstract boolean deleteFriends(User obj);

    public abstract boolean deleteHystory(T obj);

    /**
     * Méthode de mise à jour
     *
     * @param obj
     * @return boolean
     */

    public abstract boolean createFriend(T obj, long id);

    public abstract boolean update(T obj);

    public abstract boolean updateName(String name, String email);

    public abstract boolean updateEmail(String email, long id);

    public abstract boolean updateMdp(String mdp, String email);

    /**
     * Méthode de recherche des informations
     *
     * @param id
     * @return T
     */

    public abstract T find(String name);

    public abstract ArrayList<T> findAll(String contenue);

    public abstract ArrayList<T> findFriends(T obj);

    public abstract T findId(long id);

    public abstract ArrayList<T> getAll();

    public abstract ArrayList<String> getAllHistory(T obj);
}