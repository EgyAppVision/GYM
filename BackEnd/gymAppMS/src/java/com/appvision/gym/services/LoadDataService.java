/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appvision.gym.services;

import com.appvision.gym.model.Exercise;
import com.appvision.gym.model.Muscle;
import java.util.List;

/**
 *
 * @author ashraf.ibrahim
 */
public interface LoadDataService {

    public List<Muscle> loadMainMuscles() throws Exception;

    public List<Muscle> LoadSubMuscles(int mainMuscle) throws Exception ;

    public List<Exercise> LoadMuscleExceriseByMainMuscleId(int mainMuscleId)throws Exception;
}
