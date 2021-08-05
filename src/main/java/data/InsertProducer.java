package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static data.ConnectionDatabase.connection;

public class InsertProducer {

    public static void insertProducer(String navn) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("Insert INTO producenter (navn) VALUES (?)");
            insertStatement.setString(1, navn);
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
