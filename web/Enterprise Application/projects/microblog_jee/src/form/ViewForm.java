package form;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Commentaire;
import beans.User;
import dao.AbstractDAOFactory;
import dao.DAO;

public class ViewForm {
    private Map<String, String> erreurs = new HashMap<String, String>();

    @SuppressWarnings("unchecked")
    public void createComment(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User utilisateur = (User) session.getAttribute("utilisateur");
        long idPost = Integer.valueOf(request.getParameter("i"));
        long userId = utilisateur.getId();
        String value = request.getParameter("content");
        value = toHTML(value);
        try {
            traiterValue(value);
        } catch (Exception e) {
            setErreurs("value", e.getMessage());
        }
        Date date = new Date();
        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

        if (erreurs.isEmpty()) {
            Commentaire c = new Commentaire(0, idPost, userId, value, shortDateFormat.format(date));
            AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
            DAO<Commentaire> commentaireDAO = adf.getCommentaire();
            commentaireDAO.create(c);
        }
    }

    private void traiterValue(String value) throws Exception {
        if (value == null || value.trim().length() == 0) {
            throw new Exception("Contenue vide");
        }
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    private void setErreurs(String champ, String message) {
        erreurs.put(champ, message);
    }

    private String toHTML(String str) {
        String out = "";
        for (char c : str.toCharArray()) {
            if (!Character.isLetterOrDigit(c))
                out += String.format("&#x%x;", (int) c);
            else
                out += String.format("%s", c);
        }
        return out;
    }
}