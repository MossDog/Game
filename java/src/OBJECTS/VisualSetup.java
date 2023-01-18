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
    public Player player;
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public int round, money, state;
    public Table statsTable;



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

                //is round over? if so start new round
                if(enemies.size() == 0) nextRound();
                    
                updateEnemies();

                if(player.health > 0) player.updatePlayer(enemies);
                else state = 2;

                //displayHUD();
                debugHUD();
                upgradeHUD();

            break;


            case 2: playerDeath(); break;


            case 3:

            case 4:
            case 5:

            default:
                System.out.println("This should not occur, switch error");
            break;

        }//end switch
    }//end draw



    /* private void displayHUD() {
    } */



    private void upgradeHUD() {

        int xOffset = (int)((width/3) * 0.5f);
        int yOffset = statsTable.getColumnCount()%2 == 0 ? height/(statsTable.getColumnCount()/2) : height/((statsTable.getColumnCount()/2)+1); //if list of upgrades is odd then ensure there is enough space for the last item.
        strokeWeight(2);
        stroke(255);
        fill(0);
        rect(width * 2/3, 0, (width/3)-1, height-1, 10);

        textSize(20);
        textAlign(CENTER);
        stroke(255);
        fill(255);

        pushMatrix();
        translate(width * 2/3, 0);

        TableRow statsRow = statsTable.getRow(0);
        TableRow priceRow = statsTable.getRow(1);
        TableRow multRow = statsTable.getRow(2);

        for(int i = 0; i < statsTable.getColumnCount(); i++){
            String column = statsTable.getColumnTitle(i);
            text("PRICE: " + priceRow.getInt(column) + "\nUPGRADE "+ column.toUpperCase() +"\n"+statsRow.getInt(column)+" +"+multRow.getString(column), xOffset/2, yOffset/2);
            if(i%2 == 0) translate(xOffset, 0);
            else translate(-xOffset, yOffset);
        }

        popMatrix();
    }//end method



    private void debugHUD() {

        textSize(28);
        textAlign(LEFT);
        stroke(255);
        fill(255);
        text("Player Health: " + player.health, 0, 28);
        text("Player Max Health: " + player.maxHealth, 0, 28 * 2);
        text("Player Health Regen: " + player.healthRegen, 0, 28 * 3);
        text("Player Regen Cooldown: " + player.regenCooldown, 0, 28 * 4);
        text("Player Damage: " + player.damage, 0, 28 * 5);
        text("Player Damage Cooldown: " + player.damageCooldown, 0, 28 * 6);
        text("Player Points: " + player.points, 0, 28 * 7);

        text("Money: " + money, 0, 28 * 9);
        text("State: " + state, 0, 28 * 10);
        text("Round: " + round, 0, 28 * 11);

        text("enemies.size(): " + enemies.size(), 0, 28 * 13);
        text("Example Enemy Health: " + random(10, 15) + (round * 2.25f), 0, 28 * 14);
        text("Example Enemy Damage: " + random(5, 10) + (round * 1.25f), 0, 28 * 15);
        text("Example Boss Health: " + random(30, 50) + (round * 2.25f), 0, 28 * 16);
        text("Example Boss Damage: " + random(7.5f, 15) + (round * 1.25f), 0, 28 * 17);
        text("Enemy Speed: " + 700, 0, 28 * 18);

    }



    public void playAgain() {

        enemies.clear();
        player.health = player.maxHealth;
        round = 0;
        state = 1;

    }//end method



    private void loadStats() {

        statsTable = loadTable("playerstats.csv", "header");

        //gets row of stats in stats.csv, row 0 holds player stats.
        TableRow row = statsTable.getRow(0);
        float maxHealth = row.getInt("health");
        float regen = row.getInt("regen");
        float regenCooldown = row.getInt("regencooldown");
        float damage = row.getInt("damage");
        float dmgCooldown = row.getInt("dmgcooldown");
        float range = row.getInt("range");
        player = new Player(width, height, maxHealth, regen, regenCooldown, damage, dmgCooldown, range, this);
        //System.out.println(" "+ width + " "+height+ " "+maxHealth+ " "+regen+ " "  +regenCooldown+ " "+damage+" "+dmgCooldown+ " "+range);
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



    //POPULATES ARRAYLIST OF ENEMIES EACH ROUND
    public void generateEnemies(){

        int bossRoundInterval = 3;
        int agentRoundInterval = 10;
        ArrayList<Enemy> tempEnemies = new ArrayList<Enemy>();

        //REGULAR ENEMIES
        for(int i = 0; i < 10 + round / 2; i++){

            tempEnemies.add(new Enemy(width, height, player, this));

        }//end loop

        //BOSS ENEMIES
        if(round % bossRoundInterval == 0){
            for(int j = 0; j < 1 * round / bossRoundInterval; j++){

                int random = (int)Math.random() % 3;

                switch(random){
                    
                    case 0:
                    tempEnemies.add(new HealthBoss(width, height, player, this));
                    break;

                    case 1:
                    tempEnemies.add(new DamageBoss(width, height, player, this));
                    break;

                    case 2:
                    tempEnemies.add(new SpeedBoss(width, height, player, this));
                    break;

                    default:break;

                }//end switch
            }//end loop
        }//end if

        //AGENTS
        if(round % agentRoundInterval == 0){
            for(int j = 0; j < 1 * round / agentRoundInterval; j++){

                tempEnemies.add(new Agent(width, height, player, this));

            }//end loop
        }//end if

        SpawnThread sThread = new SpawnThread(tempEnemies, enemies, 800);
        sThread.start();
        System.out.println("ijfoije");
        while(enemies.size()==0){System.out.println("thread status: " + sThread.getState());}
    }//end method
}//end
