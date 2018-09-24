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
public class RequestWorkoutV2 {

    private int workoutId;
    private String workoutName;
    private int activityId;
    private String activityName ;
    private String trainerComment;
    private String traineeComment ;
    private List<RequestWorkoutExerciseV2> workoutExcerices;
    private int requestId;

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public String getTrainerComment() {
        return trainerComment;
    }

    public void setTrainerComment(String trainerComment) {
        this.trainerComment = trainerComment;
    }

    public String getTraineeComment() {
        return traineeComment;
    }

    public void setTraineeComment(String traineeComment) {
        this.traineeComment = traineeComment;
    }

   

    public List<RequestWorkoutExerciseV2> getWorkoutExcerices() {
        return workoutExcerices;
    }

    public void setWorkoutExcerices(List<RequestWorkoutExerciseV2> workoutExcerices) {
        this.workoutExcerices = workoutExcerices;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    
}
