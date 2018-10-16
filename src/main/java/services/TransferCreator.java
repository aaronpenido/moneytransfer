package services;

import models.account.AccountEntity;
import models.transfer.Transfer;
import models.transfer.TransferEntity;
import repositories.AccountRepository;
import repositories.TransferRepository;

import javax.inject.Inject;

public class TransferCreator {

    private TransferRepository transferRepository;
    private AccountRepository accountRepository;

    @Inject
    TransferCreator(TransferRepository transferRepository, AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
    }

    public Transfer perform(Transfer transfer) {
        AccountEntity account = accountRepository.findById(transfer.getAccountId());
        AccountEntity receiverAccount = accountRepository.findById(transfer.getReceiverId());

        TransferEntity transferEntity = new TransferEntity(transfer, account, receiverAccount);

        TransferEntity savedTransfer = transferRepository.persist(transferEntity);

        return new Transfer(savedTransfer);
    }
}
