/* THIS CLASS IS USED TO GIVE GAME OBJECTS ACCESS TO P.C.PAPPLET */

package OBJECTS;

import java.util.ArrayList;

import processing.core.PApplet;

public class VisualSetup extends PApplet
{

    //declare variables
    private Player player;
    public boolean roundWin;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private int round, numEnemies;

    //settings
    public void settings()
    {
        size(1920,1080);//min size for tv graphic is 500 x 500
        println("CWD: " + System.getProperty("user.dir"));//current working directory
        fullScreen();// full screen
    }//end settings


    //setup
    public void setup()
    {
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
        background(0);
        //is round over? if so start new round
        if(roundWin == true){
            nextRound();
            roundWin = false;
        }

        updateEnemies();
        player.updatePlayer();

    }//end draw

    private void nextRound(){
        round++;
        numEnemies = 10 + round;
        generateEnemies();
    }

    // Enemy-Player Collision detection
    boolean circleRect(Player player, Enemy enemy){

        // temporary variables to set edges for testing
        float testX = player.position.x;
        float testY = player.position.y;
    
        // which edge is closest?
        // left edge
        if (player.position.x < enemy.position.x){
            testX = enemy.position.x;
        }else
        // right edge
        if (player.position.x > enemy.position.x +  enemy.size){
            testX = enemy.position.x +  enemy.size;
        }

        // which edge is closest?
        // top edge
        if (player.position.y < enemy.position.y){
            testY = enemy.position.y;
        }   
        else
        // bottom edge
        if (player.position.y > enemy.position.y +  enemy.size){
            testY = enemy.position.y +  enemy.size;
        } 
    
        // get distance from closest edges
        float distX = player.position.x-testX;
        float distY = player.position.y-testY;
        float distance = sqrt( (distX*distX) + (distY*distY) );
    
        // if the distance is less than the radius, collision!
        if (distance <= player.diameter){
        return true;
        }
        return false;
    }

    public void updateEnemies(){
        //LOOP THROUGH ARRAYLIST OF ALL ENEMIES
        for(int i = 0; i < enemies.size(); i++){
            //update all enemies
            enemies.get(i).updateEnemy();
        }//end loop
    }//end method

    //POPULATES ARRAYLIST OF ENEMIES
    public void generateEnemies(){
        for(int i = 0; i < 50; i++){
            enemies.add(new Enemy(width, height, 100, round * 5, this));
        }//end loop
    }//end method

}//end