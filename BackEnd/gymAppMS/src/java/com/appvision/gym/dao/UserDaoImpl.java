package com.appvision.gym.dao;

import com.appvision.gym.defines.Defines;
import com.appvision.gym.model.LoginModel;
import com.appvision.gym.model.LookupModel;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

;

@Repository("sellerDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Override
//    public boolean userSignUp(User user) {
//try{
//    
//   Date sDate = new Date(user.getBirthDate().getTime());
//
//        String Sql = "INSERT INTO `gym`.`user`"
//                + "(`user_name`,"
//                + "`user_mobile`,"
//                + "`user_email`,";2
//                + "`user_first_name`,"
//                + "`user_last_name`,"
//                + "`user_password`,"
//                + "`user_birth_date`,"
//                + "`user_gender`,"
//                + "`user_weight`,"
//                + "`user_tall`,"
//                + "`user_prefered_activity`,"
//                + "`user_prefered_place`,"
//                + "`user_type`)"
//                + "VALUES"
//                + "('" + user.getUserName() + "','"
//                + user.getMobile() + "','"
//                + user.getEmail() + "','"
//                + user.getFirstName() + "','"
//                + user.getLastName() + "','"
//                + user.getPassword() + "','"
//                + sDate + "','"
//                + user.getGender() + "','"
//                + user.getWeight() + "','"
//                + user.getHeight() + "','"
//                + user.getPreferedActivity() + "','"
//                + user.getPreferedPlace() + "','"
//                + user.getType() + "');";
//        jdbcTemplate.update(Sql);
//        return true;
//}catch(Exception ex )
//{
//    return false;
//}
//    }
    @Override
    public int userSignUp(final User user) {
        try {
            KeyHolder key = new GeneratedKeyHolder();
            PreparedStatementCreator creator = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    Date sDate = new Date(user.getBirthDate().getTime());
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO user(user_name,user_mobile,user_email,user_first_name,user_last_name,user_password,user_birth_date,user_gender,user_weight,user_tall,user_prefered_activity,user_prefered_place,user_type)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, user.getUserName());
                    ps.setString(2, user.getMobile());
                    ps.setString(3, user.getEmail());
                    ps.setString(4, user.getFirstName());
                    ps.setString(5, user.getLastName());
                    ps.setString(6, user.getPassword());
                    ps.setDate(7, sDate);
                    ps.setInt(8, user.getGender());
                    ps.setInt(9, user.getWeight());
                    ps.setInt(10, user.getHeight());
                    ps.setInt(11, user.getPreferedActivity());
                    ps.setInt(12, user.getPreferedPlace());
                    ps.setInt(13, user.getType());
                    return ps;
                }
            };
            //INSERT INTO user_workout_exercies(user_workout_exercies_workout,user_workout_exercies_exercies,user_workout_exercies_trainer_comment,user_workout_exercies_trainee_comment)VALUES(?,?,?,?);

            jdbcTemplate.update(creator, key);

            int id = key.getKey().intValue();

            return id;
        } catch (Exception ex) {
            int x = 0;
        }

        return 0;
    }

    @Override
    public boolean IsMailExist(String mail) {
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
    }

    @Override
    public boolean IsUserNameExist(String userName) {
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
    }

    @Override
    public User GetUserByUserNameAndPassword(LoginModel loginModel) {
        return jdbcTemplate.query("SELECT * FROM user where user_name='" + loginModel.getUserName() + "' and user_password='" + loginModel.getPassword() + "'", new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet rs) throws SQLException,
                    DataAccessException {

                if (rs.next()) {
                    User u = new User();
                    u.setUserId(rs.getInt("user_id"));
                    u.setUserName(rs.getString("user_name"));
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

                    return u;
                }
                return new User();
            }
        });
    }

    @Override
    public List<User> GetAllUsersByName(String name, int userId, int mode, int place, int activity) {
        String sql = "";
        if (mode == Defines.mailSearchingMode) {
            sql = "select u.*,(select count(follower_id) from user_relation where follower_id = u.user_id and following_id =" + userId + " ) as isfollowing from user u where user_email ='" + name + "'";
        } else if (mode == Defines.moblieSearchingMode) {
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

            sql = sql + " where " + tempString;
        } else if (mode == Defines.twoStringSearchingMode) {
            String[] KeyworkArr = name.split(" ");
            Set<String> NGramsTokenFristName = selectNGrams(KeyworkArr[0]);
            Set<String> NGramsTokenLastName = selectNGrams(KeyworkArr[1]);
            String fristNamecondtionTemplate = " user_first_name like '%$fristname$%'  ";
            String LastnamecondtionTemplate = "  user_last_name like '%$lastname$%' ";
            sql = "select u.*,(select count(follower_id) from user_relation where follower_id = u.user_id and following_id =" + userId + " ) as isfollowing from user u";
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

            sql = sql + " where " + tempString;
        }
        if (place > 0) {
            sql += " and  user_prefered_place = " + place;
        }
        if (activity > 0) {
            sql += " and  user_prefered_activity = " + activity;
        }
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<User>>() {
            @Override
            public List<User> extractData(ResultSet rs) throws SQLException,
                    DataAccessException {

                List userList = new ArrayList<User>();
                while (rs.next()) {
                    User u = new User();
                    u.setUserId(rs.getInt("user_id"));
                    u.setUserName(rs.getString("user_name"));
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
                }

                return userList;
            }
        });
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
    public boolean Follow(final int  follower, final int follwing) {
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
    }

}
