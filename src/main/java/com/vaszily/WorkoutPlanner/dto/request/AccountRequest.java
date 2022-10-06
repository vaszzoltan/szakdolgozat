package com.vaszily.WorkoutPlanner.dto.request;

import com.vaszily.WorkoutPlanner.model.auth.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {
    private String username;
    private String password;

    public Account asEntity(){
        Account account = new Account();
        account.setUsername(this.getUsername());
        account.setPassword(password);
        account.setEnabled('y');
        return account;
    }
}
