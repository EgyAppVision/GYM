package com.appvision.gym.dao;

import com.appvision.gym.defines.Defines;
import com.appvision.gym.model.LoginModel;
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
import java.util.Set;
import java.util.TreeSet;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;



@Repository("sellerDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Logger debuglog = Logger.getLogger("debuglog");

    @Override
    public int userSignUp(final User user) {
        try {
            debuglog.debug("Preparing insert statment ");
            KeyHolder key = new GeneratedKeyHolder();
            PreparedStatementCreator creator = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    Date sDate = new Date(user.getBirthDate().getTime());
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO user(user_mobile,user_email,user_first_name,user_last_name,user_password,user_birth_date,user_gender,user_weight,user_tall,user_prefered_activity,user_prefered_place,user_type)VALUES (?,?,?,?,?,?,?,?,?,?,?,?);",
                            Statement.RETURN_GENERATED_KEYS);
                    // ps.setString(1, user.getUserName());
                    ps.setString(1, user.getMobile());
                    ps.setString(2, user.getEmail());
                    ps.setString(3, user.getFirstName());
                    ps.setString(4, user.getLastName());
                    ps.setString(5, user.getPassword());
                    ps.setDate(6, sDate);
                    ps.setInt(7, user.getGender());
                    ps.setInt(8, user.getWeight());
                    ps.setInt(9, user.getHeight());
                    ps.setInt(10, user.getPreferedActivity());
                    ps.setInt(11, user.getPreferedPlace());
                    ps.setInt(12, user.getType());
                    return ps;
                }
            };
            //INSERT INTO user_workout_exercies(user_workout_exercies_workout,user_workout_exercies_exercies,user_workout_exercies_trainer_comment,user_workout_exercies_trainee_comment)VALUES(?,?,?,?);
            debuglog.debug("Inserting Data to Database  ");
            jdbcTemplate.update(creator, key);
            debuglog.debug("Data inserted  to Database  ");
            int id = key.getKey().intValue();
            debuglog.debug("Inserted  Id  : " + id);
            return id;
        } catch (Exception ex) {
            debuglog.error("error while excutin sql >>  Error :" + ex.getMessage(), ex);
        }

        return 0;
    }

    @Override
    public boolean IsMailExist(String mail) {
        try {
            return jdbcTemplate.query("SELECT * FROM user where user_email='" + mail + "';", new ResultSetExtractor<Boolean>() {
                @Override
                public Boolean extractData(ResultSet rs) throws SQLException,
                        DataAccessException {

                    if (rs.next()) {
                        return true;
                    }
                    return false;
                }
            });
        } catch (Exception ex) {
            debuglog.error("error while excutin sql >>  Error :" + ex.getMessage(), ex);
        }
        //TODO 
        return false;
    }

    @Override
    public boolean IsUserNameExist(String userName) {
        try {
            return jdbcTemplate.query("SELECT * FROM user where user_name='" + userName + "';", new ResultSetExtractor<Boolean>() {
                @Override
                public Boolean extractData(ResultSet rs) throws SQLException,
                        DataAccessException {

                    if (rs.next()) {
                        return true;
                    }
                    return false;
                }
            });
        } catch (Exception ex) {
            debuglog.error("error while excutin sql >>  Error :" + ex.getMessage(), ex);
        }
        //TODO
        return false;
    }

    @Override
    public User GetUserByUserNameAndPassword(LoginModel loginModel) throws Exception {
        try {
            String sql = "SELECT * FROM user where user_email='" + loginModel.getUserEmail() + "' and user_password='" + loginModel.getPassword() + "'";
            debuglog.debug("Select user form data base ");
            debuglog.debug("SQL : " + sql);
            return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {
                @Override
                public User extractData(ResultSet rs) throws SQLException,
                        DataAccessException {
                    debuglog.debug("Extract data ");
                    if (rs.next()) {
                        User u = new User();
                        u.setUserId(rs.getInt("user_id"));
                        // u.setUserName(rs.getString("user_name"));
                        u.setMobile(rs.getString("user_mobile"));
                        u.setEmail(rs.getString("user_email"));
                        u.setFirstName(rs.getString("user_first_name"));
                        u.setLastName(rs.getString("user_last_name"));
                        u.setPassword(rs.getString("user_password"));
                        u.setBirthDate(rs.getDate("user_birth_date"));
                        u.setGender(rs.getInt("user_gender"));
                        u.setWeight(rs.getInt("user_weight"));
                        u.setHeight(rs.getInt("user_tall"));
                        u.setPreferedActivity(rs.getInt("user_prefered_activity"));
                        u.setPreferedPlace(rs.getInt("user_prefered_place"));
                        u.setType(rs.getInt("user_type"));
                        debuglog.debug("selected user " + u.toString());
                        return u;
                    }
                    debuglog.debug("No user found");
                    return new User();
                }
            });
        } catch (Exception ex) {
            debuglog.error("error while excutin sql >>  Error :" + ex.getMessage(), ex);
            throw ex;
        }
        // return new User();
    }

    @Override
    public List<User> GetAllUsersByName(String name, int userId, int mode, int place, int activity,int userType) throws Exception {
        try {
            String sql = "";
            debuglog.debug("Creating SQL Statement ");
            if (mode == Defines.mailSearchingMode) {
                debuglog.debug(" Select statment by E-mail ");
                sql = "select u.*,(select count(follower_id) from user_relation where follower_id = u.user_id and following_id =" + userId + " ) as isfollowing from user u where user_email ='" + name + "'";
            } else if (mode == Defines.moblieSearchingMode) {
                debuglog.debug(" Select statment by mobile ");
                sql = "select u.*,(select count(follower_id) from user_relation where follower_id = u.user_id and following_id =" + userId + " ) as isfollowing from user u where user_mobile ='" + name + "'";
            } else if (mode == Defines.oneStringSearchingMode) {
                String condtionTemplate = " user_first_name like '%$fristname$%' or user_last_name like '%$lastname$%' ";
                sql = "select u.*,(select count(follower_id) from user_relation where follower_id = u.user_id and following_id =" + userId + " ) as isfollowing from user u";
                String tempString = "";
                Set<String> NGramsToken = selectNGrams(name);
                int i = 0;
                for (String str : NGramsToken) {
                    if (i > 0) {
                        tempString += " OR ";
                    }
                    tempString += condtionTemplate;
                    tempString = tempString.replace("$fristname$", str);
                    tempString = tempString.replace("$lastname$", str);
                    i++;
                }
                debuglog.debug("contion part " + tempString);
                sql = sql + " where " + tempString;
            } else if (mode == Defines.twoStringSearchingMode) {
                String[] KeyworkArr = name.split(" ");
                Set<String> NGramsTokenFristName = selectNGrams(KeyworkArr[0]);
                Set<String> NGramsTokenLastName = selectNGrams(KeyworkArr[1]);
                String fristNamecondtionTemplate = " user_first_name like '%$fristname$%'  ";
                String LastnamecondtionTemplate = "  user_last_name like '%$lastname$%' ";
                sql = "select u.*,(select count(follower_id) from user_relation where follower_id = " + userId + " and following_id =u.user_id ) as isfollowing from user u";
                String tempString = "";
                int i = 0;
                for (String str : NGramsTokenFristName) {
                    if (i > 0) {
                        tempString += " OR ";
                    }
                    tempString += fristNamecondtionTemplate;
                    tempString = tempString.replace("$fristname$", str);

                    i++;
                }

                for (String str : NGramsTokenLastName) {

                    tempString += " OR ";
                    tempString += LastnamecondtionTemplate;
                    tempString = tempString.replace("$lastname$", str);

                }
                debuglog.debug("contion part " + tempString);
                sql = sql + " where " + tempString;
            }
            if (place > 0) {
                debuglog.debug("Adding Place condtion ");
                sql += " and  user_prefered_place = " + place;

            }
            if (activity > 0) {
                debuglog.debug("Adding activity condtion ");
                sql += " and  user_prefered_activity = " + activity;
            }
            
            if (userType ==Defines.userTypeTrainer) {
                debuglog.debug("Adding activity condtion ");
                sql += " and  user_type = " + userType;
            }

            debuglog.debug("SQL Statment :  " + sql);
            return jdbcTemplate.query(sql, new ResultSetExtractor<List<User>>() {
                @Override
                public List<User> extractData(ResultSet rs) throws SQLException,
                        DataAccessException {

                    List userList = new ArrayList<User>();
                    while (rs.next()) {
                        User u = new User();
                        u.setUserId(rs.getInt("user_id"));
                        // u.setUserName(rs.getString("user_name"));
                        u.setMobile(rs.getString("user_mobile"));
                        u.setEmail(rs.getString("user_email"));
                        u.setFirstName(rs.getString("user_first_name"));
                        u.setLastName(rs.getString("user_last_name"));
                        u.setPassword(rs.getString("user_password"));
                        u.setBirthDate(rs.getDate("user_birth_date"));
                        u.setGender(rs.getInt("user_gender"));
                        u.setWeight(rs.getInt("user_weight"));
                        u.setHeight(rs.getInt("user_tall"));
                        u.setPreferedActivity(rs.getInt("user_prefered_activity"));
                        u.setPreferedPlace(rs.getInt("user_prefered_place"));
                        u.setType(rs.getInt("user_type"));
                        u.setIsFollowing(rs.getInt("isfollowing"));
                        userList.add(u);
                        debuglog.debug("user :" + u.toString());
                    }

                    return userList;
                }
            });
        } catch (Exception ex) {
            debuglog.error("error while excutin sql >>  Error :" + ex.getMessage(), ex);
            throw ex;
        }

       
    }

    private Set<String> selectNGrams(String term) {
        int PARTIAL_LENGTH = 3;
        Double PARTIAL_COUNT_double = Math.ceil(term.length() / PARTIAL_LENGTH);
        int PARTIAL_COUNT = PARTIAL_COUNT_double.intValue();
        Set<String> partialSet = new TreeSet();
        int availDistance = Math.max(term.length() - PARTIAL_LENGTH, 0);
        //
        for (int i = 0; i < PARTIAL_COUNT; i++) {
            int pos0 = (PARTIAL_COUNT > 1)
                    ? availDistance * i / (PARTIAL_COUNT - 1)
                    : 0;
            int pos1 = Math.min(pos0 + PARTIAL_LENGTH, term.length());
            //
            String partial = term.substring(pos0, pos1);
            partial = partial.toLowerCase();
            //
            partialSet.add(partial);
        }
        return partialSet;
    }

    @Override
    public boolean Follow(final int follower, final int follwing) {

        try {
            PreparedStatementCreator creator = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                    PreparedStatement ps = connection.prepareStatement("INSERT INTO user_relation(follower_id,following_id)VALUES(?,?);",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, follower);
                    ps.setInt(2, follwing);
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
    public User GetUserProfile (int UserID ) throws Exception {
        try {
            String sql = "SELECT * FROM user inner join prefered_activity on user.user_prefered_activity= prefered_activity.prefered_activity_id  inner join  prefered_place on  prefered_place.idprefered_place_id = user.user_prefered_place where user_id ="+UserID;
            debuglog.debug("Select user form data base ");
            debuglog.debug("SQL : " + sql);
            return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {
                @Override
                public User extractData(ResultSet rs) throws SQLException,
                        DataAccessException {
                    debuglog.debug("Extract data ");
                    if (rs.next()) {
                        User u = new User();
                        u.setUserId(rs.getInt("user_id"));
                        // u.setUserName(rs.getString("user_name"));
                        u.setMobile(rs.getString("user_mobile"));
                        u.setEmail(rs.getString("user_email"));
                        u.setFirstName(rs.getString("user_first_name"));
                        u.setLastName(rs.getString("user_last_name"));
                        u.setBirthDate(rs.getDate("user_birth_date"));
                        u.setGender(rs.getInt("user_gender"));
                        u.setWeight(rs.getInt("user_weight"));
                        u.setHeight(rs.getInt("user_tall"));
                        u.setPreferedActivity(rs.getInt("user_prefered_activity"));
                        u.setPreferedPlace(rs.getInt("user_prefered_place"));
                        u.setType(rs.getInt("user_type"));
                        u.setPreferedActivityDesc(rs.getString("prefered_activity_name"));
                        u.setPreferedPlaceDesc(rs.getString("prefered_Place_name"));
                        debuglog.debug("selected user " + u.toString());
                        return u;
                    }
                    debuglog.debug("No user found");
                    return new User();
                }
            });
        } catch (Exception ex) {
            debuglog.error("error while excutin sql >>  Error :" + ex.getMessage(), ex);
            throw ex;
        }
        // return new User();
    }
}
