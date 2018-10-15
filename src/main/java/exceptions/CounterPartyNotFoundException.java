package exceptions;

public class CounterPartyNotFoundException extends RuntimeException {

    public CounterPartyNotFoundException(Integer counterPartyId) {
        super(String.format("Counterparty with id %s was not found", counterPartyId));
    }
}
