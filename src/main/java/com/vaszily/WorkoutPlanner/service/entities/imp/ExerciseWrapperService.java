package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.exception.MissingAuthorityException;
import com.vaszily.WorkoutPlanner.model.ExerciseWrapper;
import com.vaszily.WorkoutPlanner.repositories.ExerciseWrapperRepo;
import com.vaszily.WorkoutPlanner.service.entities.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
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
        return exerciseWrapperRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("This exercisewrapper does not exist! ID: "+ id));
    }

    @Override
    public ExerciseWrapper save(ExerciseWrapper toSave) {
        toSave.setExercise(exerciseService.getById(toSave.getExercise().getId()));
        return exerciseWrapperRepo.saveAndFlush(toSave);
    }

    @Override
    public ExerciseWrapper update(Long id, ExerciseWrapper exerciseWrapper, Principal principal) {
        ExerciseWrapper toUpdate = getById(id);
        if(!toUpdate.getCreatedBy().equals(principal.getName()))
            throw new MissingAuthorityException("Can't update this exercise! Different creator!");
        exerciseWrapper.setExercise(exerciseService.getById(exerciseWrapper.getExercise().getId()));
        toUpdate.update(exerciseWrapper);
        return exerciseWrapperRepo.save(toUpdate);


    }

    @Override
    public void delete(Long id, Principal principal) {
        ExerciseWrapper toDelete = getById(id);
        if(!toDelete.getCreatedBy().equals(principal.getName()))
            throw new MissingAuthorityException("Can't delete this exercise wrapper! Different creator!");
        exerciseWrapperRepo.delete(toDelete);
    }
}
