/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appvision.gym.dao;

import com.appvision.gym.model.Exercise;
import com.appvision.gym.model.LookupModel;
import com.appvision.gym.model.Muscle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ashraf.ibrahim
 */
public interface LoadDataDao {

    public List<LookupModel> loadUserGender();

    public List<LookupModel> loadUserPrefaredPlace();

    public List<LookupModel> loadUserPrefaredActivity();

    public List<LookupModel> loadUserType();

    public List<Muscle> loadMainMuscles();

    public List<Muscle> LoadSubMuscles(int mainMuscle);
    
    public List<Exercise> LoadMuscleExceriseByMainMuscleId(int mainMuscleId);
}
