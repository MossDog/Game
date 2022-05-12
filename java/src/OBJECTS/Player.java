package OBJECTS;

import java.sql.Time;

import processing.core.PVector;

public class Player extends VisualSetup{

    //declare variables.
    public PVector position;
    public int diameter, color;
    VisualSetup p;

    //cosntructor
    public Player(int width, int height, VisualSetup p){
        this.position = new PVector(width/2, height/2);
        this.diameter = (int)(height * 0.2f);
        this.p = p;
    }

    public void drawPlayer(){
        p.fill(255);
        p.stroke(color%360, 100, 100);
        p.ellipse(position.x, position.y, diameter, diameter);
        color++;
    }

}