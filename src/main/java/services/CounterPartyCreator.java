package services;

import models.counterparty.CounterParty;
import models.counterparty.CounterPartyEntity;
import persistence.CounterPartyRepository;

import javax.inject.Inject;

public class CounterPartyCreator {

    private CounterPartyRepository counterPartyRepository;

    @Inject
    CounterPartyCreator(CounterPartyRepository counterPartyRepository) {
        this.counterPartyRepository = counterPartyRepository;
    }

    public CounterParty perform(CounterParty counterParty) {
        CounterPartyEntity counterPartyEntity = new CounterPartyEntity(counterParty);

        counterPartyEntity = counterPartyRepository.persist(counterPartyEntity);

        return new CounterParty(counterPartyEntity);
    }
}
