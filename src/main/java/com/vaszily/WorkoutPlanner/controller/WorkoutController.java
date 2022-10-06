package com.vaszily.WorkoutPlanner.controller;

import com.vaszily.WorkoutPlanner.dto.request.WorkoutRequest;
import com.vaszily.WorkoutPlanner.dto.response.WorkoutResponse;
import com.vaszily.WorkoutPlanner.service.entities.imp.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api/workout")
public class WorkoutController {
    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService){
        this.workoutService = workoutService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WorkoutResponse> getAll(){
        return workoutService.getAll().stream().map(WorkoutResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WorkoutResponse getById(@PathVariable Long id){
        return new WorkoutResponse(workoutService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutResponse addWorkout(@RequestBody WorkoutRequest workoutRequest, Principal principal){
        return new WorkoutResponse(workoutService.save(workoutRequest.asEntity(principal)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WorkoutResponse updateWorkout(@PathVariable Long id, @RequestBody WorkoutRequest workoutRequest, Principal principal){
        return new WorkoutResponse(workoutService.update(id, workoutRequest.asEntity(), principal));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkout(@PathVariable Long id, Principal principal){
        workoutService.delete(id, principal);
    }

    @PutMapping("/subscribe/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void addWorkoutToAccount(@PathVariable Long id, Principal principal){
        workoutService.addWorkoutToAccount(id, principal.getName());
    }
    @PutMapping("/unsubscribe/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeWorkoutFromAccount(@PathVariable Long id, Principal principal){
        workoutService.removeWorkoutFromAccount(id, principal.getName());
    }

}
