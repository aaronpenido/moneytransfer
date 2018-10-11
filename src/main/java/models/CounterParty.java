package models;

import requestbodies.CounterPartyRequestBody;

public class CounterParty {

    private Integer id;
    private String name;
    private String country;
    private String identificationNumber;

    public CounterParty(CounterPartyRequestBody counterPartyRequestBody) {
        this.name = counterPartyRequestBody.getName();
        this.country = counterPartyRequestBody.getCountry();
        this.identificationNumber = counterPartyRequestBody.getIdentificationNumber();
    }

    public CounterParty(CounterPartyEntity counterPartyEntity) {
        this.id = counterPartyEntity.getId();
        this.name = counterPartyEntity.getName();
        this.country = counterPartyEntity.getCountry();
        this.identificationNumber = counterPartyEntity.getIdentificationNumber();
    }

    public Integer getId() {
        return id;
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
