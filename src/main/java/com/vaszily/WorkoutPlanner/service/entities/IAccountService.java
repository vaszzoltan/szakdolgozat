package com.vaszily.WorkoutPlanner.service.entities;

import com.vaszily.WorkoutPlanner.model.auth.Account;

import java.util.List;

public interface IAccountService {
    List<Account> getAll();
    Account getByUserName(String name);
    Account save(Account toSave);
    Account update(String username, Account toUpdate);
    void delete(String username);
}
