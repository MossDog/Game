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
        player = new Player(width, height, 10, this);
        round = 0;
        roundWin = true;

    }//end setup



    //draw
    public void draw(){

        System.out.println();
        background(0);

        //is round over? if so start new round
        if(enemies.size() == 0){
            nextRound();
            roundWin = false;
        }//end if

        updateEnemies();
        player.updatePlayer(enemies);
        player.fire(enemies);

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
                e.updateEnemy(height);
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

                enemies.add(new Enemy(width, height, 100, round * 5, 5 + (round * 0.1f), this));

            }//end loop
        }//end if

        for(int i = 0; i < 10 + round / 2; i++){

            enemies.add(new Enemy(width, height, 100, round * 5, random(30, 50) / (float)(round * 0.05), this));

        }//end loop
    }//end method
}//end
