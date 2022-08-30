package com.vaszily.WorkoutPlanner.service.entities;

import com.vaszily.WorkoutPlanner.model.BaseEntity;
import java.util.List;

public interface EntityService<T extends BaseEntity> {
    List<T> getAll();
    List<T> getAllByName(String name);
    T getById(Long Id);
    T save(T toSave);
    T update(Long id, T toUpdate);
    void delete(Long id);
}
