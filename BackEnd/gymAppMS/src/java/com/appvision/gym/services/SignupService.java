package com.appvision.gym.services;

import com.appvision.gym.model.SingupDataModel;
import com.appvision.gym.model.User;


public interface SignupService {
	
	public  boolean userSignUp(User user) ;
        public  SingupDataModel loadData ();
}
