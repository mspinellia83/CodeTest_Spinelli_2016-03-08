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
public class TennisGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Monitor monitor=new Monitor();
      Player one=new Player("Massimo",1, monitor);
      Player two=new Player("Angelo",2, monitor);
      //Player three.....
      
      Random random = new Random();
      int min=1;
      int max=2;
      int playerStarting=RandomUtil.generateIntRandom(min, max);
    
      System.out.println("Starting Tennis Game");
      one.printDetailsPlayer();
      two.printDetailsPlayer();
      System.out.println("Starting Player "+playerStarting);
      if(playerStarting==1)
      {  
          new Thread(one).start();
          new Thread(two).start();
      }
     else
      {  new Thread(two).start();
         new Thread(one).start();
      }
    
    }
    
}
