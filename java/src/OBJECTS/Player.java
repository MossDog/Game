package OBJECTS;

import processing.core.PVector;

public class Player extends VisualSetup{

    //declare variables.
    public PVector position;
    public int diameter;
    private VisualSetup p;

    //cosntructor
    public Player(int width, int height, VisualSetup p){
        this.position = new PVector(width/2, height/2);
        this.diameter = (int)(height * 0.2f);
        this.p = p;
    }

    public void updatePlayer(){
        p.fill(255);
        p.stroke(abs(p.frameCount%360), 100, 100);
        p.ellipse(position.x, position.y, diameter, diameter);
    }

}