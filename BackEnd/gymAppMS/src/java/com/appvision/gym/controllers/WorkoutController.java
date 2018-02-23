/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appvision.gym.controllers;

import com.appvision.gym.model.User;
import com.appvision.gym.model.Workout;
import com.appvision.gym.services.WorkoutService;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ashraf.ibrahim
 */
@RestController
@RequestMapping("/Workout")
public class WorkoutController {
 
    @Autowired
    WorkoutService workoutService;
    
    Logger debuglog = Logger.getLogger("debuglog");
    
    @CrossOrigin(origins = "*")  
	@RequestMapping(value = "/addworkout", method = RequestMethod.POST)
	public String Addworkout(@Valid @RequestBody   Workout workout,BindingResult bindingResult) {
		
        if ( !bindingResult.hasErrors()){
            debuglog.debug("receving request  " +workout.toString() );
            
            //con = ConnectionManager.getConnection();
            boolean result = workoutService.AddWorkOut(workout);
            if (result)
                return  "STATUS:" + 0 ;
        }else
        {
            return  "STATUS:" + -3 ;
        }
            
            return  "STATUS:" + -1 ;
	}
}
