package wiks.trademarket.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiks.trademarket.entities.Account;
import wiks.trademarket.entities.AccountCreateRequest;
import wiks.trademarket.services.AccountService;

@RestController
@RequestMapping("/market/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public Account createAccount(@RequestBody AccountCreateRequest account) {
        return accountService.createAccount(account);
    }
}
