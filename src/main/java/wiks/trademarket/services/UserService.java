package wiks.trademarket.services;

import wiks.trademarket.entities.User;
import wiks.trademarket.entities.UserAuthRequest;
import wiks.trademarket.entities.UserGetResponse;

public interface UserService {
    UserGetResponse createUser(User user);

    UserGetResponse logInUser(UserAuthRequest userRequest);
}
