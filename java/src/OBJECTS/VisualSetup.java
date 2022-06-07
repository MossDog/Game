/* THIS CLASS IS USED TO GIVE GAME OBJECTS ACCESS TO P.C.PAPPLET */

package OBJECTS;

import java.util.ArrayList;
import java.util.Iterator;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class VisualSetup extends PApplet
{

    //declare variables
    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public int round, money, state;
    public Table stats;



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
        loadStats();
        state = 0;

    }//end setup



    //draw
    public void draw(){

            switch(state){

                case 0:
                    startGame();
                break;

                case 1:
                    background(0);
                    noFill();
                    stroke(0, 100, 100, 70);
                    circle(width/2, height/2, player.range * 2);

                    //is round over? if so start new round
                    if(enemies.size() == 0){
                        nextRound();
                    }//end if
                    
                    updateEnemies();
                    player.updatePlayer(enemies);
                    
                break;

                case 2:
                    playerDeath();
                break;

                case 3:

                case 4:
                case 5:

                default:
                    System.out.println("This should not occur, switch error");
                break;

            }//end switch
    }//end draw



    public void playAgain() {

        enemies.clear();
        player.health = player.maxHealth;
        round = 0;
        state = 1;

    }//end method



    private void loadStats() {

        stats = loadTable("playerstats.csv", "header");

        //gets row of stats in stats.csv, row 1 holds titles.
        TableRow row = stats.getRow(0);
        float maxHealth = row.getInt("health");
        float damage = row.getInt("damage");
        float dmgCooldown = row.getInt("dmgcooldown");
        float range = row.getInt("range");
        player = new Player(width, height, maxHealth, damage, dmgCooldown, range, this);

    }//end method

    

    //SAVE ANY STAT CHANGES TO PLAYERSTATS.CSV BEFORE PROGRAM IS CLOSED
    /* private void saveStats(){

    }//end method */



    private void playerDeath() {

        textSize(128);
        textAlign(CENTER);
        background(0);
        stroke(255);
        fill(255);
        text("YOU DIED!!!", width/2, height/3);
        text("PRESS SPACE TO RESTART", width/2, height/3 * 2);

    }//end method



    public void keyPressed(){

        switch(state){

            case 0:
                if(key == ' ') state = 1;
            break;

            case 2:
                if(key == ' ') playAgain();
            break;

            default:

            break;

        }//end switch
    }//end method



    private void startGame() {

        textSize(128);
        textAlign(CENTER);
        background(0);
        stroke(255);
        fill(255);
        text("PRESS SPACE TO START", width/2, height/2);

    }//end method



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
                player.points += 5;

            }//end else
        }//end while
    }//end method



    //POPULATES ARRAYLIST OF ENEMIES
    public void generateEnemies(){

        if(round % 5 == 0){

            for(int j = 0; j < 1 * round / 5; j++){

                enemies.add(new Enemy(width, height, random(30, 50) + (round * 2.25f), random(7.5f, 15) + (round * 1.25f), 700, player, this));

            }//end loop
        }//end if

        for(int i = 0; i < 10 + round / 2; i++){

            enemies.add(new Enemy(width, height, random(10, 15) + (round * 2.25f), random(5, 10) + (round * 1.25f), 700, player, this));

        }//end loop
    }//end method
}//end
