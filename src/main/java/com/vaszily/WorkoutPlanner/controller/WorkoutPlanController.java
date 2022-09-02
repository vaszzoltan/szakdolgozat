package com.vaszily.WorkoutPlanner.controller;

import com.vaszily.WorkoutPlanner.dto.request.WorkoutPlanRequest;
import com.vaszily.WorkoutPlanner.model.WorkoutPlan;
import com.vaszily.WorkoutPlanner.service.entities.imp.WorkoutPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/workout-plan")
public class WorkoutPlanController {
    private final WorkoutPlanService workoutPlanService;

    @Autowired
    public WorkoutPlanController(WorkoutPlanService workoutPlanService){
        this.workoutPlanService = workoutPlanService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WorkoutPlan> getAll(){
        return workoutPlanService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WorkoutPlan getById(@PathVariable Long id){
        return workoutPlanService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutPlan addWorkoutPlan(WorkoutPlanRequest workoutPlanRequest){
        return workoutPlanService.save(workoutPlanRequest.asEntity());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WorkoutPlan updateWorkoutPlan(@PathVariable Long id, WorkoutPlanRequest workoutPlanRequest){
        return workoutPlanService.update(id, workoutPlanRequest.asEntity());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkoutPlan(@PathVariable Long id){
        workoutPlanService.delete(id);
    }
}
