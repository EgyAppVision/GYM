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
public class RequestTrainer {
    private int requestId ; 
    private int userId; 
    private int trainerId; 
    private int requestStatus; 
    private int activity; 
    private String userComment; 
    private String trainerComment;
    private int numOfSession;
    private User trainer;
    private User trainee;
    private String requestStatusDescreption ; 
    private LookupModel activityModel;
    
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public int getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(int requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int Activity) {
        this.activity = Activity;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public String getTrainerComment() {
        return trainerComment;
    }

    public void setTrainerComment(String trainerComment) {
        this.trainerComment = trainerComment;
    }

    public User getTrainer() {
        return trainer;
    }

    public void setTrainer(User trainer) {
        this.trainer = trainer;
    }

    public String getRequestStatusDescreption() {
        return requestStatusDescreption;
    }

    public void setRequestStatusDescreption(String requestStatusDescreption) {
        this.requestStatusDescreption = requestStatusDescreption;
    }

    public User getTrainee() {
        return trainee;
    }

    public void setTrainee(User trainee) {
        this.trainee = trainee;
    }

    public LookupModel getActivityModel() {
        return activityModel;
    }

    public void setActivityModel(LookupModel activityModel) {
        this.activityModel = activityModel;
    }

    public int getNumOfSession() {
        return numOfSession;
    }

    public void setNumOfSession(int numOfSession) {
        this.numOfSession = numOfSession;
    }

    
    @Override
    public String toString() {
        return "RequestTrainer{" + "requestId=" + requestId + ", userId=" + userId + ", trainerId=" + trainerId + ", requestStatus=" + requestStatus + ", activity=" + activity + ", userComment=" + userComment + ", trainerComment=" + trainerComment + ", trainer=" + trainer + ", trainee=" + trainee + ", requestStatusDescreption=" + requestStatusDescreption + ", activityModel=" + activityModel + '}';
    }

    
    
    
}
