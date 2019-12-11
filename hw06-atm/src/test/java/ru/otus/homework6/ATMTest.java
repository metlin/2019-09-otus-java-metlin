package ru.otus.homework6;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ru.otus.homework6.cells.Nominal.*;

public class ATMTest {

    private ATM atm;

    @Test
    public void getBanknotesTestPositive() {
        atm = new SberbankATM(TEN,FIFTY,HUNDRED);
        atm.addBanknotes(10, 50);
        atm.issueRequestedAmount(500);
        int balance = atm.getBalance();

        assertEquals(0, balance);
    }

    @Test
    public void getBanknotesTestNegative() {
        atm = new SberbankATM(TEN,FIFTY,HUNDRED);
        atm.addBanknotes(10, 50);
        atm.issueRequestedAmount(50);
        int balance = atm.getBalance();

        assertNotEquals(100, balance);
    }

    @Test
    public void addBanknotesPositive() {
        atm = new SberbankATM(TEN,FIFTY,HUNDRED);
        atm.addBanknotes(100, 3);
        int balance = atm.getBalance();
        assertEquals(300, balance);
    }

    @Test
    public void addBanknotesNegative() {
        atm = new SberbankATM(TEN,FIFTY,HUNDRED);
        atm.addBanknotes(100, 50);
        int balance = atm.getBalance();
        assertNotEquals(320, balance);
    }
}
