import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveVillain {

    private static final String GET_VILLAIN_NAME = "SELECT v.name FROM villains v WHERE v.id = ?";
    private static final String DELETE_VILLAIN = "DELETE FROM villains v WHERE v.id = ?";
    private static final String RELEASE_MINIONS_FROM_VILLAIN = "DELETE FROM minions_villains mv WHERE mv.villain_id = ?";
    private static final String NO_SUCH_VILLAIN = "No such villain was found";
    private static final String VILLAIN_DELETED_FORMAT = "%s was deleted%n";
    private static final String MINIONS_RELEASED_FORMAT = "%d minions released";


    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        int villainId = new Scanner(System.in).nextInt();

        PreparedStatement villainNameStatement = connection.prepareStatement(GET_VILLAIN_NAME);
        villainNameStatement.setInt(1, villainId);
        ResultSet villainNameSet = villainNameStatement.executeQuery();

        if (!villainNameSet.next()) {
            System.out.println(NO_SUCH_VILLAIN);
            connection.close();
            return;
        }

        String villainName = villainNameSet.getString(Constants.COLUMN_LABEL_NAME);

        connection.setAutoCommit(false);

        try (
                PreparedStatement releaseMinionsStatement = connection.prepareStatement(RELEASE_MINIONS_FROM_VILLAIN);
                PreparedStatement deleteStatement = connection.prepareStatement(DELETE_VILLAIN)) {

            releaseMinionsStatement.setInt(1, villainId);
            int countReleasedMinions = releaseMinionsStatement.executeUpdate();

            deleteStatement.setInt(1, villainId);
            deleteStatement.executeUpdate();

            connection.commit();

            System.out.printf(VILLAIN_DELETED_FORMAT, villainName);
            System.out.printf(MINIONS_RELEASED_FORMAT, countReleasedMinions);

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }

        connection.close();
    }
}
