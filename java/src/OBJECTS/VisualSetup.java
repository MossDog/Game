/* THIS CLASS IS USED TO GIVE GAME OBJECTS ACCESS TO P.C.PAPPLET */

package OBJECTS;

import processing.core.PApplet;

public class VisualSetup extends PApplet
{

    //declare variables
    private Player player;
    private boolean roundWin;

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
    }//end setup


    //draw
    public void draw(){
        player.drawPlayer();

        //is round over? if so start new round
        if(roundWin == true){
            nextRound();
            roundWin = false;
        }

    }//end draw

    private void nextRound(){
        
    }

}//end