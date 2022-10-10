package com.vaszily.WorkoutPlanner.dto.request;

import com.vaszily.WorkoutPlanner.model.auth.Account;
import com.vaszily.WorkoutPlanner.service.others.password.ValidPassword;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AccountRequest {
    @NotBlank
    private String username;
    @ValidPassword
    private String password;

    public Account asEntity(){

        Account account = new Account();
        account.setUsername(this.getUsername());
        account.setPassword(new BCryptPasswordEncoder().encode(password));
        account.setEnabled('y');
        return account;
    }
}
