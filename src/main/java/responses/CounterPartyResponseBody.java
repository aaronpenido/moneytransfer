package responses;

import models.CounterParty;

import java.util.UUID;

public class CounterPartyResponseBody {

    private final UUID id;

    public CounterPartyResponseBody(CounterParty counterParty) {
        this.id = counterParty.getId();
    }

    public UUID getId() {
        return this.id;
    }
}
