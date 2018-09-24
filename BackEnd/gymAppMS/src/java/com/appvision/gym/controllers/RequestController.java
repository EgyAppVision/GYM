/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appvision.gym.controllers;

import com.appvision.gym.model.RequestTrainer;
import com.appvision.gym.services.RequestService;
import com.appvision.gym.services.SignupService;
import java.util.List;
import java.util.logging.Level;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ashraf.ibrahim
 */

@RestController
@RequestMapping("/requestService")
public class RequestController {
    
      @Autowired
    private RequestService requestService;
    Logger debuglog = Logger.getLogger("debuglog");
    
    
     @CrossOrigin(origins = "*")
    @RequestMapping(value = "/changeRequestStatus", method = RequestMethod.GET)
    public String AddRequestTrainer(@Valid @RequestParam String requestidStr , @Valid@RequestParam String requestStatusStr ) 
    {
     
        debuglog.debug("#####################Starting#############################");
         debuglog.debug("Request Recived with requestid= " +requestidStr  + "requestStatus =" + requestStatusStr );
        int requestStatus;
        int requestid;
        try {
            requestStatus = Integer.valueOf(requestStatusStr);
            requestid = Integer.valueOf(requestidStr);
        } catch (Exception ex) {
            debuglog.debug("Invalid input paramter ");
            debuglog.error("Error "+ ex.getMessage() , ex);
            debuglog.debug("#####################Ending#############################");
            return "STATUS:" + -1;
        }

        boolean result = requestService.ChangeRequestSataus(requestid, requestStatus);
        if (result) {
            debuglog.debug("#####################Ending#############################");
            return "STATUS:" + 0;
        }
        debuglog.debug("#####################Ending#############################");
        return "STATUS:" + -3;
    
    }
    
    
      @CrossOrigin(origins = "*")
    @RequestMapping(value = "/GetAllUserRequest", method = RequestMethod.GET)
    public Object GetAllUsersRequest(@Valid @RequestParam String userId ) 
    {
        debuglog.debug("#####################Starting#############################");
         debuglog.debug("Request Recived with userId= " +userId   );
        int userIdInt; 
        try {
            userIdInt = Integer.valueOf(userId);
            
        } catch (Exception ex) {
            debuglog.debug("Invalid input paramter ");
            debuglog.error("Error "+ ex.getMessage() , ex);
            debuglog.debug("#####################Ending#############################");
            return "STATUS:" + -1;
        }

        List<RequestTrainer> result =null ;
          try {
              result = requestService.GetAllUsersRequest(userIdInt);
          } catch (Exception ex) {
              debuglog.debug("an error has occured " + ex.getMessage());
              debuglog.debug("#####################Ending#############################");
              
        return "STATUS:" + -3;
          }
            debuglog.debug("#####################Ending#############################");
            return result;
    }
    
        @CrossOrigin(origins = "*")
    @RequestMapping(value = "/GetAllTrainerRequest", method = RequestMethod.GET)
    public Object GetAllTrainerRequest(@Valid @RequestParam String trainerId ) 
    {
        debuglog.debug("#####################Starting#############################");
         debuglog.debug("Request Recived with userId= " +trainerId   );
        int userIdInt; 
        try {
            userIdInt = Integer.valueOf(trainerId);
            
        } catch (Exception ex) {
            debuglog.debug("Invalid input paramter ");
            debuglog.error("Error "+ ex.getMessage() , ex);
            debuglog.debug("#####################Ending#############################");
            return "STATUS:" + -1;
        }

        List<RequestTrainer> result =null ;
          try {
              result = requestService.GetAllTrainerRequest(userIdInt);
          } catch (Exception ex) {
              debuglog.debug("an error has occured " + ex.getMessage());
              debuglog.debug("#####################Ending#############################");
              
        return "STATUS:" + -3;
          }
            debuglog.debug("#####################Ending#############################");
            return result;
    }
}
