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
public class Exercise {
    
private int exerciseId ; 
private String exerciseDesc;
private  int exerciseTypeId;
private  int exerciseSubMuscle;
private String exerciseTypeDesc;
private String  muscleTypeDesc;
private  String muscleName;

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getExerciseDesc() {
        return exerciseDesc;
    }

    public void setExerciseDesc(String exerciseDesc) {
        this.exerciseDesc = exerciseDesc;
    }

    public int getExerciseTypeId() {
        return exerciseTypeId;
    }

    public void setExerciseTypeId(int exerciseTypeId) {
        this.exerciseTypeId = exerciseTypeId;
    }

    public int getExerciseSubMuscle() {
        return exerciseSubMuscle;
    }

    public void setExerciseSubMuscle(int exerciseSubMuscle) {
        this.exerciseSubMuscle = exerciseSubMuscle;
    }

    public String getExerciseTypeDesc() {
        return exerciseTypeDesc;
    }

    public void setExerciseTypeDesc(String exerciseTypeDesc) {
        this.exerciseTypeDesc = exerciseTypeDesc;
    }

    public String getMuscleTypeDesc() {
        return muscleTypeDesc;
    }

    public void setMuscleTypeDesc(String muscleTypeDesc) {
        this.muscleTypeDesc = muscleTypeDesc;
    }

    public String getMuscleName() {
        return muscleName;
    }

    public void setMuscleName(String muscleName) {
        this.muscleName = muscleName;
    }



}
