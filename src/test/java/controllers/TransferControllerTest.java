package controllers;

import models.Transfer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import requestbodies.TransferRequestBody;
import responses.TransferResponseBody;
import services.TransferCreator;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransferControllerTest {

    private TransferController transferController;

    @Mock
    private TransferCreator transferCreator;

    @Before
    public void setUp() {
        transferController = new TransferController(transferCreator);
    }

    @Test
    public void callTransferCreatorToCreateATransfer() {
        TransferRequestBody transferRequestBody = mock(TransferRequestBody.class);

        Transfer expectedTransfer = new Transfer(transferRequestBody);

        Transfer savedTransfer = mock(Transfer.class);
        when(transferCreator.perform(any(Transfer.class))).thenReturn(savedTransfer);

        transferController.create(transferRequestBody);

        ArgumentCaptor<Transfer> captor = ArgumentCaptor.forClass(Transfer.class);

        verify(transferCreator).perform(captor.capture());

        Transfer transfer = captor.getValue();

        assertThat(transfer).isEqualToComparingFieldByFieldRecursively(expectedTransfer);
    }

    @Test
    public void returnTransferResponseBodyValues() {
        int id = 1;
        int accountId = 2;
        int receiverId = 3;
        BigDecimal amount = BigDecimal.TEN;
        String currency = "EUR";
        String description = "Description";
        String scheduledFor = "2018-09-10";

        TransferRequestBody transferRequestBody = new TransferRequestBody(accountId, receiverId, amount, currency, description, scheduledFor);

        Transfer savedTransfer = mock(Transfer.class);
        when(savedTransfer.getId()).thenReturn(id);
        when(transferCreator.perform(any(Transfer.class))).thenReturn(savedTransfer);

        Response response = transferController.create(transferRequestBody);

        TransferResponseBody transferResponseBody = (TransferResponseBody) response.getEntity();

        assertThat(transferResponseBody.getId()).isEqualTo(id);
    }
}