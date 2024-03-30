import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNamesCasing {

    private static final String UPDATE_TOWN_NAMES = "UPDATE towns t SET t.name = UPPER(t.name) WHERE t.country = ?";
    private static final String GET_TOWN_NAMES_BY_COUNTRY = "SELECT t.name FROM towns t WHERE t.country = ?";
    private static final String NO_TOWNS_AFFECTED = "No town names were affected.";
    private static final String TOWNS_AFFECTED_FORMAT = "%d town names were affected.%n";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        String countryName = new Scanner(System.in).nextLine();

        PreparedStatement updateStatement = connection.prepareStatement(UPDATE_TOWN_NAMES);
        updateStatement.setString(1, countryName);
        int countUpdatedTowns = updateStatement.executeUpdate();

        if (countUpdatedTowns == 0) {
            System.out.println(NO_TOWNS_AFFECTED);
            return;
        }

        System.out.printf(TOWNS_AFFECTED_FORMAT, countUpdatedTowns);

        PreparedStatement getTownsStatement = connection.prepareStatement(GET_TOWN_NAMES_BY_COUNTRY);
        getTownsStatement.setString(1, countryName);
        ResultSet resultSet = getTownsStatement.executeQuery();

        List<String> townNames = new ArrayList<>();
        while (resultSet.next()) {
            townNames.add(resultSet.getString(Constants.COLUMN_LABEL_NAME));
        }

        System.out.println(townNames);

        connection.close();
    }
}
