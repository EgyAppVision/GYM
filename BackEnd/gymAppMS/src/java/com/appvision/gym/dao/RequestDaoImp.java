/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appvision.gym.dao;

import com.appvision.gym.model.LookupModel;
import com.appvision.gym.model.RequestTrainer;
import com.appvision.gym.model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ashraf.ibrahim
 */
@Repository("RequestDao")
public class RequestDaoImp implements RequestDao{
    
     @Autowired
    private JdbcTemplate jdbcTemplate;
    private Logger debuglog = Logger.getLogger("debuglog");
    
//     @Override
//    public boolean AddrequestTrainer( final RequestTrainer requestTrainer) {
//       try {
//            PreparedStatementCreator creator = new PreparedStatementCreator() {
//                @Override
//                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//
//                    PreparedStatement ps = connection.prepareStatement("INSERT INTO request_Trainer(user_id,trainer_id,request_status,user_comment,Activity)VALUES(?,?,?,?,?);;",
//                            Statement.RETURN_GENERATED_KEYS);
//                    ps.setInt(1, requestTrainer.getUserId());
//                    ps.setInt(2, requestTrainer.getTrainerId());
//                    ps.setInt(3, 1);
//                    ps.setString(4, requestTrainer.getUserComment());
//                    ps.setInt(5, requestTrainer.getActivity());
//                    
//                    return ps;
//                }
//            };
//
//            jdbcTemplate.update(creator);
//            return true;
//
//        } catch (Exception ex) {
//            debuglog.error("error while excutin sql >>  Error :" + ex.getMessage(), ex);
//        }
//
//        return false;
//    }

    
      @Override
    public boolean AddrequestTrainer( final RequestTrainer requestTrainer) {
       try {
            PreparedStatementCreator creator = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                    PreparedStatement ps = connection.prepareStatement("INSERT INTO TrainingRequest (numOfSession,playerMessage,status,activityTypeId,playerId,trainerId) VALUES (?,?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, requestTrainer.getNumOfSession());
                    ps.setString(2, requestTrainer.getUserComment());
                    ps.setInt(3, 1);//set status to pending 
                    ps.setInt(4, requestTrainer.getActivity());
                    ps.setInt(5, requestTrainer.getUserId());
                    ps.setInt(6, requestTrainer.getTrainerId());
                    
                    return ps;
                }
            };

            jdbcTemplate.update(creator);
            return true;

        } catch (Exception ex) {
            debuglog.error("error while excutin sql >>  Error :" + ex.getMessage(), ex);
        }

