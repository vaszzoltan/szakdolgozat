package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.model.auth.Account;
import com.vaszily.WorkoutPlanner.repositories.AccountRepo;
import com.vaszily.WorkoutPlanner.service.entities.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class AccountService implements IAccountService {
    private final AccountRepo accountRepo;
    @Autowired
    public AccountService(AccountRepo accountRepo){
        this.accountRepo = accountRepo;
    }
    @Override
    public List<Account> getAll() {
        return accountRepo.findAll();
    }

    @Override
    public List<Account> getAllByName(String username) {
        return accountRepo.findAllByUsername(username);
    }

    @Override
    public Account getById(Long Id) {
        return accountRepo.findById(Id).orElseThrow(EntityExistsException::new);
    }

    @Override
    public Account save(Account toSave) {
        return accountRepo.save(toSave);
    }

    @Override
    public Account update(Long id, Account toUpdate) {
        Account account  = getById(id);
        account.setUsername(toUpdate.getUsername());
        account.setWorkouts(toUpdate.getWorkouts());
        account.setWorkoutPlans(toUpdate.getWorkoutPlans());
        return account;
    }

    @Override
    public void delete(Long id) {
        accountRepo.delete(getById(id));
    }
}
