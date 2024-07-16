package org.system.SystemeGestionLabs.BaseDeDonnees;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnexion implements IDBconnexion {

    public static final String DBsqlite_URL = "resources/DBsqlite.db";
    private Connection connexion;

    public Connection getConnexion() {
        return connexion;
    }

    @Override
    public void connect() {
        try {
            // Charger le driver JDBC SQLite (nécessaire pour les versions de JDBC < 4)
            Class.forName("org.sqlite.JDBC");
            String absolutePath = Paths.get(DBsqlite_URL).toAbsolutePath().toString();
            String dbUrl = "jdbc:sqlite:" + absolutePath;
            // Établir la connexion à la base de données
            connexion = DriverManager.getConnection(dbUrl);

            System.out.println("Connexion à la base de données SQLite établie.");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Driver JDBC SQLite non trouvé.", e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la connexion à la base de données.", e);
        }
    }
    public void disconnect() {
        if (connexion != null) {
            try {
                connexion.close();
                System.out.println("Connexion à la base de données SQLite fermée.");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur lors de la fermeture de la connexion.", e);
            }
        }
    }
}