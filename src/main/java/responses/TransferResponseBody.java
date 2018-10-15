package responses;

import models.Transfer;

public class TransferResponseBody {

    private Integer id;

    public TransferResponseBody(Transfer transfer) {
        this.id = transfer.getId();
    }

    public Integer getId() {
        return id;
    }
}
