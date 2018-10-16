package models.transfer;

import models.account.AccountEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity(name = "Transfer")
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private AccountEntity account;

    @ManyToOne(fetch = FetchType.LAZY)
    private AccountEntity receiverAccount;

    private BigDecimal amount;

    private String currency;

    private String description;

    private String scheduledFor;

    public TransferEntity() {
    }

    public TransferEntity(Transfer transfer, AccountEntity account, AccountEntity receiverAccount) {
        this.id = transfer.getId();
        this.account = account;
        this.receiverAccount = receiverAccount;
        this.amount = transfer.getAmount();
        this.currency = transfer.getCurrency();
        this.description = transfer.getDescription();
        this.scheduledFor = transfer.getScheduledFor();
    }

    public Integer getId() {
        return id;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public AccountEntity getReceiverAccount() {
        return receiverAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDescription() {
        return description;
    }

    public String getScheduledFor() {
        return scheduledFor;
    }
}
