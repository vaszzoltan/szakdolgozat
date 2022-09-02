package com.vaszily.WorkoutPlanner.controller;

import com.vaszily.WorkoutPlanner.dto.request.ExerciseWrapperRequest;
import com.vaszily.WorkoutPlanner.model.ExerciseWrapper;
import com.vaszily.WorkoutPlanner.service.entities.imp.ExerciseWrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/exercise-wrapper")
public class ExerciseWrapperController {
    private final ExerciseWrapperService exerciseWrapperService;

    @Autowired
    public ExerciseWrapperController(ExerciseWrapperService exerciseWrapperService){
        this.exerciseWrapperService = exerciseWrapperService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciseWrapper> getAll(){
        return exerciseWrapperService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExerciseWrapper getById(@PathVariable Long id){
        return exerciseWrapperService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseWrapper addExerciseWrapper(ExerciseWrapperRequest exerciseRequest){
        return exerciseWrapperService.save(exerciseRequest.asEntity());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExerciseWrapper updateExerciseWrapper(@PathVariable Long id, ExerciseWrapperRequest exerciseRequest){
        return exerciseWrapperService.update(id, exerciseRequest.asEntity());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExerciseWrapper(@PathVariable Long id){
        exerciseWrapperService.delete(id);
    }
}
