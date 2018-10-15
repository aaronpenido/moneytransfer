package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "CounterParty")
public class CounterPartyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String country;

    private String identificationNumber;

    public CounterPartyEntity() {
    }

    public CounterPartyEntity(CounterParty counterParty) {
        this.id = counterParty.getId();
        this.name = counterParty.getName();
        this.country = counterParty.getCountry();
        this.identificationNumber = counterParty.getIdentificationNumber();
    }

    public Integer getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getCountry() {
        return country;
    }

    String getIdentificationNumber() {
        return identificationNumber;
    }
}
