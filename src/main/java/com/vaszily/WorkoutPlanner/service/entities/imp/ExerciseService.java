package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.exception.MissingAuthorityException;
import com.vaszily.WorkoutPlanner.exception.ReferencedEntityException;
import com.vaszily.WorkoutPlanner.model.Exercise;
import com.vaszily.WorkoutPlanner.repositories.ExerciseRepo;
import com.vaszily.WorkoutPlanner.service.entities.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.List;

@Service
public class ExerciseService implements EntityService<Exercise> {
    private ExerciseRepo exerciseRepo;
    @Autowired
    public ExerciseService(ExerciseRepo exerciseRepo){
        this.exerciseRepo = exerciseRepo;
    }

    @Override
    public List<Exercise> getAll() {
        return exerciseRepo.findAll();
    }

    @Override
    public List<Exercise> getAllByName(String name) {
        throw new RuntimeException();
    }

    @Override
    public Exercise getById(Long id) {
        return exerciseRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Exercise save(Exercise toSave) {
        return exerciseRepo.save(toSave);
    }

    @Override
    public Exercise update(Long id, Exercise exercise, Principal principal) {
        Exercise toUpdate = exerciseRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        if(!toUpdate.getCreatedBy().equals(principal.getName()))
            throw new MissingAuthorityException("Can't update this exercise! Different creator!");
        toUpdate.update(exercise);
        return exerciseRepo.save(toUpdate);


    }

    @Override
    public void delete(Long id, Principal principal) {
        Exercise toDelete = exerciseRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        if(toDelete.getExerciseWrappers().size()>0)
            throw new ReferencedEntityException("This excersice used by at least a exercise wrapper!");
        if(!toDelete.getCreatedBy().equals(principal.getName()))
            throw new MissingAuthorityException("Can't delete this exercise! Different creator!");
        exerciseRepo.delete(toDelete);
    }
}
