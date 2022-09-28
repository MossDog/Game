package OBJECTS;

import processing.core.PShape;
import processing.core.PVector;

public class Enemy extends VisualSetup{

    public PVector position, center, velocity, unitVector;
    public PShape shape;
    public long prevTimeDamage, prevTimeMove;
    public String rank;
    public float size, distance, health, damage, damageCooldown, speed;
    private VisualSetup v;
    private Player player;
    private boolean collision;
    


    public Enemy(int width, int height, String rank_, Player player, VisualSetup applet){

        this.v = applet;
        this.player = player;
        this.position = generatePosition(width, height);
        this.size = random(25, 50);

        switch(rank_){

            case "base":
                this.health = random(10, 15) + (v.round * 2.25f);
                this.damage = random(5, 10) + (v.round * 1.25f);
                this.speed = 3 + (v.round * 0.01f);
                this.size = 25;
                this.shape = generateShape(rank_);
            break;

            case "hBoss":
                this.health = random(30, 50) + (v.round * 2.25f);
                this.damage = random(5, 10) + (v.round * 1.25f);
                this.speed = 3 + (v.round * 0.01f);
                this.size = 35;
                this.shape = generateShape(rank_);
            break;

            case "dBoss":
                this.health = random(10, 15) + (round * 2.25f);
                this.damage = random(7.5f, 15) + (round * 1.25f);
                this.speed = 3 + (round * 0.01f);
                this.size = 25;
                this.shape = generateShape(rank_);
            break;

            case "sBoss":
                this.health = random(10, 15) + (round * 2.25f);
                this.damage = random(5, 10) + (round * 1.25f);
                this.speed = 5 + (round * 0.01f);
                this.size = 15;
                this.shape = generateShape(rank_);
            break;

            case "agent":
                this.health = random(30, 50) + (round * 2.25f);
                this.damage = random(7.5f, 15) + (round * 1.25f);
                this.speed = 5 + (round * 0.01f);
                this.size = 35;
                this.shape = generateShape(rank_);
            break;

            default:
            break;

        }//end switch
        this.center = new PVector(width/2, height/2);
        this.unitVector = PVector.sub(center, position).normalize();
        this.velocity = unitVector.mult(speed);
        this.prevTimeDamage = System.currentTimeMillis();
        this.damageCooldown = 1000;

    }//end constructor



    private PShape generateShape(String rank){

        PShape shape_;

        switch(rank){

            case "base":
                shape_ = v.createShape(RECT, -size, -size, size*2, size*2);
                shape_.setFill(color(0));
                shape_.setStrokeWeight(3);
                shape_.setStroke(color(255));
            return shape_;

            case "hBoss":
                shape_ = v.createShape(RECT, -size, -size, size*2, size*2);
                shape_.setFill(color(0));
                shape_.setStrokeWeight(3);
                shape_.setStroke(color(0, 255, 0));
            return shape_;

            case "dBoss":
                shape_ = v.createShape(RECT, -size, -size, size*2, size*2);
                shape_.setFill(color(0));
                shape_.setStrokeWeight(3);
                shape_.setStroke(color(255));
            return shape_;

            case "sBoss":
                shape_ = v.createShape(RECT, -size, -size, size*2, size*2);
                shape_.setFill(color(0));
                shape_.setStrokeWeight(3);
                shape_.setStroke(color(255));
            return shape_;

            case "agent":
                PShape body, hole;
                float holeSize = size / 3;

                //MAKE GROUP OF SHAPES
                shape_ = v.createShape(GROUP);

                //MAKE BODY OF AGENT
                body = v.createShape(RECT, -size, -size, size*2, size*2);
                body.setFill(color(255));
                body.setStrokeWeight(2);
                body.setStroke(color(255, 0, 0));

                //MAKE HOLE IN AGENT
                hole = v.createShape(RECT, -holeSize, -holeSize, holeSize*2, holeSize*2);
                hole.setFill(color(0));
                hole.setStrokeWeight(1);
                hole.setStroke(color(255, 0, 0));

                //ADD SHAPES TO GROUP
                shape_.addChild(body);
                shape_.addChild(hole);
            return shape_;//return group of shapes

            default:
            System.out.println("shape not created");
            return null;

        }//end switch
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
        v.textAlign(CENTER);
        v.noStroke();
        v.fill(255);
        v.text((int)health, 0, 0);

        v.popMatrix();//pop and restore previous co-ordinate system from stack


    }//end method



    private void move(){

        long timePassed = System.currentTimeMillis() - prevTimeMove;

        if(timePassed > 10){

            position.add(velocity);
            
            prevTimeMove = System.currentTimeMillis();

        }//end if
    }//end method



    private void dealDmg(){

        long timePassed = System.currentTimeMillis() - prevTimeDamage;

        if(timePassed > damageCooldown){

            //deal damage if play
            player.health -= damage;
            
            prevTimeDamage = System.currentTimeMillis();

        }//end if
    }//end method



    public PVector generatePosition(int width, int height){

        int side = (int)random(4);
        PVector position;

        switch(side){
            //Verticle
            case 0:
                position = new PVector(random(-width, 0), random(-width, height+width));
                return position;
            case 1:
                position = new PVector(random(width, width*2), random(-width, height+width));
                return position;
            //Horizontal
            case 2:
                position = new PVector(random(0, width), random(-width, 0));
                return position;
            case 3:
                position = new PVector(random(0, width), random(height, height+width));
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
        distance = sqrt((distX*distX) + (distY*distY));
        
        // if the distance is less than the radius, collision!
        return (distance <= player.diameter/2) ? true : false;

    }//end method
}//end class
