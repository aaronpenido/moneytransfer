import controllers.TransferController;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.core.SynchronousDispatcher;
import org.jboss.resteasy.core.SynchronousExecutionContext;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.net.URISyntaxException;

import static javax.ws.rs.core.Response.Status.OK;
import static org.assertj.core.api.Assertions.assertThat;

@Ignore
public class TransferControllerComponentTest {

    private static Dispatcher dispatcher;
    private static TransferController transferController;

    @Before
    public void setUp() {
        transferController = new TransferController();
        dispatcher = MockDispatcherFactory.createDispatcher();
        dispatcher.getRegistry().addSingletonResource(transferController);
    }

    @Test
    public void helloTest() throws Exception {
        MockHttpResponse response = sendAsyncPostRequest("/transfers", null);

        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
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
