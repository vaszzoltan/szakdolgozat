package com.vaszily.WorkoutPlanner.controller;

import com.vaszily.WorkoutPlanner.dto.request.ExerciseWrapperRequest;
import com.vaszily.WorkoutPlanner.dto.response.ExerciseWrapperResponse;
import com.vaszily.WorkoutPlanner.service.entities.imp.ExerciseWrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api/exercise-wrapper")
public class ExerciseWrapperController {
    private final ExerciseWrapperService exerciseWrapperService;

    @Autowired
    public ExerciseWrapperController(ExerciseWrapperService exerciseWrapperService){
        this.exerciseWrapperService = exerciseWrapperService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciseWrapperResponse> getAll(){
        return exerciseWrapperService.getAll().stream().map(ExerciseWrapperResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExerciseWrapperResponse getById(@PathVariable Long id){
        return new ExerciseWrapperResponse(exerciseWrapperService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseWrapperResponse addExerciseWrapper(@RequestBody ExerciseWrapperRequest exerciseRequest, Principal principal){
        return new ExerciseWrapperResponse(exerciseWrapperService.save(exerciseRequest.asEntity(principal)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExerciseWrapperResponse updateExerciseWrapper(@PathVariable Long id, @RequestBody ExerciseWrapperRequest exerciseRequest, Principal principal){
        return new ExerciseWrapperResponse(exerciseWrapperService.update(id, exerciseRequest.asEntity(), principal));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExerciseWrapper(@PathVariable Long id, Principal principal){
        exerciseWrapperService.delete(id, principal);
    }
}
