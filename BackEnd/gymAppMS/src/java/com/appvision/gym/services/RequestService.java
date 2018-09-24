/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appvision.gym.services;

import com.appvision.gym.model.RequestTrainer;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author ashraf.ibrahim
 */

public interface RequestService {
    
    public boolean  ChangeRequestSataus (int requestId, int requestStatus );
    public List<RequestTrainer> GetAllUsersRequest (int userId) throws Exception;
    public List<RequestTrainer> GetAllTrainerRequest(int tarinerId) throws Exception;
}
