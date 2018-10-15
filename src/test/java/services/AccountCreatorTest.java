package services;

import models.Account;
import models.AccountEntity;
import models.CounterPartyEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import persistence.AccountRepository;
import persistence.CounterPartyRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountCreatorTest {

    private AccountCreator accountCreator;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CounterPartyRepository counterPartyRepository;

    @Before
    public void setUp() {
        accountCreator = new AccountCreator(accountRepository, counterPartyRepository);
    }

    @Test
    public void callAccountRepositoryToSaveOnDatabaseWithRightValues() {
        int counterPartyId = 2;

        CounterPartyEntity counterPartyEntity = mock(CounterPartyEntity.class);
        when(counterPartyEntity.getId()).thenReturn(counterPartyId);

        AccountEntity accountEntity = mock(AccountEntity.class);
        when(accountEntity.getCounterParty()).thenReturn(counterPartyEntity);

        Account account = new Account(accountEntity);

        when(counterPartyRepository.findById(counterPartyId)).thenReturn(counterPartyEntity);
        when(accountRepository.persist(any(AccountEntity.class))).thenReturn(accountEntity);

        accountCreator.perform(account);

        ArgumentCaptor<AccountEntity> captor = ArgumentCaptor.forClass(AccountEntity.class);

        verify(accountRepository).persist(captor.capture());

        AccountEntity savedAccountEntity = captor.getValue();

        AccountEntity expectedAccountEntity = new AccountEntity(account, counterPartyEntity);

        assertThat(savedAccountEntity).isEqualToComparingFieldByFieldRecursively(expectedAccountEntity);
    }

    @Test
    public void returnSavedAccountValues() {
        int id = 1;

        CounterPartyEntity counterPartyEntity = mock(CounterPartyEntity.class);

        AccountEntity accountEntity = mock(AccountEntity.class);
        when(accountEntity.getCounterParty()).thenReturn(counterPartyEntity);

        Account account = new Account(accountEntity);

        when(accountEntity.getId()).thenReturn(id);

        when(counterPartyRepository.findById(any(Integer.class))).thenReturn(counterPartyEntity);
        when(accountRepository.persist(any(AccountEntity.class))).thenReturn(accountEntity);

        Account savedAccount = accountCreator.perform(account);

        assertThat(savedAccount.getId()).isEqualTo(id);
    }
}