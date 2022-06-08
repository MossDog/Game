package OBJECTS;

import processing.core.PVector;

public class Enemy extends VisualSetup{

    public PVector position, center, velocity;
    public long prevTime, timePassed;
    public float size, distance, dmg, dmgCooldown, health;
    private VisualSetup v;
    private Player player;
    private boolean collision;
    


    public Enemy(int width, int height, float health, float damage, float speed, Player player, VisualSetup applet){

        this.center = new PVector(width/2, height/2);
        this.position = generatePosition(width, height);
        this.size = random(25, 50);
        this.velocity = PVector.sub(center, position).div(speed);
        this.prevTime = System.currentTimeMillis();
        this.health = health;
        this.dmg = damage;
        this.dmgCooldown = 1000;
        this.v = applet;
        this.player = player;

    }



    public void updateEnemy(){

        //CALCULATE COLLISION
        collision = circleRect();

        if(collision) dealDmg();
        else position.add(velocity);

        //DRAW ENEMY TO SCREEN
        v.fill(0, 0, 0);
        v.strokeWeight(3);
        v.stroke(0, 100, 100);
        v.rect(position.x, position.y, size, size);

    }//end method



    private void dealDmg(){

        timePassed = System.currentTimeMillis() - prevTime;

        if(timePassed > dmgCooldown){

            if(player.health > 0) player.health -= dmg;
            
            prevTime = System.currentTimeMillis();

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
