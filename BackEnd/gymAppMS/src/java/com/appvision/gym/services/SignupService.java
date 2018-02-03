package com.appvision.gym.services;

import com.appvision.gym.model.LookupModel;
import com.appvision.gym.model.SingupDataModel;
import com.appvision.gym.model.User;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public interface SignupService {
	
	public  boolean userSignUp(User user,Connection connection) ;
        public  SingupDataModel loadData ();
}
