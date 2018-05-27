/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appvision.gym.services;

import com.appvision.gym.dao.LoadDataDao;
import com.appvision.gym.model.Exercise;
import com.appvision.gym.model.Muscle;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ashraf.ibrahim
 */

@Service("LoadDataService")
public class LoadDataServiceImp implements LoadDataService{
@Autowired 
LoadDataDao loadDataDao;
    
    @Override
    public List<Muscle> loadMainMuscles() throws Exception{
      return loadDataDao.loadMainMuscles();
    }

    @Override
    public List<Muscle> LoadSubMuscles(int mainMuscle)  throws Exception {
     return loadDataDao.LoadSubMuscles(mainMuscle);
    }

    @Override
    public List<Exercise> LoadMuscleExceriseByMainMuscleId(int mainMuscleId) throws Exception{
       return   loadDataDao.LoadMuscleExceriseByMainMuscleId(mainMuscleId);
    }
    
}
