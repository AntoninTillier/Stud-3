package form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Article;
import beans.User;
import dao.AbstractDAOFactory;
import dao.DAO;

public class HomeForm {
    @SuppressWarnings("unused")
    private Map<String, String> erreurs = new HashMap<String, String>();

    @SuppressWarnings("unchecked")
    public void listArticle(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("utilisateur");
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<Article> articleDAO = adf.getArticle();
        ArrayList<Article> listOfArticle = articleDAO.getAll();
        DAO<User> userDAO = adf.getUser();
        ArrayList<User> listFriends = userDAO.findFriends(user);
        ArrayList<Article> res = new ArrayList<Article>();
        for (Article a : listOfArticle) {
            if (a.getIdUser() == user.getId()) {
                res.add(a);
            } else {
                for (User u : listFriends) {
                    if (a.getIdUser() == u.getId()) {
                        res.add(a);
                    }
                }
            }
        }
        session.setAttribute("homeForm", res);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<User> addFriends(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User utilisateur = (User) session.getAttribute("utilisateur");
        String search = request.getParameter("search");
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<User> userDAO = adf.getUser();
        ArrayList<User> listUser = userDAO.findAll(search);
        ArrayList<User> res = new ArrayList<User>();
        ArrayList<User> friends = userDAO.findFriends(utilisateur);
        for (User user : listUser) {
            if (user.getId() != utilisateur.getId()) {
                if (friends.isEmpty()) {
                    res.add(user);
                } else {
                    for (User u : friends) {
                        if (user.getId() != u.getId()) {
                            res.add(user);
                        }
                    }
                }
            }
        }
        System.out.println(res);
        return res;
    }
}