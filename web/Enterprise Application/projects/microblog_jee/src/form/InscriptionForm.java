package form;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.springframework.web.util.HtmlUtils;

import dao.AbstractDAOFactory;
import dao.DAO;

import beans.User;

public class InscriptionForm {
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "motdepasse";
    private static final String CHAMP_CONF = "confirmation";
    private static final String CHAMP_NOM = "nom";
    private static final String ALGO_CHIFFREMENT = "SHA-256";
    private Map<String, String> erreurs = new HashMap<String, String>();

    @SuppressWarnings("unchecked")
    public User inscrireUtilisateur(HttpServletRequest request) {
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<User> userDAO = adf.getUser();
        String email = getValeurChamp(request, CHAMP_EMAIL);
        String motDePasse = getValeurChamp(request, CHAMP_PASS);
        String confirmation = getValeurChamp(request, CHAMP_CONF);
        String nom = getValeurChamp(request, CHAMP_NOM);
        nom = toHTML(nom);
        Date date = new Date();
        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        User utilisateur = new User(0, email, motDePasse, nom, shortDateFormat.format(date));
        traiterEmail(email, utilisateur);
        traiterMotsDePasse(motDePasse, confirmation, utilisateur);
        traiterNom(nom, utilisateur);
        System.out.println("erreurs : " + erreurs);
        if (erreurs.isEmpty()) {
            userDAO.create(utilisateur);
            User u = userDAO.find(utilisateur.getEmail());
            String res = "Vous venez de vous inscrire";
            userDAO.createHistory(u, shortDateFormat.format(date), res);
        } else {
            utilisateur = null;
        }
        return utilisateur;
    }

    /*
     * Appel à la validation de l'adresse email reçue et initialisation de la
     * propriété email du bean
     */
    private void traiterEmail(String email, User utilisateur) {
        try {
            validationEmail(email);
        } catch (FormValidationException e) {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }
        utilisateur.setEmail(email);
    }

    /*
     * Appel à la validation des mots de passe reçus, chiffrement du mot de passe et
     * initialisation de la propriété motDePasse du bean
     */
    private void traiterMotsDePasse(String motDePasse, String confirmation, User utilisateur) {
        try {
            validationMotsDePasse(motDePasse, confirmation);
        } catch (FormValidationException e) {
            setErreur(CHAMP_PASS, e.getMessage());
            setErreur(CHAMP_CONF, null);
        }
        /*
         * Utilisation de la bibliothèque Jasypt pour chiffrer le mot de passe
         * efficacement.
         *
         * L'algorithme SHA-256 est ici utilisé, avec par défaut un salage aléatoire et
         * un grand nombre d'itérations de la fonction de hashage.
         *
         * La String retournée est de longueur 56 et contient le hash en Base64.
         */
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm(ALGO_CHIFFREMENT);
        passwordEncryptor.setPlainDigest(false);
        String motDePasseChiffre = passwordEncryptor.encryptPassword(motDePasse);
        utilisateur.setMotDePasse(motDePasseChiffre);
    }

    private void traiterNom(String nom, User utilisateur) {
        utilisateur.setNom(nom);
    }

    /* Validation de l'adresse email */
    @SuppressWarnings("unchecked")
    private void validationEmail(String email) throws FormValidationException {
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<User> userDAO = adf.getUser();
        if (email != null) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new FormValidationException("Merci de saisir une adresse mail valide.");
            } else if (userDAO.find(email) != null) {
                throw new FormValidationException(
                        "Cette adresse email est déjà utilisée, merci d'en choisir une autre.");
            }
        } else {
            throw new FormValidationException("Merci de saisir une adresse mail.");
        }
    }

    private void validationMotsDePasse(String motDePasse, String confirmation) throws FormValidationException {
        if (motDePasse != null && confirmation != null) {
            if (!motDePasse.equals(confirmation)) {
                throw new FormValidationException("Merci de saisir le même mot de passe dans les deux champs.");
            }
        } else {
            throw new FormValidationException("Merci de saisir un mot de passe valide.");
        }
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
            return valeur.trim();
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    public Map<String, String> getErreurs() {
        return this.erreurs;
    }

    private String toHTML(String str) {
        String out = "";
        out = HtmlUtils.htmlEscape(str);
        return out;
    }
}