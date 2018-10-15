package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "Transfer")
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer accountId;

    private Integer receiverId;

    private BigDecimal amount;

    private String currency;

    private String description;

    private String scheduledFor;

    public TransferEntity(Transfer transfer) {
        this.id = transfer.getId();
        this.accountId = transfer.getAccountId();
        this.receiverId = transfer.getReceiverId();
        this.amount = transfer.getAmount();
        this.currency = transfer.getCurrency();
        this.description = transfer.getDescription();
        this.scheduledFor = transfer.getScheduledFor();
    }

    public Integer getId() {
        return id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public Integer getReceiverId() {
        return receiverId;
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
