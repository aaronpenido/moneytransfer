package models;

import requestbodies.TransferRequestBody;

import java.math.BigDecimal;

public class Transfer {

    private Integer id;
    private Integer accountId;
    private Integer receiverId;
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

    public Transfer(TransferEntity savedTransfer) {
        this.id = savedTransfer.getId();
        this.accountId = savedTransfer.getAccountId();
        this.receiverId = savedTransfer.getReceiverId();
        this.amount = savedTransfer.getAmount();
        this.currency = savedTransfer.getCurrency();
        this.description = savedTransfer.getDescription();
        this.scheduledFor = savedTransfer.getScheduledFor();
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