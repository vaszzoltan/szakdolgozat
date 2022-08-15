package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.model.ExerciseWrapper;
import com.vaszily.WorkoutPlanner.repositories.ExerciseWrapperRepo;
import com.vaszily.WorkoutPlanner.service.entities.EntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@AllArgsConstructor
@Service
public class ExerciseService implements EntityService<ExerciseWrapper> {
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
        toUpdate.setExercise(exerciseWrapper.getExercise());
        toUpdate.setReps(exerciseWrapper.getReps());
        toUpdate.setSets(exerciseWrapper.getSets());
        toUpdate.setDurationInSecond(exerciseWrapper.getDurationInSecond());
        toUpdate.setStaticOrDynamic(exerciseWrapper.isStaticOrDynamic());
        toUpdate.setUsedWeight(exerciseWrapper.getUsedWeight());
        toUpdate.setTask(exerciseWrapper.getTask());
        return exerciseWrapperRepo.save(toUpdate);


    }

    @Override
    public void delete(Long id) {
        ExerciseWrapper toDelete = exerciseWrapperRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        exerciseWrapperRepo.delete(toDelete);
    }
}
