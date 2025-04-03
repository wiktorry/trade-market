package wiks.trademarket.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private int id;
    private Currency currency;
    private double balance;
    private int userId;
}
