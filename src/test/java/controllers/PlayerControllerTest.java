package controllers;

import junit.framework.TestCase;
import models.Player;
import org.junit.jupiter.api.DisplayName;
@DisplayName("JUnit test for playerMove controller.")
public class PlayerControllerTest extends TestCase {

    public void testPlayerMove() {
                try {
                    //preamble. Makes necessery methods available to this test, even though they are considered private.
                    PlayerController pc = new PlayerController();
                    Player player1 = new Player("Player 1");
                    Player player2 = new Player("Player 2");


                    //move 1
                    assertEquals(0,player1.getLocation());
                    pc.playerMove(player1,2);
                    assertEquals(2,player1.getLocation());
                    if(player1.getLocation() == 2){
                        System.out.println("Player moved 2 spaces");
                    }
                    pc.playerMove(player1,1);
                    assertEquals(3,player1.getLocation());
                    if(player1.getLocation() == 3){
                        System.out.println("Player moved 1 space");
                    }
                    pc.playerMove(player1,22);
                    assertEquals(1,player1.getLocation());
                    if(player1.getLocation() == 1){
                        System.out.println("Player passed start, position equals P-24.");
                    }
                    pc.playerMove(player2,2);
                    assertEquals(2,player2.getLocation());
                    if(player2.getLocation() == 2){
                        System.out.println("player 2 moved 2 spaces.");
                    }


                } catch (Exception e) {
                    System.out.println("oops:" + e);
                }
            }
        }