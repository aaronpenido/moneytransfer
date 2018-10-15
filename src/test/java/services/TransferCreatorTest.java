package services;

import models.Transfer;
import models.TransferEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import persistence.TransferRepository;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransferCreatorTest {

    private TransferCreator transferCreator;

    @Mock
    private TransferRepository transferRepository;

    @Before
    public void setUp() {
        transferCreator = new TransferCreator(transferRepository);
    }

    @Test
    public void callTransferRepositoryToSaveOnDatabaseWithRightValues() {
        Transfer transfer = getTransfer();
        TransferEntity savedTransferEntity = mock(TransferEntity.class);

        when(transferRepository.persist(any(TransferEntity.class))).thenReturn(savedTransferEntity);

        transferCreator.perform(transfer);

        ArgumentCaptor<TransferEntity> captor = ArgumentCaptor.forClass(TransferEntity.class);

        verify(transferRepository).persist(captor.capture());

        TransferEntity transferEntity = captor.getValue();

        TransferEntity expectedTransferEntity = new TransferEntity(transfer);

        assertThat(transferEntity).isEqualToComparingFieldByFieldRecursively(expectedTransferEntity);
    }

    @Test
    public void returnSavedTransferValues() {
        int id = 1;

        Transfer transfer = mock(Transfer.class);
        when(transfer.getId()).thenReturn(id);

        TransferEntity savedTransferEntity = mock(TransferEntity.class);
        when(savedTransferEntity.getId()).thenReturn(id);

        when(transferRepository.persist(any(TransferEntity.class))).thenReturn(savedTransferEntity);

        Transfer savedTransfer = transferCreator.perform(transfer);

        assertThat(savedTransfer.getId()).isEqualTo(id);
    }

    private Transfer getTransfer() {
        Transfer transfer = mock(Transfer.class);

        when(transfer.getId()).thenReturn(1);
        when(transfer.getAccountId()).thenReturn(2);
        when(transfer.getAmount()).thenReturn(BigDecimal.TEN);
        when(transfer.getCurrency()).thenReturn("EUR");
        when(transfer.getDescription()).thenReturn("Description");

        return transfer;
    }
}