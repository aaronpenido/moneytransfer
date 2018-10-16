package controllers.account;

import models.account.Account;

public class AccountResponseBody {

    private Integer id;

    AccountResponseBody(Account account) {
        this.id = account.getId();
    }

    public Integer getId() {
        return id;
    }
}
