package requestbodies;

import java.math.BigDecimal;

public class TransferRequestBody {

    private int accountId;
    private int receiverId;
    private BigDecimal amount;
    private String currency;
    private String description;
    private String scheduledFor;

    public TransferRequestBody() {
    }

    public TransferRequestBody(int accountId, int receiverId, BigDecimal amount,
                               String currency, String description, String scheduledFor) {
        this.accountId = accountId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.scheduledFor = scheduledFor;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getReceiverId() {
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
