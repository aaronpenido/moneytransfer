package components;

import com.google.gson.Gson;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.core.SynchronousDispatcher;
import org.jboss.resteasy.core.SynchronousExecutionContext;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;

import javax.ws.rs.core.MediaType;
import java.net.URISyntaxException;

public class MockHttp {

    private static Dispatcher dispatcher;

    public MockHttp(Object controller) {
        dispatcher = MockDispatcherFactory.createDispatcher();
        dispatcher.getRegistry().addSingletonResource(controller);
    }

    public MockHttpResponse sendAsyncPostRequest(String path, Object requestBody) throws URISyntaxException {

        MockHttpRequest request = MockHttpRequest.post(path);

        if (requestBody != null) {
            request.accept(MediaType.APPLICATION_JSON);
            request.contentType(MediaType.APPLICATION_JSON_TYPE);

            String requestBodyAsJson = asJson(requestBody);
            request.content(requestBodyAsJson.getBytes());
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

    private String asJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
