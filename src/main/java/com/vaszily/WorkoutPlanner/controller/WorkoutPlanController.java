package com.vaszily.WorkoutPlanner.controller;

import com.vaszily.WorkoutPlanner.dto.request.WorkoutPlanRequest;
import com.vaszily.WorkoutPlanner.dto.response.WorkoutPlanResponse;
import com.vaszily.WorkoutPlanner.service.entities.imp.WorkoutPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<WorkoutPlanResponse> getAll(){
        return workoutPlanService.getAll().stream().map(WorkoutPlanResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WorkoutPlanResponse getById(@PathVariable Long id){
        return new WorkoutPlanResponse(workoutPlanService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutPlanResponse addWorkoutPlan(@RequestBody WorkoutPlanRequest workoutPlanRequest){
        return new WorkoutPlanResponse(workoutPlanService.save(workoutPlanRequest.asEntity()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WorkoutPlanResponse updateWorkoutPlan(@PathVariable Long id, @RequestBody WorkoutPlanRequest workoutPlanRequest){
        return new WorkoutPlanResponse(workoutPlanService.update(id, workoutPlanRequest.asEntity()));
    }

    @PutMapping("/subscribe/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void addWorkoutPlanToAccount(@PathVariable Long id, Principal principal){
        workoutPlanService.addWorkoutPlanToAccount(id,principal.getName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkoutPlan(@PathVariable Long id){
        workoutPlanService.delete(id);
    }
}
