package GAME;

import OBJECTS.Player;
import processing.core.PApplet;

public class VisualSetup extends PApplet
{

    //declare variables
    Player player;


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
        colorMode(RGB, 255, 255, 255);
        background(0);
        //declare visualizations
        player = new Player(width, height);
    }//end setup


    //draw
    public void draw(){
        player.drawPlayer();
    }//end draw

}//end