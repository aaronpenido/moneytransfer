package requestbodies;

public class CounterPartyRequestBody {

    private String name;
    private String country;
    private String identificationNumber;

    public CounterPartyRequestBody() {
    }

    public CounterPartyRequestBody(String name, String country, String identificationNumber) {
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
}
