package OBJECTS;

import processing.core.PShape;

public class Agent extends Enemy{
    
    public Agent(int width, int height, Player player, VisualSetup applet){

        super(width, height, player, applet);
        this.health = v.random(300, 500) + (v.round * 2.25f);
        this.damage = v.random(75, 150) + (v.round * 1.25f);
        this.speed = 5 + (v.round * 0.01f);
        this.size = 35;
        this.shape = generateShape();
    
    }

    @Override
    public PShape generateShape(){

        PShape shape_;

        PShape body, hole;
        float holeSize = size / 3;

        //MAKE GROUP OF SHAPES
        shape_ = v.createShape(processing.core.PApplet.GROUP);

        //MAKE BODY OF AGENT
        body = v.createShape(processing.core.PApplet.RECT, -size, -size, size*2, size*2);
        body.setFill(v.color(255));
        body.setStrokeWeight(2);
        body.setStroke(v.color(255, 0, 0));

        //MAKE HOLE IN AGENT
        hole = v.createShape(processing.core.PApplet.RECT, -holeSize, -holeSize, holeSize*2, holeSize*2);
        hole.setFill(v.color(0));
        hole.setStrokeWeight(1);
        hole.setStroke(v.color(255, 0, 0));

        //ADD SHAPES TO GROUP
        shape_.addChild(body);
        shape_.addChild(hole);
        return shape_;
    }//end method

}
