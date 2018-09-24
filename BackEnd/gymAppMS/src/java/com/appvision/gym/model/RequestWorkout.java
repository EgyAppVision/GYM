/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appvision.gym.model;

import java.util.List;

/**
 *
 * @author ashraf.ibrahim
 */
public class RequestWorkout {
        
    private int workoutId ;
    private  String workOutDes; 
  
    private int workoutTrainee;
    private  List<RequestWorkoutExerice>  workoutExcerices ;
    private int requestId ; 

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
   
    public String getWorkOutDes() {
        return workOutDes;
    }

    public void setWorkOutDes(String workOutDes) {
        this.workOutDes = workOutDes;
    }

    public int getWorkoutTrainee() {
        return workoutTrainee;
    }

    public void setWorkoutTrainee(int workoutTrainee) {
        this.workoutTrainee = workoutTrainee;
    }

    public List<RequestWorkoutExerice> getWorkoutExcerices() {
        return workoutExcerices;
    }

    public void setWorkoutExcerices(List<RequestWorkoutExerice> workoutExcerices) {
        this.workoutExcerices = workoutExcerices;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

}
