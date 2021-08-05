package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static data.ConnectionDatabase.connection;

public class InsertProducer {

    private static String insertProducerSQL = "Insert INTO producenter (navn) VALUES (?)";

    public static void insertProducer(String navn) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(insertProducerSQL);
            insertStatement.setString(1, navn);
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
