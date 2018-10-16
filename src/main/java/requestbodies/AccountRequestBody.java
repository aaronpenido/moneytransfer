package requestbodies;

import exceptions.InvalidRequestBodyException;
import models.AccountState;
import responses.ErrorResponseBody;
import responses.ErrorResponseBodyCollection;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountRequestBody implements RequestBodyValidator {

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

    @Override
    public void validate() {
        Map<String, String> errors = new HashMap<>();

        if (balance == null) {
            errors.put("invalidBalance", "Balance is required");
        }

        if (currency == null || currency.isEmpty()) {
            errors.put("invalidCurrency", "Currency is required");
        }

        if (counterPartyId == null) {
            errors.put("invalidCounterPartyId", "Counter Party Id is required");
        }

        if (containsErrors(errors)) {
            Set<ErrorResponseBody> responseErrors = errors.entrySet()
                    .stream()
                    .map(this::getErrorResponseBodyFromEntry)
                    .collect(Collectors.toSet());

            ErrorResponseBodyCollection errorCollection = new ErrorResponseBodyCollection(responseErrors);

            throw new InvalidRequestBodyException(errorCollection);
        }
    }

    private boolean containsErrors(Map<String, String> map) {
        return !map.isEmpty();
    }

    private ErrorResponseBody getErrorResponseBodyFromEntry(Map.Entry<String,String> entry) {
        return new ErrorResponseBody(entry.getKey(), entry.getValue());
    }
}
