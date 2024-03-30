import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetVillainsNames {
    private static final String QUERY_GET_VILLAINS_NAMES =
            "SELECT v.name, COUNT(DISTINCT(mv.minion_id)) AS 'minions_count' FROM villains v" +
                    " JOIN minions_villains mv on v.id = mv.villain_id" +
                    " GROUP BY mv.villain_id" +
                    " HAVING minions_count > ?" +
                    " ORDER BY minions_count DESC;";

    private static final String COLUMN_LABEL_MINIONS_COUNT = "minions_count";
    private static final String OUTPUT_FORMAT = "%s %s%n";


    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement statement = connection.prepareStatement(QUERY_GET_VILLAINS_NAMES);

        statement.setInt(1, 15);

        final ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            final String villainName = resultSet.getString(Constants.COLUMN_LABEL_NAME);
            final int minionsCount = resultSet.getInt(COLUMN_LABEL_MINIONS_COUNT);

            System.out.printf(OUTPUT_FORMAT, villainName, minionsCount);
        }

        connection.close();
    }
}
