package models;

import requestbodies.TransferRequestBody;

import java.math.BigDecimal;

public class Transfer {

    private int id;
    private int accountId;
    private int receiverId;
    private BigDecimal amount;
    private String currency;
    private String description;
    private String scheduledFor;

    public Transfer(TransferRequestBody transferRequestBody) {
        this.accountId = transferRequestBody.getAccountId();
        this.receiverId = transferRequestBody.getReceiverId();
        this.amount = transferRequestBody.getAmount();
        this.currency = transferRequestBody.getCurrency();
        this.description = transferRequestBody.getDescription();
        this.scheduledFor = transferRequestBody.getScheduledFor();
    }

    public int getId() {
        return id;
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
