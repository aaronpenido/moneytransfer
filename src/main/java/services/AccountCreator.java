package services;

import models.account.Account;
import models.account.AccountEntity;
import models.counterparty.CounterPartyEntity;
import persistence.AccountRepository;
import persistence.CounterPartyRepository;

import javax.inject.Inject;

public class AccountCreator {

    private AccountRepository accountRepository;
    private CounterPartyRepository counterPartyRepository;

    @Inject
    AccountCreator(AccountRepository accountRepository, CounterPartyRepository counterPartyRepository) {
        this.accountRepository = accountRepository;
        this.counterPartyRepository = counterPartyRepository;
    }

    public Account perform(Account account) {
        Integer counterPartyId = account.getCounterParty().getId();

        CounterPartyEntity counterPartyEntity = counterPartyRepository.findById(counterPartyId);

        AccountEntity accountEntity = new AccountEntity(account, counterPartyEntity);

        AccountEntity savedAccountEntity = accountRepository.persist(accountEntity);

        return new Account(savedAccountEntity);
    }
}
