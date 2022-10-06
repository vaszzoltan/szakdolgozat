package com.vaszily.WorkoutPlanner.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WorkoutPlanRatingRequest {
    @NotNull(message ="Value cannot be null!")
    @Min(value = 0, message = "Value cannot be less than 0!")
    @Max(value=5, message = "Value cannot be greater then 5!")
    private Integer value;
}
