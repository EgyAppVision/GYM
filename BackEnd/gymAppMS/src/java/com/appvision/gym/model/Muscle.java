/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appvision.gym.model;

/**
 *
 * @author ashraf.ibrahim
 */
public class Muscle {

    private int mainMuscleId;
    private String muscleName;
    private int muscleTypeId;
    private String muscleTypeDesc;
    private int subMuscleId;

    public int getMainMuscleId() {
        return mainMuscleId;
    }

    public void setMainMuscleId(int mainMuscleId) {
        this.mainMuscleId = mainMuscleId;
    }

    public String getMuscleName() {
        return muscleName;
    }

    public void setMuscleName(String muscleName) {
        this.muscleName = muscleName;
    }

    public int getMuscleTypeId() {
        return muscleTypeId;
    }

    public void setMuscleTypeId(int muscleTypeId) {
        this.muscleTypeId = muscleTypeId;
    }

    public String getMuscleTypeDesc() {
        return muscleTypeDesc;
    }

    public void setMuscleTypeDesc(String muscleTypeDesc) {
        this.muscleTypeDesc = muscleTypeDesc;
    }

    public int getSubMuscleId() {
        return subMuscleId;
    }

    public void setSubMuscleId(int subMuscleId) {
        this.subMuscleId = subMuscleId;
    }
    
    
    
}
