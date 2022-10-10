package com.vaszily.WorkoutPlanner.controller;

import com.vaszily.WorkoutPlanner.dto.request.TaskRequest;
import com.vaszily.WorkoutPlanner.dto.response.TaskResponse;
import com.vaszily.WorkoutPlanner.service.entities.imp.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponse> getAll(){
        return taskService.getAll().stream().map(TaskResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse getById(@PathVariable Long id){
        return new TaskResponse(taskService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse addTask(@RequestBody @Valid TaskRequest exerciseRequest, Principal principal){
        return new TaskResponse(taskService.save(exerciseRequest.asEntity(principal)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody @Valid TaskRequest exerciseRequest, Principal principal){
        return new TaskResponse(taskService.update(id, exerciseRequest.asEntity(), principal));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id, Principal principal){
        taskService.delete(id, principal);
    }
}
