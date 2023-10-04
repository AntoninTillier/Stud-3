package com.aq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
    //URL de connexion
    private String url = "jdbc:mysql://localhost:8889/projetDP?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
    //Nom du user
    private String user = "root";
    //Mot de passe de l'utilisateur
    private String passwd = "root";
    //Objet Connection
    private static Connection connect;

    //Constructeur privé
    private ConnectionSingleton() {
        try {
            connect = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
    public static Connection getInstance() {
        if (connect == null) {
            new ConnectionSingleton();
        }
        return connect;
    }
}