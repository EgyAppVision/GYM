/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appvision.gym.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ashraf.ibrahim
 */
public class RequestWorkoutExerciseV2 {

    private int exericeId;
   private String exericeName;
    private ExerciseProprty proposedEx;
    private  ExerciseProprty actuaEx ;
    
    private List <ExerciseProprty> actuaExlist;
    
    public int getExericeId() {
        return exericeId;
    }

    public void setExericeId(int exericeId) {
        this.exericeId = exericeId;
    }

    public ExerciseProprty getProposedEx() {
        return proposedEx;
    }

    public void setProposedEx(ExerciseProprty proposedEx) {
        this.proposedEx = proposedEx;
    }

    public ExerciseProprty getActuaEx() {
        return actuaEx;
    }

    public void setActuaEx(ExerciseProprty actuaEx) {
        this.actuaEx = actuaEx;
    }
    

    public String getExericeName() {
        return exericeName;
    }

    public void setExericeName(String exericeName) {
        this.exericeName = exericeName;
    }

    public List<ExerciseProprty> getActuaExlist() {
        return actuaExlist;
    }

    public void setActuaExlist(List<ExerciseProprty> actuaExlist) {
        this.actuaExlist = actuaExlist;
    }

    
}
