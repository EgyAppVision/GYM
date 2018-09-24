package com.appvision.gym.controllers;

import com.appvision.gym.services.SignupService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appvision.gym.services.LoadDataService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/loadDataServices")
public class LoadDataController {

    @Autowired
    private SignupService signupService;

    @Autowired
    private LoadDataService loadDataService;
    Logger debuglog = Logger.getLogger("debuglog");


	// Another example from Get
    // How to call it http://localhost:8080/gymMS/userServices/getUserById?id=123 
    // Content-Type: text/plainx 
    // I do not recomend this.
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getSingupData", method = RequestMethod.GET)
    public Object getSingupData() {
        try {
            return signupService.loadData();
        } catch (Exception ex) {
            debuglog.debug("an error has Occured " + ex.getMessage());
            return "STATUS:-3";
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getMainMuscles", method = RequestMethod.GET)
    public Object getMainMuscles() {
        try {
            return loadDataService.loadMainMuscles();
        } catch (Exception ex) {
            debuglog.debug("an error has Occured " + ex.getMessage());
            return "STATUS:-3";
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getsubMuscles", method = RequestMethod.GET)
    public Object getSubMuscles(@RequestParam int mainMuscleId) {
        try {
            return loadDataService.LoadSubMuscles(mainMuscleId);
        } catch (Exception ex) {
            debuglog.debug("an error has Occured " + ex.getMessage());
            return "STATUS:-3";
        }
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getSubMuscleExerciseByMainMuscleId", method = RequestMethod.GET)
    public Object getSubMuscleExerciseByMainMuscleId(@RequestParam int mainMuscleId) {
        try {
            return loadDataService.LoadMuscleExceriseByMainMuscleId(mainMuscleId);
        } catch (Exception ex) {
             debuglog.debug("an error has Occured " + ex.getMessage());
            return "STATUS:-3";
        }
    } 
   
}
