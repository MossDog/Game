package OBJECTS;

import processing.core.PShape;

public class HealthBoss extends Enemy{

    public HealthBoss(int width, int height, Player player, VisualSetup applet){

        super(width, height, player, applet);
        this.health = v.random(300, 500) + (float) Math.pow(v.round, 1.8f);
        this.damage = v.random(50, 100) + (float) Math.pow(v.round, 1.7f);
        this.speed = 3 + (v.round * 0.01f);
        this.size = 35;
        this.shape = generateShape();
    
    }

    @Override
    public PShape generateShape(){

        PShape shape_;

        shape_ = v.createShape(processing.core.PApplet.RECT, -size, -size, size*2, size*2);
        shape_.setFill(v.color(0));
        shape_.setStrokeWeight(3);
        shape_.setStroke(v.color(0, 255, 0));
        return shape_;
    }//end method
}
