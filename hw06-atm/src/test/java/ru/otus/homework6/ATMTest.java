package ru.otus.homework6;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ATMTest {

    private ATM atm;

    @Test
    public void getBanknotesTestPositive() {
        atm = new SberbankATM();
        atm.addBanknotes(10, 50, 100);
        atm.issueRequestedAmount(60);
        int balance = atm.getBalance();

        assertEquals(100, balance);
    }

    @Test
    public void getBanknotesTestNegative() {
        atm = new SberbankATM();
        atm.addBanknotes(10, 50, 100);
        atm.issueRequestedAmount(50);
        int balance = atm.getBalance();

        assertNotEquals(100, balance);
    }

    @Test
    public void addBanknotesPositive() {
        atm = new SberbankATM();
        atm.addBanknotes(100, 50, 100, 10, 50);
        int balance = atm.getBalance();
        assertEquals(310, balance);
    }

    @Test
    public void addBanknotesNegative() {
        atm = new SberbankATM();
        atm.addBanknotes(100, 50, 100, 10, 50);
        int balance = atm.getBalance();
        assertNotEquals(320, balance);
    }
}
