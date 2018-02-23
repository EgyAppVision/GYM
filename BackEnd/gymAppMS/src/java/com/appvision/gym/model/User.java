package com.appvision.gym.model;

import java.io.Serializable;
import java.util.Date;
//import javax.validation.constraints.NotNull;
import org.hibernate.validator.NotNull;
public class User implements Serializable{
/**
	 * 
	 */
private static final long serialVersionUID = 1448793414629205821L;
private int userId;
@NotNull (message = "null value")
private String firstName;
@NotNull (message = "null value")
private String userName;
private  String password;
private String lastName;
private int gender;
private  String mobile;
private String email;
private int activity;  
private String place ;
private Date birthDate;
private int weight;
private  int height;
private int fatPercentage;
private int preferedActivity;
private int preferedPlace;
private  int type;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getFatPercentage() {
        return fatPercentage;
    }

    public void setFatPercentage(int fatPercentage) {
        this.fatPercentage = fatPercentage;
    }

    public int getPreferedActivity() {
        return preferedActivity;
    }

    public void setPreferedActivity(int preferedActivity) {
        this.preferedActivity = preferedActivity;
    }

    public int getPreferedPlace() {
        return preferedPlace;
    }

    public void setPreferedPlace(int preferedPlace) {
        this.preferedPlace = preferedPlace;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
