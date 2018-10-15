package requestbodies;

import models.AccountState;

import java.math.BigDecimal;

public class AccountRequestBody {

    private String name;
    private BigDecimal balance;
    private String currency;
    private AccountState state;
    private Integer counterPartyId;

    public AccountRequestBody() {
    }

    public AccountRequestBody(String name, BigDecimal balance, String currency, AccountState state, Integer counterPartyId) {
        this.name = name;
        this.balance = balance;
        this.currency = currency;
        this.state = state;
        this.counterPartyId = counterPartyId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public AccountState getState() {
        return state;
    }

    public Integer getCounterPartyId() {
        return counterPartyId;
    }
}
