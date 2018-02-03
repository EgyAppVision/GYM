/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appvision.gym.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 *
 * @author ashraf.ibrahim
 */
public class Utilty {
    
    

    public static HashMap loadPropertiesFile(String fileName)
            throws Exception {

        HashMap propertiesModel = null;

        try {

            ResourceBundle resourceBundle = ResourceBundle.getBundle(fileName);
            propertiesModel = new HashMap();

            Enumeration keys = resourceBundle.getKeys();

            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                String value = "";
                
                    value = resourceBundle.getString(key).trim();
                                propertiesModel.put(key, value);
            }

        } catch (Exception e) {

            System.out.println("Exception in Utility.loadProperties(). e= "
                    + e.getMessage());
            throw e;

        }

        return propertiesModel;

    }

}
