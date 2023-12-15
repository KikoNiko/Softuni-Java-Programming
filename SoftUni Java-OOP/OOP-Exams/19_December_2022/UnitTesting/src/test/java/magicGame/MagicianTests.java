package magicGame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MagicianTests {

    private static final String USERNAME = "Test_Magician";
    private static final int HEALTH = 100;
    private static final String MAGIC_NAME = "Test_Magic";
    private Magic magic;
    private Magician magician;

    @Before
    public void setUp() {
        magician = new Magician(USERNAME, HEALTH);
        magic = new Magic(MAGIC_NAME, 50);
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateMagician_WithNullName_ShouldThrow() {
        new Magician(null, HEALTH);
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateMagician_WithEmptyName_ShouldThrow() {
        new Magician("", HEALTH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CreateMagician_WithNegativeHealth_ShouldThrow() {
        new Magician(USERNAME, -10);
    }

    @Test
    public void test_CreateMagician_WithCorrectValues_ShouldWork() {
        assertEquals(USERNAME, magician.getUsername());
        assertEquals(HEALTH, magician.getHealth());
        assertEquals(new ArrayList<>(), magician.getMagics());
    }

    @Test
    public void test_TakeDamage_WithoutKill() {
        magician.takeDamage(50);
        assertEquals(HEALTH - 50, magician.getHealth());
    }

    @Test
    public void test_TakeDamage_Kills() {
        magician.takeDamage(150);
        assertEquals(0, magician.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void test_TakeDamage_ForDeadMagician() {
        magician.takeDamage(100);
        magician.takeDamage(50);
    }

    @Test(expected = NullPointerException.class)
    public void test_AddMagic_WithNullValue_ShouldThrow() {
        magician.addMagic(null);
    }

    @Test
    public void test_AddMagic_WithCorrectValue_ShouldWork() {
        magician.addMagic(magic);
        assertEquals(1, magician.getMagics().size());
    }

    @Test
    public void test_RemoveMagic_ShouldWork() {
        magician.addMagic(magic);
        magician.removeMagic(magic);
        assertTrue(magician.getMagics().isEmpty());
    }

    @Test
    public void test_GetMagic_Missing_ShouldBeNull() {
        Magic fakeMagic = magician.getMagic(MAGIC_NAME);
        assertNull(fakeMagic);
    }

    @Test
    public void test_GetMagic_ShouldReturn_CorrectMagic() {
        magician.addMagic(magic);
        Magic testMagic = magician.getMagic(MAGIC_NAME);
        assertEquals(testMagic, magic);
    }

}
