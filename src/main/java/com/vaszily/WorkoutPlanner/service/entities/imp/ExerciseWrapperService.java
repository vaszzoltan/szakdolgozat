package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.model.ExerciseWrapper;
import com.vaszily.WorkoutPlanner.repositories.ExerciseWrapperRepo;
import com.vaszily.WorkoutPlanner.service.entities.EntityService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class ExerciseWrapperService implements EntityService<ExerciseWrapper> {
    private ExerciseWrapperRepo exerciseWrapperRepo;

    @Override
    public List<ExerciseWrapper> getAll() {
        return exerciseWrapperRepo.findAll();
    }

    @Override
    public List<ExerciseWrapper> getAllByName(String name) {
        throw new RuntimeException();
    }

    @Override
    public ExerciseWrapper getById(Long id) {
        return exerciseWrapperRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ExerciseWrapper save(ExerciseWrapper toSave) {

        return exerciseWrapperRepo.save(toSave);
    }

    @Override
    public ExerciseWrapper update(Long id, ExerciseWrapper exerciseWrapper) {
        ExerciseWrapper toUpdate = exerciseWrapperRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        toUpdate.update(exerciseWrapper);
        return exerciseWrapperRepo.save(toUpdate);


    }

    @Override
    public void delete(Long id) {
        ExerciseWrapper toDelete = exerciseWrapperRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        exerciseWrapperRepo.delete(toDelete);
    }
}
