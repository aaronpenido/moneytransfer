package controllers.counterparty;

import models.counterparty.CounterParty;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import services.CounterPartyCreator;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CounterPartyControllerTest {

    private CounterPartyController counterPartyController;

    @Mock
    private CounterPartyCreator counterPartyCreator;

    @Before
    public void setUp() {
        counterPartyController = new CounterPartyController(counterPartyCreator);
    }

    @Test
    public void callCounterPartyCreatorToCreateACounterParty() {
        String name = "Central Bank";
        String country = "GER";
        String identificationNumber = "123456789";

        CounterPartyRequestBody counterPartyRequestBody = new CounterPartyRequestBody(name, country, identificationNumber);

        Integer generatedId = 123;
        CounterParty createdCounterParty = mock(CounterParty.class);
        when(createdCounterParty.getId()).thenReturn(generatedId);

        when(counterPartyCreator.perform(any(CounterParty.class))).thenReturn(createdCounterParty);

        counterPartyController.create(counterPartyRequestBody);

        ArgumentCaptor<CounterParty> captor = ArgumentCaptor.forClass(CounterParty.class);

        verify(counterPartyCreator).perform(captor.capture());

        CounterParty counterParty = captor.getValue();

        assertThat(counterParty.getName()).isEqualTo(name);
        assertThat(counterParty.getCountry()).isEqualTo(country);
        assertThat(counterParty.getIdentificationNumber()).isEqualTo(identificationNumber);
    }

    @Test
    public void returnCounterPartyResponseBodyValues() {
        String name = "Central Bank";
        String country = "GER";
        String identificationNumber = "123456789";
        CounterPartyRequestBody counterPartyRequestBody = new CounterPartyRequestBody(name, country, identificationNumber);

        Integer generatedId = 123;
        CounterParty createdCounterParty = mock(CounterParty.class);
        when(createdCounterParty.getId()).thenReturn(generatedId);

        when(counterPartyCreator.perform(any(CounterParty.class))).thenReturn(createdCounterParty);

        Response response = counterPartyController.create(counterPartyRequestBody);

        CounterPartyResponseBody responseBody = (CounterPartyResponseBody) response.getEntity();

        assertThat(responseBody.getId()).isEqualTo(generatedId);
    }

    @Test
    public void validateRequestBody() {
        CounterPartyRequestBody counterPartyRequestBody = mock(CounterPartyRequestBody.class);

        CounterParty createdCounterParty = mock(CounterParty.class);
        when(counterPartyCreator.perform(any(CounterParty.class))).thenReturn(createdCounterParty);

        counterPartyController.create(counterPartyRequestBody);

        verify(counterPartyRequestBody).validate();
    }
}