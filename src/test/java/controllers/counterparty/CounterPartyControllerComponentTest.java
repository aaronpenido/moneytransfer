package controllers.counterparty;

import components.MockHttp;
import models.counterparty.CounterParty;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import services.CounterPartyCreator;

import static javax.ws.rs.core.Response.Status.CREATED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CounterPartyControllerComponentTest {

    private static final String COUNTERPARTIES_ENDPOINT = "/counterparties";

    private CounterPartyController counterPartyController;

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
        CounterPartyRequestBody counterPartyRequestBody =
                new CounterPartyRequestBody("Central Bank", "GER", "123456789");

        MockHttpResponse response = mockHttp.sendAsyncPostRequest(COUNTERPARTIES_ENDPOINT, counterPartyRequestBody);

        assertThat(response.getStatus()).isEqualTo(CREATED.getStatusCode());
    }
}