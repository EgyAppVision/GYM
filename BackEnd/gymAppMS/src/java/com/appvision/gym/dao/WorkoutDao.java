/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appvision.gym.dao;

import com.appvision.gym.model.Workout;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ashraf.ibrahim
 */

public interface WorkoutDao {
    
    public boolean AddWorkOut(Workout workout) throws Exception;
}
