package com.appvision.gym.controllers;

import com.appvision.gym.model.LoginModel;
import com.appvision.gym.model.User;
import com.appvision.gym.services.SignupService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userServices")
public class UserController {

    @Autowired
    private SignupService signupService;
    Logger debuglog = Logger.getLogger("debuglog");
	//get example
    // How to call it http://localhost:8080/gymMS/userServices/isUser/12345
    // Content-Type: application/json 
    // userEmail will be sent in the url like whatyou see in the call
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/isMailExist", method = RequestMethod.GET)
    public Boolean isEamilExist(
            @RequestParam("userEmail") String userEmail) {

        return signupService.IsMailExist(userEmail);

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/isUserNameExist", method = RequestMethod.GET)
    public Boolean isUserNameExist(
            @RequestParam("userName") String userName) {

        return signupService.IsUserNameExist(userName);

    }
	//post example
    // How to call it http://localhost:8080/gymMS/userServices/userSignUp
    // Content-Type: application/json 
    // send user opject in the request body in json format. 
    //example { "userId":123, "userName":"Hamad"}
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/userSignUp", method = RequestMethod.POST)
    public String userSignUp(@Valid @RequestBody User user, BindingResult bindingResult) {
        debuglog.debug("#####################Starting#############################");
        debuglog.debug("receving request  " + user.toString());
        if (!bindingResult.hasErrors()) {

            int result = signupService.userSignUp(user);
            if (result > 0) {
                debuglog.debug("respones : STATUS:" + 0);
                debuglog.debug("#####################End#############################");
                return "STATUS:" + 0 + ",UserID:" + result;
            }
        } else {
            debuglog.debug("Invaild following inptus ");
            for (ObjectError error : bindingResult.getAllErrors()) {
                debuglog.debug(error.getDefaultMessage());
            }
            debuglog.debug("respones : STATUS:" + -3);
            debuglog.debug("#####################End#############################");
            return "STATUS:" + -3;
        }
        debuglog.debug("respones : STATUS:" + -1);
        debuglog.debug("#####################End#############################");
        return "STATUS:" + -1;
    }

	// Another example from Get
    // How to call it http://localhost:8080/gymMS/userServices/getUserById?id=123 
    // Content-Type: text/plainx 
    // I do not recomend this.
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public User getUserById(@RequestParam String id) {
        return new User();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object Login(@RequestBody @Valid LoginModel loginModel, BindingResult bindingResult) {
        debuglog.debug("#####################Starting#############################");
        debuglog.debug("receving request  " + loginModel.toString());
        if (!bindingResult.hasErrors()) {
            debuglog.debug("#####################End#############################");
            try{
            return signupService.GetUserByUserNameAndPassword(loginModel);
            }catch(Exception ex  )
            {
                return "STATUS:-3";
            }
        } else {
            debuglog.debug("Invaild following inptus ");
            for (ObjectError error : bindingResult.getAllErrors()) {
                debuglog.debug(error.getDefaultMessage());
            }
            debuglog.debug("#####################End#############################");
            return "STATUS:-1";
        }

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/GetAllUsersByname", method = RequestMethod.GET)
    public Object GetAllUsersByname(HttpServletRequest request) {
        debuglog.debug("#####################Starting#############################");
        String keyname;
        int userId;
        int place = 0;
        int activity = 0;
        try {

            debuglog.debug("Request Reciver with Keyname= " + request.getParameter("keyname") + " , userId= " + request.getParameter("userId") + " , place = " + request.getParameter("place") + " , activity= " + request.getParameter("activity"));
            keyname = request.getParameter("keyname");
            if (request.getParameter("userId") != null && request.getParameter("userId") != "") {
                userId = new Integer(request.getParameter("userId"));
            } else {
                debuglog.debug("Key name can't be null");
                return  "STATUS:-1";
            }
            if (request.getParameter("place") != null && request.getParameter("place") != "") {
                place = new Integer(request.getParameter("place"));
            }

            if (request.getParameter("activity") != null && request.getParameter("activity") != "") {
                activity = new Integer(request.getParameter("activity"));
            }

            if (keyname == null || keyname == "") {
                debuglog.debug("Key name can't be null");
                return  "STATUS:-1";
            }

            List<User> users = signupService.GetAllUsersByName(keyname, userId, place, activity);
        debuglog.debug("#####################End#############################");
        return users;
        } catch (Exception e) {
            debuglog.error("Error : " + e.getMessage(), e);
            debuglog.debug("#####################End#############################");
            return "STATUS:-3";
        }
        

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/follow", method = RequestMethod.GET)
    public String follow(@RequestParam String following, @RequestParam String follower) {
        debuglog.debug("#####################Starting#############################");
         debuglog.debug("Request Recived with following= " +following  + "follwer =" + follower );
        int followingint;
        int followerint;
        try {
            followingint = Integer.valueOf(following);
            followerint = Integer.valueOf(follower);
        } catch (Exception ex) {
            debuglog.debug("Invalid input paramter ");
            debuglog.error("Error "+ ex.getMessage() , ex);
            debuglog.debug("#####################Ending#############################");
            return "STATUS:" + -1;
        }

        boolean result = signupService.Follow(followerint, followingint);
        if (result) {
            debuglog.debug("#####################Ending#############################");
            return "STATUS:" + 0;
        }
        debuglog.debug("#####################Ending#############################");
        return "STATUS:" + -3;
    }
}
