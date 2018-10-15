package responses;

import models.Account;

public class AccountResponseBody {

    private Integer id;

    public AccountResponseBody(Account account) {
        this.id = account.getId();
    }

    public Integer getId() {
        return id;
    }
}
