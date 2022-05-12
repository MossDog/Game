package OBJECTS;

import processing.core.PApplet;
import processing.core.PVector;

public class Player extends PApplet{

    //declare variables.
    public PVector position;
    public int diameter;

    //cosntructor
    public Player(int width, int height){
        this.position = new PVector(width/2, height/2);
        this.diameter = (int)(height * 0.2f);
    }

    public void drawPlayer(){
        
        ellipse(position.x, position.y, diameter, diameter);
    }

}