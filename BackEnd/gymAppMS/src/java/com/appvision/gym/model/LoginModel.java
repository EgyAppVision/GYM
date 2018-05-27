/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appvision.gym.model;

import com.appvision.gym.defines.Defines;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author ashraf.ibrahim
 */
public class LoginModel {
    @NotNull (message = "Null Eamil ")
    @Pattern(regexp = Defines.mailPattern, message = "Wrong email")
    private String userEmail;
    @NotNull (message = "Null password ")
    private String password;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginModel{" + "userEmail=" + userEmail + ", password=" + password + '}';
    }

   
    
    
}
