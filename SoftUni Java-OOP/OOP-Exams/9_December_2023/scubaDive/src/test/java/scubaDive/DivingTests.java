package scubaDive;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DivingTests {

    private final String DIVING_NAME = "TEST";
    private final int CAPACITY = 1;
    private final String DIVER_NAME = "TEST_DIVER";
    private final double OXYGEN = 50;
    private Diving diving;
    private DeepWaterDiver deepWaterDiver;

    @Before
    public void setUp() {
        diving = new Diving(DIVING_NAME, CAPACITY);
        deepWaterDiver = new DeepWaterDiver(DIVER_NAME, OXYGEN);
    }


    @Test(expected = NullPointerException.class)
    public void test_CreateDiving_WithNullName_ShouldThrow() {
        new Diving(null, CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateDiving_WithEmptyName_ShouldThrow() {
        new Diving("    ", CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CreateDiving_WithNegativeCapacity_ShouldThrow() {
        new Diving(DIVING_NAME, -1);
    }

    @Test
    public void test_CreateDiving_WithCorrectValues_ShouldSucceed() {
        assertEquals(DIVING_NAME, diving.getName());
        assertEquals(CAPACITY, diving.getCapacity());
    }

    @Test
    public void test_CreateDiving_CorrectlyInitializes_NewEmptyList() {
        assertEquals(0, diving.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddDiver_WhenCapacityReached_ShouldThrow() {
        Diving testDiving = new Diving(DIVING_NAME, 0);
        testDiving.addDeepWaterDiver(deepWaterDiver);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddDiver_WhenDiverExists_ShouldThrow() {
        diving.addDeepWaterDiver(deepWaterDiver);
        diving.addDeepWaterDiver(deepWaterDiver);
    }

    @Test
    public void test_AddDiver_WithCorrectValue_ShouldSucceed() {
        diving.addDeepWaterDiver(deepWaterDiver);
        assertEquals(1, diving.getCount());
    }

    @Test
    public void test_RemoveDiver_ShouldCorrectlyRemove() {
        diving.addDeepWaterDiver(deepWaterDiver);
        assertTrue(diving.removeDeepWaterDiver(DIVER_NAME));
        assertEquals(0, diving.getCount());
    }

    @Test
    public void test_RemoveDiver_WhenDiverNotExist_ShouldReturnFalse() {
        assertFalse(diving.removeDeepWaterDiver(DIVER_NAME));
    }

}
