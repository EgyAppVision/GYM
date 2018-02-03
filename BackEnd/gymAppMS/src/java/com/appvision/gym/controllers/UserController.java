package com.appvision.gym.controllers;

import com.appvision.gym.model.User;
import com.appvision.gym.services.SignupService;
import com.appvision.gym.util.ConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
	@RequestMapping(value = "/isUser/{userEmail}", method = RequestMethod.GET)
	public Boolean isUser(
			@PathVariable("userEmail") String userEmail) {
		return true;
	}
	
	//post example
	// How to call it http://localhost:8080/gymMS/userServices/userSignUp
    // Content-Type: application/json 
	// send user opject in the request body in json format. 
	//example { "userId":123, "userName":"Hamad"}
	@CrossOrigin(origins = "*")  
	@RequestMapping(value = "/userSignUp", method = RequestMethod.POST)
	public String userSignUp(@Valid @RequestBody   User user,BindingResult bindingResult) {
	Connection  con  = null;	
            try {
                if ( !bindingResult.hasErrors()){
                debuglog.debug("receving request  " +user.toString() );
                
                con = ConnectionManager.getConnection();
                boolean result = signupService.userSignUp(user,con);
                if (result)
                  return  "STATUS:" + 0 ;
                }else 
                {
                    return  "STATUS:" + -3 ; 
                }
            } catch (ClassNotFoundException ex) {
                debuglog.error("an error has occured " +  ex.getMessage(),ex);
            } catch (SQLException ex) {
              debuglog.error("an error has occured " +  ex.getMessage(),ex);
            }finally{
                    if (con !=null)
                    {
                        try {
                            con.close();
                        } catch (SQLException ex) {
                            java.util.logging.Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            
            return  "STATUS:" + -1 ;
	}
	
	// Another example from Get
	// How to call it http://localhost:8080/gymMS/userServices/getUserById?id=123 
	// Content-Type: text/plainx 
	// I do not recomend this.
	
	@CrossOrigin(origins = "*")  
	@RequestMapping(value = "/getUserById", method = RequestMethod.GET)
	public User getUserById(@RequestParam  String id) {
		return  new User();
	}
	
}
