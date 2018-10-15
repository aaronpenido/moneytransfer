package components;

import com.google.gson.Gson;
import controllers.TransferController;
import models.Transfer;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import requestbodies.TransferRequestBody;
import services.TransferCreator;

import java.math.BigDecimal;

import static javax.ws.rs.core.Response.Status.CREATED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransferControllerComponentTest {

    private TransferController transferController;

    @Mock
    private TransferCreator transferCreator;

    private MockHttp mockHttp;

    @Before
    public void setUp() {
        transferController = new TransferController(transferCreator);

        mockHttp = new MockHttp(transferController);
    }

    @Test
    public void callTransfersEndpoint() throws Exception {
        TransferRequestBody transferRequestBody = new TransferRequestBody(1, 1, BigDecimal.ONE,
                "EUR", "Transfer", "2018-09-10");

        when(transferCreator.perform(any(Transfer.class))).thenReturn(new Transfer(transferRequestBody));

        String requestBody = new Gson().toJson(transferRequestBody);

        MockHttpResponse response = mockHttp.sendAsyncPostRequest("/transfers", requestBody);

        assertThat(response.getStatus()).isEqualTo(CREATED.getStatusCode());
    }
}
