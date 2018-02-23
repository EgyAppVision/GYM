package com.appvision.gym.dao;

import com.appvision.gym.model.User;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

;

@Repository("sellerDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//	@Override
//	public boolean userSignUp(User user,Connection connection){
//		          PreparedStatement stmt =null;
//            try {
//                String sqlstsm = "insert into test.user_data (first_name,last_name,email,activity,place,age,weight,Height,fatpercentage) "
//                        + "values (?,?,?,?,?,?,?,?,?)";
//               stmt =connection.prepareStatement(sqlstsm);
//                stmt.setString(1, user.getFirstName());
//                stmt.setString(2, user.getLastName());
//                stmt.setString(3, user.getEmail());
//                stmt.setInt(4, user.getActivity());
//                stmt.setString(5, user.getPlace());
//                stmt.setInt(6, user.getAge());
//                stmt.setInt(7, user.getWeight());
//                stmt.setInt(8, user.getHeight());
//                stmt.setInt(9, user.getFatPercentage());
//               
//                stmt.executeUpdate();
//                
//                return true;
//            } catch (SQLException ex) {
//               Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }finally{
//                if (stmt !=null)
//                    try {
//                        stmt.close();
//                } catch (SQLException ex) {
////                    Logger.getLogger(SecurityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            
//            return  false;
//	}
    @Override
    public boolean userSignUp(User user) {
try{
    
   Date sDate = new Date(user.getBirthDate().getTime());

        String Sql = "INSERT INTO `gym`.`user`"
                + "(`user_name`,"
                + "`user_mobile`,"
                + "`user_email`,"
                + "`user_first_name`,"
                + "`user_last_name`,"
                + "`user_password`,"
                + "`user_birth_date`,"
                + "`user_gender`,"
                + "`user_weight`,"
                + "`user_tall`,"
                + "`user_prefered_activity`,"
                + "`user_prefered_place`,"
                + "`user_type`)"
                + "VALUES"
                + "('" + user.getUserName() + "','"
                + user.getMobile() + "','"
                + user.getEmail() + "','"
                + user.getFirstName() + "','"
                + user.getLastName() + "','"
                + user.getPassword() + "','"
                + sDate + "','"
                + user.getGender() + "','"
                + user.getWeight() + "','"
                + user.getHeight() + "','"
                + user.getPreferedActivity() + "','"
                + user.getPreferedPlace() + "','"
                + user.getType() + "');";
        jdbcTemplate.update(Sql);
        return true;
}catch(Exception ex )
{
    return false;
}
    }

}
