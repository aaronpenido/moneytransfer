package controllers.counterparty;

import exceptions.InvalidRequestBodyException;
import validators.RequestBodyValidator;
import models.errors.ErrorResponseBody;
import models.errors.ErrorResponseBodyCollection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CounterPartyRequestBody implements RequestBodyValidator {

    private String name;
    private String country;
    private String identificationNumber;

    public CounterPartyRequestBody() {
    }

    CounterPartyRequestBody(String name, String country, String identificationNumber) {
        this.name = name;
        this.country = country;
        this.identificationNumber = identificationNumber;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    @Override
    public void validate() {
        Map<String, String> errors = new HashMap<>();

        if (isNullOrEmpty(name)) {
            errors.put("invalidName", "Name is required");
        }

        if (isNullOrEmpty(country)) {
            errors.put("invalidCountry", "Country is required");
        }

        if (isNullOrEmpty(identificationNumber)) {
            errors.put("invalidIdentificationNumber", "Identification Number is required");
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

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
