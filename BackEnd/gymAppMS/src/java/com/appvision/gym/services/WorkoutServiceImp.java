/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appvision.gym.services;

import com.appvision.gym.dao.WorkoutDao;
import com.appvision.gym.model.Workout;
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
    public boolean AddWorkOut(Workout workout) {
       return workoutDao.AddWorkOut(workout);
    }
    
    
}
