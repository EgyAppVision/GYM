/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appvision.gym.services;

import com.appvision.gym.dao.WorkoutDao;
import com.appvision.gym.model.RequestWorkout;
import com.appvision.gym.model.RequestWorkoutV2;
import com.appvision.gym.model.Workout;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ashraf.ibrahim
 */
@Service("workoutService")
public class WorkoutServiceImp implements WorkoutService{
@Autowired 
WorkoutDao workoutDao;
    @Override
    @Transactional
    public boolean AddWorkOut(Workout workout) throws Exception {
       return workoutDao.AddWorkOut(workout);
    }

    @Override
    @Transactional
    public boolean AddRequestWorkOut(RequestWorkout workout) throws Exception {
      return workoutDao.AddRequestWorkout(workout);
    }

    @Override
    public boolean updateRequestWorkOut(RequestWorkout workout) throws Exception {
       return workoutDao.updateRequestWorkout(workout);
    }

    @Override
    @Transactional
    public boolean AddRequestWorkout(RequestWorkoutV2 workout) throws Exception {
         return workoutDao.AddRequestWorkout(workout);
    }

    @Override
    public boolean AddActualWorkout(RequestWorkoutV2 workout) throws Exception {
        return workoutDao.AddActualWorkout(workout);
    }

    @Override
    public List<RequestWorkoutV2>  GetRequestWorkout(int requestId) throws Exception {
        return workoutDao.GetRequestWorkout(requestId);
    }
    
    
}
