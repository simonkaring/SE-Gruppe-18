package data;

import model.Producent;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static data.ConnectionDatabase.connection;

public class InsertProduction {

    public static void insertProduction(String productionTitle, Producent production){
        try {
            PreparedStatement insertStatement = connection.prepareStatement("Insert INTO programmer (navn, producent_id) VALUES (?,?)");
            insertStatement.setString(1, productionTitle);
            insertStatement.setInt(2, production.getProducentID());
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
