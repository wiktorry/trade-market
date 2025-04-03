package wiks.trademarket.services;

import wiks.trademarket.entities.Account;
import wiks.trademarket.entities.AccountCreateRequest;

public interface AccountService {
    Account createAccount(AccountCreateRequest account);
}
