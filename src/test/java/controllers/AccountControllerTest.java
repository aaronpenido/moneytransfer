package controllers;

import models.Account;
import models.AccountState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import requestbodies.AccountRequestBody;
import responses.AccountResponseBody;
import services.AccountCreator;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    private AccountController accountController;

    @Mock
    private AccountCreator accountCreator;

    @Before
    public void setUp() {
        accountController = new AccountController(accountCreator);
    }

    @Test
    public void callAccountCreatorToCreateAnAccount() {
        AccountRequestBody accountRequestBody = getAccountRequestBody();

        Account account = new Account(accountRequestBody);

        when(accountCreator.perform(any(Account.class))).thenReturn(account);

        accountController.create(accountRequestBody);

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);

        verify(accountCreator).perform(captor.capture());

        Account accountSentToCreator = captor.getValue();

        assertThat(accountSentToCreator).isEqualToComparingFieldByFieldRecursively(account);
    }

    @Test
    public void returnAccountResponseBodyValues() {
        int id = 1;

        AccountRequestBody accountRequestBody = getAccountRequestBody();

        Account account = mock(Account.class);
        when(account.getId()).thenReturn(id);

        when(accountCreator.perform(any(Account.class))).thenReturn(account);

        Response response = accountController.create(accountRequestBody);

        AccountResponseBody accountResponseBody = (AccountResponseBody) response.getEntity();

        assertThat(accountResponseBody.getId()).isEqualTo(id);
    }

    private AccountRequestBody getAccountRequestBody() {
        String name = "Account Name";
        BigDecimal balance = BigDecimal.TEN;
        String currency = "EUR";
        AccountState state = AccountState.ACTIVE;
        Integer counterPartyId = 1;

        return new AccountRequestBody(name, balance, currency, state, counterPartyId);
    }
}