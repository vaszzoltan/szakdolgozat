package com.vaszily.WorkoutPlanner.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseRequest {
    private Long id;
    private String name;
    private Boolean dynamic;
}
