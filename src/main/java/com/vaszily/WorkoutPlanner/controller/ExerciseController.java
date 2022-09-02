package com.vaszily.WorkoutPlanner.controller;

import com.vaszily.WorkoutPlanner.dto.request.ExerciseRequest;
import com.vaszily.WorkoutPlanner.model.Exercise;
import com.vaszily.WorkoutPlanner.service.entities.imp.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Exercise> getAll(){
        return exerciseService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Exercise getById(@PathVariable Long id){
        return exerciseService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Exercise addExercise(ExerciseRequest exerciseRequest){
        return exerciseService.save(exerciseRequest.asEntity());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Exercise updateExercise(@PathVariable Long id, ExerciseRequest exerciseRequest){
        return exerciseService.update(id, exerciseRequest.asEntity());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExercise(@PathVariable Long id){
        exerciseService.delete(id);
    }
}
