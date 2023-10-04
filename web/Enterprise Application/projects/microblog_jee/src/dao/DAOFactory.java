package dao;

import java.sql.Connection;

public class DAOFactory extends AbstractDAOFactory {
    protected static final Connection conn = ConnectionSingleton.getInstance();

    @SuppressWarnings("rawtypes")
    public DAO getUser() {
        return new UserDAO(conn);
    }

    @SuppressWarnings("rawtypes")
    public DAO getArticle() {
        return new ArticleDAO(conn);
    }

    @SuppressWarnings("rawtypes")
    public DAO getCommentaire() {
        return new CommentaireDAO(conn);
    }
}