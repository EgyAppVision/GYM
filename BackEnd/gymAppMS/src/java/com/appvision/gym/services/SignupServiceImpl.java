package com.appvision.gym.services;

import com.appvision.gym.dao.LoadDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appvision.gym.dao.UserDao;
import com.appvision.gym.model.LookupModel;
import com.appvision.gym.model.SingupDataModel;
import com.appvision.gym.model.User;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


@Service("securityService")
public class SignupServiceImpl implements SignupService{

	@Autowired
	private UserDao securityDao;
        @Autowired 
        private  LoadDataDao loadDataDao;
	
	
	@Override
	public  boolean userSignUp(User user) {
		return securityDao.userSignUp(user);
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

}
