/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appvision.gym.dao;
import com.appvision.gym.model.LookupModel;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ashraf.ibrahim
 */
public interface LoadDataDao {
    
    public List<LookupModel>  loadUserGender ();
    public List<LookupModel>  loadUserPrefaredPlace ();
    public List<LookupModel>  loadUserPrefaredActivity ();
    public List<LookupModel>  loadUserType ();
}
