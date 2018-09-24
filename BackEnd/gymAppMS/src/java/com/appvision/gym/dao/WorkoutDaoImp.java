/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appvision.gym.dao;

import com.appvision.gym.model.ExerciseProprty;
import com.appvision.gym.model.RequestWorkout;
import com.appvision.gym.model.RequestWorkoutExerice;
import com.appvision.gym.model.RequestWorkoutExerciseV2;
import com.appvision.gym.model.RequestWorkoutV2;
import com.appvision.gym.model.Workout;
import com.appvision.gym.model.WorkoutExcerices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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
@Repository("workout")
public class WorkoutDaoImp implements WorkoutDao {

    private Logger debuglog = Logger.getLogger("debuglog");
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean AddWorkOut(final Workout workout) throws Exception {
        KeyHolder key = new GeneratedKeyHolder();
        try {
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

            for (WorkoutExcerices exercise : workout.getWorkoutExcerices()) {
                String Sql = "INSERT INTO user_workout_exercies(user_workout_exercies_workout,user_workout_exercies_exercies,user_workout_exercies_trainee_comment)VALUES(" + id + "," + exercise.getExcericeId() + ",'" + exercise.getExcericeDescreption() + "')";
                jdbcTemplate.update(Sql);
            }
            return true;
        } catch (Exception ex) {
            debuglog.error("error while excutin sql >>  Error :" + ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean AddRequestWorkout(final RequestWorkout workout) throws Exception {
        KeyHolder key = new GeneratedKeyHolder();
        try {
            PreparedStatementCreator creator = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO user_workout(user_workout_desc,user_workout_trainee, user_workout_request) VALUES (?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(2, workout.getWorkoutTrainee());
                    ps.setString(1, workout.getWorkOutDes());
                    ps.setInt(3, workout.getRequestId());
                    return ps;
                }
            };
            //INSERT INTO user_workout_exercies(user_workout_exercies_workout,user_workout_exercies_exercies,user_workout_exercies_trainer_comment,user_workout_exercies_trainee_comment)VALUES(?,?,?,?);

            jdbcTemplate.update(creator, key);

            final int workoutId = key.getKey().intValue();

            for (RequestWorkoutExerice exercise : workout.getWorkoutExcerices()) {
                KeyHolder exKey = new GeneratedKeyHolder();
                final RequestWorkoutExerice tempex = exercise;
                PreparedStatementCreator exerciseCreator = new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                        PreparedStatement ps = connection.prepareStatement("INSERT INTO request_workout_exercies (workout_id,exercie_id) VALUES (?,?);",
                                Statement.RETURN_GENERATED_KEYS);
                        ps.setInt(1, workoutId);
                        ps.setInt(2, tempex.getExericeId());

                        return ps;
                    }
                };
                //String Sql =  "INSERT INTO user_workout_exercies(user_workout_exercies_workout,user_workout_exercies_exercies,user_workout_exercies_trainee_comment)VALUES("+id+","+exercise.getExcericeId()+",'"+exercise.getExcericeDescreption()+"')";
                jdbcTemplate.update(exerciseCreator, exKey);

                final int exId = exKey.getKey().intValue();
                PreparedStatementCreator propertyAdvisedCreator = new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        String adviseSql = "INSERT INTO proposed_exercise_property(excercise_id,key_name,key_value)VALUES";
                        if (tempex.getAdvisedSets() != null & tempex.getAdvisedSets() != "") {
                            adviseSql += "(?,?,?)";
                        }
                        if (tempex.getAdvisedReps() != null & tempex.getAdvisedReps() != "") {
                            adviseSql += ",(?,?,?)";
                        }
                        if (tempex.getAdvisedweight() != null & tempex.getAdvisedweight() != "") {
                            adviseSql += ",(?,?,?)";
                        }

                        PreparedStatement ps = connection.prepareStatement(adviseSql,
                                Statement.RETURN_GENERATED_KEYS);
                        if (tempex.getAdvisedSets() != null & tempex.getAdvisedSets() != "") {
                            ps.setInt(1, exId);
                            ps.setString(2, "SET");
                            ps.setString(3, tempex.getAdvisedSets());

                        }
                        if (tempex.getAdvisedReps() != null & tempex.getAdvisedReps() != "") {
                            ps.setInt(4, exId);
                            ps.setString(5, "REPETITION");
                            ps.setString(6, tempex.getAdvisedReps());
                        }
                        if (tempex.getAdvisedweight() != null & tempex.getAdvisedweight() != "") {
                            ps.setInt(7, exId);
                            ps.setString(8, "WEIGHT");
                            ps.setString(9, tempex.getAdvisedweight());
                        }

                        return ps;
                    }
                };

                jdbcTemplate.update(propertyAdvisedCreator);

                PreparedStatementCreator propertyAtualCreator = new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                        String actualSql = "INSERT INTO Actual_exercise_property(excercise_id,key_name,key_value)VALUES";

                        if (tempex.getActualSets() != null & tempex.getActualSets() != "") {
                            actualSql += "(?,?,?)";
                        }
                        if (tempex.getActualReps() != null & tempex.getActualReps() != "") {
                            actualSql += ",(?,?,?)";
                        }
                        if (tempex.getActualweight() != null & tempex.getActualweight() != "") {
                            actualSql += ",(?,?,?)";
                        }

                        PreparedStatement ps = connection.prepareStatement(actualSql,
                                Statement.RETURN_GENERATED_KEYS);

                        if (tempex.getActualSets() != null & tempex.getActualSets() != "") {
                            ps.setInt(1, exId);
                            ps.setString(2, "SET");
                            ps.setString(3, tempex.getActualSets());
                        }
                        if (tempex.getActualReps() != null & tempex.getActualReps() != "") {
                            ps.setInt(4, exId);
                            ps.setString(5, "REPETITION");
                            ps.setString(6, tempex.getActualReps());
                        }
                        if (tempex.getActualweight() != null & tempex.getActualweight() != "") {
                            ps.setInt(7, exId);
                            ps.setString(8, "WEIGHT");
                            ps.setString(9, tempex.getActualweight());
                        }

                        return ps;
                    }
                };

                jdbcTemplate.update(propertyAtualCreator);
            }
            return true;
        } catch (Exception ex) {
            debuglog.error("error while excutin sql >>  Error :" + ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean updateRequestWorkout(final RequestWorkout workout) throws Exception {

        try {

            final int workoutId = workout.getWorkoutId();

            for (RequestWorkoutExerice exercise : workout.getWorkoutExcerices()) {
                KeyHolder exKey = new GeneratedKeyHolder();
                final RequestWorkoutExerice tempex = exercise;
                PreparedStatementCreator exerciseCreator = new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                        PreparedStatement ps = connection.prepareStatement("INSERT INTO request_workout_exercies (workout_id,exercie_id) VALUES (?,?);",
                                Statement.RETURN_GENERATED_KEYS);
                        ps.setInt(1, workoutId);
                        ps.setInt(2, tempex.getExericeId());

                        return ps;
                    }
                };
                //String Sql =  "INSERT INTO user_workout_exercies(user_workout_exercies_workout,user_workout_exercies_exercies,user_workout_exercies_trainee_comment)VALUES("+id+","+exercise.getExcericeId()+",'"+exercise.getExcericeDescreption()+"')";
                jdbcTemplate.update(exerciseCreator, exKey);

                final int exId = exKey.getKey().intValue();
                PreparedStatementCreator propertyAdvisedCreator = new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        String adviseSql = "INSERT INTO proposed_exercise_property(excercise_id,key_name,key_value)VALUES";
                        if (tempex.getAdvisedSets() != null & tempex.getAdvisedSets() != "") {
                            adviseSql += "(?,?,?)";
                        }
                        if (tempex.getAdvisedReps() != null & tempex.getAdvisedReps() != "") {
                            adviseSql += ",(?,?,?)";
                        }
                        if (tempex.getAdvisedweight() != null & tempex.getAdvisedweight() != "") {
                            adviseSql += ",(?,?,?)";
                        }

                        PreparedStatement ps = connection.prepareStatement(adviseSql,
                                Statement.RETURN_GENERATED_KEYS);
                        if (tempex.getAdvisedSets() != null & tempex.getAdvisedSets() != "") {
                            ps.setInt(1, exId);
                            ps.setString(2, "SET");
                            ps.setString(3, tempex.getAdvisedSets());

                        }
                        if (tempex.getAdvisedReps() != null & tempex.getAdvisedReps() != "") {
                            ps.setInt(4, exId);
                            ps.setString(5, "REPETITION");
                            ps.setString(6, tempex.getAdvisedReps());
                        }
                        if (tempex.getAdvisedweight() != null & tempex.getAdvisedweight() != "") {
                            ps.setInt(7, exId);
                            ps.setString(8, "WEIGHT");
                            ps.setString(9, tempex.getAdvisedweight());
                        }

                        return ps;
                    }
                };

                jdbcTemplate.update(propertyAdvisedCreator);

                PreparedStatementCreator propertyAtualCreator = new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                        String actualSql = "INSERT INTO Actual_exercise_property(excercise_id,key_name,key_value)VALUES";

                        if (tempex.getActualSets() != null & tempex.getActualSets() != "") {
                            actualSql += "(?,?,?)";
                        }
                        if (tempex.getActualReps() != null & tempex.getActualReps() != "") {
                            actualSql += ",(?,?,?)";
                        }
                        if (tempex.getActualweight() != null & tempex.getActualweight() != "") {
                            actualSql += ",(?,?,?)";
                        }

                        PreparedStatement ps = connection.prepareStatement(actualSql,
                                Statement.RETURN_GENERATED_KEYS);

                        if (tempex.getActualSets() != null & tempex.getActualSets() != "") {
                            ps.setInt(1, exId);
                            ps.setString(2, "SET");
                            ps.setString(3, tempex.getActualSets());
                        }
                        if (tempex.getActualReps() != null & tempex.getActualReps() != "") {
                            ps.setInt(4, exId);
                            ps.setString(5, "REPETITION");
                            ps.setString(6, tempex.getActualReps());
                        }
                        if (tempex.getActualweight() != null & tempex.getActualweight() != "") {
                            ps.setInt(7, exId);
                            ps.setString(8, "WEIGHT");
                            ps.setString(9, tempex.getActualweight());
                        }

                        return ps;
                    }
                };

                jdbcTemplate.update(propertyAtualCreator);
            }
            return true;
        } catch (Exception ex) {
            debuglog.error("error while excutin sql >>  Error :" + ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public boolean AddRequestWorkout(final RequestWorkoutV2 workout) throws Exception {
        KeyHolder key = new GeneratedKeyHolder();
        try {
            PreparedStatementCreator creator = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO workout (requestWorkoutId,trainerComment, activityTypeId, workoutName) VALUES (?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, workout.getRequestId());
                    ps.setString(2, workout.getTrainerComment());
                    ps.setInt(3, workout.getActivityId());
                    ps.setString(4, workout.getWorkoutName());
                    return ps;
                }
            };
            //INSERT INTO user_workout_exercies(user_workout_exercies_workout,user_workout_exercies_exercies,user_workout_exercies_trainer_comment,user_workout_exercies_trainee_comment)VALUES(?,?,?,?);

            jdbcTemplate.update(creator, key);

            final int workoutid = key.getKey().intValue();
//   for (final RequestWorkoutExericeV2 rw : workout.getWorkoutExcerices())
//   {
//        PreparedStatementCreator excericeCreator = new PreparedStatementCreator() {
//      @Override
//      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//        PreparedStatement ps = connection.prepareStatement("INSERT INTO proposedExerciseProperty ('excerciseId','keyName','keyValue') VALUES (?,?,?)", 
//            Statement.RETURN_GENERATED_KEYS);
//        ps.setInt(1, rw.getExericeId());
//        ps.setString(2, workout.getTrainerComment());
//        ps.setInt(3, workout.getActivityId());
//        ps.setString(4, workout.getWorkoutName());
//        return ps;
//      }
//       };
//   }

            int[] updated = jdbcTemplate.batchUpdate("INSERT INTO WorkoutExcercise (workoutId,excerciseId) VALUES (?,?);", new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    RequestWorkoutExerciseV2 rw = workout.getWorkoutExcerices().get(i);
                    ps.setInt(1, workoutid);
                    ps.setInt(2, rw.getExericeId());
                }

                @Override
                public int getBatchSize() {
                    return workout.getWorkoutExcerices().size();
                }
            });

            final Map<Integer, Integer> hashmap = jdbcTemplate.query("SELECT * FROM gym.WorkoutExcercise  where workoutId =  " + workoutid, new ResultSetExtractor<Map>() {
                @Override
                public Map extractData(ResultSet rs) throws SQLException, DataAccessException {
                    HashMap<Integer, Integer> generatedkeys = new HashMap<Integer, Integer>();
                    while (rs.next()) {
                        generatedkeys.put(rs.getInt("excerciseId"), rs.getInt("idWorkoutExcercise"));
                    }
                    return generatedkeys;
                }

            });

