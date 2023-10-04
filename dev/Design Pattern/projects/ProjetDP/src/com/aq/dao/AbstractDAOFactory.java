package com.aq.dao;

public abstract class AbstractDAOFactory {
    public static final int DAO_FACTORY = 0;
    public static final int XML_DAO_FACTORY = 1;
    @SuppressWarnings("rawtypes")
    public abstract DAO getUser();

    //Méthode permettant de récupérer les Factory
    public static AbstractDAOFactory getFactory(int type) {
        switch (type) {
            case DAO_FACTORY:
                return new DAOFactory();
            case XML_DAO_FACTORY:
                return null;//new XMLDAOFactory();
            default:
                return null;
        }
    }
}