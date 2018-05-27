/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appvision.gym.dao;

import com.appvision.gym.model.Exercise;
import com.appvision.gym.model.Workout;
import com.appvision.gym.model.WorkoutExcerices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ashraf.ibrahim
 */
@Repository("workout")
public class WorkoutDaoImp implements WorkoutDao{

    private Logger debuglog = Logger.getLogger("debuglog");
     @Autowired
    private JdbcTemplate jdbcTemplate;
     
    @Override
    public boolean AddWorkOut(final Workout workout) throws Exception{
       KeyHolder key = new GeneratedKeyHolder();
       try{
       PreparedStatementCreator creator = new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO user_workout(user_workout_desc,user_workout_trainee) VALUES (?,?);", 
            Statement.RETURN_GENERATED_KEYS);
        ps.setInt(2, workout.getWorkoutTrainee());
        ps.setString(1, workout.getWorkOutDes());
        return ps;
      }
       };
      //INSERT INTO user_workout_exercies(user_workout_exercies_workout,user_workout_exercies_exercies,user_workout_exercies_trainer_comment,user_workout_exercies_trainee_comment)VALUES(?,?,?,?);

   jdbcTemplate.update(creator, key);       
   
   int id = key.getKey().intValue();
   
   for (WorkoutExcerices exercise :  workout.getWorkoutExcerices())
   {
       String Sql =  "INSERT INTO user_workout_exercies(user_workout_exercies_workout,user_workout_exercies_exercies,user_workout_exercies_trainee_comment)VALUES("+id+","+exercise.getExcericeId()+",'"+exercise.getExcericeDescreption()+"')";
    jdbcTemplate.update(Sql);
   }
       return true;
    }catch(Exception ex)
    {
        debuglog.error("error while excutin sql >>  Error :" + ex.getMessage(), ex);
        throw ex;
    }
    }
}
