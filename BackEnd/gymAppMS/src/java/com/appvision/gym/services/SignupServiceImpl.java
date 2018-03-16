package com.appvision.gym.services;

import com.appvision.gym.dao.LoadDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appvision.gym.dao.UserDao;
import com.appvision.gym.model.LoginModel;
import com.appvision.gym.model.LookupModel;
import com.appvision.gym.model.SingupDataModel;
import com.appvision.gym.model.User;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


@Service("securityService")
public class SignupServiceImpl implements SignupService{

	@Autowired
	private UserDao userDao;
        @Autowired 
        private  LoadDataDao loadDataDao;
	
	
	@Override
	public  int userSignUp(User user) {
		return userDao.userSignUp(user);
	}

    @Override
    public  SingupDataModel loadData() {
        
        SingupDataModel dataModel = new SingupDataModel();
        List<LookupModel> genderList =  loadDataDao.loadUserGender();
        List <LookupModel> preferedplaces =  loadDataDao.loadUserPrefaredPlace();
        List<LookupModel> preferedactivities = loadDataDao.loadUserPrefaredActivity();
        List<LookupModel> userType = loadDataDao.loadUserType();
        dataModel.setUserGender(genderList);
        dataModel.setUserPrefaredActivity(preferedactivities);
        dataModel.setUserPrefaredPlace(preferedplaces);
        dataModel.setUserType(userType);
        return  dataModel;
    }

    @Override
    public boolean IsMailExist(String mail) {
      return userDao.IsMailExist(mail);
    }
    
    @Override
    public boolean IsUserNameExist(String userName) {
      return userDao.IsUserNameExist(userName);
    }

    @Override
    public User GetUserByUserNameAndPassword(LoginModel loginModel) {
       return userDao.GetUserByUserNameAndPassword(loginModel);
    }

}
