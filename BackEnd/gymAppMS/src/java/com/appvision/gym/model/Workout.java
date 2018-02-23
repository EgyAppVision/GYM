/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appvision.gym.model;

import java.util.List;
import com.appvision.gym.model.Exercise;
/**
 *
 * @author ashraf.ibrahim
 */
public class Workout {
    private  String workOutDes; 
    private int workoutTrainee;
    private  List<WorkoutExcerices>  workoutExcerices ; 

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

    public List<WorkoutExcerices> getWorkoutExcerices() {
        return workoutExcerices;
    }

    public void setWorkoutExcerices(List<WorkoutExcerices> WorkoutExcerices) {
        this.workoutExcerices = WorkoutExcerices;
    }

    @Override
    public String toString() {
        return "Workout{" + "workOutDes=" + workOutDes + ", workoutTrainee=" + workoutTrainee + ", WorkoutExcerices=" + workoutExcerices + '}';
    }

    
    
}
