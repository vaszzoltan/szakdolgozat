package com.vaszily.WorkoutPlanner.dto.request;

import com.vaszily.WorkoutPlanner.model.auth.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
public class AccountRequest {
    private String username;
    private String password;

    public Account asEntity(){

        Account account = new Account();
        account.setUsername(this.getUsername());
        account.setPassword(new BCryptPasswordEncoder().encode(password));
        account.setEnabled('y');
        return account;
    }
}
