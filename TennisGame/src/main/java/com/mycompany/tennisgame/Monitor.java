/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tennisgame;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.*;

/**
 *
 * @author Max-AcerGrande
 */
public class Monitor{
    
    private Lock lock= new ReentrantLock();
    private Condition codaPlayer= lock.newCondition(); 
    private Map<Integer,String> points=null;
    private boolean ballIsBusy;
    private int numberPlayerSosp;
    private boolean gameover;
    private Map<Integer,Integer> scores=null;
    private int playerWon;
    private String lastcomputedScore=null;
  
    public Monitor(){
    points=new HashMap<>();
    points.put(0,"love");
    points.put(1,"fifteen");
    points.put(2,"thirty");
    points.put(3,"forty");
    
    scores=new HashMap<>();
    scores.put(1,0);
    scores.put(2,0);
    
    numberPlayerSosp=0;
    ballIsBusy=false;
    gameover=false;
    playerWon=-1;
    }
    
    public int startShoot(Player player)  throws InterruptedException {
    lock.lock();
    int numberRandomGenerated=0;
		
    try
    { 	while (ballIsBusy)
                  {       numberPlayerSosp++;
                          codaPlayer.await();
                          numberPlayerSosp--;
                  }
        if(!gameover)
        {ballIsBusy=true;
         numberRandomGenerated=RandomUtil.generateIntRandom(0,points.size()-1);
         player.setLastPoint(numberRandomGenerated);
         int numGiocatore=player.getNumberPlayer();
         scores.put(numGiocatore, scores.get(numGiocatore)+numberRandomGenerated);
         player.printDetailsLastPoint();
        } 

      }finally{lock.unlock();}
        
    return numberRandomGenerated;
    }
 
    public void endShoot(Player player){
    lock.lock();
    ballIsBusy=false;
    if(!gameover)
        computeScore();

    if (numberPlayerSosp>0)
            codaPlayer.signalAll();

    lock.unlock();
		
    }
    
    //si potrebbero togliere un po di if :) usando magari abs, ma per ora lascio cosi..
    public void computeScore(){
    int scoreOnePlayer=scores.get(1);
    int scoreSecondPlayer=scores.get(2);
    if(scoreOnePlayer==scoreSecondPlayer && scoreOnePlayer>=3)
       lastcomputedScore="Score: deuce";
    else
        if(scoreOnePlayer>=4 && (scoreOnePlayer-scoreSecondPlayer >=2))
        {lastcomputedScore="Score: Player 1 won";
         gameover=true;
         playerWon=1;
        }
    else if(scoreOnePlayer>=3 && (scoreOnePlayer-scoreSecondPlayer >=1))
        lastcomputedScore="Score: Player 1 advantage";
    else if (scoreSecondPlayer>=4 && (scoreSecondPlayer-scoreOnePlayer) >=2)
        { 
          lastcomputedScore="Score: Player 2 won";
          gameover=true;
          playerWon=2;
        }       
    else if (scoreSecondPlayer>=3 && (scoreSecondPlayer-scoreOnePlayer) >=1)
            lastcomputedScore="Score: Player 2 advantage";

    if(lastcomputedScore!=null)
        System.out.println(lastcomputedScore);
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public Condition getCodaPlayer() {
        return codaPlayer;
    }

    public void setCodaPlayer(Condition codaPlayer) {
        this.codaPlayer = codaPlayer;
    }

    public Map<Integer, String> getPoints() {
        return points;
    }

    public void setPoints(Map<Integer, String> points) {
        this.points = points;
    }

    public boolean isBallIsBusy() {
        return ballIsBusy;
    }

    public void setBallIsBusy(boolean ballIsBusy) {
        this.ballIsBusy = ballIsBusy;
    }

    public int getNumberPlayerSosp() {
        return numberPlayerSosp;
    }

    public void setNumberPlayerSosp(int numberPlayerSosp) {
        this.numberPlayerSosp = numberPlayerSosp;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    public Map<Integer, Integer> getScores() {
        return scores;
    }

    public void setScores(Map<Integer, Integer> scores) {
        this.scores = scores;
    }
    

    public int getPlayerWon() {
        return playerWon;
    }

    public void setPlayerWon(int playerWon) {
        this.playerWon = playerWon;
    }

    public String getLastcomputedScore() {
        return lastcomputedScore;
    }

    public void setLastcomputedScore(String lastcomputedScore) {
        this.lastcomputedScore = lastcomputedScore;
    }
    
}
