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

    public List<LookupModel> loadUserGender() throws Exception;

    public List<LookupModel> loadUserPrefaredPlace() throws Exception;

    public List<LookupModel> loadUserPrefaredActivity() throws Exception ;

    public List<LookupModel> loadUserType() throws Exception;

    public List<Muscle> loadMainMuscles() throws Exception;

    public List<Muscle> LoadSubMuscles(int mainMuscle) throws Exception;
    
    public List<Exercise> LoadMuscleExceriseByMainMuscleId(int mainMuscleId)throws Exception;
}
