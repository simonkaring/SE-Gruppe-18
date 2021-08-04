package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static data.ConnectionDatabase.connection;

public class DeleteProduction {

    private static String deleteSQL = "DELETE FROM programmer WHERE navn=?";

    public static void deleteProduction(String production) {

        try {
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL);
            deleteStatement.setString(1, production);
            deleteStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
