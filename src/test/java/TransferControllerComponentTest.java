import com.google.gson.Gson;
import controllers.TransferController;
import models.Transfer;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.core.SynchronousDispatcher;
import org.jboss.resteasy.core.SynchronousExecutionContext;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import requestbodies.TransferRequestBody;
import services.TransferCreator;

import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.net.URISyntaxException;

import static javax.ws.rs.core.Response.Status.CREATED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransferControllerComponentTest {

    private static Dispatcher dispatcher;

    private TransferController transferController;

    @Mock
    private TransferCreator transferCreator;

    @Before
    public void setUp() {
        transferController = new TransferController(transferCreator);
        dispatcher = MockDispatcherFactory.createDispatcher();
        dispatcher.getRegistry().addSingletonResource(transferController);
    }

    @Test
    public void callTransfersEndpoint() throws Exception {
        TransferRequestBody transferRequestBody = new TransferRequestBody(1, 1, BigDecimal.ONE,
                "EUR", "Transfer", "2018-09-10");

        when(transferCreator.perform(any(Transfer.class))).thenReturn(new Transfer(transferRequestBody));

        String requestBody = new Gson().toJson(transferRequestBody);

        MockHttpResponse response = sendAsyncPostRequest("/transfers", requestBody);

        assertThat(response.getStatus()).isEqualTo(CREATED.getStatusCode());
    }

    private MockHttpResponse sendAsyncPostRequest(String path, String requestBody) throws URISyntaxException {

        MockHttpRequest request = MockHttpRequest.post(path);

        if (requestBody != null) {
            request.accept(MediaType.APPLICATION_JSON);
            request.contentType(MediaType.APPLICATION_JSON_TYPE);
            request.content(requestBody.getBytes());
        }

        MockHttpResponse response = new MockHttpResponse();
        SynchronousExecutionContext synchronousExecutionContext = new SynchronousExecutionContext((SynchronousDispatcher) dispatcher, request, response);
        request.setAsynchronousContext(synchronousExecutionContext);

        return sendHttpRequest(request, response);
    }

    private MockHttpResponse sendHttpRequest(MockHttpRequest request, MockHttpResponse response) {
        dispatcher.invoke(request, response);
        return response;
    }
}
