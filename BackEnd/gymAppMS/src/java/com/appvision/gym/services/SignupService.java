package com.appvision.gym.services;

import com.appvision.gym.model.LoginModel;
import com.appvision.gym.model.SingupDataModel;
import com.appvision.gym.model.User;
import java.util.List;

public interface SignupService {

    public int userSignUp(User user);

    public SingupDataModel loadData() throws Exception;
 
    public boolean IsMailExist(String mail);

    public boolean IsUserNameExist(String userName);

    public User GetUserByUserNameAndPassword(LoginModel loginModel) throws Exception;

    public List<User> GetAllUsersByName(String name, int userId, int place, int activity) throws Exception;

    public boolean Follow(int follower, int follwing);
}
