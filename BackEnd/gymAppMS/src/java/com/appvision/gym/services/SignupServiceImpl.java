package com.appvision.gym.services;

import com.appvision.gym.dao.LoadDataDao;
import com.appvision.gym.dao.UserDao;
import com.appvision.gym.defines.Defines;
import com.appvision.gym.model.LoginModel;
import com.appvision.gym.model.LookupModel;
import com.appvision.gym.model.SingupDataModel;
import com.appvision.gym.model.User;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("securityService")
public class SignupServiceImpl implements SignupService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private LoadDataDao loadDataDao;

    private String mailPattern
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private String numPattern = "[0-9]+";

    @Override
    public int userSignUp(User user) {
        return userDao.userSignUp(user);
    }

    @Override
    public SingupDataModel loadData() {

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

    @Override
    public List<User> GetAllUsersByName(String name, int userId,int place, int activity) {
        name = name.trim().toLowerCase();
        if (matchMailRegx(name, mailPattern)) {
            return userDao.GetAllUsersByName(name, userId, Defines.mailSearchingMode , place,  activity);
        } else if (matchMailRegx(name, numPattern)) {
            return userDao.GetAllUsersByName(name, userId, Defines.moblieSearchingMode, place,  activity);
        } else if (name.contains(" ")) {
            return userDao.GetAllUsersByName(name, userId, Defines.twoStringSearchingMode, place,  activity);
        } else {
            return userDao.GetAllUsersByName(name, userId, Defines.oneStringSearchingMode ,  place,  activity);
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

}
