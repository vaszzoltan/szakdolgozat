package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.model.Exercise;
import com.vaszily.WorkoutPlanner.model.Task;
import com.vaszily.WorkoutPlanner.repositories.ExerciseRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class ExerciseServiceTest {

    private ExerciseService underTest;
    @Mock
    private ExerciseRepo exerciseRepo;


    @BeforeEach
    void setUp(){
        underTest = new ExerciseService(exerciseRepo);
    }


    @Test
    void getAll() {
        underTest.getAll();
        Mockito.verify(exerciseRepo).findAll() ;
    }



    @Test
    @Disabled
    void getAllByName() {
    }

    @Test
    @Disabled
    void getById() {
    }

    @Test
    void save() {
        Exercise exercise = new Exercise("32",new Task());
        underTest.save(exercise);
        ArgumentCaptor<Exercise> exerciseArgumentCaptor =ArgumentCaptor.forClass(Exercise.class);
        Mockito.verify(exerciseRepo).save(exerciseArgumentCaptor.capture());
        Exercise capturedExercise = exerciseArgumentCaptor.getValue();
        assertThat(capturedExercise).isEqualTo(exercise);
    }

    @Test
    @Disabled
    void update() {
    }

    @Test
    @Disabled
    void delete() {
    }
}