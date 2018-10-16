package controllers.counterparty;

import models.counterparty.CounterParty;

public class CounterPartyResponseBody {

    private Integer id;
    private String name;
    private String country;
    private String identificationNumber;

    CounterPartyResponseBody(CounterParty counterParty) {
        this.id = counterParty.getId();
        this.name = counterParty.getName();
        this.country = counterParty.getCountry();
        this.identificationNumber = counterParty.getIdentificationNumber();
    }

    public Integer getId() {
        return this.id;
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
}
