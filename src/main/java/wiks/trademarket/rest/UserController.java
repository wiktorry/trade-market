package wiks.trademarket.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiks.trademarket.entities.User;
import wiks.trademarket.entities.UserAuthRequest;
import wiks.trademarket.entities.UserGetResponse;
import wiks.trademarket.services.UserService;

@RestController
@RequestMapping("/market/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signUp")
    public UserGetResponse signUp(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/signIn")
    public UserGetResponse signIn(@RequestBody UserAuthRequest userRequest) {
        return userService.logInUser(userRequest);
    }
}
