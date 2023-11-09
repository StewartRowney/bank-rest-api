package com.example.demo.domain.models.accounts;

import com.example.demo.domain.enums.ResultType;
import com.example.demo.domain.models.Result;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


public class CurrentAccount extends Account {

    //VARIABLES
    private BigDecimal overdraft;

    //CONSTRUCTOR
    public CurrentAccount(LocalDateTime dateAccountOpened, BigDecimal balance, BigDecimal overdraft) {
        super(dateAccountOpened, balance);
        this.overdraft = overdraft;
    }

    //GETTERS
    public BigDecimal getOverdraft() {
        return overdraft;
    }

    //SETTERS
    public void setOverdraft(BigDecimal overdraft) {
        this.overdraft = overdraft;
    }

    //METHODS
    @Override
    public Result withdraw(BigDecimal amount) {
        if (!isActive()) {
            return new Result(ResultType.FAILURE, ACCOUNT_NOT_ACTIVE_MESSAGE);
        }

        BigDecimal newBalance = balance.subtract(amount);
        if (newBalance.add(overdraft).signum() != 1) {
            return new Result(ResultType.FAILURE, NOT_ENOUGH_FUNDS_MESSAGE);
        }

        balance = newBalance;
        return new Result(ResultType.SUCCESS, SUCCESSFUL_MESSAGE);
    }

    @Override
    public Result deposit(BigDecimal amount) {
        if (!isActive()) {
            return new Result(ResultType.FAILURE, "Account has been deactivated");
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
                Current Account
                Opened on: %s
                %s
                Balance: £%s
                Overdraft: £%s
                """, getDateAccountOpened(), accountActiveState, getBalance(), overdraft);
    }
}
