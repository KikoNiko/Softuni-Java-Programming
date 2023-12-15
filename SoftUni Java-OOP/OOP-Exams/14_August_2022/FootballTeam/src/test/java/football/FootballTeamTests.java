package football;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FootballTeamTests {
    private static final String NAME = "Test_Name";
    private static final int VACANT_POSITIONS = 1;
    private static final String FOOTBALLER_NAME = "Test_Footballer";
    private FootballTeam footballTeam;
    private Footballer footballer;

    @Before
    public void setUp() {
        this.footballTeam = new FootballTeam(NAME, VACANT_POSITIONS);
        this.footballer = new Footballer(FOOTBALLER_NAME);
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateTeam_WithNullName_ShouldThrow() {
        new FootballTeam(null, VACANT_POSITIONS);
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateTeam_WithEmptyName_ShouldThrow() {
        new FootballTeam("   ", VACANT_POSITIONS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CreateTeam_WithNegativeVacantPositions_ShouldThrow() {
        new FootballTeam(NAME, -1);
    }

    @Test
    public void test_CreateTeam_WithCorrectValues_ShouldSucceed() {
        FootballTeam team = new FootballTeam(NAME, VACANT_POSITIONS);
        assertEquals(NAME, team.getName());
        assertEquals(VACANT_POSITIONS, team.getVacantPositions());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddFootballer_ShouldFail_WhenVacantPositions_Reached() {
        FootballTeam team = new FootballTeam(NAME, 0);
        team.addFootballer(footballer);
    }

    @Test
    public void test_AddFootballer_ShouldCorrectlyIncrease_FootballerCount() {
        footballTeam.addFootballer(footballer);
        assertEquals(1, footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveFootballer_ShouldThrow_WhenPlayerMissing() {
        footballTeam.removeFootballer(footballer.getName());
    }

    @Test
    public void test_RemoveFootballer_ShouldCorrectly_RemovePlayer() {
        footballTeam.addFootballer(footballer);
        footballTeam.removeFootballer(FOOTBALLER_NAME);
        assertEquals(0, footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_FootballerForSale_ShouldThrow_WhenPlayerMissing() {
        footballTeam.footballerForSale(FOOTBALLER_NAME);
    }

    @Test
    public void test_FootballerForSale_ShouldCorrectly_SetPlayerInactive() {
        footballTeam.addFootballer(footballer);
        footballTeam.footballerForSale(FOOTBALLER_NAME);
        assertFalse(footballer.isActive());
    }
}
