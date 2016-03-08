/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennisgame;


/**
 *
 * @author Max-AcerGrande
 */
public class Player implements Runnable {
 private Monitor monitor=null;
 private String name=null;
 private int numberPlayer;
 private int lastPoint;
 
 
   
     public Player(String name,int numberPlayer,Monitor monitor) {
     this.name=name;
     this.numberPlayer=numberPlayer;
     this.lastPoint=0;
     this.monitor=monitor;
     
     }
        
    @Override
    public void run() {
     
        try {
           while(!monitor.isGameover())
           {
            Thread.sleep(2000);
            monitor.startShoot(this);
            Thread.sleep(2000);
            monitor.endShoot(this);
           }
           
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
    }
    
    public void printDetailsLastPoint()
    {
        System.out.println("Player named "+name+" has generated the last following point:"+lastPoint+"->"+monitor.getPoints().get(lastPoint));
    }
    
    public void printDetailsPlayer()
    {System.out.println("Player "+this.getNumberPlayer()+" named "+name);
    
    }
   
    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  
    public int getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(int lastPoint) {
        this.lastPoint = lastPoint;
    }

    public int getNumberPlayer() {
        return numberPlayer;
    }

    public void setNumberPlayer(int numberPlayer) {
        this.numberPlayer = numberPlayer;
    }

    
    
}
