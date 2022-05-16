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


    public void updateEnemies(){
        //LOOP THROUGH ARRAYLIST OF ALL ENEMIES
        for(int i = 0; i < enemies.size(); i++){
            //update all enemies
            enemies.get(i).updateEnemy(height);
        }//end loop
    }//end method

    //POPULATES ARRAYLIST OF ENEMIES
    public void generateEnemies(){

        if(round % 5 == 0){
            for(int j = 0; j < 1 * round / 5; j++){
                enemies.add(new Enemy(width, height, 100, round * 5, random(100, 150) / (float)(round * 0.05), this));
            }
        }

        for(int i = 0; i < 50; i++){
            enemies.add(new Enemy(width, height, 100, round * 5, random(30, 50) / (float)(round * 0.05), this));
        }//end loop
    }//end method

}//end