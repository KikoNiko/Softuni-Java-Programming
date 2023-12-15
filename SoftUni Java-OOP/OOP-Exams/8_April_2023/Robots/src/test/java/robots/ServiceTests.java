package robots;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTests {
    private static final String NAME = "Test_Service";
    private static final int CAPACITY = 1;
    private static final String ROBOT_NAME = "Test_Robot";
    private Service service;
    private Robot robot;

    @Before
    public void setUp() {
        service = new Service(NAME, CAPACITY);
        robot = new Robot(ROBOT_NAME);
    }


    @Test(expected = NullPointerException.class)
    public void test_CreateService_WithNullName_ShouldThrow() {
        new Service(null, CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateService_WithEmptyName_ShouldThrow() {
        new Service("    ", CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CreateService_WithNegativeCapacity_ShouldThrow() {
        new Service(NAME, -1);
    }

    @Test
    public void test_CreateService_WithCorrectValues_ShouldSucceed() {
        assertEquals(NAME, service.getName());
        assertEquals(CAPACITY, service.getCapacity());
    }


    @Test(expected = IllegalArgumentException.class)
    public void test_AddRobot_ShouldThrow_WhenCapacityReached() {
        service = new Service(NAME, 0);
        service.add(robot);
    }

    @Test
    public void test_AddRobot_ShouldCorrectly_IncreaseRobotsSize() {
        service.add(robot);
        assertEquals(1, service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveRobot_ShouldThrow_WhenRobotMissing() {
        service.remove(ROBOT_NAME);
    }

    @Test
    public void test_RemoveRobot_ShouldCorrectly_DecreaseRobotsSize() {
        service.add(robot);
        service.remove(robot.getName());
        assertEquals(0, service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ForSale_ShouldThrow_WhenRobotMissing() {
        service.forSale(ROBOT_NAME);
    }

    @Test
    public void test_ForSale_ShouldSetRobotReadyForSale() {
        service.add(robot);
        service.forSale(robot.getName());
        assertFalse(robot.isReadyForSale());
    }

}
