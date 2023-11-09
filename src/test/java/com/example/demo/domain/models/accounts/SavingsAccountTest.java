package com.example.demo.domain.models.accounts;

import com.example.demo.domain.enums.ResultType;
import com.example.demo.domain.models.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SavingsAccountTest {

    private SavingsAccount uut;

    @Value("${successful-transaction-message}")
    protected String SUCCESSFUL_MESSAGE;
    @Value("${not-enough-funds-message}")
    protected String NOT_ENOUGH_FUNDS_MESSAGE;
    @Value("${account-not-active-message}")
    protected String ACCOUNT_NOT_ACTIVE_MESSAGE;

    @BeforeEach
    public void beforeEach() {
        uut = new SavingsAccount(LocalDateTime.now(), BigDecimal.valueOf(10000));
        uut.setNOT_ENOUGH_FUNDS_MESSAGE(NOT_ENOUGH_FUNDS_MESSAGE);
        uut.setSUCCESSFUL_MESSAGE(SUCCESSFUL_MESSAGE);
        uut.setACCOUNT_NOT_ACTIVE_MESSAGE(ACCOUNT_NOT_ACTIVE_MESSAGE);
        uut.setINTEREST_RATE(2);
    }

    @Test
    public void testCanWithdrawMoneyFromActiveAccount() {
        Result result = uut.withdraw(BigDecimal.valueOf(5000));
        Result expectedResult = new Result(ResultType.SUCCESS, SUCCESSFUL_MESSAGE);

        assertAll(
                () -> assertEquals(expectedResult, result),
                () -> assertEquals(BigDecimal.valueOf(5000), uut.getBalance())
        );
    }

    @Test
    public void testCannotWithdrawTooMuchMoneyFromActiveAccount() {
        Result result = uut.withdraw(BigDecimal.valueOf(15000));
        Result expectedResult = new Result(ResultType.FAILURE, NOT_ENOUGH_FUNDS_MESSAGE);

        assertAll(
                () -> assertEquals(expectedResult, result),
                () -> assertEquals(BigDecimal.valueOf(10000), uut.getBalance())
        );
    }

    @Test
    public void testCannotWithdrawMoneyFromDeactivatedAccount() {
        uut.closeAccount();
        Result result = uut.withdraw(BigDecimal.valueOf(5000));
        Result expectedResult = new Result(ResultType.FAILURE, ACCOUNT_NOT_ACTIVE_MESSAGE);

        assertAll(
                () -> assertEquals(expectedResult, result),
                () -> assertEquals(BigDecimal.valueOf(10000), uut.getBalance())
        );
    }

    @Test
    public void testCanDepositMoneyToActiveAccount() {
        Result result = uut.deposit(BigDecimal.valueOf(5000));
        Result expectedResult = new Result(ResultType.SUCCESS, SUCCESSFUL_MESSAGE);

        assertAll(
                () -> assertEquals(expectedResult, result),
                () -> assertEquals(BigDecimal.valueOf(15000), uut.getBalance())
        );
    }

    @Test
    public void testCannotDepositMoneyToDeactivatedAccount() {
        uut.closeAccount();
        Result result = uut.deposit(BigDecimal.valueOf(5000));
        Result expectedResult = new Result(ResultType.FAILURE, ACCOUNT_NOT_ACTIVE_MESSAGE);

        assertAll(
                () -> assertEquals(expectedResult, result),
                () -> assertEquals(BigDecimal.valueOf(10000), uut.getBalance())
        );
    }

    @Test
    public void testCanApplyInterestToActiveAccount() {
        Result result = uut.applyInterest();
        Result expectedResult = new Result(ResultType.SUCCESS, SUCCESSFUL_MESSAGE);

        assertAll(
                () -> assertEquals(expectedResult, result),
                () -> assertEquals( 0, BigDecimal.valueOf(10200.00).compareTo(uut.getBalance()))
        );
    }

    @Test
    public void testCannotApplyInterestToDeactivatedAccount() {
        uut.closeAccount();
        Result result = uut.applyInterest();
        Result expectedResult = new Result(ResultType.FAILURE, ACCOUNT_NOT_ACTIVE_MESSAGE);

        assertAll(
                () -> assertEquals(expectedResult, result),
                () -> assertEquals(BigDecimal.valueOf(10000), uut.getBalance())
        );
    }

}