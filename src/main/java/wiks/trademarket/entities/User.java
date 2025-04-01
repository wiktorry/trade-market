package wiks.trademarket.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserGetResponse toGetResponse() {
        return new UserGetResponse(this.id, this.firstName, this.lastName, this.email);
    }
}
