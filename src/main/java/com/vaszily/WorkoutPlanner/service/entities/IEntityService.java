package com.vaszily.WorkoutPlanner.service.entities;

import com.vaszily.WorkoutPlanner.model.BaseEntity;

import java.security.Principal;
import java.util.List;

public interface IEntityService<T extends BaseEntity> {
    List<T> getAll();
    T getById(Long Id);
    T save(T toSave);
    T update(Long id, T toUpdate, Principal principal);
    void delete(Long id, Principal principal);
}
