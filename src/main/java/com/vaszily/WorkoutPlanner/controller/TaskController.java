package com.vaszily.WorkoutPlanner.controller;

import com.vaszily.WorkoutPlanner.dto.request.TaskRequest;
import com.vaszily.WorkoutPlanner.model.Task;
import com.vaszily.WorkoutPlanner.service.entities.imp.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAll(){
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task getById(@PathVariable Long id){
        return taskService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(TaskRequest exerciseRequest){
        return taskService.save(exerciseRequest.asEntity());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task updateTask(@PathVariable Long id, TaskRequest exerciseRequest){
        return taskService.update(id, exerciseRequest.asEntity());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id){
        taskService.delete(id);
    }
}