//            jdbcTemplate.batchUpdate(
//        "INSERT INTO proposedExerciseProperty (excerciseId,keyName,keyValue) VALUES (?,?,?)",
//        ,
//                new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR}
// );
            int[] updated1 = jdbcTemplate.batchUpdate("INSERT INTO proposedExerciseProperty (excerciseId,keyName,keyValue) VALUES (?,?,?) ,(?,?,?), (?,?,?)", new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    RequestWorkoutExerciseV2 rw = workout.getWorkoutExcerices().get(i);
                    if (rw.getProposedEx().getExcericeSets() != null && rw.getProposedEx().getExcericeSets() != "") {
                        ps.setInt(1, hashmap.get(rw.getExericeId()));
                        ps.setString(2, "SET");
                        ps.setString(3, rw.getProposedEx().getExcericeSets());
                    } else {
                        ps.setInt(1, hashmap.get(rw.getExericeId()));
                        ps.setString(2, "SET");
                        ps.setString(3, "0");
                    }

                    if (rw.getProposedEx().getExcericeReps() != null && rw.getProposedEx().getExcericeReps() != "") {
                        ps.setInt(4, hashmap.get(rw.getExericeId()));
                        ps.setString(5, "REPETITION");
                        ps.setString(6, rw.getProposedEx().getExcericeReps());
                    } else {
                        ps.setInt(4, hashmap.get(rw.getExericeId()));
                        ps.setString(5, "REPETITION");
                        ps.setString(6, "0");
                    }

                    if (rw.getProposedEx().getExcericeweight() != null && rw.getProposedEx().getExcericeweight() != "") {
                        ps.setInt(7, hashmap.get(rw.getExericeId()));
                        ps.setString(8, "WEIGHT");
                        ps.setString(9, rw.getProposedEx().getExcericeweight());
                    } else {
                        ps.setInt(7, hashmap.get(rw.getExericeId()));
                        ps.setString(8, "WEIGHT");
                        ps.setString(9, "0");
                    }
                }

                @Override
                public int getBatchSize() {
                    return workout.getWorkoutExcerices().size();
                }
            });

        } catch (Exception ex) {

            throw ex;
        }

        return true;
    }

    @Override
    public boolean AddActualWorkout(final RequestWorkoutV2 workout) throws Exception {

        try {

            final Map<Integer, Integer> hashmap = jdbcTemplate.query("SELECT * FROM gym.WorkoutExcercise  where workoutId =  " + workout.getWorkoutId(), new ResultSetExtractor<Map>() {
                @Override
                public Map extractData(ResultSet rs) throws SQLException, DataAccessException {
                    HashMap<Integer, Integer> generatedkeys = new HashMap<Integer, Integer>();
                    while (rs.next()) {
                        generatedkeys.put(rs.getInt("excerciseId"), rs.getInt("idWorkoutExcercise"));
                    }
                    return generatedkeys;
                }

            });

//            jdbcTemplate.batchUpdate(
//        "INSERT INTO proposedExerciseProperty (excerciseId,keyName,keyValue) VALUES (?,?,?)",
//        ,
//                new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR}
// );
            int[] updated1 = jdbcTemplate.batchUpdate("INSERT INTO ActualExcerciseProperty (excerciseId,keyName,keyValue) VALUES (?,?,?) ,(?,?,?), (?,?,?)", new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    RequestWorkoutExerciseV2 rw = workout.getWorkoutExcerices().get(i);
                    if (rw.getProposedEx().getExcericeSets() != null && rw.getProposedEx().getExcericeSets() != "") {
                        ps.setInt(1, hashmap.get(rw.getExericeId()));
                        ps.setString(2, "SET");
                        ps.setString(3, rw.getProposedEx().getExcericeSets());
                    } else {
                        ps.setInt(1, hashmap.get(rw.getExericeId()));
                        ps.setString(2, "SET");
                        ps.setString(3, "0");
                    }
                    if (rw.getProposedEx().getExcericeReps() != null && rw.getProposedEx().getExcericeReps() != "") {
                        ps.setInt(4, hashmap.get(rw.getExericeId()));
                        ps.setString(5, "REPETITION");
                        ps.setString(6, rw.getProposedEx().getExcericeReps());
                    } else {
                        ps.setInt(4, hashmap.get(rw.getExericeId()));
                        ps.setString(5, "REPETITION");
                        ps.setString(6, "0");
                    }
                    if (rw.getProposedEx().getExcericeweight() != null && rw.getProposedEx().getExcericeweight() != "") {
                        ps.setInt(7, hashmap.get(rw.getExericeId()));
                        ps.setString(8, "WEIGHT");
                        ps.setString(9, rw.getProposedEx().getExcericeweight());
                    } else {
                        ps.setInt(7, hashmap.get(rw.getExericeId()));
                        ps.setString(8, "WEIGHT");
                        ps.setString(9, "0");
                    }
                }

                @Override
                public int getBatchSize() {
                    return workout.getWorkoutExcerices().size();
                }
            });

        } catch (Exception e) {
            throw e;
        }

        return true;
    }

    @Override
    public List<RequestWorkoutV2> GetRequestWorkout(int requestId) throws Exception {

        String sql = "SELECT distinct  * FROM TrainingRequest tr \n"
                + "inner join workout w on w.requestWorkoutId= tr.idTrainingRequest\n"
                + "inner join  WorkoutExcercise we on we.workoutId=w.idworkout\n"
                + "inner join prefered_activity pe on tr.activityTypeId = pe.prefered_activity_id\n"
                + "inner join exercise  ex on ex.exercise_id = we.excerciseId \n"
                + "inner join (\n"
                + "SELECT  pep.excerciseId,pep.keyName,pep.keyValue proposed  ,aep.keyValue actual ,  CAST(aep.date AS DATE) AS Date   FROM proposedExerciseProperty pep\n"
                + "left join ActualExcerciseProperty aep on pep.excerciseId =aep.excerciseId and pep.keyName=aep.keyName \n"
                + ") prop on prop.excerciseId=we.idWorkoutExcercise\n"
                + "where tr.idTrainingRequest="+requestId+ "\n"
                + "order by idworkout, we.idWorkoutExcercise,prop.date  ";

        return jdbcTemplate.query(sql, new ResultSetExtractor<List<RequestWorkoutV2>>() {

            @Override
            public List<RequestWorkoutV2> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<RequestWorkoutV2> requestWorkoutList = new ArrayList<RequestWorkoutV2>();
                RequestWorkoutV2 requestWorkout = new RequestWorkoutV2();
                boolean exChanged = false;
                boolean dateCahnged = true;
                int oldExID = 0;
                Date olddate = null;
                int oldworkoutId = 0;
                if (rs.next()) {
                    requestWorkout.setWorkoutId(rs.getInt("idworkout"));
                    requestWorkout.setRequestId(rs.getInt("idTrainingRequest"));
                    requestWorkout.setActivityName(rs.getString("prefered_activity_name"));
                    requestWorkout.setTraineeComment(rs.getString("playerMessage"));
                    requestWorkout.setTrainerComment(rs.getString("trainerMessage"));
                    requestWorkout.setWorkoutName(rs.getString("workoutName"));

                }

                ArrayList<RequestWorkoutExerciseV2> workoutExcerices = new ArrayList<RequestWorkoutExerciseV2>();
                RequestWorkoutExerciseV2 exerciseV2 = new RequestWorkoutExerciseV2();
                exerciseV2.setExericeId(rs.getInt("exercise_id"));
                exerciseV2.setExericeName(rs.getString("exercise_desc"));
                ExerciseProprty actual = new ExerciseProprty();
                ExerciseProprty prop = new ExerciseProprty();
                List<ExerciseProprty> actualList = new ArrayList<ExerciseProprty>();
                rs.previous();
                while (rs.next()) {

                    if (oldworkoutId != 0 && oldworkoutId != rs.getInt("idworkout")) {
                        actualList.add(actual);
                        exerciseV2.setActuaExlist(actualList);
                        exerciseV2.setProposedEx(prop);
                        workoutExcerices.add(exerciseV2);
                        requestWorkout.setWorkoutExcerices(workoutExcerices);
                        requestWorkoutList.add(requestWorkout);
                        exerciseV2 = new RequestWorkoutExerciseV2();
                        actual = new ExerciseProprty();
                        prop = new ExerciseProprty();
                        actualList = new ArrayList<ExerciseProprty>();
                        oldExID = 0;
                        olddate = null;
                        exChanged = false;
                        requestWorkout = new RequestWorkoutV2();
                        workoutExcerices = new ArrayList<RequestWorkoutExerciseV2>();
                        requestWorkout.setRequestId(rs.getInt("idTrainingRequest"));
                        requestWorkout.setActivityName(rs.getString("prefered_activity_name"));
                        requestWorkout.setTraineeComment(rs.getString("playerMessage"));
                        requestWorkout.setTrainerComment(rs.getString("trainerMessage"));
                        requestWorkout.setWorkoutName(rs.getString("workoutName"));
                        requestWorkout.setWorkoutId(rs.getInt("idworkout"));
                        exerciseV2.setExericeId(rs.getInt("exercise_id"));
                exerciseV2.setExericeName(rs.getString("exercise_desc"));
                    }

                    if (olddate != null && !olddate.equals(rs.getDate("date"))) {

                        actual.setWorkoutdate(olddate);
                        actualList.add(actual);

                        actual = new ExerciseProprty();
                        dateCahnged = true;
                    }else {
                         dateCahnged = false;
                    }

                    if (exChanged && oldExID != rs.getInt("idWorkoutExcercise")) {
                        if (!dateCahnged) {
                            actual.setWorkoutdate(olddate);
                            actualList.add(actual);

                            actual = new ExerciseProprty();
                        }

                       
                        exerciseV2.setProposedEx(prop);
                        exerciseV2.setActuaExlist(actualList);
                        workoutExcerices.add(exerciseV2);

                        exerciseV2 = new RequestWorkoutExerciseV2();
                        exerciseV2.setExericeId(rs.getInt("exercise_id"));
                        exerciseV2.setExericeName(rs.getString("exercise_desc"));
                        actualList = new ArrayList<ExerciseProprty>();
                        //workoutExcerices = new  ArrayList<RequestWorkoutExerciseV2>();
                    }
                    exChanged = true;
                    if (rs.getString("keyname").equalsIgnoreCase("SET")) {
                        actual.setExcericeSets(rs.getString("actual"));
                        prop.setExcericeSets(rs.getString("proposed"));
                    }
                    if (rs.getString("keyname").equalsIgnoreCase("REPETITION")) {
                        actual.setExcericeReps(rs.getString("actual"));
                        prop.setExcericeReps(rs.getString("proposed"));
                    }

                    if (rs.getString("keyname").equalsIgnoreCase("WEIGHT")) {
                        actual.setExcericeweight(rs.getString("actual"));
                        prop.setExcericeweight(rs.getString("proposed"));
                    }

                    //exChanged = false;
                    oldExID = rs.getInt("idWorkoutExcercise");
                    olddate = rs.getDate("date");
                    oldworkoutId = rs.getInt("idworkout");

                }
                actualList.add(actual);
                if (actualList.size() > 0) {
                    exerciseV2.setActuaExlist(actualList);
                }
                exerciseV2.setProposedEx(prop);
                workoutExcerices.add(exerciseV2);
                requestWorkout.setWorkoutExcerices(workoutExcerices);
                requestWorkoutList.add(requestWorkout);
                return requestWorkoutList;
            }

        });
    }

}
