/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appvision.gym.model;

import java.util.Date;

/**
 *
 * @author ashraf.ibrahim
 */
public class ExerciseProprty {
    
    private String excericeReps;
    private String excericeweight;
    private String excericeSets;
    private  Date workoutdate;
    public String getExcericeReps() {
        return excericeReps;
    }

    public void setExcericeReps(String excericeReps) {
        this.excericeReps = excericeReps;
    }

    public String getExcericeweight() {
        return excericeweight;
    }

    public void setExcericeweight(String excericeweight) {
        this.excericeweight = excericeweight;
    }

    public String getExcericeSets() {
        return excericeSets;
    }

    public void setExcericeSets(String excericeSets) {
        this.excericeSets = excericeSets;
    }

    public Date getWorkoutdate() {
        return workoutdate;
    }

    public void setWorkoutdate(Date workoutdate) {
        this.workoutdate = workoutdate;
    }
    
    
    
    
}
