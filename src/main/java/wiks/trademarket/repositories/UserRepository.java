package wiks.trademarket.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import wiks.trademarket.entities.User;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userRowMapper = ((rs, rowNum) ->
            new User(rs.getInt("id"), rs.getString("first_name"),
                    rs.getString("last_name"), rs.getString("email"),
                    rs.getString("password")));

    public User save(User user) {
        int id = jdbcTemplate
                .update("INSERT INTO users (first_name, last_name, email, password) VALUES (?,?,?,?)",
                        user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        return jdbcTemplate
                .queryForObject("SELECT * FROM users WHERE id = ?", userRowMapper, id);
    }

    public Optional<User> findByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE email = ?", userRowMapper, email);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
