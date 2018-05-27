/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appvision.gym.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ashraf.ibrahim
 */
public class WorkoutExcerices {
    
    @NotNull
    @DecimalMin(message = "Wrong excericeId ", value = "1")
    private  int excericeId;
    
    private String excericeDescreption;

    public int getExcericeId() {
        return excericeId;
    }

    public void setExcericeId(int excericeId) {
        this.excericeId = excericeId;
    }

    public String getExcericeDescreption() {
        return excericeDescreption;
    }

    public void setExcericeDescreption(String excericeDescreption) {
        this.excericeDescreption = excericeDescreption;
    }
    
    
}
