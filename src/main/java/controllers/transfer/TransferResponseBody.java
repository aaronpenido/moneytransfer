package controllers.transfer;

import models.transfer.Transfer;

public class TransferResponseBody {

    private Integer id;

    TransferResponseBody(Transfer transfer) {
        this.id = transfer.getId();
    }

    public Integer getId() {
        return id;
    }
}
