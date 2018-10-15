import com.google.gson.Gson;
import controllers.CounterPartyController;
import models.CounterParty;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import requestbodies.CounterPartyRequestBody;
import services.CounterPartyCreator;

import static javax.ws.rs.core.Response.Status.CREATED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CounterPartyControllerComponentTest {

    private static final String COUNTERPARTIES_ENDPOINT = "/counterparties";

    private static CounterPartyController counterPartyController;

    @Mock
    private CounterPartyCreator counterPartyCreator;

    private MockHttp mockHttp;

    @Before
    public void setUp() {
        counterPartyController = new CounterPartyController(counterPartyCreator);

        mockHttp = new MockHttp(counterPartyController);

        CounterParty counterParty = mock(CounterParty.class);
        when(counterPartyCreator.perform(any(CounterParty.class))).thenReturn(counterParty);
    }

    @Test
    public void callTransfersEndpoint() throws Exception {
        String name = "Central Bank";
        String country = "GER";
        String identificationNumber = "123456789";

        CounterPartyRequestBody counterPartyRequestBody = new CounterPartyRequestBody(name, country, identificationNumber);

        String requestBodyAsJson = asJson(counterPartyRequestBody);

        MockHttpResponse response = mockHttp.sendAsyncPostRequest(COUNTERPARTIES_ENDPOINT, requestBodyAsJson);

        assertThat(response.getStatus()).isEqualTo(CREATED.getStatusCode());
    }

    private String asJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}