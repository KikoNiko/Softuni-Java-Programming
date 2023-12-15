package bank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BankTests {

    private static final String NAME = "Test_Bank";
    private static final int CAPACITY = 100;
    private Bank bank;
    private Client client;

    @Before
    public void setUp() {
        this.bank = new Bank(NAME, CAPACITY);
        this.client = new Client("test_client");
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateBank_With_NullName_ShouldThrow() {
        new Bank(null, CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateBank_With_EmptyName_ShouldThrow() {
        new Bank("   ", CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CreateBank_WithNegativeCapacity_ShouldThrow() {
        new Bank(NAME, -1);
    }

    @Test
    public void test_CreateBank_WithCorrectArguments_ShouldSucceed() {
        Bank bank = new Bank(NAME, CAPACITY);
        assertEquals(NAME, bank.getName());
        assertEquals(CAPACITY, bank.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddClient_ShouldFail_WhenCapacityReached() {
        Bank bank = new Bank(NAME, 0);
        bank.addClient(client);
    }

    @Test
    public void test_AddClient_Should_CorrectlyIncrease_ClientCount() {
        bank.addClient(client);
        assertEquals(1, bank.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveClient_ShouldFail_WhenClientMissing() {
        bank.removeClient(client.getName());
    }

    @Test
    public void test_RemoveClient_Should_CorrectlyDecrease_ClientCount() {
        bank.addClient(client);
        bank.removeClient(client.getName());
        assertEquals(0, bank.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_LoanWithdrawal_ShouldFail_WhenClientMissing() {
        bank.removeClient(client.getName());
    }

    @Test
    public void test_LoanWithdrawal_Should_SetClient_NotApprovedForLoan() {
        bank.addClient(client);
        bank.loanWithdrawal(client.getName());
        assertFalse(client.isApprovedForLoan());
    }
}
