/* THIS CLASS IS USED TO GIVE GAME OBJECTS ACCESS TO P.C.PAPPLET */

package OBJECTS;

import java.util.ArrayList;
import java.util.Iterator;

import processing.core.PApplet;

public class VisualSetup extends PApplet
{

    //declare variables
    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public boolean roundWin;
    private int round;



    //settings
    public void settings(){

        size(1920,1080);//min size for tv graphic is 500 x 500
        println("CWD: " + System.getProperty("user.dir"));//current working directory
        fullScreen();// full screen

    }//end settings



    //setup
    public void setup(){

        colorMode(HSB, 360, 100, 100);
        strokeWeight(5);
        background(0);
        //declare visualizations
        player = new Player(width, height, this);
        round = 0;
        roundWin = true;

    }//end setup



    //draw
    public void draw(){

        if(player.health > 0){

            background(0);

            //is round over? if so start new round
            if(enemies.size() == 0){
                nextRound();
                roundWin = false;
            }//end if

            player.updatePlayer(enemies);
            updateEnemies();

        }//end if
        else{

            System.out.println("Player has died");

        }//end else
    }//end draw



    private void nextRound(){

        round++;
        generateEnemies();

    }//end method



    //UPDATES ALL ENEMIES IN ARRAYLIST
    public void updateEnemies(){

        //USE AN ITERATOR TO REMOVE DEAD ENEMIES FROM LIST AND UPDATE OTHERS

        Enemy e;
        Iterator<Enemy> itr = enemies.iterator();

        while(itr.hasNext()){

            e = itr.next();

            //update all enemies
            if(e.health > 0){
                e.updateEnemy();
            }//end if
            else{
                itr.remove();
            }//end else
        }//end while

    }//end method



    //POPULATES ARRAYLIST OF ENEMIES
    public void generateEnemies(){

        if(round % 5 == 0){

            for(int j = 0; j < 1 * round / 5; j++){

                enemies.add(new Enemy(width, height, random(30, 50) + (round * 2.25f), random(15, 30) + (round * 2.25f), 700, player, this));

            }//end loop
        }//end if

        for(int i = 0; i < 10 + round / 2; i++){

            enemies.add(new Enemy(width, height, random(10, 15) + (round * 2.25f), random(10, 15) + (round * 2.25f), 700, player, this));

        }//end loop
    }//end method
}//end
