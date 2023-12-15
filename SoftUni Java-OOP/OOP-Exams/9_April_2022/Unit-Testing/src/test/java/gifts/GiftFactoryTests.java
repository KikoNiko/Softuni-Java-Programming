package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class GiftFactoryTests {

    private Gift gift;
    private GiftFactory giftFactory;
    private final String TYPE = "Test_Type";
    private final double MAGIC = 1;

    @Before
    public void setUp() {
        this.gift = new Gift(TYPE, MAGIC);
        this.giftFactory = new GiftFactory();
        giftFactory.createGift(gift);
    }

    @Test
    public void test_CreateGift_ShouldIncrease_GiftCount() {
        assertEquals(1, giftFactory.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CreateGift_ShouldThrow() {
        giftFactory.createGift(gift);
    }

    @Test(expected = NullPointerException.class)
    public void test_RemoveGift_ShouldThrow_WhenNullName() {
        giftFactory.removeGift(null);
    }

    @Test(expected = NullPointerException.class)
    public void test_RemoveGift_ShouldThrow_WhenEmptyName() {
        giftFactory.removeGift("  ");
    }

    @Test
    public void test_RemoveGift_ShouldRemove() {
        giftFactory.removeGift(TYPE);
        assertEquals(0, giftFactory.getCount());
    }

    @Test
    public void test_GetPresentWithLeastMagic() {
        giftFactory.createGift(new Gift("Test", 3.5));
        assertEquals(giftFactory.getPresentWithLeastMagic(), gift);
    }

    @Test
    public void test_GetPresentByName() {
        giftFactory.getPresent(gift.getType());
        assertEquals(gift.getType(), TYPE);
    }

    @Test
    public void test_GetPresentByNameShouldFail() {
        Gift fake = giftFactory.getPresent("fake");
        assertTrue(fake == null);
    }

}
