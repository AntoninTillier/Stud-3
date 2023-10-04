package form;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.Article;
import beans.Commentaire;
import beans.User;
import dao.AbstractDAOFactory;
import dao.DAO;

public class UpdateUserForm {
    private static final String CHAMP_NAME = "nom";
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "motdepasse";
    private static final String CHAMP_SUPP = "supCompte";
    private static final String ALGO_CHIFFREMENT = "SHA-256";
    public static boolean supp = false;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    public void update(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User utilisateur = (User) session.getAttribute("utilisateur");
        String nom = getValeurChamp(request, CHAMP_NAME);
        nom = toHTML(nom);
        String email = getValeurChamp(request, CHAMP_EMAIL);
        email = toHTML(email);
        String motDePasse = getValeurChamp(request, CHAMP_PASS);
        String supprimer = getValeurChamp(request, CHAMP_SUPP);
        if (nom != null) {
            try {
                updateName(nom, utilisateur);
            } catch (Exception e) {
                setErreur(CHAMP_NAME, e.getMessage());
            }
            if (erreurs.get(CHAMP_NAME) == null) {
                utilisateur.setNom(nom);
                session.setAttribute("utilisateur", utilisateur);
            }
        }
        if (email != null) {
            try {
                updateEmail(email, utilisateur);
            } catch (Exception e) {
                setErreur(CHAMP_EMAIL, e.getMessage());
            }
            if (erreurs.get(CHAMP_EMAIL) == null) {
                utilisateur.setEmail(email);
                session.setAttribute("utilisateur", utilisateur);
            }
        }
        if (motDePasse != null) {
            try {
                updateMDP(motDePasse, utilisateur);
            } catch (Exception e) {
                setErreur(CHAMP_PASS, e.getMessage());
            }
        }
        if (supprimer != null) {
            try {
                delete(utilisateur, request);
            } catch (Exception e) {
                setErreur(CHAMP_SUPP, e.getMessage());
            }
            if (erreurs.get(CHAMP_SUPP) == null) {
                supp = true;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void updateName(String name, User user) throws Exception {
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<User> userDAO = adf.getUser();
        if (!userDAO.updateName(name, user.getEmail())) {
            throw new Exception("La mise à jours du nom n'a pas réussie");
        } else {
            Date date = new Date();
            DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
            String res = "Vous avez modifié votre nom " + user.getNom() + " par " + name;
            userDAO.createHistory(user, shortDateFormat.format(date), res);
        }
    }

    @SuppressWarnings("unchecked")
    private void updateEmail(String email, User user) throws Exception {
        if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            throw new Exception("Merci de saisir une adresse mail valide.");
        } else {
            AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
            DAO<User> userDAO = adf.getUser();
            userDAO.updateEmail(email, user.getId());
            Date date = new Date();
            DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
            String res = "Vous avez modifié votre email " + user.getEmail() + " par " + email;
            userDAO.createHistory(user, shortDateFormat.format(date), res);
        }
    }

    @SuppressWarnings("unchecked")
    private void updateMDP(String mdp, User user) throws Exception {
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm(ALGO_CHIFFREMENT);
        passwordEncryptor.setPlainDigest(false);
        String motDePasseChiffre = passwordEncryptor.encryptPassword(mdp);
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<User> userDAO = adf.getUser();
        if (!userDAO.updateMdp(motDePasseChiffre, user.getEmail())) {
            throw new Exception("La mise à jours du mot de passe n'a pas réussie");
        } else {
            Date date = new Date();
            DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
            String res = "Vous avez modifié votre mot de passe";
            userDAO.createHistory(user, shortDateFormat.format(date), res);
        }
    }

    @SuppressWarnings("unchecked")
    private void delete(User user, HttpServletRequest request) throws Exception {
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        HttpSession session = request.getSession();
        User utilisateur = (User) session.getAttribute("utilisateur");
        DAO<Commentaire> commentaireDAO = adf.getCommentaire();
        DAO<User> userDAO = adf.getUser();
        userDAO.deleteHystory(utilisateur);
        if (commentaireDAO.deleteAll(utilisateur)) {
            DAO<Article> articleDAO = adf.getArticle();
            ArrayList<Article> resArt = articleDAO.getAll();
            ArrayList<Article> listArt = new ArrayList<Article>();
            for (Article article : resArt) {
                if (article.getIdUser() == utilisateur.getId())
                    listArt.add(article);
            }
            ArrayList<Commentaire> resC = commentaireDAO.getAll();
            for (Article article : listArt) {
                File f = new File(article.getImage());
                f.delete();
                for (Commentaire commentaire : resC) {
                    if (commentaire.getPostId() == article.getId()) {
                        commentaireDAO.delete(commentaire);
                    }
                }
                articleDAO.delete(article);
            }
            userDAO.deleteFriends(utilisateur);
            userDAO.delete(utilisateur);
        } else
            throw new Exception("Suppression des commentaires n'a pas réussie");


    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
    }

    private String toHTML(String str) {
        String out = "";
        if (str == null)
            return out;
        else {
            for (char c : str.toCharArray()) {
                if (!Character.isLetterOrDigit(c))
                    out += String.format("&#x%x;", (int) c);
                else
                    out += String.format("%s", c);
            }
        }
        return out;
    }
}