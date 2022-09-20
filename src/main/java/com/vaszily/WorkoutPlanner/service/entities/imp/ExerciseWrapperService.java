package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.model.ExerciseWrapper;
import com.vaszily.WorkoutPlanner.repositories.ExerciseWrapperRepo;
import com.vaszily.WorkoutPlanner.service.entities.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class ExerciseWrapperService implements EntityService<ExerciseWrapper> {
    private final ExerciseWrapperRepo exerciseWrapperRepo;
    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseWrapperService(ExerciseWrapperRepo exerciseWrapperRepo, ExerciseService exerciseService){
        this.exerciseWrapperRepo = exerciseWrapperRepo;
        this.exerciseService = exerciseService;
    }

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
        toSave.setExercise(exerciseService.getById(toSave.getExercise().getId()));
        return exerciseWrapperRepo.saveAndFlush(toSave);
    }

    @Override
    public ExerciseWrapper update(Long id, ExerciseWrapper exerciseWrapper) {
        ExerciseWrapper toUpdate = exerciseWrapperRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        exerciseWrapper.setExercise(exerciseService.getById(exerciseWrapper.getExercise().getId()));
        toUpdate.update(exerciseWrapper);
        return exerciseWrapperRepo.save(toUpdate);


    }

    @Override
    public void delete(Long id) {
        ExerciseWrapper toDelete = exerciseWrapperRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        exerciseWrapperRepo.delete(toDelete);
    }
}
