package OBJECTS;

import processing.core.PShape;

public class SpeedBoss extends Enemy{
    public SpeedBoss(int width, int height, Player player, VisualSetup applet){

        super(width, height, player, applet);
        this.health = v.random(100, 150) + (v.round * 2.25f);
        this.damage = v.random(50, 100) + (v.round * 1.25f);
        this.speed = 5 + (v.round * 0.1f);
        this.size = 15;
        this.shape = generateShape();
    
    }

    @Override
    public PShape generateShape(){

        PShape shape_;

        shape_ = v.createShape(processing.core.PApplet.RECT, -size, -size, size*2, size*2);
        shape_.setFill(v.color(0));
        shape_.setStrokeWeight(3);
        shape_.setStroke(v.color(255));
        return shape_;
    }//end method
}
