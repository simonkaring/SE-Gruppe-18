package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static data.ConnectionDatabase.connection;

public class DeleteProduction {

    public static void deleteProduction(String production) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("DELETE FROM programmer WHERE navn=?");
            insertStatement.setString(1, production);
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
