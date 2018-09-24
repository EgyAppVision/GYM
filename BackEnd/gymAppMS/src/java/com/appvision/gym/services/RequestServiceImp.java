/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appvision.gym.services;

import com.appvision.gym.dao.RequestDao;
import com.appvision.gym.model.RequestTrainer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ashraf.ibrahim
 */
@Service("RequestService")
public class RequestServiceImp  implements RequestService{
 
    @Autowired 
    RequestDao  requestDao;

    @Override
    public boolean ChangeRequestSataus(int requestId, int requestStatus) {
      return   requestDao.ChangeRequestSataus(requestId, requestStatus);
    }

    @Override
    public List<RequestTrainer> GetAllUsersRequest(int userId) throws Exception {
      return requestDao.GetAllUsersRequest(userId);
    }

    @Override
    public List<RequestTrainer> GetAllTrainerRequest(int tarinerId) throws Exception {
        return requestDao.GetAllTrainerRequest(tarinerId);
    }
}
