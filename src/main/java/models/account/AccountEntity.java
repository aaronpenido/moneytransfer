package models.account;

import models.counterparty.CounterPartyEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity(name = "Account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private BigDecimal balance;

    private String currency;

    @Enumerated(EnumType.STRING)
    private AccountState state;

    @ManyToOne
    private CounterPartyEntity counterParty;

    public AccountEntity() {
    }

    public AccountEntity(Account account, CounterPartyEntity counterParty) {
        this.id = account.getId();
        this.name = account.getName();
        this.balance = account.getBalance();
        this.currency = account.getCurrency();
        this.state = account.getState();
        this.counterParty = counterParty;
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

    public CounterPartyEntity getCounterParty() {
        return counterParty;
    }
}
