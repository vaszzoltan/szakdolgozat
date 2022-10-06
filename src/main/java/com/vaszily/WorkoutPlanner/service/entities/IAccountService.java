package com.vaszily.WorkoutPlanner.service.entities;

import com.vaszily.WorkoutPlanner.model.auth.Account;

import java.util.List;

public interface IAccountService {
    List<Account> getAll();
    Account getByUserName(String name);
    Account getById(Long Id);
    Account save(Account toSave);
    Account update(Long id, Account toUpdate);
    void delete(Long id);
}
