package wiks.trademarket.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import wiks.trademarket.entities.Account;
import wiks.trademarket.entities.Currency;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Account> accountRowMapper = ((rs, rowNum) ->
            new Account(rs.getInt("id"), Currency.valueOf(rs.getString("currency")),
                    rs.getDouble("balance"), rs.getInt("user_id")));

    public List<Account> findAllByUserId(int userId) {
        return jdbcTemplate.query("SELECT * FROM accounts WHERE user_id = ?", accountRowMapper, userId);
    }

    public Account save(Account account) {
        int id = jdbcTemplate
                .update("INSERT INTO accounts (currency, balance, user_id) VALUES (?, ?, ?)",
                        account.getCurrency().toString(), account.getBalance(), account.getUserId());
        return jdbcTemplate
                .queryForObject("SELECT * FROM accounts WHERE id = ?", accountRowMapper, id);
    }
}
