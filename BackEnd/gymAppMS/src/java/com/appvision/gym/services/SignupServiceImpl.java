package com.appvision.gym.services;

import com.appvision.gym.dao.LoadDataDao;
import com.appvision.gym.dao.RequestDao;
import com.appvision.gym.dao.UserDao;
import com.appvision.gym.defines.Defines;
import com.appvision.gym.model.LoginModel;
import com.appvision.gym.model.LookupModel;
import com.appvision.gym.model.RequestTrainer;
import com.appvision.gym.model.SingupDataModel;
import com.appvision.gym.model.User;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("securityService")
public class SignupServiceImpl implements SignupService {

    
 private Logger debuglog = Logger.getLogger("debuglog");
    @Autowired
    private UserDao userDao;
    @Autowired
    private 
            RequestDao requestDao;
    @Autowired
    private LoadDataDao loadDataDao;



    @Override
    public int userSignUp(User user) {
        debuglog.debug("server request ");
        return userDao.userSignUp(user);
    }

    @Override
    public SingupDataModel loadData() throws Exception {

     try {
         SingupDataModel dataModel = new SingupDataModel();
         List<LookupModel> genderList = loadDataDao.loadUserGender();
         List<LookupModel> preferedplaces = loadDataDao.loadUserPrefaredPlace();
         List<LookupModel> preferedactivities = loadDataDao.loadUserPrefaredActivity();
         List<LookupModel> userType = loadDataDao.loadUserType();
         dataModel.setUserGender(genderList);
         dataModel.setUserPrefaredActivity(preferedactivities);
         dataModel.setUserPrefaredPlace(preferedplaces);
         dataModel.setUserType(userType);
         return dataModel;
     } catch (Exception ex) {
         debuglog.debug("an error has occured " +  ex.getMessage());
     throw  ex;
     }
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
    public User GetUserByUserNameAndPassword(LoginModel loginModel)throws Exception {
        return userDao.GetUserByUserNameAndPassword(loginModel);
    }

    @Override
    public List<User> GetAllUsersByName(String name, int userId,int place, int activity,int userType)throws Exception{
       try{
           
        name = name.trim().toLowerCase();
        debuglog.debug("Scaning Key" + name );
        if (matchMailRegx(name, Defines.mailPattern)) {
            debuglog.debug("E-mail Found selecting by Email " );
            return userDao.GetAllUsersByName(name, userId, Defines.mailSearchingMode , place,  activity, userType);
        } else if (matchMailRegx(name, Defines.mobilePattern)) {
            debuglog.debug("Mobile Found selecting by mobile " );
            return userDao.GetAllUsersByName(name, userId, Defines.moblieSearchingMode, place,  activity,userType);
        } else if (name.contains(" ")) {
            debuglog.debug("firstName and lastName Found selecting by Fristname and lastname  " );
            return userDao.GetAllUsersByName(name, userId, Defines.twoStringSearchingMode, place,  activity,userType);
        } else {
            debuglog.debug("will be selecting by one work as  frist name and last name " );
            return userDao.GetAllUsersByName(name, userId, Defines.oneStringSearchingMode ,  place,  activity,userType);
        }
       }catch(Exception ex ) 
       {
           debuglog.error("An Error has Occured " +ex.getMessage(), ex  );
           throw ex;
       }
       
    }

    private boolean matchMailRegx(String Str, String patternStr) {

        Pattern pattern = Pattern.compile(patternStr);

        Matcher matcher = pattern.matcher(Str);
        return matcher.matches();
    }

    @Override
    public boolean Follow(int follower, int follwing) {
       return userDao.Follow(follower, follwing);
    }

    @Override
    public boolean AddrequestTrainer(RequestTrainer requestTrainer) {
       return requestDao.AddrequestTrainer(requestTrainer);
    }

    @Override
    public boolean UpdateRequestStatus(int requestId, int requestStauts) {
       return requestDao.ChangeRequestSataus(requestId, requestStauts);
    }

    @Override
    public User GetUserProfile(int UserID) throws Exception {
        return userDao.GetUserProfile(UserID);
    }

}
