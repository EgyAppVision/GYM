/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appvision.gym.dao;

import com.appvision.gym.model.RequestTrainer;
import java.util.List;

/**
 *
 * @author ashraf.ibrahim
 */
public interface RequestDao {
     public boolean AddrequestTrainer (RequestTrainer requestTrainer);
    
    public List<RequestTrainer> GetAllUsersRequest (int userId) throws Exception;
    public List<RequestTrainer> GetAllTrainerRequest(int tarinerId) throws Exception;
    public boolean  ChangeRequestSataus (int requestId, int requestStatus ); 
    
    
}
