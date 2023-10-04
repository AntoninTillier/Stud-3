package form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.util.HtmlUtils;

import beans.Commentaire;
import beans.User;
import dao.AbstractDAOFactory;
import dao.DAO;

public class CommentsForm {
    private Map<String, String> erreurs = new HashMap<String, String>();

    @SuppressWarnings("unchecked")
    public Commentaire updateComment(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User utilisateur = (User) session.getAttribute("utilisateur");
        long idC = Integer.valueOf(request.getParameter("i"));
        long userId = utilisateur.getId();
        String value = request.getParameter("content");
        value = toHTML(value);
        try {
            traiterValue(value);
        } catch (Exception e) {
            setErreurs("value", e.getMessage());
        }
        Commentaire c = null;
        if (erreurs.isEmpty()) {
            c = new Commentaire(idC, 0, userId, value, "");
            AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
            DAO<Commentaire> commentaireDAO = adf.getCommentaire();
            if (commentaireDAO.update(c)) {
            } else {
                c = null;
            }
        }
        return c;
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
        out = HtmlUtils.htmlEscape(str);
        return out;
    }
}