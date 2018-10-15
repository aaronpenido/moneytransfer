package requestbodies;

import java.math.BigDecimal;

public class TransferRequestBody {

    private Integer accountId;
    private Integer receiverId;
    private BigDecimal amount;
    private String currency;
    private String description;
    private String scheduledFor;

    public TransferRequestBody() {
    }

    public TransferRequestBody(Integer accountId, Integer receiverId, BigDecimal amount,
                               String currency, String description, String scheduledFor) {
        this.accountId = accountId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.scheduledFor = scheduledFor;
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
