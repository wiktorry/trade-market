package wiks.trademarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wiks.trademarket.entities.User;
import wiks.trademarket.entities.UserAuthRequest;
import wiks.trademarket.entities.UserGetResponse;
import wiks.trademarket.exceptions.BadRequestException;
import wiks.trademarket.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserGetResponse createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("User with this email already exists");
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user).toGetResponse();
    }

    @Override
    public UserGetResponse logInUser(UserAuthRequest userRequest) {
        User user = userRepository.findByEmail(userRequest.email())
                .orElseThrow(() -> new BadRequestException("User doesn't exist in database"));
        if (!passwordEncoder.matches(userRequest.password(), user.getPassword())) {
            throw new BadRequestException("User doesn't exist in database");
        }
        return user.toGetResponse();
    }
}
