/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appvision.gym.dao;

import com.appvision.gym.model.LookupModel;
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
public class LoadDataDaoImp implements LoadDataDao{
        @Autowired
	private JdbcTemplate jdbcTemplate;

    @Override
    public List<LookupModel> loadUserGender() {
         return jdbcTemplate.query("select * from user_gender",new ResultSetExtractor<List<LookupModel>>(){  
    @Override  
     public List<LookupModel> extractData(ResultSet rs) throws SQLException,  
            DataAccessException {  
      
        List<LookupModel> list=new ArrayList<LookupModel>();  
        while(rs.next()){  
        LookupModel e=new LookupModel();  
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
        return jdbcTemplate.query("select * from prefered_place",new ResultSetExtractor<List<LookupModel>>(){  
    @Override  
     public List<LookupModel> extractData(ResultSet rs) throws SQLException,  
            DataAccessException {  
      
        List<LookupModel> list=new ArrayList<LookupModel>();  
        while(rs.next()){  
        LookupModel e=new LookupModel();  
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
       return jdbcTemplate.query("select * from prefered_activity",new ResultSetExtractor<List<LookupModel>>(){  
    @Override  
     public List<LookupModel> extractData(ResultSet rs) throws SQLException,  
            DataAccessException {  
      
        List<LookupModel> list=new ArrayList<LookupModel>();  
        while(rs.next()){  
        LookupModel e=new LookupModel();  
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
        return jdbcTemplate.query("select * from user_type",new ResultSetExtractor<List<LookupModel>>(){  
    @Override  
     public List<LookupModel> extractData(ResultSet rs) throws SQLException,  
            DataAccessException {  
      
        List<LookupModel> list=new ArrayList<LookupModel>();  
        while(rs.next()){  
        LookupModel e=new LookupModel();  
        e.setId(rs.getInt(1));  
        e.setValue(rs.getString(2));  
        
        list.add(e);  
        }  
        return list;  
        }  
    });
    }

    
}
