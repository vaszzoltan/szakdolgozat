package com.vaszily.WorkoutPlanner.controller;

import com.vaszily.WorkoutPlanner.dto.request.ExerciseRequest;
import com.vaszily.WorkoutPlanner.dto.response.ExerciseResponse;
import com.vaszily.WorkoutPlanner.service.entities.imp.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciseResponse> getAll(){
        return exerciseService.getAll().stream().map(ExerciseResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExerciseResponse getById(@PathVariable Long id){
        return new ExerciseResponse(exerciseService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseResponse addExercise(ExerciseRequest exerciseRequest){
        return new ExerciseResponse(exerciseService.save(exerciseRequest.asEntity()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExerciseResponse updateExercise(@PathVariable Long id, ExerciseRequest exerciseRequest){
        return new ExerciseResponse(exerciseService.update(id, exerciseRequest.asEntity()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExercise(@PathVariable Long id){
        exerciseService.delete(id);
    }
}
