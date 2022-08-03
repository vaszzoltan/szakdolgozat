package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.model.WorkoutPlan;
import com.vaszily.WorkoutPlanner.service.entities.EntityService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WorkoutPlanService implements EntityService<WorkoutPlan> {
    @Override
    public List<WorkoutPlan> getAll() {
        return null;
    }

    @Override
    public List<WorkoutPlan> getAllByName(String name) {
        return null;
    }

    @Override
    public WorkoutPlan getById(Long Id) {
        return null;
    }

    @Override
    public WorkoutPlan save(WorkoutPlan toSave) {
        return null;
    }

    @Override
    public WorkoutPlan update(Long id, WorkoutPlan toUpdate) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
