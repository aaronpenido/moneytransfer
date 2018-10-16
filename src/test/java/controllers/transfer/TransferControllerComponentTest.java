package controllers.transfer;

import components.MockHttp;
import models.transfer.Transfer;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import services.TransferCreator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static javax.ws.rs.core.Response.Status.CREATED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransferControllerComponentTest {

    private static final String TRANSFERS_ENDPOINT = "/transfers";

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
        String scheduledFor = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        TransferRequestBody transferRequestBody = new TransferRequestBody(1, 1, BigDecimal.ONE,
                "EUR", "Transfer", scheduledFor);

        when(transferCreator.perform(any(Transfer.class))).thenReturn(new Transfer(transferRequestBody));

        MockHttpResponse response = mockHttp.sendAsyncPostRequest(TRANSFERS_ENDPOINT, transferRequestBody);

        assertThat(response.getStatus()).isEqualTo(CREATED.getStatusCode());
    }
}
