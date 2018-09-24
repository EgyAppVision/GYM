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
public class RequestWorkoutExerice {


private int  exericeId; 
private String advisedReps;
private String advisedweight;
private String advisedSets;
private String actualReps;
private String actualSets;
private String actualweight;

   

    public int getExericeId() {
        return exericeId;
    }

    public void setExericeId(int exericeId) {
        this.exericeId = exericeId;
    }

    public String getAdvisedReps() {
        return advisedReps;
    }

    public void setAdvisedReps(String advisedReps) {
        this.advisedReps = advisedReps;
    }



    public String getAdvisedSets() {
        return advisedSets;
    }

    public void setAdvisedSets(String advisedSets) {
        this.advisedSets = advisedSets;
    }

    public String getActualReps() {
        return actualReps;
    }

    public void setActualReps(String actualReps) {
        this.actualReps = actualReps;
    }

    public String getActualSets() {
        return actualSets;
    }

    public void setActualSets(String actualSets) {
        this.actualSets = actualSets;
    }

    public String getAdvisedweight() {
        return advisedweight;
    }

    public void setAdvisedweight(String advisedweight) {
        this.advisedweight = advisedweight;
    }

    public String getActualweight() {
        return actualweight;
    }

    public void setActualweight(String actualweight) {
        this.actualweight = actualweight;
    }


}
