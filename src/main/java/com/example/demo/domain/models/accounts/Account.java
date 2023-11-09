package com.example.demo.domain.models.accounts;

import com.example.demo.domain.enums.ResultType;
import com.example.demo.domain.models.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


public abstract class Account {

    //VARIABLES
    private final UUID id;
    private final LocalDateTime dateAccountOpened;
    protected BigDecimal balance;
    private boolean isActive;
    @Value("${not-enough-funds-message}")
    protected String NOT_ENOUGH_FUNDS_MESSAGE;
    @Value("${successful-transaction-message}")
    protected String SUCCESSFUL_MESSAGE;
    @Value("${account-not-active-message}")
    protected String ACCOUNT_NOT_ACTIVE_MESSAGE;


    //CONSTRUCTOR
    public Account(LocalDateTime dateAccountOpened, BigDecimal balance) {
        this.id = UUID.randomUUID();
        this.dateAccountOpened = dateAccountOpened;
        this.balance = balance;
        isActive = true;
    }

    //GETTERS
    public UUID getId() {
        return id;
    }
    public LocalDateTime getDateAccountOpened() {
        return dateAccountOpened;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public boolean isActive() {
        return isActive;
    }
    public String getNOT_ENOUGH_FUNDS_MESSAGE() {
        return NOT_ENOUGH_FUNDS_MESSAGE;
    }
    public String getSUCCESSFUL_MESSAGE() {
        return SUCCESSFUL_MESSAGE;
    }
    public String getACCOUNT_NOT_ACTIVE_MESSAGE() {
        return ACCOUNT_NOT_ACTIVE_MESSAGE;
    }

    //SETTERS
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    public void setNOT_ENOUGH_FUNDS_MESSAGE(String NOT_ENOUGH_FUNDS_MESSAGE) {
        this.NOT_ENOUGH_FUNDS_MESSAGE = NOT_ENOUGH_FUNDS_MESSAGE;
    }
    public void setSUCCESSFUL_MESSAGE(String SUCCESSFUL_MESSAGE) {
        this.SUCCESSFUL_MESSAGE = SUCCESSFUL_MESSAGE;
    }
    public void setACCOUNT_NOT_ACTIVE_MESSAGE(String ACCOUNT_NOT_ACTIVE_MESSAGE) {
        this.ACCOUNT_NOT_ACTIVE_MESSAGE = ACCOUNT_NOT_ACTIVE_MESSAGE;
    }

    //METHODS
    public abstract Result withdraw(BigDecimal amount);
    public abstract Result deposit(BigDecimal amount);
    public abstract String getAccountInformation();

    public Result closeAccount() {
        if (balance.signum() != 1) {
            return new Result(ResultType.FAILURE, ACCOUNT_NOT_ACTIVE_MESSAGE);
        }

        setIsActive(false);
        return new Result(ResultType.SUCCESS, SUCCESSFUL_MESSAGE);
    }
}
