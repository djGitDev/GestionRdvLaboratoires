package org.system.SystemeGestionLabs.BaseDeDonnees;

import com.google.gson.JsonObject;
import org.system.SystemeGestionLabs.Disponibilite;

import java.sql.*;


public class DisonibilitesDAO implements IDAO<Disponibilite> {
    private static String NOM_TABLE = "Disponibilites";
    private DBManager manager;

    @Override
    public JsonObject chercher(String nom) {
        ResultSet resultatRequete = null;
        JsonObject jsonObject = null;
        try {
            // Utilisation d'une requête préparée pour sécuriser les paramètres
            String requeteSQL = "SELECT * FROM " + NOM_TABLE + " WHERE nomExamen = \"" + nom + "\" LIMIT 1;";
            resultatRequete = chercherResultat(requeteSQL);
            if (resultatRequete.next()) {
                ResultSetMetaData metaData = resultatRequete.getMetaData();
                int columnCount = metaData.getColumnCount();
                jsonObject = new JsonObject();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultatRequete.getObject(i);
                    jsonObject.addProperty(columnName, value.toString());
                }
                String IdToDelete = resultatRequete.getString(5);
                String deleteSQL = "DELETE FROM " + NOM_TABLE + " WHERE ID = ? ;";
                try {
                    Connection connect = ((SQLConnexion) manager.getConnection()).getConnexion();
                    PreparedStatement deleteStatement = connect.prepareStatement(deleteSQL);
                    deleteStatement.setString(1, IdToDelete);
                    deleteStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
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
    public boolean verifiePrescenceDonneeSpecifique(String nom) {
        ResultSet resultatRequette = null;
        try {
            // Utilisation d'une requête préparée pour sécuriser les paramètres
            String requeteSQL = "SELECT nomExamen FROM " + NOM_TABLE + " WHERE nomExamen = \"" + nom + "\" LIMIT 1;";

            resultatRequette = chercherResultat(requeteSQL);

            if (resultatRequette != null && resultatRequette.next()) {
                // Si une ligne est retournée, la valeur existe
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
    public void setManager(DBManager dbManager) {
        manager = dbManager;
    }

    @Override
    public void inserer(Disponibilite disponibilite) {
        // a ne pas implementer
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

    @Override
    public boolean verifiePrescenceDonnees() {
        //a ne pas implementer
        return false;
    }

    @Override
    public JsonObject transfererResultat() {
        // a ne pas implementer
        return null;
    }




}
