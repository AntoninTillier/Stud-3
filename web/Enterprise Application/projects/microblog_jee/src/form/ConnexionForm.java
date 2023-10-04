package form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.User;
import dao.AbstractDAOFactory;
import dao.DAO;

public class ConnexionForm {
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "motdepasse";
    private static final String ALGO_CHIFFREMENT = "SHA-256";
    private Map<String, String> erreurs = new HashMap<String, String>();
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    @SuppressWarnings("unchecked")
    public User connecterUtilisateur(HttpServletRequest request) {
        /* Récupération des champs du formulaire */
        String email = getValeurChamp(request, CHAMP_EMAIL);
        String motDePasse = getValeurChamp(request, CHAMP_PASS);
        User utilisateur = null;
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<User> userDAO = adf.getUser();
        User tempUser = userDAO.find(email);
        if (tempUser != null) {
            /* Validation du champ email. */
            try {
                validationEmail(email, tempUser.getEmail());
            } catch (Exception e) {
                setErreur(CHAMP_EMAIL, e.getMessage());
            }
            // utilisateur.setEmail(email);
            /* Validation du champ mot de passe. */
            try {
                validationMotDePasse(motDePasse, tempUser.getMotDePasse());
            } catch (Exception e) {
                setErreur(CHAMP_PASS, e.getMessage());
            }
            // utilisateur.setMotDePasse(motDePasse);
            /* Initialisation du résultat global de la validation. */
            if (erreurs.isEmpty()) {
                utilisateur = tempUser;
                tempUser = null;
            }
        }
        return utilisateur;
    }

    /**
     * Valide l'adresse email saisie.
     */
    private void validationEmail(String email, String tEmail) throws Exception {
        if (email.equals(tEmail) && email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            throw new Exception("Merci de saisir une adresse mail valide.");
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMotDePasse(String motDePasse, String tMotDePasse) throws Exception {
        if (motDePasse != null) {
            ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
            passwordEncryptor.setAlgorithm(ALGO_CHIFFREMENT);
            passwordEncryptor.setPlainDigest(false);
            if (!passwordEncryptor.checkPassword(motDePasse, tMotDePasse)) {
                throw new Exception("Merci de saisir votre mot de passe.");
            }
        } else {
            throw new Exception("Merci de saisir votre mot de passe.");
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
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
}