///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.appvision.gym.initialization;
//
//import com.appvision.gym.defines.Defines;
//import javax.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//import com.appvision.gym.util.Utilty;
//import java.util.logging.Level;
//import java.util.logging.Logger;
///**
// *
// * @author ashraf.ibrahim
// */
//@Component
//public class Initialization {
//    
//    @PostConstruct
//    void init (){
//        try {
//            Defines.propertiesModel =   Utilty.loadPropertiesFile("Appconfig");
//        } catch (Exception ex) {
//            Logger.getLogger(Initialization.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//}
