package com.vaszily.WorkoutPlanner.service.entities.imp;

import com.vaszily.WorkoutPlanner.repositories.ExerciseWrapperRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class ExerciseWrapperServiceTest {

    private ExerciseService underTest;
    @Mock
    private ExerciseWrapperRepo exerciseWrapperRepo;


    @BeforeEach
    void setUp(){
       // underTest = new ExerciseService(exerciseWrapperRepo);
    }


    @Test
    void getAll() {
        underTest.getAll();
        Mockito.verify(exerciseWrapperRepo).findAll() ;
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
       /* ExerciseWrapper exerciseWrapper = new ExerciseWrapper("32",new Task());
        underTest.save(exerciseWrapper);
        ArgumentCaptor<ExerciseWrapper> exerciseArgumentCaptor =ArgumentCaptor.forClass(ExerciseWrapper.class);
        Mockito.verify(exerciseRepo).save(exerciseArgumentCaptor.capture());
        ExerciseWrapper capturedExerciseWrapper = exerciseArgumentCaptor.getValue();
        assertThat(capturedExerciseWrapper).isEqualTo(exerciseWrapper);*/
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