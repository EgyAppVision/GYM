package com.appvision.gym.dao;

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
try{
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
}catch(Exception ex){
    int x = 0 ;
}

return 0;
    }

    @Override
    public boolean IsMailExist(String mail) {
        return jdbcTemplate.query("SELECT * FROM user where user_email='"+mail+"';", new ResultSetExtractor<Boolean>() {
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
        return jdbcTemplate.query("SELECT * FROM user where user_name='"+userName+"';", new ResultSetExtractor<Boolean>() {
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
      return jdbcTemplate.query("SELECT * FROM user where user_name='"+loginModel.getUserName()+"' and user_password='"+loginModel.getPassword()+"'", new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet rs) throws SQLException,
                    DataAccessException {

                if (rs.next()) {
                 User u =new User();
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

    
    
}
