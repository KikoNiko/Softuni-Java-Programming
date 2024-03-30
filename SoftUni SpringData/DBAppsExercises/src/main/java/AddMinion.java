import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AddMinion {

    private static final String GET_TOWN_BY_NAME = "SELECT t.id FROM towns t WHERE t.name = ?";
    private static final String GET_VILLAIN_BY_NAME = "SELECT v.id FROM villains v WHERE v.name = ?";
    private static final String GET_LAST_MINION_ID = "SELECT m.id FROM minions m ORDER BY m.id DESC LIMIT 1";
    private static final String INSERT_TOWN = "INSERT INTO towns (name) VALUES (?)";
    private static final String INSERT_VILLAIN = "INSERT INTO villains (name, evilness_factor) VALUES (?, ?)";
    private static final String INSERT_MINION = "INSERT INTO minions (name, age, town_id) VALUES (?, ?, ?)";
    private static final String INSERT_MINION_VILLAIN = "INSERT INTO minions_villains (minion_id, villain_id) VALUES (?, ?)";
    private static final String EVILNESS_FACTOR = "evil";
    private static final String SUCCESSFULLY_ADDED_TOWN = "Town %s was added to the database.%n";
    private static final String SUCCESSFULLY_ADDED_VILLAIN = "Villain %s was added to the database.%n";
    private static final String SUCCESSFULLY_ADDED_MINION_TO_VILLAIN = "Successfully added %s to be minion of %s.%n";


    public static void main(String[] args) throws SQLException {
        final Connection sqlConnection = Utils.getSQLConnection();

        Scanner scanner = new Scanner(System.in);
        String[] minionInfo = scanner.nextLine().split(" ");
        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String minionTown = minionInfo[3];

        String villainName = scanner.nextLine().split(" ")[1];

        int townId = getId(sqlConnection,
                List.of(minionTown),
                GET_TOWN_BY_NAME,
                INSERT_TOWN,
                SUCCESSFULLY_ADDED_TOWN);

        int villainId = getId(sqlConnection,
                List.of(villainName, EVILNESS_FACTOR),
                GET_VILLAIN_BY_NAME,
                INSERT_VILLAIN,
                SUCCESSFULLY_ADDED_VILLAIN);

        PreparedStatement insertMinionStatement = sqlConnection.prepareStatement(INSERT_MINION);

        insertMinionStatement.setString(1, minionName);
        insertMinionStatement.setInt(2, minionAge);
        insertMinionStatement.setInt(3, townId);

        insertMinionStatement.executeUpdate();

        PreparedStatement lastMinionStatement = sqlConnection.prepareStatement(GET_LAST_MINION_ID);

        ResultSet lastMinionSet = lastMinionStatement.executeQuery();
        lastMinionSet.next();

        int minionId = lastMinionSet.getInt(Constants.COLUMN_LABEL_ID);

        PreparedStatement insertMinionToVillainStatement = sqlConnection.prepareStatement(INSERT_MINION_VILLAIN);

        insertMinionToVillainStatement.setInt(1, minionId);
        insertMinionToVillainStatement.setInt(2, villainId);

        insertMinionToVillainStatement.executeUpdate();

        System.out.printf(SUCCESSFULLY_ADDED_MINION_TO_VILLAIN, minionName, villainName);

        sqlConnection.close();
    }


    private static int getId(Connection connection,
                             List<String> arguments,
                             String selectQuery,
                             String insertQuery,
                             String printFormat) throws SQLException {

        final String name = arguments.get(0);

        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        selectStatement.setString(1, name);
        ResultSet resultSet = selectStatement.executeQuery();

        if (!resultSet.next()) {
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

            for (int index = 1; index <= arguments.size(); index++) {
                insertStatement.setString(index, arguments.get(index - 1));
            }

            insertStatement.executeUpdate();

            ResultSet newResultSet = selectStatement.executeQuery();
            newResultSet.next();

            System.out.printf(printFormat, name);

            return newResultSet.getInt(Constants.COLUMN_LABEL_ID);
        }

        return resultSet.getInt(Constants.COLUMN_LABEL_ID);
    }
}
