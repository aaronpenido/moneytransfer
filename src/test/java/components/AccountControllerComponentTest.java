package components;

import controllers.AccountController;
import models.Account;
import models.AccountState;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import requestbodies.AccountRequestBody;
import services.AccountCreator;

import java.math.BigDecimal;
import java.net.URISyntaxException;

import static javax.ws.rs.core.Response.Status.CREATED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerComponentTest {

    private static final String ACCOUNTS_ENDPOINT = "/accounts";

    private AccountController accountController;

    private MockHttp mockHttp;

    @Mock
    private AccountCreator accountCreator;

    @Before
    public void setUp() {
        accountController = new AccountController(accountCreator);

        mockHttp = new MockHttp(accountController);

        when(accountCreator.perform(any(Account.class))).thenReturn(mock(Account.class));
    }

    @Test
    public void callAccountsEndpoint() throws URISyntaxException {
        AccountRequestBody accountRequestBody = new AccountRequestBody("Account Name", BigDecimal.TEN,
                "EUR", AccountState.ACTIVE, 1);

        MockHttpResponse response = mockHttp.sendAsyncPostRequest(ACCOUNTS_ENDPOINT, accountRequestBody);

        assertThat(response.getStatus()).isEqualTo(CREATED.getStatusCode());
    }
}