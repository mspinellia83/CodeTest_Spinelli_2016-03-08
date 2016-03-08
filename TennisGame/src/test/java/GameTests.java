

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mycompany.tennisgame.Monitor;
import com.mycompany.tennisgame.Player;
import com.mycompany.tennisgame.RandomUtil;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;



/**
 *
 * @author Max-AcerGrande
 */
public class GameTests {
      Monitor monitor=new Monitor();
      //Player one=new Player("Massimo",1, monitor);
      //Player two=new Player("Angelo",2, monitor);
 
    @Test
    public void computedScoreTest()
    {
        Map<Integer,Integer> scores=new HashMap<>();
        scores.put(1,4);
        scores.put(2,0);
        monitor.setScores(scores);
        monitor.computeScore();
        assertTrue(monitor.isGameover());
        assertEquals(monitor.getPlayerWon(),1);
        
        monitor.setGameover(false);
        scores.put(2,4);
        monitor.computeScore();
        assertEquals(monitor.getLastcomputedScore(), "Score: deuce");
   
        scores.put(2,7);
        monitor.computeScore();
        assertTrue(monitor.isGameover());
        assertEquals(monitor.getPlayerWon(),2);
        
        
        monitor.setGameover(false);
        scores.put(1,6);
        monitor.computeScore();
        assertTrue(!monitor.isGameover());
        assertEquals(monitor.getLastcomputedScore(), "Score: Player 2 advantage");
        
        monitor.setGameover(false);
        scores.put(1,8);
        monitor.computeScore();
        assertTrue(!monitor.isGameover());
        assertEquals(monitor.getLastcomputedScore(), "Score: Player 1 advantage");
        
    }
    @Test
    public void RandomGeneratorTest()
    {   int random=RandomUtil.generateIntRandom(0, 3);
        assertTrue(random>=0 && random<=3);
        
        random=RandomUtil.generateIntRandom(1, 2);
        assertTrue(random>=1 && random<=2);
        
        random=RandomUtil.generateIntRandom(101, 107);
        assertTrue(random>=101 && random<=107);
        
    }
}
