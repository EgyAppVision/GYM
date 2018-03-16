package com.appvision.gym.dao;

import com.appvision.gym.model.LoginModel;
import com.appvision.gym.model.User;
import java.sql.Connection;
import java.util.List;

public interface UserDao {
	
	public int userSignUp (User user);
        
        public boolean IsMailExist (String mail);
        public boolean IsUserNameExist(final String userName);
        public User GetUserByUserNameAndPassword (LoginModel loginModel );
        
        public List<User> GetAllUsersByName (String name, int userId );

}
