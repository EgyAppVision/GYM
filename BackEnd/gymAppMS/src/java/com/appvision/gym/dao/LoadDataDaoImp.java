/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appvision.gym.dao;

import com.appvision.gym.model.Exercise;
import com.appvision.gym.model.LookupModel;
import com.appvision.gym.model.Muscle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ashraf.ibrahim
 */
@Repository("sellerDao1")
public class LoadDataDaoImp implements LoadDataDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<LookupModel> loadUserGender() {
        return jdbcTemplate.query("select * from user_gender", new ResultSetExtractor<List<LookupModel>>() {
            @Override
            public List<LookupModel> extractData(ResultSet rs) throws SQLException,
                    DataAccessException {

                List<LookupModel> list = new ArrayList<LookupModel>();
                while (rs.next()) {
                    LookupModel e = new LookupModel();
                    e.setId(rs.getInt(1));
                    e.setValue(rs.getString(2));

                    list.add(e);
                }
                return list;
            }
        });
    }

    @Override
    public List<LookupModel> loadUserPrefaredPlace() {
        return jdbcTemplate.query("select * from prefered_place", new ResultSetExtractor<List<LookupModel>>() {
            @Override
            public List<LookupModel> extractData(ResultSet rs) throws SQLException,
                    DataAccessException {

                List<LookupModel> list = new ArrayList<LookupModel>();
                while (rs.next()) {
                    LookupModel e = new LookupModel();
                    e.setId(rs.getInt(1));
                    e.setValue(rs.getString(2));

                    list.add(e);
                }
                return list;
            }
        });
    }

    @Override
    public List<LookupModel> loadUserPrefaredActivity() {
        return jdbcTemplate.query("select * from prefered_activity", new ResultSetExtractor<List<LookupModel>>() {
            @Override
            public List<LookupModel> extractData(ResultSet rs) throws SQLException,
                    DataAccessException {

                List<LookupModel> list = new ArrayList<LookupModel>();
                while (rs.next()) {
                    LookupModel e = new LookupModel();
                    e.setId(rs.getInt(1));
                    e.setValue(rs.getString(2));

                    list.add(e);
                }
                return list;
            }
        });
    }

    @Override
    public List<LookupModel> loadUserType() {
        return jdbcTemplate.query("select * from user_type", new ResultSetExtractor<List<LookupModel>>() {
            @Override
            public List<LookupModel> extractData(ResultSet rs) throws SQLException,
                    DataAccessException {

                List<LookupModel> list = new ArrayList<LookupModel>();
                while (rs.next()) {
                    LookupModel e = new LookupModel();
                    e.setId(rs.getInt(1));
                    e.setValue(rs.getString(2));

                    list.add(e);
                }
                return list;
            }
        });
    }

    @Override
    public List<Muscle> loadMainMuscles() {
        return jdbcTemplate.query("select * from muscle inner join muscle_type on muscle.muscle_type_id =muscle_type.muscle_type_id where  muscle_id not in (select sub_muscle_id from muscle_relation)", new ResultSetExtractor<List<Muscle>>() {
            @Override
            public List<Muscle> extractData(ResultSet rs) throws SQLException,
                    DataAccessException {

                List<Muscle> list = new ArrayList<Muscle>();
                while (rs.next()) {
                    Muscle e = new Muscle();
                    e.setMainMuscleId(rs.getInt("muscle_id"));
                    e.setMuscleName(rs.getString("muscle_name"));
                    e.setMuscleTypeDesc(rs.getString("muscle_type_desc"));
                    e.setMuscleTypeId(rs.getInt("muscle_type_id"));

                    list.add(e);
                }
                return list;
            }
        });
    }

    public List<Muscle> LoadSubMuscles(int mainMuscle) {
        String sql = "select * from muscle inner join muscle_relation on sub_muscle_id = muscle_id inner join muscle_type on muscle.muscle_type_id =muscle_type.muscle_type_id where main_muscle_id = " + mainMuscle;
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<Muscle>>() {
            @Override
            public List<Muscle> extractData(ResultSet rs) throws SQLException,
                    DataAccessException {

                List<Muscle> list = new ArrayList<Muscle>();
                while (rs.next()) {
                    Muscle e = new Muscle();
                    e.setMainMuscleId(rs.getInt("muscle_id"));
                    e.setMuscleName(rs.getString("muscle_name"));
                    e.setMuscleTypeDesc(rs.getString("muscle_type_desc"));
                    e.setMuscleTypeId(rs.getInt("muscle_type_id"));
                    e.setSubMuscleId(rs.getInt("sub_muscle_id"));
                    list.add(e);
                }
                return list;
            }
        });
    }

    public List<Exercise> LoadMuscleExceriseByMainMuscleId(int mainMuscleId) {
        String sql = "select * from exercise inner join \n"
                + "exercise_type on exercise.exercise_type_id = exercise_type.exercise_type_id\n"
                + "inner join muscle on muscle.muscle_id = exercise.exercise_sub_muscle\n"
                + "inner join muscle_type on muscle.muscle_type_id = muscle_type.muscle_type_id\n"
                + "inner join muscle_relation on muscle_relation.sub_muscle_id = muscle.muscle_id\n"
                + "where muscle_relation.main_muscle_id = 8";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<Exercise>>() {
            @Override
            public List<Exercise> extractData(ResultSet rs) throws SQLException,
                    DataAccessException {

                List<Exercise> list = new ArrayList<Exercise>();
                while (rs.next()) {
                    Exercise e = new Exercise();
                    e.setExerciseDesc(rs.getString("exercise_desc"));
                    e.setExerciseId(rs.getInt("exercise_id"));
                    e.setExerciseSubMuscle(rs.getInt("exercise_sub_muscle"));
                    e.setExerciseTypeDesc(rs.getString("exercise_type_desc"));
                    e.setExerciseTypeId(rs.getInt("exercise_type_id"));
                    e.setMuscleName(rs.getString("muscle_name"));
                    e.setMuscleTypeDesc(rs.getString("muscle_type_desc"));
                    list.add(e);

                    
                }
                return list;
            }
        });
    }
}
