/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.appvision.gym.defines;

import java.util.HashMap;

/**
 *
 * @author ashraf.ibrahim
 */
public class Defines {
    public static int mailSearchingMode =1;
    public static int moblieSearchingMode =2;
    public static int twoStringSearchingMode =3;
    public static int oneStringSearchingMode =4;
    public static final  String mailPattern
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static  final String mobilePattern = "^((01(([0,1,2,5])))([0-9]{8}))$";
}
