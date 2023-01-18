package OBJECTS;

import processing.core.PShape;
import processing.core.PVector;

public class Enemy{

    public PVector position, center, velocity, unitVector;
    public PShape shape;
    public long prevTimeDamage, prevTimeMove;
    public float size, distance, health, damage, damageCooldown, speed;
    public VisualSetup v;
    public Player player;
    public boolean collision;
    


    public Enemy(int width, int height, Player player, VisualSetup applet){

        this.v = applet;
        this.player = player;
        this.position = generatePosition(width, height);
        this.size = v.random(25, 50);

        this.health = v.random(100, 150) + (float) Math.pow(v.round, 1.7f);
        this.damage = v.random(50, 100) + (float) Math.pow(v.round, 1.7f);
        this.speed = 3 + (v.round * 0.01f);
        this.size = 25;
        this.shape = generateShape();
        
        this.center = new PVector(width/2, height/2);
        this.unitVector = PVector.sub(center, position).normalize();
        this.velocity = unitVector.mult(speed);
        this.prevTimeDamage = System.currentTimeMillis();
        this.damageCooldown = 1000;

    }//end constructor



    public PShape generateShape(){

        PShape shape_;

        shape_ = v.createShape(processing.core.PApplet.RECT, -size, -size, size*2, size*2);
        shape_.setFill(v.color(0));
        shape_.setStrokeWeight(3);
        shape_.setStroke(v.color(255));
        return shape_;

    }//end method



    public void updateEnemy(){

        //CALCULATE COLLISION / MOVE
        collision = circleRect();
        if(collision) dealDmg();
        else move();

        
        v.pushMatrix();//push current co-ordinate system to stack

        //DRAW ENEMY TO SCREEN
        v.translate(position.x, position.y);//(0, 0) now corresponds to (position.x, position.y)
        v.shape(shape);//draw shape to screen at Enemy position

        //DRAW ENEMY HEALTH INSIDE ENEMY
        v.textSize(14);
        v.textAlign(processing.core.PApplet.CENTER);
        v.noStroke();
        v.fill(255);
        v.text((int)health, 0, 0);

        v.popMatrix();//pop and restore previous co-ordinate system from stack


    }//end method



    public void move(){

        long timePassed = System.currentTimeMillis() - prevTimeMove;

        if(timePassed > 10){

            position.add(velocity);
            
            prevTimeMove = System.currentTimeMillis();

        }//end if
    }//end method



    public void dealDmg(){

        long timePassed = System.currentTimeMillis() - prevTimeDamage;

        if(timePassed > damageCooldown){

            //deal damage if play
            player.health -= damage;
            
            prevTimeDamage = System.currentTimeMillis();

        }//end if
    }//end method



    public PVector generatePosition(int width, int height){

        int side = (int) v.random(4);
        PVector position;

        switch(side){
            //Verticle
            case 0:
                position = new PVector(v.random(-width, 0), v.random(-width, height+width));
                return position;
            case 1:
                position = new PVector(v.random(width, width*2), v.random(-width, height+width));
                return position;
            //Horizontal
            case 2:
                position = new PVector(v.random(0, width), v.random(-width, 0));
                return position;
            case 3:
                position = new PVector(v.random(0, width), v.random(height, height+width));
                return position;
            //Default
            default:
            System.out.println("default case");
            return null;

        }//end switch
    }//end method


    
    // Enemy-Player Collision detection
    boolean circleRect(){

        // temporary variables to set edges for testing
        float testX = center.x;
        float testY = center.y;
        
        // which edge is closest?
        // left edge
        if (center.x < position.x){
            testX = position.x;
        }else
        // right edge
        if (center.x > position.x +  size){
            testX = position.x +  size;
        }
    
        // which edge is closest?
        // top edge
        if (center.y < position.y){
            testY = position.y;
        }   
        else
        // bottom edge
        if (center.y > position.y +  size){
            testY = position.y +  size;
        } 
        
        // get distance from closest edges
        float distX = center.x-testX;
        float distY = center.y-testY;
        distance = processing.core.PApplet.sqrt((distX*distX) + (distY*distY));
        // if the distance is less than the radius, collision!
        return (distance <= player.diameter/2) ? true : false;

    }//end method
}//end class
