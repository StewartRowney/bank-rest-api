package com.example.demo.domain.models.accounts;

import com.example.demo.domain.enums.ResultType;
import com.example.demo.domain.models.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SavingsAccount extends Account {

    //VARIABLES
    @Value("${savings-account-interest-rate}")
    private double INTEREST_RATE;

    //CONSTRUCTOR
    public SavingsAccount(LocalDateTime dateAccountOpened, BigDecimal balance) {
        super(dateAccountOpened, balance);
    }

    //SETTERS
    public void setINTEREST_RATE(double INTEREST_RATE) {
        this.INTEREST_RATE = INTEREST_RATE;
    }

    //METHODS
    @Override
    public Result withdraw(BigDecimal amount) {
        if (!isActive()) {
            return new Result(ResultType.FAILURE, ACCOUNT_NOT_ACTIVE_MESSAGE);
        }

        BigDecimal newBalance = balance.subtract(amount);
        if (newBalance.signum() != 1) {
            return new Result(ResultType.FAILURE, NOT_ENOUGH_FUNDS_MESSAGE);
        }

        balance = newBalance;
        return new Result(ResultType.SUCCESS, SUCCESSFUL_MESSAGE);
    }

    @Override
    public Result deposit(BigDecimal amount) {
        if (!isActive()) {
            return new Result(ResultType.FAILURE, ACCOUNT_NOT_ACTIVE_MESSAGE);
        }
        balance = balance.add(amount);
        return new Result(ResultType.SUCCESS, SUCCESSFUL_MESSAGE);
    }

    @Override
    public String getAccountInformation() {
        String accountActiveState;
        if (isActive()) {
            accountActiveState = "Account active";
        }
        else {
            accountActiveState = "Account not active";
        }

        return String.format("""
                Savings Account
                Opened on: %s
                %s
                Balance: £%s
                Interest rate: %s
                """, getDateAccountOpened(), accountActiveState, getBalance(), INTEREST_RATE);
    }

    public Result applyInterest() {
        if (!isActive()) {
            return new Result(ResultType.FAILURE, ACCOUNT_NOT_ACTIVE_MESSAGE);
        }
        BigDecimal interestPay = balance.multiply(BigDecimal.valueOf(INTEREST_RATE/100));
        balance = balance.add(interestPay);
        return new Result(ResultType.SUCCESS, SUCCESSFUL_MESSAGE);
    }
}
