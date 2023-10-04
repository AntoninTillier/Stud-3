package form;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.util.HtmlUtils;

import beans.Article;
import beans.User;
import dao.AbstractDAOFactory;
import dao.DAO;

public class PostForm {
    private static final String CHAMP_TITRE = "title";
    private static final String CHAMP_VALUE = "content";
    @SuppressWarnings("unused")
    private static final String IMAGE = "photo";
    private Map<String, String> erreurs = new HashMap<String, String>();

    @SuppressWarnings({"unused", "unchecked"})
    public Article postArticle(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User utilisateur = (User) session.getAttribute("utilisateur");
        String titre = null;
        String value = null;
        String image = "";
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    /*
                     * Traiter les champs classiques ici (input type="text|radio|checkbox|etc",
                     * select, etc).
                     */
                    String nomChamp = item.getFieldName();
                    String valeurChamp = item.getString();
                    if (nomChamp.equals(CHAMP_TITRE)) {
                        titre = valeurChamp;
                        titre = toHTML(titre);
                        traiterTitre(valeurChamp);
                    }
                    if (nomChamp.equals(CHAMP_VALUE)) {
                        value = valeurChamp;
                        value = toHTML(value);
                        traiterValue(valeurChamp);
                    }
                } else {
                    /* Traiter les champs de type fichier (input type="file"). */
                    String nomChamp = item.getFieldName();
                    String nomFichier = FilenameUtils.getName(item.getName());
                    InputStream contenuFichier = null;
                    try {
                        contenuFichier = item.getInputStream();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    BufferedInputStream entree = null;
                    BufferedOutputStream sortie = null;
                    try {
                        /* Ouvre les flux. */
                        entree = new BufferedInputStream(contenuFichier, 10240);
                        String chemin = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
                        chemin = chemin.replace(".metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/", "");
                        chemin = chemin.replace("WEB-INF/classes/", "");
                        if (chemin.contains("%20"))
                            chemin = chemin.replace("%20", " ");
                        chemin += "imageServeur/";
                        if (!nomFichier.isEmpty()) {
                            String name = chemin + utilisateur.getId() + ZonedDateTime.now().toInstant().toEpochMilli()
                                    + nomFichier;
                            if (!new File(name).exists()) {
                                try {
                                    new File(name).createNewFile();
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                            File f = new File(name);
                            image = name;
                            try {
                                sortie = new BufferedOutputStream(new FileOutputStream(f), 10240);
                            } catch (FileNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            byte[] tampon = new byte[10240];
                            int longueur;
                            try {
                                while ((longueur = entree.read(tampon)) > 0) {
                                    sortie.write(tampon, 0, longueur);
                                }
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    } finally {
                        try {
                            if (sortie != null)
                                sortie.close();
                        } catch (IOException ignore) {
                        }
                        try {
                            entree.close();
                        } catch (IOException ignore) {
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        Article article = null;
        if (erreurs.isEmpty()) {
            System.out.println(image);
            AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
            DAO<Article> articleDAO = adf.getArticle();
            article = new Article(0, utilisateur.getId(), titre, value, image, shortDateFormat.format(date));
            if (!articleDAO.create(article)) {
                article = null;
            }
        }
        return article;
    }

    @SuppressWarnings({"unchecked", "unused"})
    public Article updateArticle(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User utilisateur = (User) session.getAttribute("utilisateur");
        ArrayList<Article> listA = new ArrayList<Article>();
        listA = (ArrayList<Article>) session.getAttribute("homeForm");
        Article a = null;
        for (Article article : listA) {
            if (article.getId() == Integer.valueOf(request.getParameter("i"))) {
                a = article;
            }
        }
        String titre = null;
        String value = null;
        String image = null;
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    /*
                     * Traiter les champs classiques ici (input type="text|radio|checkbox|etc",
                     * select, etc).
                     */
                    String nomChamp = item.getFieldName();
                    String valeurChamp = item.getString();
                    if (nomChamp.equals(CHAMP_TITRE)) {
                        titre = valeurChamp;
                        traiterTitre(titre);
                    }
                    if (nomChamp.equals(CHAMP_VALUE)) {
                        value = valeurChamp;
                        traiterValue(value);
                    }
                } else {
                    /* Traiter les champs de type fichier (input type="file"). */
                    String nomChamp = item.getFieldName();
                    String nomFichier = FilenameUtils.getName(item.getName());
                    if (!nomFichier.isEmpty()) {
                        InputStream contenuFichier = null;
                        try {
                            contenuFichier = item.getInputStream();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        BufferedInputStream entree = null;
                        BufferedOutputStream sortie = null;
                        try {
                            /* Ouvre les flux. */
                            entree = new BufferedInputStream(contenuFichier, 10240);
                            String chemin = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
                            chemin = chemin.replace(".metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/",
                                    "");
                            chemin = chemin.replace("WEB-INF/classes/", "");
                            if (chemin.contains("%20"))
                                chemin = chemin.replace("%20", " ");
                            chemin += "imageServeur/";
                            if (!nomFichier.isEmpty()) {
                                String name = chemin + utilisateur.getId()
                                        + ZonedDateTime.now().toInstant().toEpochMilli() + nomFichier;
                                if (!new File(name).exists()) {
                                    try {
                                        new File(name).createNewFile();
                                    } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                }
                                File f = new File(name);
                                image = name;
                                try {
                                    sortie = new BufferedOutputStream(new FileOutputStream(f), 10240);
                                } catch (FileNotFoundException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                byte[] tampon = new byte[10240];
                                int longueur;
                                try {
                                    while ((longueur = entree.read(tampon)) > 0) {
                                        sortie.write(tampon, 0, longueur);
                                    }
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        } finally {
                            try {
                                if (sortie != null)
                                    sortie.close();
                            } catch (IOException ignore) {
                            }
                            try {
                                entree.close();
                            } catch (IOException ignore) {
                            }
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (erreurs.isEmpty()) {
            a.setTitre(titre);
            a.setValue(value);
            if (image != null) {
                File f = new File(a.getImage());
                f.delete();
                a.setImage(image);
            }
            AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
            DAO<Article> articleDAO = adf.getArticle();
            if (!articleDAO.update(a)) {
                a = null;
            }
        } else {
            a = null;
        }
        System.out.println(a);
        return a;
    }

    private void traiterTitre(String titre) {
        try {
            validationTitre(titre);
        } catch (Exception e) {
            setErreur(CHAMP_TITRE, e.getMessage());
        }
    }

    private void validationTitre(String titre) throws Exception {
        if (titre == null) {
            throw new Exception("Merci de saisir le titre de l'article.");
        }
    }

    private void traiterValue(String value) {
        try {
            validationValue(value);
        } catch (Exception e) {
            setErreur(CHAMP_VALUE, e.getMessage());
        }
    }

    private void validationValue(String value) throws Exception {
        if (value == null) {
            throw new Exception("Merci de saisir un contenu pour l'article.");
        }
    }

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