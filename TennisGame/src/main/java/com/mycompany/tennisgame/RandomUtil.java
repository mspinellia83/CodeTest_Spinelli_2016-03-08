/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennisgame;

import java.util.Random;

/**
 *
 * @author Max-AcerGrande
 */
public class RandomUtil {
 
    
    public static int generateIntRandom(int min,int max) {
        
          return new Random().nextInt(max - min + 1) + min;
          
    }
}
