package wiks.trademarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wiks.trademarket.entities.Account;
import wiks.trademarket.entities.AccountCreateRequest;
import wiks.trademarket.exceptions.BadRequestException;
import wiks.trademarket.repositories.AccountRepository;
import wiks.trademarket.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public Account createAccount(AccountCreateRequest account) {
        if (userRepository.findById(account.userId()).isEmpty()) {
            throw new BadRequestException("User not found");
        }
        List<Account> accountList = accountRepository.findAllByUserId(account.userId());
        accountList.forEach((userAccount) -> {
            if (account.currency() == userAccount.getCurrency()) {
                throw new BadRequestException("Account with this currency already exists");
            }
        });
        return accountRepository.save(new Account(0, account.currency(), 0, account.userId()));
    }
}
