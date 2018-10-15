package models;

import java.math.BigDecimal;

public class Account {

    private Integer id;
    private String name;
    private BigDecimal balance;
    private String currency;
    private AccountState state;
    private CounterParty counterParty;

    public Account(AccountEntity accountEntity) {
        this.id = accountEntity.getId();
        this.name = accountEntity.getName();
        this.balance = accountEntity.getBalance();
        this.currency = accountEntity.getCurrency();
        this.state = accountEntity.getState();
        this.counterParty = new CounterParty(accountEntity.getCounterParty());
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    BigDecimal getBalance() {
        return balance;
    }

    String getCurrency() {
        return currency;
    }

    AccountState getState() {
        return state;
    }

    public CounterParty getCounterParty() {
        return counterParty;
    }
}
