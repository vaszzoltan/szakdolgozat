package com.vaszily.WorkoutPlanner.service.entities;

import com.vaszily.WorkoutPlanner.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements EntityService<Account>{
    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public List<Account> getAllByName(String name) {
        return null;
    }

    @Override
    public Account getById(Long Id) {
        return null;
    }

    @Override
    public Account save(Account toSave) {
        return null;
    }

    @Override
    public Account update(Long id, Account toUpdate) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
