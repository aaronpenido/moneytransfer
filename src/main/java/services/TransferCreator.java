package services;

import models.Transfer;
import models.TransferEntity;
import persistence.TransferRepository;

import javax.inject.Inject;

public class TransferCreator {

    private TransferRepository transferRepository;

    @Inject
    TransferCreator(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public Transfer perform(Transfer transfer) {
        TransferEntity transferEntity = new TransferEntity(transfer);

        TransferEntity savedTransfer = transferRepository.persist(transferEntity);

        return new Transfer(savedTransfer);
    }
}
