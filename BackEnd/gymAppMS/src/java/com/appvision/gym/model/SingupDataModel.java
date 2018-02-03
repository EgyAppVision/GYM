/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appvision.gym.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ashraf.ibrahim
 */
public class SingupDataModel {
    
    List<LookupModel>  userGender ;
    List<LookupModel> userPrefaredPlace;
    List<LookupModel> userPrefaredActivity;
    List<LookupModel> userType;

    public List<LookupModel> getUserGender() {
        return userGender;
    }

    public void setUserGender(List<LookupModel> userGender) {
        this.userGender = userGender;
    }

    public List<LookupModel> getUserPrefaredPlace() {
        return userPrefaredPlace;
    }

    public void setUserPrefaredPlace(List<LookupModel> userPrefaredPlace) {
        this.userPrefaredPlace = userPrefaredPlace;
    }

    public List<LookupModel> getUserPrefaredActivity() {
        return userPrefaredActivity;
    }

    public void setUserPrefaredActivity(List<LookupModel> userPrefaredActivity) {
        this.userPrefaredActivity = userPrefaredActivity;
    }

    public List<LookupModel> getUserType() {
        return userType;
    }

    public void setUserType(List<LookupModel> userType) {
        this.userType = userType;
    }
    
    
}