        return false;
    }
    
    @Override
    public List<RequestTrainer> GetAllUsersRequest(int userId) throws Exception {
        try {
            String sql = "select * from TrainingRequest inner join RequestStatusLookup on TrainingRequest.status = RequestStatusLookup.idRequestStatusLookup inner join user on TrainingRequest.trainerId =user.user_id inner join prefered_activity on  prefered_activity.prefered_activity_id =TrainingRequest.activityTypeId  where TrainingRequest.playerId  = " + userId;
            debuglog.debug("Select user form data base ");
            debuglog.debug("SQL : " + sql);
            return jdbcTemplate.query(sql, new ResultSetExtractor<List<RequestTrainer>>() {
                @Override
                public List<RequestTrainer>  extractData(ResultSet rs) throws SQLException,
                        DataAccessException {
                    debuglog.debug("Extract data ");
                    List<RequestTrainer> requestTrainers = new ArrayList<RequestTrainer>(); 
                    while (rs.next()) {
                       RequestTrainer requestTrainer = new RequestTrainer();
                        LookupModel activity  = new LookupModel();
                       User trainer = new User();
                       requestTrainer.setRequestId(rs.getInt("idTrainingRequest"));
                       requestTrainer.setRequestStatus(rs.getInt("status"));
                       requestTrainer.setRequestStatusDescreption(rs.getString("statusName"));
                       requestTrainer.setUserComment(rs.getString("playerMessage"));
                       requestTrainer.setTrainerComment(rs.getString("trainerMessage"));
                       trainer.setFirstName(rs.getString("user_first_name"));
                       trainer.setLastName(rs.getString("user_last_name"));
                       trainer.setEmail(rs.getString("user_email"));
                       trainer.setMobile(rs.getString("user_mobile"));
                       trainer.setBirthDate(rs.getDate("user_birth_date"));
                       trainer.setWeight(rs.getInt("user_weight"));
                       trainer.setHeight(rs.getInt("user_tall"));
                       trainer.setGender(rs.getInt("user_gender"));
                       trainer.setUserId(rs.getInt("user_id"));
                       requestTrainer.setTrainer(trainer);
                       activity.setId(rs.getInt("activityTypeId"));
                       activity.setValue(rs.getString("prefered_activity_name"));
                       requestTrainer.setActivityModel(activity);
                       requestTrainers.add(requestTrainer);
                    }
                    return requestTrainers;
                    
                }
            });
        } catch (Exception ex) {
            debuglog.error("error while excutin sql >>  Error :" + ex.getMessage(), ex);
            throw ex;
        }
    }
    
    
    
     @Override
    public List<RequestTrainer> GetAllTrainerRequest(int trainerId) throws Exception{
        try {
            String sql = "select * from TrainingRequest inner join RequestStatusLookup on TrainingRequest.status = RequestStatusLookup.idRequestStatusLookup inner join user on TrainingRequest.playerId =user.user_id inner join prefered_activity on  prefered_activity.prefered_activity_id =TrainingRequest.activityTypeId  where TrainingRequest.trainerId  =  " + trainerId;
            debuglog.debug("Select user form data base ");
            debuglog.debug("SQL : " + sql);
            return jdbcTemplate.query(sql, new ResultSetExtractor<List<RequestTrainer>>() {
                @Override
                public List<RequestTrainer>  extractData(ResultSet rs) throws SQLException,
                        DataAccessException {
                    debuglog.debug("Extract data ");
                    List<RequestTrainer> requestTrainers = new ArrayList<RequestTrainer>(); 
                    while (rs.next()) {
                       RequestTrainer requestTrainer = new RequestTrainer();
                        LookupModel activity  = new LookupModel();
                       User trainee = new User();
                       requestTrainer.setRequestId(rs.getInt("idTrainingRequest"));
                       requestTrainer.setRequestStatus(rs.getInt("status"));
                       requestTrainer.setRequestStatusDescreption(rs.getString("statusName"));
                       requestTrainer.setUserComment(rs.getString("playerMessage"));
                       requestTrainer.setTrainerComment(rs.getString("trainerMessage"));
                       trainee.setFirstName(rs.getString("user_first_name"));
                       trainee.setLastName(rs.getString("user_last_name"));
                       trainee.setEmail(rs.getString("user_email"));
                       trainee.setMobile(rs.getString("user_mobile"));
                       trainee.setBirthDate(rs.getDate("user_birth_date"));
                       trainee.setWeight(rs.getInt("user_weight"));
                       trainee.setHeight(rs.getInt("user_tall"));
                       trainee.setGender(rs.getInt("user_gender"));
                       trainee.setUserId(rs.getInt("user_id"));
                       requestTrainer.setTrainee(trainee);
                       activity.setId(rs.getInt("activityTypeId"));
                       activity.setValue(rs.getString("prefered_activity_name"));
                       requestTrainer.setActivityModel(activity);
                       requestTrainers.add(requestTrainer);
                    }
                    return requestTrainers;
                    
                }
            });
        } catch (Exception ex) {
            debuglog.error("error while excutin sql >>  Error :" + ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean ChangeRequestSataus(final int requestId,final int requestStatus) {
        try {
            debuglog.debug("Preparing insert statment ");
           
            PreparedStatementCreator creator = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement("update TrainingRequest set  status= ? where idTrainingRequest = ?");
                    ps.setInt(1, requestStatus);
                    ps.setInt(2, requestId);
                    return ps;
                }
            };
            debuglog.debug("updating Data to Database  ");
            jdbcTemplate.update(creator);
            debuglog.debug("Data updated  to Database  ");
            return true;
        } catch (Exception ex) {
            debuglog.error("error while excutin sql >>  Error :" + ex.getMessage(), ex);
        }

        return false;
    }

}
