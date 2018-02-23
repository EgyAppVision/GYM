package com.appvision.gym.controllers;

import com.appvision.gym.model.Exercise;
import com.appvision.gym.model.Muscle;
import com.appvision.gym.services.SignupService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appvision.gym.model.SingupDataModel;
import com.appvision.gym.services.LoadDataService;
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

	// Another example from Get
    // How to call it http://localhost:8080/gymMS/userServices/getUserById?id=123 
    // Content-Type: text/plainx 
    // I do not recomend this.
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getSingupData", method = RequestMethod.GET)
    public SingupDataModel getSingupData() {
        return signupService.loadData();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getMainMuscles", method = RequestMethod.GET)
    public List<Muscle> getMainMuscles() {
        return loadDataService.loadMainMuscles();

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getsubMuscles", method = RequestMethod.GET)
    public List<Muscle> getSubMuscles(@RequestParam int mainMuscleId) {
        return loadDataService.LoadSubMuscles(mainMuscleId);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getSubMuscleExerciseByMainMuscleId", method = RequestMethod.GET)
    public List<Exercise> getSubMuscleExerciseByMainMuscleId(@RequestParam int mainMuscleId) {
        return loadDataService.LoadMuscleExceriseByMainMuscleId(mainMuscleId);
    } 
    
      
};
