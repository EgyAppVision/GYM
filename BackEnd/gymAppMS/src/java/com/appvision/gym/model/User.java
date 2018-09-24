package com.appvision.gym.model;

import com.appvision.gym.defines.Defines;
import java.io.Serializable;
import java.util.Date;
//import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1448793414629205821L;
    private int userId;
//@NotNull (message = "null value")
    @NotNull(message = "First name Can't be Null ")
    private String firstName;
//@NotBlank (message = "null value")
    //private String userName;
    @NotNull(message = "password name Can't be Null ")
    private String password;
    @NotNull(message = "lastName name Can't be Null ")
    private String lastName;
    @NotNull
    @DecimalMin(message = "Wrong Gender ", value = "1")
    private int gender;
    @NotNull
    @Pattern(regexp = Defines.mobilePattern, message = "Wrong Mobile number")
    private String mobile;
    @NotNull
    @Pattern(regexp = Defines.mailPattern, message = "Wrong email")
    private String email;
   
    @NotNull
    private Date birthDate;
    @NotNull
    private int weight;
    private int height;
    private int fatPercentage;
    @DecimalMin(message = "Wrong preferedActivity ", value = "1")
    private int preferedActivity;
    @DecimalMin(message = "Wrong preferedActivity ", value = "1")
    private int preferedPlace;
    @DecimalMin(message = "Wrong type ", value = "1")
    private int type;
    private int isFollowing;

    private String preferedActivityDesc;
        private String preferedPlaceDesc;
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
    };

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

    public int getIsFollowing() {
        return isFollowing;
    }

    public void setIsFollowing(int isFollowing) {
        this.isFollowing = isFollowing;
    }

    public String getPreferedActivityDesc() {
        return preferedActivityDesc;
    }

    public void setPreferedActivityDesc(String preferedActivityDesc) {
        this.preferedActivityDesc = preferedActivityDesc;
    }

    public String getPreferedPlaceDesc() {
        return preferedPlaceDesc;
    }

    public void setPreferedPlaceDesc(String preferedPlaceDesc) {
        this.preferedPlaceDesc = preferedPlaceDesc;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", firstName=" + firstName + ", password=" + password + ", lastName=" + lastName + ", gender=" + gender + ", mobile=" + mobile + ", email=" + email + ", birthDate=" + birthDate + ", weight=" + weight + ", height=" + height + ", fatPercentage=" + fatPercentage + ", preferedActivity=" + preferedActivity + ", preferedPlace=" + preferedPlace + ", type=" + type + ", isFollowing=" + isFollowing + '}';
    }

    

}
