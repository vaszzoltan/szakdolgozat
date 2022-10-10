package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.model.auth.Account;
import com.vaszily.WorkoutPlanner.model.auth.Authorities;
import com.vaszily.WorkoutPlanner.repositories.AccountRepo;
import com.vaszily.WorkoutPlanner.repositories.AuthoritiesRepo;
import com.vaszily.WorkoutPlanner.service.entities.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class AccountService implements IAccountService {
    private final AccountRepo accountRepo;
    private final AuthoritiesRepo authoritiesRepo;

    @Autowired
    public AccountService(AccountRepo accountRepo, AuthoritiesRepo authoritiesRepo) {
        this.authoritiesRepo = authoritiesRepo;
        this.accountRepo = accountRepo;
    }

    @Override
    public List<Account> getAll() {
        return accountRepo.findAll();
    }


    public Account getByUserName(String username) {
        return accountRepo.findByUsername(username).orElseThrow(EntityExistsException::new);
    }

    @Override
    public Account getById(Long Id) {
        return accountRepo.findById(Id).orElseThrow(EntityExistsException::new);
    }

    @Override
    @Transactional
    public Account save(Account toSave) {
        Authorities authorities = new Authorities();
        authorities.setUsername(toSave);
        Account account = accountRepo.save(toSave);
        authorities.setAuthority("WRITE");
        authoritiesRepo.save(authorities);
        authorities.setAuthority("READ");
        authoritiesRepo.save(authorities);
        return account;
    }

    @Override
    public Account update(Long id, Account toUpdate) {
        Account account = getById(id);
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
