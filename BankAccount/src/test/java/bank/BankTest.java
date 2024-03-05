package bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankTest {

    BankAccount account;
    
    @BeforeEach
    void setup(){
        account = new BankAccount(10);
    }

    @DisplayName("Doesn´t deposit negative amount")
    @Test
    void deposit_NegativeAmount_ThrowException(){
        int negativeNumber = -1;
        
        assertThrows(IllegalArgumentException.class, () -> {account.deposit(negativeNumber);});
    }

    @DisplayName("Deposit correctly amount")
    @Test
    void deposit_PositiveAmount_ReturnsBalance(){
        int expectedValue = account.getBalance() + 5;

        double returnValue = account.deposit(5);

        assertEquals(returnValue, expectedValue);
    }

    @DisplayName("Don´t withdraw more than balance")
    @Test
    void withdraw_MoreThanBalance_ReturnFalse(){
        boolean expectedValue = false;
        int balance = account.getBalance();

        boolean returnValue = account.withdraw(balance + 1);

        assertEquals(returnValue, expectedValue);
    }

    @DisplayName("Withdraw correct amount correctly")
    @Test
    void withdraw_LessOrEqualToBalance_ReturnTrue(){
        boolean expectedValue = true;
        int balance = account.getBalance();

        boolean returnValue = account.withdraw(balance);

        assertEquals(returnValue, expectedValue);
        assertEquals(account.getBalance(),0);
    }

    @DisplayName("Returns correctly the balance")
    @Test
    void getBalance_ReturnBalance(){
        int expectedValue = 10;

        int returnValue = account.getBalance();

        assertEquals(returnValue, expectedValue);
    }

    @DisplayName("Calculates correctly the monthly payments of loans")
    @Test
    void payment_AnyParameters_ReturnTotalAmount(){
        double total_amount = 10000;
        double interest = 0.001;
        int npayments = 12;
        double expectedValue = 838.759926;

        double returnValue = account.payment(total_amount, interest, npayments);

        assertEquals(returnValue, expectedValue,6);
    }

    @DisplayName("If months equal 0 returns amount")
    @Test
    void pending_0Months_ReturnAmount(){
        double total_amount = 10000; 
        double interest = 0.001;
        int npayments = 12;
        int month=0;
        double expectedValue = total_amount;

        double returnValue = account.pending(total_amount, interest, npayments, month);

        assertEquals(returnValue, expectedValue);
    }

    @DisplayName("If months are greater than 0 return correct pending")
    @Test
    void pending_MonthsGreaterThan0_ReturnCorrectAmount(){
        double total_amount = 10000; 
        double interest = 0.001;
        int npayments = 12;
        int month=2;
        double expectedValue = 8341.651389;

        double returnValue = account.pending(total_amount, interest, npayments,month);

        assertEquals(returnValue, expectedValue,6);
    }

}