package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.repositories.AuthoritiesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesService {
    private final AuthoritiesRepo authoritiesRepo;

    @Autowired
    public AuthoritiesService(AuthoritiesRepo authoritiesRepo){
        this.authoritiesRepo = authoritiesRepo;
    }
}
