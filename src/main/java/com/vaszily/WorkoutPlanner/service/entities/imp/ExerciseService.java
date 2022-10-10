package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.exception.MissingAuthorityException;
import com.vaszily.WorkoutPlanner.exception.ReferencedEntityException;
import com.vaszily.WorkoutPlanner.model.Exercise;
import com.vaszily.WorkoutPlanner.repositories.ExerciseRepo;
import com.vaszily.WorkoutPlanner.service.entities.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.security.Principal;
import java.util.List;;

@Service
public class ExerciseService implements EntityService<Exercise> {
    private Logger log = LoggerFactory.getLogger(this.getClass());

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
        return exerciseRepo.findById(id).orElseThrow(() -> new EntityExistsException("This exercise does not exist! ID: "+ id));
    }

    @Override
    public Exercise save(Exercise toSave) {
        return exerciseRepo.save(toSave);
    }

    @Override
    public Exercise update(Long id, Exercise exercise, Principal principal) {
        Exercise toUpdate = getById(id);
        if(!toUpdate.getCreatedBy().equals(principal.getName())) {
            log.info(principal.getName() + " try to modify an exercise!");
            throw new MissingAuthorityException("Can't update this exercise! Different creator!");
        }
        toUpdate.update(exercise);
        return exerciseRepo.save(toUpdate);


    }

    @Override
    public void delete(Long id, Principal principal) {
        Exercise toDelete = getById(id);
        if(toDelete.getExerciseWrappers().size()>0)
            throw new ReferencedEntityException("This excersice used by at least a exercise wrapper!");
        if(!toDelete.getCreatedBy().equals(principal.getName())) {
            log.info(principal.getName() + " try to delete an exercise!");
            throw new MissingAuthorityException("Can't delete this exercise! Different creator!");
        }
        exerciseRepo.delete(toDelete);
    }
}
