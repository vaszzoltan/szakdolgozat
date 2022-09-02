package com.vaszily.WorkoutPlanner.controller;

import com.vaszily.WorkoutPlanner.dto.request.WorkoutRequest;
import com.vaszily.WorkoutPlanner.model.Workout;
import com.vaszily.WorkoutPlanner.service.entities.imp.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/workout")
public class WorkoutController {
    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService){
        this.workoutService = workoutService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Workout> getAll(){
        return workoutService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Workout getById(@PathVariable Long id){
        return workoutService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Workout addWorkout(WorkoutRequest exerciseRequest){
        return workoutService.save(exerciseRequest.asEntity());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Workout updateWorkout(@PathVariable Long id, WorkoutRequest exerciseRequest){
        return workoutService.update(id, exerciseRequest.asEntity());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkout(@PathVariable Long id){
        workoutService.delete(id);
    }
}
