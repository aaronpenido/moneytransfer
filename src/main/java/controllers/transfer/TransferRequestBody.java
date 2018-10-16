package controllers.transfer;

import exceptions.InvalidRequestBodyException;
import validators.RequestBodyValidator;
import models.errors.ErrorResponseBody;
import models.errors.ErrorResponseBodyCollection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TransferRequestBody implements RequestBodyValidator {

    private Integer accountId;
    private Integer receiverId;
    private BigDecimal amount;
    private String currency;
    private String description;
    private String scheduledFor;

    public TransferRequestBody() {
    }

    TransferRequestBody(Integer accountId, Integer receiverId, BigDecimal amount,
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

    @Override
    public void validate() {
        Map<String, String> errors = new HashMap<>();

        if (accountId == null) {
            errors.put("invalidAccountId", "Account Id is required");
        }

        if (receiverId == null) {
            errors.put("invalidReceiverId", "Receiver Id is required");
        }

        if (amount == null) {
            errors.put("invalidAmount", "Amount is required");
        }

        if (isAmountZero()) {
            errors.put("invalidAmount", "Amount must be grater than zero");
        }

        if (isNullOrEmpty(currency)) {
            errors.put("invalidCurrency", "Currency is required");
        }

        errors.putAll(getScheduledForErrors());

        if (containsErrors(errors)) {
            Set<ErrorResponseBody> responseErrors = errors.entrySet()
                    .stream()
                    .map(this::getErrorResponseBodyFromEntry)
                    .collect(Collectors.toSet());

            ErrorResponseBodyCollection errorCollection = new ErrorResponseBodyCollection(responseErrors);

            throw new InvalidRequestBodyException(errorCollection);
        }
    }

    private Map<String, String> getScheduledForErrors() {
        Map<String, String> errors = new HashMap<>();
        String pattern = "yyyy-MM-dd";

        try {
            LocalDate localDate = LocalDate.parse(scheduledFor, DateTimeFormatter.ofPattern(pattern));

            if (localDate.isBefore(LocalDate.now())) {
                errors.put("invalidScheduledFor", "ScheduledFor must be equals or after the current date");
            }
        } catch (DateTimeParseException dateTimeParseException) {
            errors.put("invalidScheduledFor", String.format("ScheduledFor field must be in the '%s' format", pattern));
        } catch (NullPointerException exception) {
            errors.put("invalidScheduledFor", "ScheduledFor is required");
        }

        return errors;
    }

    private boolean isAmountZero() {
        return amount != null && amount.compareTo(BigDecimal.ZERO) == 0;
    }

    private boolean containsErrors(Map<String, String> map) {
        return !map.isEmpty();
    }

    private ErrorResponseBody getErrorResponseBodyFromEntry(Map.Entry<String,String> entry) {
        return new ErrorResponseBody(entry.getKey(), entry.getValue());
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
