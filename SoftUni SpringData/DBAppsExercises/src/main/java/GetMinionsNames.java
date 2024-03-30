import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetMinionsNames {
    private static final String GET_MINIONS_NAMES =
                    "SELECT m.name, m.age FROM minions m" +
                    " JOIN minions_villains mv on m.id = mv.minion_id" +
                    " WHERE mv.villain_id = ?;";
    private static final String GET_VILLAIN_NAME_BY_ID = "SELECT name FROM villains v WHERE v.id = ?;";
    private static final String NO_VILLAIN_FOUND = "No villain with ID %d exists in the database.";
    private static final String VILLAIN_FORMAT = "Villain: %s%n";
    private static final String MINION_FORMAT = "%d. %s %d%n";


    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        final int villainId = new Scanner(System.in).nextInt();

        final PreparedStatement villainStatement = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);
        villainStatement.setInt(1, villainId);
        final ResultSet villainResultSet = villainStatement.executeQuery();

        if (!villainResultSet.next()) {
            System.out.printf(NO_VILLAIN_FOUND, villainId);
            return;
        }

        final String villainName = villainResultSet.getString(Constants.COLUMN_LABEL_NAME);
        
        System.out.printf(VILLAIN_FORMAT, villainName);

        final PreparedStatement minionsStatement = connection.prepareStatement(GET_MINIONS_NAMES);
        minionsStatement.setInt(1, villainId);
        final ResultSet minionsResultSet = minionsStatement.executeQuery();

        for (int i = 1; minionsResultSet.next(); i++) {
            final String minionName = minionsResultSet.getString(Constants.COLUMN_LABEL_NAME);
            final int minionAge = minionsResultSet.getInt(Constants.COLUMN_LABEL_AGE);
            System.out.printf(MINION_FORMAT, i, minionName, minionAge);
        }

        connection.close();
    }
}
