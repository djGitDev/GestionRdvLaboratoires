package org.system.SystemeGestionLabs.BaseDeDonnees;

import com.google.gson.JsonObject;
import org.system.SystemeGestionLabs.ResultatExamen;

import java.sql.*;

public class ResultatsExamensDAO implements IDAO<ResultatExamen> {

    private static String NOM_TABLE = "ResultatsExamens";
    private DBManager manager;

    @Override
    public JsonObject chercher(String nom) {
        // a ne pas utiliser dans cette application
        // on cherche pas des enregistrements
        // on simule seulement un envoi automatique d'enregistrement
        return null;
    }

    @Override
    public JsonObject transfererResultat() {
        ResultSet resultatRequete = null;
        JsonObject jsonObject = null;

        try {

            resultatRequete = chercherResultat("SELECT * FROM " + NOM_TABLE + " ORDER BY numeroRDV ASC LIMIT 1");
            int noRdv = resultatRequete.getInt("numeroRDV");
            String selectQuery = "DELETE FROM " + NOM_TABLE + " WHERE numeroRDV = ?";
            try {
                Connection connect = ((SQLConnexion) manager.getConnection()).getConnexion();
                PreparedStatement stmt = connect.prepareStatement(selectQuery);
                stmt.setInt(1, noRdv);
                int rowsAffected = stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (resultatRequete.next()) {
                ResultSetMetaData metaData = resultatRequete.getMetaData();
                int columnCount = metaData.getColumnCount();
                jsonObject = new JsonObject();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultatRequete.getObject(i);
                    jsonObject.addProperty(columnName, value.toString());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération et transformation du résultat en JSON", e);
        } finally {
            // Fermeture des ressources
            try {
                if (resultatRequete != null) resultatRequete.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    @Override
    public boolean verifiePrescenceDonnees() {
        ResultSet resultatRequette = null;
        try {
            resultatRequette = chercherResultat("SELECT COUNT(*) AS count FROM " + NOM_TABLE + ";");
            if (resultatRequette.getInt("count") > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Fermeture des ressources
            try {
                if (resultatRequette != null) resultatRequette.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean verifiePrescenceDonneeSpecifique(String nom) {
        return false;
    }

    @Override
    public void setManager(DBManager dbManager) {
        manager = dbManager;
    }

    @Override
    public void inserer(ResultatExamen resultat) {
        String noPATIENT = resultat.getCodePatient();
        int noRDV = resultat.getNumeroRDV();
        String DateExamen = resultat.getDateExamen();
        String uri = resultat.getUriDocument();

        String checkSql = "SELECT COUNT(*) FROM " + NOM_TABLE + " WHERE codePatient = ? AND numeroRDV = ? AND dateExamen = ? AND uriDocument = ?";

        String insertSql = "INSERT INTO " + NOM_TABLE + " (codePatient, numeroRDV, dateExamen, uriDocument) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = ((SQLConnexion) manager.getConnection()).getConnexion();
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            System.out.println("Connexion établie: " + (conn != null && !conn.isClosed()));
            checkStmt.setString(1, noPATIENT);
            checkStmt.setInt(2, noRDV);
            checkStmt.setString(3, DateExamen);
            checkStmt.setString(4, uri);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                System.out.println("L'enregistrement existe déjà.");
            } else {
                // Si l'enregistrement n'existe pas, l'insérer
                insertStmt.setString(1, noPATIENT);
                insertStmt.setInt(2, noRDV);
                insertStmt.setString(3, DateExamen);
                insertStmt.setString(4, uri);

                int rowsAffected = insertStmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("L'enregistrement a été inséré.");
                } else {
                    System.out.println("Erreur lors de l'insertion de l'enregistrement.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    private ResultSet chercherResultat(String requeteSQL) {
        Connection connect = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connect = ((SQLConnexion) manager.getConnection()).getConnexion();
            stmt = connect.prepareStatement(requeteSQL);
            rs = stmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
