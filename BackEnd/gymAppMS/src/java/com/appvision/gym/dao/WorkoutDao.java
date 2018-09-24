/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appvision.gym.dao;

import com.appvision.gym.model.RequestWorkout;
import com.appvision.gym.model.RequestWorkoutV2;
import com.appvision.gym.model.Workout;
import java.util.List;

/**
 *
 * @author ashraf.ibrahim
 */

public interface WorkoutDao {
    
    public boolean AddWorkOut(Workout workout) throws Exception;
    public boolean AddRequestWorkout(final RequestWorkout workout) throws Exception;
    public boolean updateRequestWorkout(final RequestWorkout workout) throws Exception;
    
    public boolean AddActualWorkout(final RequestWorkoutV2 workout) throws Exception;
    public boolean AddRequestWorkout(final RequestWorkoutV2 workout) throws Exception;
    
    public List<RequestWorkoutV2>  GetRequestWorkout(final int requestId ) throws Exception;
}
